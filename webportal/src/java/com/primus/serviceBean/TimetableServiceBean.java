/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.serviceBean;

import com.primus.data.AcademicSession;
import com.primus.data.DepartmentName;
import java.io.Serializable;
import javax.persistence.Query;
import com.primus.data.FacultyName;
import com.primus.data.Lecture;
import java.util.List;
import com.primus.data.TimeTable;
import com.primus.data.TimeTableDailyEntries;
import com.primus.enums.DayOfWeek;
import com.primus.enums.LectureHall;
import com.primus.enums.Status;
import com.primus.gsondata.GsonLecture;
import com.primus.interfaces.TimetableService;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Olisa
 */
@Transactional
@Component
public class TimetableServiceBean extends PrimusBasePersistenceService implements TimetableService {

    @Autowired
    LectureServiceBean lectureServiceBean;
    @Autowired
    DepartmentServiceBean departmentServiceBean;

    @Override
    public void create(TimeTable timeTable) throws Exception {
        if (findTimeTableByDepartment(timeTable.getDepartment().getDepartmentName(), timeTable.getAcademicSession()) != null) {
            throw new Exception("Timetable has already been created for this department and this session");
        }
//        for (TimeTableDayEntry dayEntry : timeTable.getTimeTableDayEntries()) {
//            for (TimeTableDailyEntries dailyEntry : dayEntry.getTimeTableDailyEntries()) {
//                if (isClashing(dailyEntry.getStartTime(), dailyEntry.getEndTime(), dailyEntry.getCourse().getId(), dailyEntry.getLectureHall(), dayEntry.getTimeTableDailyEntries())) {
//                    throw new Exception("Timetable Clash detected. Please resolve conflict and try again.");
//                }
//            }
//        }
        super.storeEntity(timeTable);
    }

    @Override
    public void edit(TimeTable timeTable) throws Exception {
        if (findTimeTableByDepartment(timeTable.getDepartment().getDepartmentName(), timeTable.getAcademicSession()) == null) {
            super.storeEntity(timeTable);
        } else {
            EntityManager em = super.getEntityManager();
            em.merge(timeTable);
//            EntityManager em = super.getEntityManager();
//            for (TimeTableDayEntry dayEntry : timeTable.getTimeTableDayEntries()) {
//                for (TimeTableDailyEntries dailyEntry : dayEntry.getTimeTableDailyEntries()) {
//                    if (isClashing(dailyEntry.getStartTime(), dailyEntry.getEndTime(), dailyEntry.getCourse().getId(), dailyEntry.getLectureHall(), dayEntry.getTimeTableDailyEntries())) {
//                        throw new Exception("Timetable Clash detected. Please resolve conflict and try again.");
//                    }
//                }
//            }
//            em.merge(timeTable);
        }


    }

    @Override
    public List<TimeTable> findAllTimeTables() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TimeTable findTimeTableByDepartment(DepartmentName departmentName, AcademicSession academicSession) throws Exception {
        Query q = super.getEntityManager().createQuery("SELECT tt FROM TimeTable tt WHERE tt.academicSession=:sess  AND tt.department.departmentName=:deptN");
        q.setParameter("deptN", departmentName);
        q.setParameter("sess", academicSession);
        return (TimeTable) super.findEntity(q);
    }

    @Override
    public List<GsonLecture> getLecturesForDay(String deptName, AcademicSession academicSession, String dayOfWeek) throws Exception {
        Calendar calendar = new GregorianCalendar();
        List<TimeTableDailyEntries> timeTableDailyEntries;
        List<GsonLecture> lectures = new ArrayList();
        DepartmentName departmentName = departmentServiceBean.findDepartmentName(deptName);
        GsonLecture gLecture;
        Lecture lecture;
        timeTableDailyEntries = getDailyEntries(findTimeTableByDepartment(departmentName, academicSession), dayOfWeek);
        for (TimeTableDailyEntries dailyEntry : timeTableDailyEntries) {
            gLecture = new GsonLecture();
            gLecture.setCourseCodeTitle(dailyEntry.getCourse().getCourseCode() + " (" + dailyEntry.getCourse().getCourseTitle() + ")");
            gLecture.setDayOfweek(dayOfWeek);
            gLecture.setDepartmentName(departmentName.getName());
            gLecture.setLocation(dailyEntry.getLectureHall().name().replaceAll("_", " "));
            lecture = lectureServiceBean.findLecture(dailyEntry.getCourse(), dailyEntry.getLectureHall(), getWeekdayAsInteger(dayOfWeek));
            //lecture = lectureServiceBean.findLecture(dailyEntry.getCourse(), dailyEntry.getLectureHall(), calendar.get(Calendar.DAY_OF_MONTH));
            System.out.println("lecturer status is " + lecture);
            if (lecture != null) {
                gLecture.setEndTime(String.valueOf(lecture.getEndHour()) + ":" + String.valueOf(lecture.getEndMin()));
                gLecture.setStartTime(String.valueOf(lecture.getStartHour()) + ":" + String.valueOf(lecture.getStartMin()));
                if (lecture.isInProgress()) {
                    gLecture.setStatus(Status.ONGOING);
                } else {
                    gLecture.setStatus(Status.ENDED);
                }

            } else {
                gLecture.setEndTime("xx:xx");
                gLecture.setStartTime("xx:xx");
                gLecture.setStatus(Status.NOT_STARTED);
            }
            lectures.add(gLecture);
        }
        return lectures;
    }

