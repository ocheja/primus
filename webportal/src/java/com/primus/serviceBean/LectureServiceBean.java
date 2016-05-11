/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.serviceBean;

import com.primus.data.Course;
import com.primus.data.Lecture;
import com.primus.data.Lecturer;
import com.primus.data.TimeTableDailyEntries;
import com.primus.enums.LectureHall;
import com.primus.interfaces.LecturerService;
import com.primus.serviceBean.exceptions.NonexistentEntityException;
import com.primus.serviceBean.exceptions.RollbackFailureException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ocheja Patrick Ileanwa
 */
@Transactional
@Component("lectureServiceBean")
public class LectureServiceBean extends PrimusBasePersistenceService {

    @Autowired
    TimeTableDayEntryServiceBean timeTableDayEntryServiceBean;
    @Autowired
    DepartmentServiceBean departmentServiceBean;
    @Autowired
    AcademicSessionServiceBean academicSessionServiceBean;
    @Autowired
    LecturerService lecturerServiceBean;

    public EntityManager getEntityManager() {
        return super.getEntityManager();
    }

    private Lecture create(Lecture lecture) throws RollbackFailureException, Exception {
        EntityManager em = getEntityManager();
        return em.merge(lecture);
    }

    public void edit(Lecture lecture) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = getEntityManager();
        lecture = em.merge(lecture);
    }

    public void destroy(Long id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = getEntityManager();
        Lecture lecture;
        try {
            lecture = em.getReference(Lecture.class, id);
            lecture.getId();
        } catch (EntityNotFoundException enfe) {
            throw new NonexistentEntityException("The lecture with id " + id + " no longer exists.", enfe);
        }
        em.remove(lecture);
    }

    public List<Lecture> findLectureEntities() {
        return findLectureEntities(true, -1, -1);
    }

    public List<Lecture> findLectureEntities(int maxResults, int firstResult) {
        return findLectureEntities(false, maxResults, firstResult);
    }

    private List<Lecture> findLectureEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        Query q = em.createQuery("select object(o) from Lecture as o");
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    public Lecture findLecture(Long id) {
        EntityManager em = getEntityManager();
        return em.find(Lecture.class, id);
    }

    public Lecture merge(Long lecturerID, LectureHall lectureHall) throws Exception {
        List<TimeTableDailyEntries> dailyEntries = null;
        TimeTableDailyEntries entry = null;
        Lecturer lecturer = null;
        Course courseWanted = null;
        Lecture lecture = null;
        try {
            String today = timeTableDayEntryServiceBean.currentDay(new GregorianCalendar(TimeZone.getTimeZone("GMT+1")).get(Calendar.DAY_OF_WEEK));
            lecturer = lecturerServiceBean.findLecturer(lecturerID);
            dailyEntries = timeTableDayEntryServiceBean
                    .findTimeTableDayEntryEntities(lectureHall,
                    academicSessionServiceBean.getCurrentAcademicSession(), today);
        } catch (Exception ex) {
            Logger.getLogger(Lecture.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Course course : lecturer.getCourses()) {
            Long id = course.getId();
            for (TimeTableDailyEntries entries : dailyEntries) {
                Long id2 = entries.getCourse().getId();
                if (id == id2) {
                    if (findCourse(entries)) {
                        entry = entries;
                        courseWanted = course;
                        break;
                    }
                }
            }
            if (courseWanted != null) {
                break;
            }
        }
        if (courseWanted != null && entry != null) {
            lecture = findLecture(courseWanted, lectureHall, getTodaysDate(), getTodaysMonth(), getTodaysYear());
            if (lecture != null) {
                lecture.setInProgress(false);
                if (leftEarly(entry)) {
                    lecture.setEndHour(getTodaysHour());
                    lecture.setEndMin(getTodaysMin());
                } else {
                    lecture.setEndHour(entry.getEndTimeHour());
                    lecture.setEndMin(entry.getEndTimeMin());
                }
                lecture = create(lecture);
            } else {
                lecture = new Lecture();
                lecture.setCourse(courseWanted);
                lecture.setDateOfLecture(getTodaysDate());
                lecture.setInProgress(true);
                lecture.setLectureHall(lectureHall);
                lecture.setMonthOfLecture(getTodaysMonth());
                lecture.setYearOfLecture(getTodaysYear());
                if (!late(entry)) {
                    lecture.setStartHour(entry.getStartTimeHour());
                    lecture.setStartMin(entry.getStartTimeMin());
                } else {
                    lecture.setStartHour(getTodaysHour());
                    lecture.setStartMin(getTodaysMin());
                }
                lecture = create(lecture);
            }
        }
        return lecture;
    }

    public Lecture findLecture(Course course, LectureHall lectureHall, int dateOfLecture, int monthOfLecture, int yearOfLecture) throws Exception {
// Based on the fact that a lecture cannot be held twice in a day at the same location
        Query query = super.getEntityManager().createQuery("select L from Lecture L where L.dateOfLecture = :dol and L.lectureHall=:lecHall and L.course=:course and L.monthOfLecture=:mol and L.yearOfLecture=:yol");
        query.setParameter("dol", dateOfLecture);
        query.setParameter("lecHall", lectureHall);
        query.setParameter("course", course);
        query.setParameter("mol", monthOfLecture);
        query.setParameter("yol", yearOfLecture);
        return (Lecture) super.findEntity(query);
    }
    public Lecture findLecture(Course course, LectureHall lectureHall, int dateOfLecture) throws Exception {
// Based on the fact that a lecture cannot be held twice in a day at the same location
        Query query = super.getEntityManager().createQuery("select L from Lecture L where L.dateOfLecture = :dol and L.lectureHall=:lecHall and L.course=:course");
        query.setParameter("dol", dateOfLecture);
        query.setParameter("lecHall", lectureHall);
        query.setParameter("course", course);
        return (Lecture) super.findEntity(query);
    }

    public int getLectureCount() {
        EntityManager em = getEntityManager();
        Query q = em.createQuery("select count(o) from Lecture as o");
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    protected Query getSearchCriteria(Serializable paramSerializable, String paramString, boolean paramBoolean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean findCourse(TimeTableDailyEntries entries) {
        boolean courseFound = false;
        int currentHr = new GregorianCalendar(TimeZone.getTimeZone("GMT+1")).get(Calendar.HOUR_OF_DAY);
        int currentMin = new GregorianCalendar(TimeZone.getTimeZone("GMT+1")).get(Calendar.MINUTE);
        if (currentHr == entries.getStartTimeHour() || (entries.getStartTimeHour() - currentHr == 1 && currentMin >= 55)
                || (currentHr - entries.getStartTimeHour() > 1 && currentHr < entries.getEndTimeHour())) {
            courseFound = true;
        }
        return courseFound;
    }

    public int getTodaysDate() {
        return new GregorianCalendar(TimeZone.getTimeZone("GMT+1")).get(Calendar.DATE);
    }

    public int getTodaysMonth() {
        return new GregorianCalendar(TimeZone.getTimeZone("GMT+1")).get(Calendar.MONTH);
    }

    public int getTodaysYear() {
        return new GregorianCalendar(TimeZone.getTimeZone("GMT+1")).get(Calendar.YEAR);
    }

    public int getTodaysHour() {
        return new GregorianCalendar(TimeZone.getTimeZone("GMT+1")).get(Calendar.HOUR_OF_DAY);
    }

    public int getTodaysMin() {
        return new GregorianCalendar(TimeZone.getTimeZone("GMT+1")).get(Calendar.MINUTE);
    }

    public boolean late(TimeTableDailyEntries entries) {
        boolean late = true;
        int currentHr = new GregorianCalendar(TimeZone.getTimeZone("GMT+1")).get(Calendar.HOUR_OF_DAY);
        int currentMin = new GregorianCalendar(TimeZone.getTimeZone("GMT+1")).get(Calendar.MINUTE);
        if ((currentHr == entries.getStartTimeHour() && currentMin <= 5)
                || (entries.getStartTimeHour() - currentHr == 1 && currentMin >= 55)) {
            late = false;
        }
        return late;
    }
    
    public boolean lateNotStarted(TimeTableDailyEntries entries) {
        boolean late = false;
        int currentHr = new GregorianCalendar(TimeZone.getTimeZone("GMT+1")).get(Calendar.HOUR_OF_DAY);
        int currentMin = new GregorianCalendar(TimeZone.getTimeZone("GMT+1")).get(Calendar.MINUTE);
        if (currentHr >= entries.getStartTimeHour() ) {
            late = true;
        }
        return late;
    }

    public boolean leftEarly(TimeTableDailyEntries entries) {
        boolean leftEarly = true;
        int currentHr = new GregorianCalendar(TimeZone.getTimeZone("GMT+1")).get(Calendar.HOUR_OF_DAY);
        int currentMin = new GregorianCalendar(TimeZone.getTimeZone("GMT+1")).get(Calendar.MINUTE);
        if ((currentHr == entries.getEndTimeHour() && currentMin <= 5)
                || (entries.getEndTimeHour() - currentHr == 1 && currentMin >= 55)) {
            leftEarly = false;
        }
        return leftEarly;
    }

    public String integerFormat(int value) {
        if (value < 10) {
            return "0" + value;
        } else {
            return "" + value;
        }
    }
}
