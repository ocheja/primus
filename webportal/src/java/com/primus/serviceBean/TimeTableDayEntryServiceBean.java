/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.serviceBean;

import com.primus.data.AcademicSession;
import com.primus.data.DepartmentName;
import com.primus.data.TimeTableDailyEntries;
import com.primus.enums.LectureHall;
import com.primus.serviceBean.exceptions.NonexistentEntityException;
import com.primus.serviceBean.exceptions.RollbackFailureException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ocheja Patrick Ileanwa
 */
@Transactional
@Component("timeTableDayEntryServiceBean")
public class TimeTableDayEntryServiceBean extends PrimusBasePersistenceService {

     public EntityManager getEntityManager() {
        return super.getEntityManager();
    }

    public Object create(Object entity) throws RollbackFailureException, Exception {
        EntityManager em = getEntityManager();
            entity = em.merge(entity);
        return entity;
    }

    public Object edit(Object entity) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = getEntityManager();
            entity = em.merge(entity);
        return entity;
    }

    public void destroy(Long id, String className) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = getEntityManager();
            Object timeTableDayEntry;
            try {
                timeTableDayEntry = em.getReference(Class.forName(className), id);
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The timeTableDayEntry with id " + id + " no longer exists.", enfe);
            }
            em.remove(timeTableDayEntry);
    }

    public List<Object> findTimeTableDayEntryEntities() {
        return findTimeTableDayEntryEntities(true, -1, -1);
    }

    public List<Object> findTimeTableDayEntryEntities(int maxResults, int firstResult) {
        return findTimeTableDayEntryEntities(false, maxResults, firstResult);
    }

    private List<Object> findTimeTableDayEntryEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
            Query q = em.createQuery("select object(o) from TimeTableDayEntry as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
    }
    
    public List<TimeTableDailyEntries> findTimeTableDayEntryEntities(DepartmentName departmentName, AcademicSession academicSession, String owner) throws Exception {
        Query q = super.getEntityManager().createQuery("select t from TimeTable tt join tt."+owner.toLowerCase()+" ttdes join ttdes.timeTableDailyEntries t WHERE tt.academicSession=:sess  AND tt.department.departmentName=:deptN");
        q.setParameter("deptN", departmentName);
        q.setParameter("sess", academicSession);
        return (List<TimeTableDailyEntries>) super.findAll(q);
    }
    
    public List<TimeTableDailyEntries> findTimeTableDayEntryEntities(LectureHall lectureHall, AcademicSession academicSession, String owner) throws Exception {
        Query q = super.getEntityManager().createQuery("select t from TimeTable tt join tt."+owner.toLowerCase()+" ttdes join ttdes.timeTableDailyEntries t WHERE tt.academicSession=:sess  AND t.lectureHall=:lecHall");
        q.setParameter("lecHall", lectureHall);
        q.setParameter("sess", academicSession);
        return (List<TimeTableDailyEntries>) super.findAll(q);
    }

    public Object findTimeTableDayEntry(Long id, String className) throws ClassNotFoundException {
        EntityManager em = getEntityManager();
            return em.find(Class.forName(className), id);
    }

    public int getTimeTableDayEntryCount() {
        EntityManager em = getEntityManager();
            Query q = em.createQuery("select count(o) from TimeTableDayEntry as o");
            return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    protected Query getSearchCriteria(Serializable paramSerializable, String paramString, boolean paramBoolean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int twentyFourHourTimeToMilliseconds(int time) {
    int hours = time / 100;
    int minutes = time % 100;
    return ((hours * 60) + minutes) * 60000;
}
    public String currentDay(int index){
        switch(index){
            case Calendar.SUNDAY:
                return "Sunday";
            case Calendar.MONDAY:
                return "Monday";
            case Calendar.TUESDAY:
                return "Tuesday";
            case Calendar.WEDNESDAY:
                return "Wednesday";
            case Calendar.THURSDAY:
                return "Thursday";
            case Calendar.FRIDAY:
                return "Friday";
            case Calendar.SATURDAY:
                return "Saturday";
            default:
                return "";
        }
    }
    
}
