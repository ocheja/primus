/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.serviceBean;

import com.primus.data.AcademicSession;
import com.primus.data.ResultGradingScheme;
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
public class ResultGradingSchemeServiceBean extends PrimusBasePersistenceService {

    public void create(ResultGradingScheme resultGradingScheme) throws Exception {
        List<String> strings;
        if( findResultGradingScheme(resultGradingScheme.getAcademicSession())!= null){
            throw new Exception("Result grading scheme has already been created for this academic Session "+resultGradingScheme.getAcademicSession().getStartYear()+"/" + resultGradingScheme.getAcademicSession().getEndYear());  
        }
        //check for grade letter consistency
        if(resultGradingScheme.getGradeLettersAndMinimumMarks().keySet().size() == resultGradingScheme.getGradeLettersAndGradePoints().size() && resultGradingScheme.getGradeLettersAndMinimumMarks().keySet().size() == resultGradingScheme.getGradeLetterDescriptions().size()  ){
        if (!resultGradingScheme.getGradeLettersAndMinimumMarks().keySet().containsAll(resultGradingScheme.getGradeLettersAndGradePoints().keySet()) ? true : !resultGradingScheme.getGradeLettersAndMinimumMarks().keySet().containsAll(resultGradingScheme.getGradeLetterDescriptions().keySet())) 
            throw new Exception("The Grade Letters must be consistent. Please check the grade Letters");
        }else{
         throw new Exception("The Grade Letters must be consistent. Please check the grade Letters");  
        }
        
        EntityManager em = super.getEntityManager();
        em.persist(resultGradingScheme);
    }
    
    public void clone(ResultGradingScheme oldResultGradingScheme,AcademicSession newAcademicSession) throws Exception {
        ResultGradingScheme resultGradingScheme = new ResultGradingScheme();
        resultGradingScheme.setAcademicSession(newAcademicSession);
        resultGradingScheme.setGradeLetterDescriptions(oldResultGradingScheme.getGradeLetterDescriptions());
        resultGradingScheme.setGradeLettersAndGradePoints(oldResultGradingScheme.getGradeLettersAndGradePoints());
       resultGradingScheme.setGradeLettersAndMinimumMarks(oldResultGradingScheme.getGradeLettersAndMinimumMarks());
        resultGradingScheme.setGradePointsDescriptions(oldResultGradingScheme.getGradePointsDescriptions());
        create(resultGradingScheme);
    }

    public void edit(ResultGradingScheme resultGradingScheme) throws Exception, Exception {
        EntityManager em = super.getEntityManager();
        resultGradingScheme = em.merge(resultGradingScheme);

        Long id = resultGradingScheme.getId();
        if (findResultGradingScheme(id) == null) {
            throw new NonexistentEntityException("The resultGradingScheme with id " + id + " no longer exists.");
        }

    }

    public void destroy(Long id) throws Exception {
        EntityManager em = super.getEntityManager();
        ResultGradingScheme resultGradingScheme;
        try {
            resultGradingScheme = em.getReference(ResultGradingScheme.class, id);
            resultGradingScheme.getId();
        } catch (EntityNotFoundException enfe) {
            throw new NonexistentEntityException("The resultGradingScheme with id " + id + " no longer exists.", enfe);
        }
        em.remove(resultGradingScheme);
    }

    public List<ResultGradingScheme> findResultGradingSchemeEntities() {
        return findResultGradingSchemeEntities(true, -1, -1);
    }

    public List<ResultGradingScheme> findResultGradingSchemeEntities(int maxResults, int firstResult) {
        return findResultGradingSchemeEntities(false, maxResults, firstResult);
    }

    private List<ResultGradingScheme> findResultGradingSchemeEntities(boolean all, int maxResults, int firstResult) {
        Query q = super.getEntityManager().createQuery("select object(o) from ResultGradingScheme as o");
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    public ResultGradingScheme findResultGradingScheme(Long id) {
        return super.getEntityManager().find(ResultGradingScheme.class, id);
    }

    public int getResultGradingSchemeCount() {
        Query q = super.getEntityManager().createQuery("select count(o) from ResultGradingScheme as o");
        return ((Long) q.getSingleResult()).intValue();
    }
 public ResultGradingScheme findResultGradingScheme(AcademicSession academicSession) {
        Query q = super.getEntityManager().createQuery("select r from ResultGradingScheme r where r.academicSession = :acaSession");
       q.setParameter("acaSession", academicSession);
        return (ResultGradingScheme) super.findEntity(q);
    }

    @Override
    protected Query getSearchCriteria(Serializable paramSerializable, String paramString, boolean paramBoolean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
