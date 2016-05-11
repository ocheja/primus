/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.serviceBean;

import com.primus.data.SchoolFeesReceipt;
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
public class SchoolFeesReceiptService implements Serializable {

    public SchoolFeesReceiptService(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SchoolFeesReceipt schoolFeesReceipt) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Student student = schoolFeesReceipt.getStudent();
            if (student != null) {
                student = em.getReference(student.getClass(), student.getId());
                schoolFeesReceipt.setStudent(student);
            }
            em.persist(schoolFeesReceipt);
            if (student != null) {
                student.getSchoolfeeReceipt().add(schoolFeesReceipt);
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

    public void edit(SchoolFeesReceipt schoolFeesReceipt) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            SchoolFeesReceipt persistentSchoolFeesReceipt = em.find(SchoolFeesReceipt.class, schoolFeesReceipt.getId());
            Student studentOld = persistentSchoolFeesReceipt.getStudent();
            Student studentNew = schoolFeesReceipt.getStudent();
            if (studentNew != null) {
                studentNew = em.getReference(studentNew.getClass(), studentNew.getId());
                schoolFeesReceipt.setStudent(studentNew);
            }
            schoolFeesReceipt = em.merge(schoolFeesReceipt);
            if (studentOld != null && !studentOld.equals(studentNew)) {
                studentOld.getSchoolfeeReceipt().remove(schoolFeesReceipt);
                studentOld = em.merge(studentOld);
            }
            if (studentNew != null && !studentNew.equals(studentOld)) {
                studentNew.getSchoolfeeReceipt().add(schoolFeesReceipt);
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
                Long id = schoolFeesReceipt.getId();
                if (findSchoolFeesReceipt(id) == null) {
                    throw new NonexistentEntityException("The schoolFeesReceipt with id " + id + " no longer exists.");
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
            SchoolFeesReceipt schoolFeesReceipt;
            try {
                schoolFeesReceipt = em.getReference(SchoolFeesReceipt.class, id);
                schoolFeesReceipt.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The schoolFeesReceipt with id " + id + " no longer exists.", enfe);
            }
            Student student = schoolFeesReceipt.getStudent();
            if (student != null) {
                student.getSchoolfeeReceipt().remove(schoolFeesReceipt);
                student = em.merge(student);
            }
            em.remove(schoolFeesReceipt);
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

    public List<SchoolFeesReceipt> findSchoolFeesReceiptEntities() {
        return findSchoolFeesReceiptEntities(true, -1, -1);
    }

    public List<SchoolFeesReceipt> findSchoolFeesReceiptEntities(int maxResults, int firstResult) {
        return findSchoolFeesReceiptEntities(false, maxResults, firstResult);
    }

    private List<SchoolFeesReceipt> findSchoolFeesReceiptEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from SchoolFeesReceipt as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public SchoolFeesReceipt findSchoolFeesReceipt(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SchoolFeesReceipt.class, id);
        } finally {
            em.close();
        }
    }

    public int getSchoolFeesReceiptCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from SchoolFeesReceipt as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
