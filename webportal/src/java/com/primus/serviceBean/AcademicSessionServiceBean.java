/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.serviceBean;

import com.primus.data.AcademicSession;
import com.primus.data.AcademicSessionLevel;
import com.primus.data.Student;
import com.primus.enums.AcademicLevel;
import com.primus.interfaces.StudentService;
import com.primus.service.exceptions.NonexistentEntityException;
import com.primus.service.exceptions.RollbackFailureException;
import com.primus.util.WildSearchStringUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Olisa
 */
@Transactional
@Component
public class AcademicSessionServiceBean extends PrimusBasePersistenceService {

    @Autowired
    StudentService studentServiceBean;
    @Autowired
    AcademicSessionLevelServiceBean academicSessionLevelServiceBean;

    public void create(AcademicSession academicSession) throws Exception {
        int startYr = academicSession.getStartYear();
        int endYr = academicSession.getEndYear();
        if (startYr > endYr) { // check if startYear is less than endYear
            throw new Exception("startYear must be less than endYear ");
        }
        if (findAcademicSession(String.valueOf(startYr), String.valueOf(endYr)) == null) { // check if AcademicSession has been created before
            for (AcademicSession oldAcademicS : findAcademicSessionEntities()) { // set old academic sessions to false
                oldAcademicS.setCurrentSession(false);
                oldAcademicS.setCourseRegistrationClosed(true);
                edit(oldAcademicS);
            }
            academicSession.setCurrentSession(true);
            EntityManager em = super.getEntityManager();
            em.persist(academicSession);
            incrementAllStudentsLevel(findAcademicSession(String.valueOf(startYr), String.valueOf(endYr)));
        } else {
            throw new Exception(" The Academic Session: " + startYr + "/" + endYr + " Already Exists");
        }
    }

    public void create(String startYear, String endYear) throws Exception {
        AcademicSession academicSession = new AcademicSession();
        int startYr;
        int endYr;
        try { // check if parameters are valid integer numbers
            startYr = Integer.parseInt(startYear);
            endYr = Integer.parseInt(endYear);
        } catch (Exception e) {
            throw new Exception("startYear and endYear must be numbers(Integers) eg: 2011");
        }
        if (startYr > endYr) { // check if startYear is less than endYear
            throw new Exception("startYear must be less than endYear ");
        }
        if (findAcademicSession(startYear, endYear) == null) { // check if AcademicSession has been created before

            for (AcademicSession oldAcademicS : findAcademicSessionEntities()) { // set old academic sessions to false
                oldAcademicS.setCurrentSession(false);
                edit(oldAcademicS);
            }
            academicSession.setCurrentSession(true);
            academicSession.setStartYear(startYr);
            academicSession.setEndYear(endYr);
            EntityManager em = super.getEntityManager();
            em.persist(academicSession);

            incrementAllStudentsLevel(findAcademicSession(startYear, endYear));

        } else {
            throw new Exception(" The Academic Session: " + startYr + "/" + endYr + " Already Exists");
        }
    }

