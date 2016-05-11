/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.serviceBean;

import com.primus.data.SchoolFeePaymentBreakdown;
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
public class SchoolFeePaymentBreakdownService implements Serializable {

    public SchoolFeePaymentBreakdownService(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SchoolFeePaymentBreakdown schoolFeePaymentBreakdown) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(schoolFeePaymentBreakdown);
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

    public void edit(SchoolFeePaymentBreakdown schoolFeePaymentBreakdown) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            schoolFeePaymentBreakdown = em.merge(schoolFeePaymentBreakdown);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = schoolFeePaymentBreakdown.getId();
                if (findSchoolFeePaymentBreakdown(id) == null) {
                    throw new NonexistentEntityException("The schoolFeePaymentBreakdown with id " + id + " no longer exists.");
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
            SchoolFeePaymentBreakdown schoolFeePaymentBreakdown;
            try {
                schoolFeePaymentBreakdown = em.getReference(SchoolFeePaymentBreakdown.class, id);
                schoolFeePaymentBreakdown.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The schoolFeePaymentBreakdown with id " + id + " no longer exists.", enfe);
            }
            em.remove(schoolFeePaymentBreakdown);
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

    public List<SchoolFeePaymentBreakdown> findSchoolFeePaymentBreakdownEntities() {
        return findSchoolFeePaymentBreakdownEntities(true, -1, -1);
    }

    public List<SchoolFeePaymentBreakdown> findSchoolFeePaymentBreakdownEntities(int maxResults, int firstResult) {
        return findSchoolFeePaymentBreakdownEntities(false, maxResults, firstResult);
    }

    private List<SchoolFeePaymentBreakdown> findSchoolFeePaymentBreakdownEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from SchoolFeePaymentBreakdown as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public SchoolFeePaymentBreakdown findSchoolFeePaymentBreakdown(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SchoolFeePaymentBreakdown.class, id);
        } finally {
            em.close();
        }
    }

    public int getSchoolFeePaymentBreakdownCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from SchoolFeePaymentBreakdown as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