    private TimeTableDailyEntries findTimeTableClash(DepartmentName departmentName, AcademicSession academicSession, LectureHall lectureHall, DayOfWeek dow, Calendar sTime, Calendar eTime) throws Exception {
        Query q = super.getEntityManager().createQuery("select ttde from TimeTable tt join tt.TimeTableDayEntries ttdes join ttdes.TimeTableDailyEntries ttde WHERE tt.academicSession=:sess  AND tt.department.departmentName=:deptN   AND ttdes.dayOfWeek =:dow  AND ttde.startTime.timeInMillis=:sTime AND ttde.endTime.timeInMillis=:eTime AND ttde.lectureHall=:hall");
        q.setParameter("deptN", departmentName);
        q.setParameter("sess", academicSession);
        q.setParameter("hall", lectureHall);
        q.setParameter("dow", dow);
        q.setParameter("sTime", sTime.getTimeInMillis());
        q.setParameter("eTime", eTime.getTimeInMillis());
        return (TimeTableDailyEntries) super.findEntity(q);
    }

    @Override
    public List<TimeTable> findTimeTablesByDepartment(DepartmentName departmentName) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isClashing(List<TimeTableDailyEntries> currentValues, List<TimeTableDailyEntries> newValues) throws Exception {
        boolean clashed = false;
        for (TimeTableDailyEntries entry : currentValues) {
            for (TimeTableDailyEntries entryInst2 : newValues) {
                String s1 = lectureServiceBean.integerFormat(entry.getStartTimeHour()) + "" + lectureServiceBean.integerFormat(entry.getStartTimeMin());
                String e1 = lectureServiceBean.integerFormat(entry.getEndTimeHour()) + "" + lectureServiceBean.integerFormat(entry.getEndTimeMin());
                String s2 = lectureServiceBean.integerFormat(entryInst2.getStartTimeHour()) + "" + lectureServiceBean.integerFormat(entryInst2.getStartTimeMin());
                String e2 = lectureServiceBean.integerFormat(entryInst2.getEndTimeHour()) + "" + lectureServiceBean.integerFormat(entryInst2.getEndTimeMin());
                boolean timeClash = false;
                int ss1 = Integer.valueOf(s1);
                int ss2 = Integer.valueOf(s2);
                int ee1 = Integer.valueOf(e1);
                if ((ss1 == ss2) || ((ss1 < ss2) && (ss2 < ee1))) {
                    timeClash = true;
                }
                String hall1 = entry.getLectureHall().name();
                String hall2 = entryInst2.getLectureHall().name();
                boolean hallClash = false;
                if (hall1.equalsIgnoreCase(hall2)) {
                    hallClash = true;
                }
                if ((timeClash) && hallClash) {
                    clashed = true;
                    break;
                }
            }
            if (clashed) {
                break;
            }
        }
        return clashed;
    }