    public void edit(AcademicSession academicSession) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = super.getEntityManager();
        academicSession = em.merge(academicSession);
    }

    public void destroy(Long id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = super.getEntityManager();
        AcademicSession academicSession = em.getReference(AcademicSession.class, id);
        academicSession.getId();
        em.remove(academicSession);

    }

    public List<AcademicSession> findAcademicSessionEntities() {
        return findAcademicSessionEntities(true, -1, -1);
    }

    public List<AcademicSession> findAcademicSessionEntities(int maxResults, int firstResult) {
        return findAcademicSessionEntities(false, maxResults, firstResult);
    }

    private List<AcademicSession> findAcademicSessionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = super.getEntityManager();
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(AcademicSession.class));
        Query q = em.createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    public AcademicSession findAcademicSession(Long id) throws Exception {
        return (AcademicSession) super.findByPrimaryKey(id, AcademicSession.class);
    }

    public int getAcademicSessionCount() {
        EntityManager em = getEntityManager();
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<AcademicSession> rt = cq.from(AcademicSession.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public AcademicSession findAcademicSession(String startYear, String endYear) throws Exception {
        Query query = super.getEntityManager().createQuery("select S from AcademicSession S where S.startYear = :stYear and S.endYear = :edYear");
        query.setParameter("stYear", WildSearchStringUtil.checkInteger(startYear));
        query.setParameter("edYear", WildSearchStringUtil.checkInteger(endYear));
        return (AcademicSession) super.findEntity(query);
    }

    public List<AcademicSession> wildSearch(String searchParam) throws Exception {
        if (searchParam.replaceAll("%", "").isEmpty()) {
            System.out.println("empty search param");
            Query query = super.getEntityManager().createQuery("select S from AcademicSession S");
            return (List<AcademicSession>) super.findAll(query);
        } else {
            Query query = super.getEntityManager().createQuery("select S from AcademicSession S where S.startYear  =:param or S.endYear =:param");
            query.setParameter("param", WildSearchStringUtil.checkInteger(searchParam));
            return (List<AcademicSession>) super.findAll(query);
        }
    }

    public AcademicSession getCurrentAcademicSession() throws Exception {
        Query query = super.getEntityManager().createQuery("select S from AcademicSession S where S.currentSession =:bl");
        query.setParameter("bl", true);
        return (AcademicSession) super.findEntity(query);
    }

    @Override
    protected Query getSearchCriteria(Serializable paramSerializable, String paramString, boolean paramBoolean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void incrementAllStudentsLevel(AcademicSession academicSession) {
        List<Student> students = studentServiceBean.findStudentEntities();
        for (Student student : students) {
            if (student.getNumberOfYearsSpent() <= student.getDegree().getMaxAllowableYears()) {
                AcademicLevel newAcaLevel = incrementAcademicLevel(student.getCurrentAcademicLevel());
                student.setCurrentAcademicLevel(newAcaLevel);
                student.setCurrentSession(academicSession);
                AcademicSessionLevel academicSessionLevel = new AcademicSessionLevel();
                academicSessionLevel.setAcademicLevel(newAcaLevel);
                academicSessionLevel.setAcademicSession(academicSession);
                academicSessionLevel.setStudent(student);
                try {
                    academicSessionLevelServiceBean.create(academicSessionLevel);
                } catch (com.primus.serviceBean.exceptions.NonexistentEntityException ex) {
                    Logger.getLogger(AcademicSessionServiceBean.class.getName()).log(Level.SEVERE, null, ex);
                } catch (com.primus.serviceBean.exceptions.RollbackFailureException ex) {
                    Logger.getLogger(AcademicSessionServiceBean.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(AcademicSessionServiceBean.class.getName()).log(Level.SEVERE, null, ex);
                }
                AcademicSessionLevel academicSessionLevelNew = academicSessionLevelServiceBean.findAcademicSessionLevel(student.getId(), newAcaLevel);
                student.getAcademicSessionLevel().add(academicSessionLevelNew);
                try {
                    studentServiceBean.register(student);
                } catch (Exception ex) {
                    Logger.getLogger(AcademicSessionServiceBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public AcademicLevel incrementAcademicLevel(AcademicLevel StudentLevel) {
        if (StudentLevel == null) {
            return AcademicLevel.l100;
        } else {
            int ord = StudentLevel.ordinal();
            return AcademicLevel.values()[ord + 1];
        }
    }

    public List<AcademicLevel> getAcademicLevels(AcademicLevel studentLevel) {
        List<AcademicLevel> academicLevels = new ArrayList<>();
        if (studentLevel == null) {
            academicLevels.add(AcademicLevel.l100);
            return academicLevels;
        } else {
            int ord = studentLevel.ordinal();
            for (int i = 0; i <= ord; i++) {
                academicLevels.add(AcademicLevel.values()[i]);
            }
            return academicLevels;
        }
    }
}
