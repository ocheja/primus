/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.serviceBean;

import com.primus.data.HostelFeeReceipt;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.primus.data.Student;
import com.primus.service.exceptions.NonexistentEntityException;
import com.primus.service.exceptions.RollbackFailureException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Olisa
 */
public class HostelFeeReceiptService implements Serializable {

    public HostelFeeReceiptService(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HostelFeeReceipt hostelFeeReceipt) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Student student = hostelFeeReceipt.getStudent();
            if (student != null) {
                student = em.getReference(student.getClass(), student.getId());
                hostelFeeReceipt.setStudent(student);
            }
            em.persist(hostelFeeReceipt);
            if (student != null) {
                student.getHostelFeeReceipt().add(hostelFeeReceipt);
                student = em.merge(student);
            }
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

    public void edit(HostelFeeReceipt hostelFeeReceipt) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            HostelFeeReceipt persistentHostelFeeReceipt = em.find(HostelFeeReceipt.class, hostelFeeReceipt.getId());
            Student studentOld = persistentHostelFeeReceipt.getStudent();
            Student studentNew = hostelFeeReceipt.getStudent();
            if (studentNew != null) {
                studentNew = em.getReference(studentNew.getClass(), studentNew.getId());
                hostelFeeReceipt.setStudent(studentNew);
            }
            hostelFeeReceipt = em.merge(hostelFeeReceipt);
            if (studentOld != null && !studentOld.equals(studentNew)) {
                studentOld.getHostelFeeReceipt().remove(hostelFeeReceipt);
                studentOld = em.merge(studentOld);
            }
            if (studentNew != null && !studentNew.equals(studentOld)) {
                studentNew.getHostelFeeReceipt().add(hostelFeeReceipt);
                studentNew = em.merge(studentNew);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = hostelFeeReceipt.getId();
                if (findHostelFeeReceipt(id) == null) {
                    throw new NonexistentEntityException("The hostelFeeReceipt with id " + id + " no longer exists.");
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
            HostelFeeReceipt hostelFeeReceipt;
            try {
                hostelFeeReceipt = em.getReference(HostelFeeReceipt.class, id);
                hostelFeeReceipt.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hostelFeeReceipt with id " + id + " no longer exists.", enfe);
            }
            Student student = hostelFeeReceipt.getStudent();
            if (student != null) {
                student.getHostelFeeReceipt().remove(hostelFeeReceipt);
                student = em.merge(student);
            }
            em.remove(hostelFeeReceipt);
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

    public List<HostelFeeReceipt> findHostelFeeReceiptEntities() {
        return findHostelFeeReceiptEntities(true, -1, -1);
    }

    public List<HostelFeeReceipt> findHostelFeeReceiptEntities(int maxResults, int firstResult) {
        return findHostelFeeReceiptEntities(false, maxResults, firstResult);
    }

    private List<HostelFeeReceipt> findHostelFeeReceiptEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from HostelFeeReceipt as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public HostelFeeReceipt findHostelFeeReceipt(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HostelFeeReceipt.class, id);
        } finally {
            em.close();
        }
    }

    public int getHostelFeeReceiptCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from HostelFeeReceipt as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
