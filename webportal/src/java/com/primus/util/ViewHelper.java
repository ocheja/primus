/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.util;

import com.primus.appstates.AdministratorState;
import com.primus.appstates.LecturerState;
import com.primus.appstates.StudentState;
import com.primus.data.Administrator;
import com.primus.data.Lecture;
import com.primus.data.Lecturer;
import com.primus.data.PortalActivity;
import com.primus.data.Student;
import com.primus.data.TimeTableDailyEntries;
import com.primus.data.TimetableLecture;
import com.primus.enums.AcademicLevel;
import com.primus.enums.DayOfWeek;
import com.primus.enums.LectureHall;
import com.primus.enums.LecturerPosition;
import com.primus.enums.Semester;
import com.primus.enums.Title;
import com.primus.enums.TitleOfDegree;
import com.primus.interfaces.CourseRegistrationService;
import com.primus.interfaces.CourseService;
import com.primus.serviceBean.AcademicSessionServiceBean;
import com.primus.serviceBean.DepartmentServiceBean;
import com.primus.serviceBean.FacultyServiceBean;
import com.primus.serviceBean.LectureServiceBean;
import com.primus.serviceBean.PortalActivityServiceBean;
import com.primus.serviceBean.ResultGradingSchemeServiceBean;
import com.primus.serviceBean.TimeTableDailyEntriesServiceBean;
import com.primus.serviceBean.TimeTableDayEntryServiceBean;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Ocheja Patrick Ileanwa
 */
@Named("viewHelper")
public class ViewHelper {

    @Autowired
    FacultyServiceBean facultyServiceBean;
    @Autowired
    DepartmentServiceBean departmentServiceBean;
    @Autowired
    CourseService courseServiceBean;
    @Autowired
    ResultGradingSchemeServiceBean resultGradingSchemeServiceBean;
    @Autowired
    AcademicSessionServiceBean academicSessionServiceBean;
    @Autowired
    CourseRegistrationService courseRegistrationServiceBean;
    @Autowired
    TimeTableDayEntryServiceBean timeTableDayEntryServiceBean;
    @Autowired
    TimeTableDailyEntriesServiceBean timeTableDailyEntriesServiceBean;
    @Autowired
    LectureServiceBean lectureServiceBean;
    @Autowired
    PortalActivityServiceBean portalActivityServiceBean;

