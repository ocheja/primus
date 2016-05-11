/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.serviceBean;

import com.primus.data.HostelUtilities;
import com.primus.service.exceptions.NonexistentEntityException;
import com.primus.service.exceptions.RollbackFailureException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.transaction.UserTransaction;

/**
 *
 * @author Olisa
 */
public class HostelUtilitiesService implements Serializable {

    public HostelUtilitiesService(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HostelUtilities hostelUtilities) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(hostelUtilities);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HostelUtilities hostelUtilities) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            hostelUtilities = em.merge(hostelUtilities);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = hostelUtilities.getId();
                if (findHostelUtilities(id) == null) {
                    throw new NonexistentEntityException("The hostelUtilities with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            HostelUtilities hostelUtilities;
            try {
                hostelUtilities = em.getReference(HostelUtilities.class, id);
                hostelUtilities.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hostelUtilities with id " + id + " no longer exists.", enfe);
            }
            em.remove(hostelUtilities);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HostelUtilities> findHostelUtilitiesEntities() {
        return findHostelUtilitiesEntities(true, -1, -1);
    }

    public List<HostelUtilities> findHostelUtilitiesEntities(int maxResults, int firstResult) {
        return findHostelUtilitiesEntities(false, maxResults, firstResult);
    }

    private List<HostelUtilities> findHostelUtilitiesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from HostelUtilities as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public HostelUtilities findHostelUtilities(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HostelUtilities.class, id);
        } finally {
            em.close();
        }
    }

    public int getHostelUtilitiesCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from HostelUtilities as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