    @Override
    public boolean isSelfClashing(List<TimeTableDailyEntries> otherValues) throws Exception {
        boolean clashed = false;
        int i;
        boolean repeatCourse;
        for (TimeTableDailyEntries entry : otherValues) {
            i = 0;
            repeatCourse = false;
            for (TimeTableDailyEntries entryInst2 : otherValues) {
                String s1 = lectureServiceBean.integerFormat(entry.getStartTimeHour()) + "" + lectureServiceBean.integerFormat(entry.getStartTimeMin());
                String e1 = lectureServiceBean.integerFormat(entry.getEndTimeHour()) + "" + lectureServiceBean.integerFormat(entry.getEndTimeMin());
                String s2 = lectureServiceBean.integerFormat(entryInst2.getStartTimeHour()) + "" + lectureServiceBean.integerFormat(entryInst2.getStartTimeMin());
                String e2 = lectureServiceBean.integerFormat(entryInst2.getEndTimeHour()) + "" + lectureServiceBean.integerFormat(entryInst2.getEndTimeMin());
                int ss1 = Integer.valueOf(s1);
                int ss2 = Integer.valueOf(s2);
                int ee1 = Integer.valueOf(e1);
                boolean timeClash = ((ss1 == ss2) || ((ss1 < ss2) && (ss2 < ee1)));
                boolean hallClash = entryInst2.getLectureHall().name().equalsIgnoreCase(entry.getLectureHall().name());
                Long ID = entry.getCourse().getId();
                Long courseID = entryInst2.getCourse().getId();
                boolean courseClash = (ID == courseID);
                if (courseClash) {
                    i++;
                    if (i <= 1) {
                        repeatCourse = true;
                    }
                }
                System.out.println("s1:" + s1 + " e1:" + e1 + " s2:" + s2 + " e2:" + e2 + " i=" + i);
                if ((timeClash) && hallClash && !repeatCourse) {
                    clashed = true;
                    break;
                }
            }
            if (clashed) {
                break;
            }
        }
        return clashed;
    }

