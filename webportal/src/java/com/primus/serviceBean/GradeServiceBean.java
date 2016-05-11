/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.serviceBean;

import com.primus.data.Grade;
import com.primus.serviceBean.exceptions.NonexistentEntityException;
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
@Transactional
@Component
public class GradeServiceBean extends PrimusBasePersistenceService{

  

    public void create(Grade grade) throws Exception {
        EntityManager em =super.getEntityManager();
            em.persist(grade);
    }

    public void edit(Grade grade) throws Exception {
        EntityManager em = super.getEntityManager();
            grade = em.merge(grade);
           Long id = grade.getId();
                if (findGrade(id) == null) {
                    throw new NonexistentEntityException("The grade with id " + id + " no longer exists.");
                }
           
    }

    public void destroy(Long id) throws Exception {
        EntityManager em = super.getEntityManager();
            Grade grade;
            try {
                grade = em.getReference(Grade.class, id);
                grade.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The grade with id " + id + " no longer exists.", enfe);
            }
            em.remove(grade);
    }

    public List<Grade> findGradeEntities() {
        return findGradeEntities(true, -1, -1);
    }

    public List<Grade> findGradeEntities(int maxResults, int firstResult) {
        return findGradeEntities(false, maxResults, firstResult);
    }

    private List<Grade> findGradeEntities(boolean all, int maxResults, int firstResult) {
         Query q = super.getEntityManager().createQuery("select object(o) from Grade as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
   }

    public Grade findGrade(Long id) throws Exception {
      return (Grade)super.findByPrimaryKey(id,Grade.class);
       }

    public int getGradeCount() {
        Query q = super.getEntityManager().createQuery("select count(o) from Grade as o");
            return ((Long) q.getSingleResult()).intValue();
        }

    @Override
    protected Query getSearchCriteria(Serializable paramSerializable, String paramString, boolean paramBoolean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
