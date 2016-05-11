/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.serviceBean;

import com.primus.data.LecturerResultGrade;
import com.primus.serviceBean.exceptions.NonexistentEntityException;
import com.primus.serviceBean.exceptions.RollbackFailureException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Olisa
 */
@Component
@Transactional
public class LecturerResultGradeServiceBean extends PrimusBasePersistenceService{


    public void create(LecturerResultGrade lecturerResultGrade) throws RollbackFailureException, Exception {
        EntityManager em =  super.getEntityManager();;
            em.persist(lecturerResultGrade);
    }

    public void edit(LecturerResultGrade lecturerResultGrade) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = getEntityManager();
            lecturerResultGrade = em.merge(lecturerResultGrade);
    }

    public void destroy(Long id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = super.getEntityManager();
            LecturerResultGrade lecturerResultGrade;
            try {
                lecturerResultGrade = em.getReference(LecturerResultGrade.class, id);
                lecturerResultGrade.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The lecturerResultGrade with id " + id + " no longer exists.", enfe);
            }
            em.remove(lecturerResultGrade);
     }

    public List<LecturerResultGrade> findLecturerResultGradeEntities() {
        return findLecturerResultGradeEntities(true, -1, -1);
    }

    public List<LecturerResultGrade> findLecturerResultGradeEntities(int maxResults, int firstResult) {
        return findLecturerResultGradeEntities(false, maxResults, firstResult);
    }

    private List<LecturerResultGrade> findLecturerResultGradeEntities(boolean all, int maxResults, int firstResult) {
           Query q = super.getEntityManager().createQuery("select object(o) from LecturerResultGrade as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        }

    public LecturerResultGrade findLecturerResultGrade(Long id) {
        EntityManager em = super.getEntityManager();
            return em.find(LecturerResultGrade.class, id);
    }

    public int getLecturerResultGradeCount() {
        EntityManager em =  super.getEntityManager();;
           Query q = em.createQuery("select count(o) from LecturerResultGrade as o");
            return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    protected Query getSearchCriteria(Serializable paramSerializable, String paramString, boolean paramBoolean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