    @Override
    protected Query getSearchCriteria(Serializable paramSerializable, String paramString, boolean paramBoolean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private List<TimeTableDailyEntries> getDailyEntries(TimeTable timeTable, String dayOfWeek) {
        if (timeTable != null) {
            switch (dayOfWeek.toUpperCase()) {
                case "SUNDAY":
                    return timeTable.getSunday().getTimeTableDailyEntries();
                case "MONDAY":
                    return timeTable.getMonday().getTimeTableDailyEntries();
                case "TUESDAY":
                    return timeTable.getTuesday().getTimeTableDailyEntries();
                case "WEDNESDAY":
                    return timeTable.getWednesday().getTimeTableDailyEntries();
                case "FRIDAY":
                    return timeTable.getFriday().getTimeTableDailyEntries();
                case "THURSDAY":
                    return timeTable.getThursday().getTimeTableDailyEntries();
                case "SATURDAY":
                    return timeTable.getSaturday().getTimeTableDailyEntries();
                default:
                    return null;
            }
        } else {
            return null;
        }

    }

    /**
     * Converts a string representation of calendar day to integer value Returns
     * -1 if not found or parameter null.
     *
     * @param dayOfWeek
     * @return Integer
     */
    public static int getWeekdayAsInteger(String dayOfWeek) {
        Calendar calendar = new GregorianCalendar();
        int currentDay = calendar.get(Calendar.DAY_OF_WEEK);

        if (dayOfWeek != null) {
            if (currentDay == 1) {
                switch (dayOfWeek.toUpperCase()) {
                    case "SUNDAY":
                        return calendar.get(Calendar.DAY_OF_MONTH);
                    case "MONDAY":
                        return calendar.get(Calendar.DAY_OF_MONTH) + 1;
                    case "TUESDAY":
                        return calendar.get(Calendar.DAY_OF_MONTH) + 2;
                    case "WEDNESDAY":
                        return calendar.get(Calendar.DAY_OF_MONTH) + 3;
                    case "FRIDAY":
                        return calendar.get(Calendar.DAY_OF_MONTH) + 5;
                    case "THURSDAY":
                        return calendar.get(Calendar.DAY_OF_MONTH) + 4;
                    case "SATURDAY":
                        return calendar.get(Calendar.DAY_OF_MONTH) + 6;
                }
            }
            if (currentDay == 2) {
                switch (dayOfWeek.toUpperCase()) {
                    case "SUNDAY":
                        return calendar.get(Calendar.DAY_OF_MONTH) - 1;
                    case "MONDAY":
                        return calendar.get(Calendar.DAY_OF_MONTH);
                    case "TUESDAY":
                        return calendar.get(Calendar.DAY_OF_MONTH) + 1;
                    case "WEDNESDAY":
                        return calendar.get(Calendar.DAY_OF_MONTH) + 2;
                    case "FRIDAY":
                        return calendar.get(Calendar.DAY_OF_MONTH) + 4;
                    case "THURSDAY":
                        return calendar.get(Calendar.DAY_OF_MONTH) + 3;
                    case "SATURDAY":
                        return calendar.get(Calendar.DAY_OF_MONTH) + 5;
                }
            }
            if (currentDay == 3) {
                switch (dayOfWeek.toUpperCase()) {
                    case "SUNDAY":
                        return calendar.get(Calendar.DAY_OF_MONTH) - 2;
                    case "MONDAY":
                        return calendar.get(Calendar.DAY_OF_MONTH) - 1;
                    case "TUESDAY":
                        return calendar.get(Calendar.DAY_OF_MONTH);
                    case "WEDNESDAY":
                        return calendar.get(Calendar.DAY_OF_MONTH) + 1;
                    case "FRIDAY":
                        return calendar.get(Calendar.DAY_OF_MONTH) + 3;
                    case "THURSDAY":
                        return calendar.get(Calendar.DAY_OF_MONTH) + 2;
                    case "SATURDAY":
                        return calendar.get(Calendar.DAY_OF_MONTH) + 4;
                }
            }
            if (currentDay == 4) {
                switch (dayOfWeek.toUpperCase()) {
                    case "SUNDAY":
                        return calendar.get(Calendar.DAY_OF_MONTH) - 3;
                    case "MONDAY":
                        return calendar.get(Calendar.DAY_OF_MONTH) - 2;
                    case "TUESDAY":
                        return calendar.get(Calendar.DAY_OF_MONTH) - 1;
                    case "WEDNESDAY":
                        return calendar.get(Calendar.DAY_OF_MONTH);
                    case "FRIDAY":
                        return calendar.get(Calendar.DAY_OF_MONTH) + 2;
                    case "THURSDAY":
                        return calendar.get(Calendar.DAY_OF_MONTH) + 1;
                    case "SATURDAY":
                        return calendar.get(Calendar.DAY_OF_MONTH) + 3;
                }
            }
            if (currentDay == 5) {
                switch (dayOfWeek.toUpperCase()) {
                    case "SUNDAY":
                        return calendar.get(Calendar.DAY_OF_MONTH) - 4;
                    case "MONDAY":
                        return calendar.get(Calendar.DAY_OF_MONTH) - 3;
                    case "TUESDAY":
                        return calendar.get(Calendar.DAY_OF_MONTH) - 2;
                    case "WEDNESDAY":
                        return calendar.get(Calendar.DAY_OF_MONTH) - 1;
                    case "FRIDAY":
                        return calendar.get(Calendar.DAY_OF_MONTH) + 1;
                    case "THURSDAY":
                        return calendar.get(Calendar.DAY_OF_MONTH);
                    case "SATURDAY":
                        return calendar.get(Calendar.DAY_OF_MONTH) + 2;
                }
            }
            if (currentDay == 6) {
                switch (dayOfWeek.toUpperCase()) {
                    case "SUNDAY":
                        return calendar.get(Calendar.DAY_OF_MONTH) - 5;
                    case "MONDAY":
                        return calendar.get(Calendar.DAY_OF_MONTH) - 4;
                    case "TUESDAY":
                        return calendar.get(Calendar.DAY_OF_MONTH) - 3;
                    case "WEDNESDAY":
                        return calendar.get(Calendar.DAY_OF_MONTH) - 2;
                    case "FRIDAY":
                        return calendar.get(Calendar.DAY_OF_MONTH);
                    case "THURSDAY":
                        return calendar.get(Calendar.DAY_OF_MONTH) - 1;
                    case "SATURDAY":
                        return calendar.get(Calendar.DAY_OF_MONTH) + 1;
                }
            }
            if (currentDay == 7) {
                switch (dayOfWeek.toUpperCase()) {
                    case "SUNDAY":
                        return calendar.get(Calendar.DAY_OF_MONTH) - 6;
                    case "MONDAY":
                        return calendar.get(Calendar.DAY_OF_MONTH) - 5;
                    case "TUESDAY":
                        return calendar.get(Calendar.DAY_OF_MONTH) - 4;
                    case "WEDNESDAY":
                        return calendar.get(Calendar.DAY_OF_MONTH) - 3;
                    case "FRIDAY":
                        return calendar.get(Calendar.DAY_OF_MONTH) - 1;
                    case "THURSDAY":
                        return calendar.get(Calendar.DAY_OF_MONTH) - 2;
                    case "SATURDAY":
                        return calendar.get(Calendar.DAY_OF_MONTH);
                }
            }
        }
        return -1;
    }

    @Override
    public List<TimeTable> findTimeTablesByFaculty(FacultyName facultyName, AcademicSession academicSession) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
