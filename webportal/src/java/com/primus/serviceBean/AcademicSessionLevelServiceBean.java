/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.serviceBean;

import com.primus.appstates.exceptions.NonexistentEntityException;
import com.primus.appstates.exceptions.RollbackFailureException;
import com.primus.data.AcademicSessionLevel;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import com.primus.data.Student;
import com.primus.enums.AcademicLevel;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Olisa
 */
@Transactional
@Component
public class AcademicSessionLevelServiceBean extends PrimusBasePersistenceService{

   
    public void create(AcademicSessionLevel academicSessionLevel) throws RollbackFailureException, Exception {
        EntityManager em =super.getEntityManager();
            Student student = academicSessionLevel.getStudent();
            
            em.persist(academicSessionLevel);
            }

    public void edit(AcademicSessionLevel academicSessionLevel) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = super.getEntityManager();
            AcademicSessionLevel persistentAcademicSessionLevel = em.find(AcademicSessionLevel.class, academicSessionLevel.getId());
            Student studentOld = persistentAcademicSessionLevel.getStudent();
            Student studentNew = academicSessionLevel.getStudent();
            if (studentNew != null) {
                studentNew = em.getReference(studentNew.getClass(), studentNew.getId());
                academicSessionLevel.setStudent(studentNew);
            }
            academicSessionLevel = em.merge(academicSessionLevel);
            if (studentOld != null && !studentOld.equals(studentNew)) {
                studentOld.getAcademicSessionLevel().remove(academicSessionLevel);
                studentOld = em.merge(studentOld);
            }
            if (studentNew != null && !studentNew.equals(studentOld)) {
                studentNew.getAcademicSessionLevel().add(academicSessionLevel);
                studentNew = em.merge(studentNew);
            }
         
    }

    public void destroy(Long id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = super.getEntityManager();
            AcademicSessionLevel academicSessionLevel;
            try {
                academicSessionLevel = em.getReference(AcademicSessionLevel.class, id);
                academicSessionLevel.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The academicSessionLevel with id " + id + " no longer exists.", enfe);
            }
            Student student = academicSessionLevel.getStudent();
            if (student != null) {
                student.getAcademicSessionLevel().remove(academicSessionLevel);
                student = em.merge(student);
            }
            em.remove(academicSessionLevel);
    }

    public List<AcademicSessionLevel> findAcademicSessionLevelEntities() {
        return findAcademicSessionLevelEntities(true, -1, -1);
    }

    public List<AcademicSessionLevel> findAcademicSessionLevelEntities(int maxResults, int firstResult) {
        return findAcademicSessionLevelEntities(false, maxResults, firstResult);
    }

    private List<AcademicSessionLevel> findAcademicSessionLevelEntities(boolean all, int maxResults, int firstResult) {
       Query q = super.getEntityManager().createQuery("select object(o) from AcademicSessionLevel as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
   }

    public AcademicSessionLevel findAcademicSessionLevel(Long id) {
        EntityManager em = getEntityManager();
            return em.find(AcademicSessionLevel.class, id);
    }
     public AcademicSessionLevel findAcademicSessionLevel(Long studentId,Long academicSessionId) {
         Query q = super.getEntityManager().createQuery("select aca from AcademicSessionLevel aca where aca.student.id =:studId and aca.academicSession=:sess");
          q.setParameter("studId",studentId);
             q.setParameter("sess",academicSessionId);        
          return (AcademicSessionLevel)super.findEntity(q);
     }
      public AcademicSessionLevel findAcademicSessionLevel(Long studentId,AcademicLevel academicLevel) {
         Query q = super.getEntityManager().createQuery("select aca from AcademicSessionLevel aca where aca.student.id =:studId and aca.academicLevel =:sess");
          q.setParameter("studId",studentId);
             q.setParameter("sess",academicLevel);        
          return (AcademicSessionLevel)super.findEntity(q);
     }

    public int getAcademicSessionLevelCount() {
          Query q = super.getEntityManager().createQuery("select count(o) from AcademicSessionLevel as o");
            return ((Long) q.getSingleResult()).intValue();
   }

    @Override
    protected Query getSearchCriteria(Serializable paramSerializable, String paramString, boolean paramBoolean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