    public void processView(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String parentPage = httpServletRequest.getParameter("parent") == null ? (String) httpServletRequest.getAttribute("parent") : httpServletRequest.getParameter("parent");
        String childPage = httpServletRequest.getParameter("node") == null ? (String) httpServletRequest.getAttribute("node") : httpServletRequest.getParameter("node");
        Object currentUserState = httpServletRequest.getAttribute("currentUserState");
        if (currentUserState != null) {
            if (currentUserState instanceof AdministratorState) {
                AdministratorState administratorState = (AdministratorState) currentUserState;
                httpServletRequest.setAttribute("UserArea", "Admin");
                System.out.println("Admin userarea");
                if (parentPage != null) {
                    if (parentPage.equalsIgnoreCase(AdministratorState.AdminParentViews.HOME.name())) {
                        administratorState.setAdminParentViews(AdministratorState.AdminParentViews.HOME);
                    } else if (parentPage.equalsIgnoreCase(AdministratorState.AdminParentViews.OTHERS.name())) {
                        administratorState.setAdminParentViews(AdministratorState.AdminParentViews.OTHERS);
                    } else if (parentPage.equalsIgnoreCase(AdministratorState.AdminParentViews.ADMIN_MANAGER.name())) {
                        administratorState.setAdminParentViews(AdministratorState.AdminParentViews.ADMIN_MANAGER);
                    } else if (parentPage.equalsIgnoreCase(AdministratorState.AdminParentViews.FACULTY_MANAGER.name())) {
                        administratorState.setAdminParentViews(AdministratorState.AdminParentViews.FACULTY_MANAGER);
                    } else if (parentPage.equalsIgnoreCase(AdministratorState.AdminParentViews.DEPARTMENT_MANAGER.name())) {
                        administratorState.setAdminParentViews(AdministratorState.AdminParentViews.DEPARTMENT_MANAGER);
                    } else if (parentPage.equalsIgnoreCase(AdministratorState.AdminParentViews.COURSE_MANAGER.name())) {
                        administratorState.setAdminParentViews(AdministratorState.AdminParentViews.COURSE_MANAGER);
                    } else if (parentPage.equalsIgnoreCase(AdministratorState.AdminParentViews.LECTURER_MANAGER.name())) {
                        administratorState.setAdminParentViews(AdministratorState.AdminParentViews.LECTURER_MANAGER);
                    } else if (parentPage.equalsIgnoreCase(AdministratorState.AdminParentViews.UTILITY_MANAGER.name())) {
                        administratorState.setAdminParentViews(AdministratorState.AdminParentViews.UTILITY_MANAGER);
                    } else if (parentPage.equalsIgnoreCase(AdministratorState.AdminParentViews.STUDENT_MANAGER.name())) {
                        administratorState.setAdminParentViews(AdministratorState.AdminParentViews.STUDENT_MANAGER);
                    } else {
                        administratorState.setAdminParentViews(AdministratorState.AdminParentViews.HOME);
                    }
                } else {
                }
                if (childPage != null) {
                    if (childPage.equalsIgnoreCase(AdministratorState.AdminNodeViews.HOME_PROFILE.name())) {
                        administratorState.setAdminNodeViews(AdministratorState.AdminNodeViews.HOME_PROFILE);
                    } else if (childPage.equalsIgnoreCase(AdministratorState.AdminNodeViews.CREATE.name())) {
                        administratorState.setAdminNodeViews(AdministratorState.AdminNodeViews.CREATE);
                    } else if (childPage.equalsIgnoreCase(AdministratorState.AdminNodeViews.VIEW.name())) {
                        administratorState.setAdminNodeViews(AdministratorState.AdminNodeViews.VIEW);
                    } else if (childPage.equalsIgnoreCase(AdministratorState.AdminNodeViews.CREATE_FACULTY.name())) {
                        administratorState.setAdminNodeViews(AdministratorState.AdminNodeViews.CREATE_FACULTY);
                        httpServletRequest.setAttribute("facultyNames", facultyServiceBean.findFacultyNameEntities());
                    } else if (childPage.equalsIgnoreCase(AdministratorState.AdminNodeViews.VIEW_FACULTIES.name())) {
                        administratorState.setAdminNodeViews(AdministratorState.AdminNodeViews.VIEW_FACULTIES);
                    } else if (childPage.equalsIgnoreCase(AdministratorState.AdminNodeViews.CREATE_DEPARTMENT.name())) {
                        httpServletRequest.setAttribute("faculties", facultyServiceBean.findFacultyEntities());
                        httpServletRequest.setAttribute("departmentNames", departmentServiceBean.findDepartmentNameEntities());
                        administratorState.setAdminNodeViews(AdministratorState.AdminNodeViews.CREATE_DEPARTMENT);
                    } else if (childPage.equalsIgnoreCase(AdministratorState.AdminNodeViews.VIEW_DEPARTMENTS.name())) {
                        administratorState.setAdminNodeViews(AdministratorState.AdminNodeViews.VIEW_DEPARTMENTS);
                    } else if (childPage.equalsIgnoreCase(AdministratorState.AdminNodeViews.CREATE_COURSE.name())) {
                        httpServletRequest.setAttribute("courses", courseServiceBean.findCourseEntities());
                        httpServletRequest.setAttribute("levels", AcademicLevel.values());
                        httpServletRequest.setAttribute("semesters", Semester.values());
                        httpServletRequest.setAttribute("departments", departmentServiceBean.findDepartmentEntities());
                        administratorState.setAdminNodeViews(AdministratorState.AdminNodeViews.CREATE_COURSE);
                    } else if (childPage.equalsIgnoreCase(AdministratorState.AdminNodeViews.VIEW_COURSES.name())) {
                        httpServletRequest.setAttribute("courses", courseServiceBean.findCourseEntities());
                        administratorState.setAdminNodeViews(AdministratorState.AdminNodeViews.VIEW_COURSES);
                    } else if (childPage.equalsIgnoreCase(AdministratorState.AdminNodeViews.CREATE_LECTURER.name())) {
                        httpServletRequest.setAttribute("departments", departmentServiceBean.findDepartmentEntities());
                        httpServletRequest.setAttribute("lecturerTitles", Title.values());
                        administratorState.setAdminNodeViews(AdministratorState.AdminNodeViews.CREATE_LECTURER);
                    } else if (childPage.equalsIgnoreCase(AdministratorState.AdminNodeViews.VIEW_LECTURERS.name())) {
                        administratorState.setAdminNodeViews(AdministratorState.AdminNodeViews.VIEW_LECTURERS);
                        httpServletRequest.setAttribute("courses", courseServiceBean.findCourseEntities());
                        httpServletRequest.setAttribute("positions", LecturerPosition.values());
                        httpServletRequest.setAttribute("levels", AcademicLevel.values());
                    } else if (childPage.equalsIgnoreCase(AdministratorState.AdminNodeViews.CREATE_STUDENT.name())) {
                        httpServletRequest.setAttribute("departments", departmentServiceBean.findDepartmentEntities());
                        httpServletRequest.setAttribute("levels", AcademicLevel.values());
                        administratorState.setAdminNodeViews(AdministratorState.AdminNodeViews.CREATE_STUDENT);
                    } else if (childPage.equalsIgnoreCase(AdministratorState.AdminNodeViews.VIEW_STUDENTS.name())) {
                        administratorState.setAdminNodeViews(AdministratorState.AdminNodeViews.VIEW_STUDENTS);
                    } else if (childPage.equalsIgnoreCase(AdministratorState.AdminNodeViews.CREATE_UTILITY.name())) {
                        httpServletRequest.setAttribute("titleofdegrees", TitleOfDegree.values());
                        httpServletRequest.setAttribute("departments", departmentServiceBean.findDepartmentEntities());
                        httpServletRequest.setAttribute("gradingschemes", resultGradingSchemeServiceBean.findResultGradingSchemeEntities());
                        administratorState.setAdminNodeViews(AdministratorState.AdminNodeViews.CREATE_UTILITY);
                    } else if (childPage.equalsIgnoreCase(AdministratorState.AdminNodeViews.VIEW_UTILITIES.name())) {
                        administratorState.setAdminNodeViews(AdministratorState.AdminNodeViews.VIEW_UTILITIES);
                    } else {
                        administratorState.setAdminNodeViews(AdministratorState.AdminNodeViews.HOME_PROFILE);
                    }
                } else {
                }
            } else if (currentUserState instanceof LecturerState) {
                LecturerState lecturerState = (LecturerState) currentUserState;
                httpServletRequest.setAttribute("UserArea", "Lecturer");
                Lecturer lecturer = lecturerState.getCurrentLecturer();
                if (lecturer != null) {
                    lecturer.setPortalActivities(portalActivityServiceBean.findPortalActivityEntities(lecturer));
                    List<PortalActivity> activities = lecturer.getPortalActivities();
                    if (activities != null) {
                        Collections.sort(activities, new Comparator<Object>() {
                            @Override
                            public int compare(Object pa1, Object pa2) {
                                return ((PortalActivity) pa2).getDateOfAction().compareTo(((PortalActivity) pa1).getDateOfAction());
                            }
                        });
                        Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("GMT+1"));
                        for (int i = 0; i < activities.size(); i++) {
                            PortalActivity activity = activities.get(i);
                            Calendar calendar2 = DateUtils.toCalendar(activity.getDateOfAction());
                            long now = calendar.getTimeInMillis();
                            long before = calendar2.getTimeInMillis();
                            long diff = now - before;
                            activity.setMilliSecondsElapsed(diff);
                            activities.remove(i);
                            activities.add(i, activity);
                        }
                        lecturer.setPortalActivities(activities);
                        lecturerState.setCurrentLecturer(lecturer);
                    }
                }
                if (parentPage != null) {
                    if (parentPage.equalsIgnoreCase(LecturerState.LecturerParentViews.HOME.name())) {
                        lecturerState.setLecturerParentViews(LecturerState.LecturerParentViews.HOME);
                    } else if (parentPage.equalsIgnoreCase(LecturerState.LecturerParentViews.MANAGE_COURSE_FORM.name())) {
                        String lectPosition = null;
                        for (LecturerPosition lp : lecturer.getLecturerType().getPositions()) {
                            if (lp.name().equalsIgnoreCase(LecturerPosition.FACULTY_OFFICER.name())
                                    || lp.name().equalsIgnoreCase(LecturerPosition.ACADEMIC_ADVISER.name())
                                    || lp.name().equalsIgnoreCase(LecturerPosition.HOD.name())) {
                                lectPosition = LecturerPosition.REGISTER.name();
                                break;
                            }
                        }
                        if (lectPosition != null) {
                            lecturerState.setLecturerParentViews(LecturerState.LecturerParentViews.MANAGE_COURSE_FORM);
                            httpServletRequest.setAttribute("academicSessions", academicSessionServiceBean.findAcademicSessionEntities());
                            httpServletRequest.setAttribute("academicLevels", AcademicLevel.values());
                            httpServletRequest.setAttribute("semesters", Semester.values());
                        }
                    } else if (parentPage.equalsIgnoreCase(LecturerState.LecturerParentViews.ENTER_CLASSROOM.name())) {
                        lecturerState.setLecturerParentViews(LecturerState.LecturerParentViews.ENTER_CLASSROOM);
                        httpServletRequest.setAttribute("academicSessions", academicSessionServiceBean.findAcademicSessionEntities());
                    } else if (parentPage.equalsIgnoreCase(LecturerState.LecturerParentViews.VIEW_TIMETABLE.name())) {
                        lecturerState.setLecturerParentViews(LecturerState.LecturerParentViews.VIEW_TIMETABLE);
                        httpServletRequest.setAttribute("departmentNames", departmentServiceBean.findDepartmentNameEntities());
                        List<TimetableLecture> timetableLectures = new ArrayList<>();
                        List<TimeTableDailyEntries> dailyEntries = null;
                        String day = timeTableDayEntryServiceBean.currentDay(new GregorianCalendar(TimeZone.getTimeZone("GMT+1")).get(Calendar.DAY_OF_WEEK));
                        String department = String.valueOf(lecturer.getDepartment().getDepartmentName().getId());
                        if (httpServletRequest.getParameter("departmentName") != null) {
                            department = httpServletRequest.getParameter("departmentName");
                        }
                        try {
                            dailyEntries = timeTableDayEntryServiceBean
                                    .findTimeTableDayEntryEntities(departmentServiceBean
                                    .findDepartmentName(Long.valueOf(department)),
                                    academicSessionServiceBean.getCurrentAcademicSession(), day);
                        } catch (Exception ex) {
                            Logger.getLogger(ViewHelper.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        for (TimeTableDailyEntries ttde : dailyEntries) {
                            TimetableLecture timetableLecture = new TimetableLecture();
                            timetableLecture.setStartEndTime(lectureServiceBean.integerFormat(ttde.getStartTimeHour())
                                    + ":" + lectureServiceBean.integerFormat(ttde.getStartTimeMin()) + "-"
                                    + lectureServiceBean.integerFormat(ttde.getEndTimeHour()) + ":"
                                    + lectureServiceBean.integerFormat(ttde.getEndTimeMin()));
                            timetableLecture.setVenue(ttde.getLectureHall().name());
                            timetableLecture.setToday(timeTableDayEntryServiceBean.currentDay(new GregorianCalendar(TimeZone.getTimeZone("GMT+1")).get(Calendar.DAY_OF_WEEK)));
                            timetableLecture.setCourseCode(ttde.getCourse().getCourseCode());
                            timetableLecture.setDepartment(department);
                            if (timetableLecture.getToday().equalsIgnoreCase(day)) {
                                Lecture lecture = null;
                                try {
                                    lecture = lectureServiceBean.findLecture(ttde.getCourse(), ttde.getLectureHall(), lectureServiceBean.getTodaysDate(), lectureServiceBean.getTodaysMonth(), lectureServiceBean.getTodaysYear());
                                } catch (Exception ex) {
                                    Logger.getLogger(ViewHelper.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                if (lecture != null) {
                                    timetableLecture.setStarted(true);
                                    timetableLecture.setStartedAt(lectureServiceBean.integerFormat(lecture.getStartHour())
                                            + ":" + lectureServiceBean.integerFormat(lecture.getStartMin()));
                                } else {
                                    timetableLecture.setShldStarted(lectureServiceBean.lateNotStarted(ttde));
                                }
                            }
                            timetableLectures.add(timetableLecture);
                        }

                        httpServletRequest.setAttribute("timetableLectures", timetableLectures);
                        httpServletRequest.setAttribute("today", day);
                        httpServletRequest.setAttribute("department", department);
                    } else if (parentPage.equalsIgnoreCase(LecturerState.LecturerParentViews.ADD_NEW_TIMETABLE.name())) {
                        String lectPosition = null;
                        for (LecturerPosition lp : lecturer.getLecturerType().getPositions()) {
                            if (lp.name().equalsIgnoreCase(LecturerPosition.STAFF_ADVISER.name())) {
                                lectPosition = LecturerPosition.STAFF_ADVISER.name();
                                break;
                            }
                        }
                        if (lectPosition != null) {
                            lecturerState.setLecturerParentViews(LecturerState.LecturerParentViews.ADD_NEW_TIMETABLE);
                            httpServletRequest.setAttribute("venues", LectureHall.values());
                            httpServletRequest.setAttribute("daysOfTheWeek", DayOfWeek.values());
                            try {
                                httpServletRequest.setAttribute("courses", courseServiceBean.findByDepartment(lecturer.getDepartment().getDepartmentName()));
                            } catch (Exception ex) {
                                Logger.getLogger(ViewHelper.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    } else if (parentPage.equalsIgnoreCase(LecturerState.LecturerParentViews.EDIT_TIMETABLE.name())) {
                        String lectPosition = null;
                        for (LecturerPosition lp : lecturer.getLecturerType().getPositions()) {
                            if (lp.name().equalsIgnoreCase(LecturerPosition.STAFF_ADVISER.name())) {
                                lectPosition = LecturerPosition.STAFF_ADVISER.name();
                                break;
                            }
                        }
                        if (lectPosition != null) {
                            lecturerState.setLecturerParentViews(LecturerState.LecturerParentViews.EDIT_TIMETABLE);
                        }
                    } else if (parentPage.equalsIgnoreCase(LecturerState.LecturerParentViews.MANAGE_RESULTS.name())) {
                        lecturerState.setLecturerParentViews(LecturerState.LecturerParentViews.MANAGE_RESULTS);
                        httpServletRequest.setAttribute("academicSessions", academicSessionServiceBean.findAcademicSessionEntities());
                    } else if (parentPage.equalsIgnoreCase(LecturerState.LecturerParentViews.UPLOAD_RESULTS.name())) {
                        lecturerState.setLecturerParentViews(LecturerState.LecturerParentViews.UPLOAD_RESULTS);
                        httpServletRequest.setAttribute("academicSessions", academicSessionServiceBean.findAcademicSessionEntities());
                        httpServletRequest.setAttribute("departmentNames", departmentServiceBean.findDepartmentNameEntities());
                    } else if (parentPage.equalsIgnoreCase(LecturerState.LecturerParentViews.OTHERS.name())) {
                        lecturerState.setLecturerParentViews(LecturerState.LecturerParentViews.OTHERS);
                    }
                }
                if (childPage != null) {
                    if (childPage.equalsIgnoreCase(LecturerState.LecturerNodeViews.HOME_PROFILE.name())) {
                        lecturerState.setLecturerNodeViews(LecturerState.LecturerNodeViews.HOME_PROFILE);
                    } else if (childPage.equalsIgnoreCase(LecturerState.LecturerNodeViews.OTHERS.name())) {
                        lecturerState.setLecturerNodeViews(LecturerState.LecturerNodeViews.OTHERS);
                    }
                }
            } else {
                StudentState studentState = (StudentState) currentUserState;
                Student student = studentState.getCurrentStudent();
                httpServletRequest.setAttribute("UserArea", "Student");
                if (student != null) {
                    student.setPortalActivities(portalActivityServiceBean.findPortalActivityEntities(student));
                    List<PortalActivity> activities = student.getPortalActivities();
                    if (activities != null) {
                        Collections.sort(activities, new Comparator<Object>() {
                            @Override
                            public int compare(Object pa1, Object pa2) {
                                return ((PortalActivity) pa2).getDateOfAction().compareTo(((PortalActivity) pa1).getDateOfAction());
                            }
                        });
                        Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("GMT+1"));
                        for (int i = 0; i < activities.size(); i++) {
                            PortalActivity activity = activities.get(i);
                            Calendar calendar2 = DateUtils.toCalendar(activity.getDateOfAction());
                            long now = calendar.getTimeInMillis();
                            long before = calendar2.getTimeInMillis();
                            long diff = now - before;
                            activity.setMilliSecondsElapsed(diff);
                            activities.remove(i);
                            activities.add(i, activity);
                        }
                        student.setPortalActivities(activities);
                        studentState.setCurrentStudent(student);
                    }
                }
                if (parentPage != null) {
                    if (parentPage.equalsIgnoreCase(StudentState.StudentParentViews.HOME.name())) {
                        studentState.setStudentParentViews(StudentState.StudentParentViews.HOME);
                    } else if (parentPage.equalsIgnoreCase(StudentState.StudentParentViews.REGISTER_COURSES.name())) {
                        studentState.setStudentParentViews(StudentState.StudentParentViews.REGISTER_COURSES);
                    } else if (parentPage.equalsIgnoreCase(StudentState.StudentParentViews.VIEW_COURSE_FORM.name())) {
                        studentState.setStudentParentViews(StudentState.StudentParentViews.VIEW_COURSE_FORM);
                    } else if (parentPage.equalsIgnoreCase(StudentState.StudentParentViews.VIEW_MESSAGES.name())) {
                        studentState.setStudentParentViews(StudentState.StudentParentViews.VIEW_MESSAGES);
                    } else if (parentPage.equalsIgnoreCase(StudentState.StudentParentViews.VIEW_RESULTS.name())) {
                        httpServletRequest.setAttribute("academicLevels", academicSessionServiceBean.getAcademicLevels(student.getCurrentAcademicLevel()));
                        studentState.setStudentParentViews(StudentState.StudentParentViews.VIEW_RESULTS);
                    } else if (parentPage.equalsIgnoreCase(StudentState.StudentParentViews.VIEW_TIMETABLE.name())) {
                        studentState.setStudentParentViews(StudentState.StudentParentViews.VIEW_TIMETABLE);
                        httpServletRequest.setAttribute("departmentNames", departmentServiceBean.findDepartmentNameEntities());
                        List<TimetableLecture> timetableLectures = new ArrayList<>();
                        List<TimeTableDailyEntries> dailyEntries = null;
                        String day = timeTableDayEntryServiceBean.currentDay(new GregorianCalendar(TimeZone.getTimeZone("GMT+1")).get(Calendar.DAY_OF_WEEK));
                        String department = String.valueOf(student.getDepartment().getDepartmentName().getId());
                        if (httpServletRequest.getParameter("departmentName") != null) {
                            department = httpServletRequest.getParameter("departmentName");
                        }
                        try {
                            dailyEntries = timeTableDayEntryServiceBean
                                    .findTimeTableDayEntryEntities(departmentServiceBean
                                    .findDepartmentName(Long.valueOf(department)),
                                    academicSessionServiceBean.getCurrentAcademicSession(), day);
                        } catch (Exception ex) {
                            Logger.getLogger(ViewHelper.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        for (TimeTableDailyEntries ttde : dailyEntries) {
                            TimetableLecture timetableLecture = new TimetableLecture();
                            timetableLecture.setStartEndTime(lectureServiceBean.integerFormat(ttde.getStartTimeHour())
                                    + ":" + lectureServiceBean.integerFormat(ttde.getStartTimeMin()) + "-"
                                    + lectureServiceBean.integerFormat(ttde.getEndTimeHour()) + ":"
                                    + lectureServiceBean.integerFormat(ttde.getEndTimeMin()));
                            timetableLecture.setVenue(ttde.getLectureHall().name());
                            timetableLecture.setToday(timeTableDayEntryServiceBean.currentDay(new GregorianCalendar(TimeZone.getTimeZone("GMT+1")).get(Calendar.DAY_OF_WEEK)));
                            timetableLecture.setCourseCode(ttde.getCourse().getCourseCode());
                            timetableLecture.setDepartment(department);
                            if (timetableLecture.getToday().equalsIgnoreCase(day)) {
                                Lecture lecture = null;
                                try {
                                    lecture = lectureServiceBean.findLecture(ttde.getCourse(), ttde.getLectureHall(), lectureServiceBean.getTodaysDate(), lectureServiceBean.getTodaysMonth(), lectureServiceBean.getTodaysYear());
                                } catch (Exception ex) {
                                    Logger.getLogger(ViewHelper.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                if (lecture != null) {
                                    timetableLecture.setStarted(true);
                                    timetableLecture.setStartedAt(lectureServiceBean.integerFormat(lecture.getStartHour())
                                            + ":" + lectureServiceBean.integerFormat(lecture.getStartMin()));
                                } else {
                                    timetableLecture.setShldStarted(lectureServiceBean.lateNotStarted(ttde));
                                }
                            }
                            timetableLectures.add(timetableLecture);
                        }

                        httpServletRequest.setAttribute("timetableLectures", timetableLectures);
                        httpServletRequest.setAttribute("today", day);
                        httpServletRequest.setAttribute("department", department);
                    }
                }
                if (childPage != null) {
                    if (childPage.equalsIgnoreCase(StudentState.StudentNodeViews.HOME_PROFILE.name())) {
                        studentState.setStudentNodeViews(StudentState.StudentNodeViews.HOME_PROFILE);
                    } else if (childPage.equalsIgnoreCase(StudentState.StudentNodeViews.OTHERS.name())) {
                        studentState.setStudentNodeViews(StudentState.StudentNodeViews.OTHERS);
                    }
                }
            }
        } else {
        }
    }

    public Object processState(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        AdministratorState administratorState = AdministratorState.getInstance(httpServletRequest);
        StudentState studentState = StudentState.getInstance(httpServletRequest);
        LecturerState lecturerState = LecturerState.getInstance(httpServletRequest);
        HttpSession httpSession = httpServletRequest.getSession();
        if (administratorState.isSignedIn()) {
            System.out.println("Admin signed In");
            Administrator administrator = administratorState.getCurrentAdministrator();
            if (administrator != null) {
                System.out.println("Admin not null");
                lecturerState.setCurrentLecturer(null);
                lecturerState.setSignedIn(false);
                studentState.setCurrentStudent(null);
                studentState.setSignedIn(false);
                httpSession.setAttribute(LecturerState.class.getName(), lecturerState);
                httpSession.setAttribute(StudentState.class.getName(), studentState);
                return administratorState;
            } else {
                System.out.println("Admin null");
                return null;
            }
        } else if (lecturerState.isSignedIn()) {
            Lecturer lecturer = lecturerState.getCurrentLecturer();
            if (lecturer != null) {
                return lecturerState;
            } else {
                return null;
            }
        } else if (studentState.isSignedIn()) {
            Student student = studentState.getCurrentStudent();
            if (student != null) {
                return studentState;
            } else {
                return null;
            }
        } else {
            System.out.println("Every null");
            return null;
        }

    }
}
