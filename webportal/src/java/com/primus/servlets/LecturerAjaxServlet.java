/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.servlets;

import com.google.gson.Gson;
import com.primus.appstates.LecturerState;
import com.primus.data.AcademicSession;
import com.primus.data.Course;
import com.primus.data.CourseForm;
import com.primus.data.Department;
import com.primus.data.Friday;
import com.primus.data.JqueryDataTableParamModel;
import com.primus.data.Lecture;
import com.primus.data.Lecturer;
import com.primus.data.Message;
import com.primus.data.Monday;
import com.primus.data.PortalActivity;
import com.primus.data.ResultSheet;
import com.primus.data.Saturday;
import com.primus.data.Sunday;
import com.primus.data.Thursday;
import com.primus.data.TimeTable;
import com.primus.data.TimeTableDailyEntries;
import com.primus.data.TimetableLecture;
import com.primus.data.Tuesday;
import com.primus.data.Wednesday;
import com.primus.enums.AcademicLevel;
import com.primus.enums.LectureHall;
import com.primus.enums.LecturerPosition;
import com.primus.enums.Semester;
import com.primus.enums.Status;
import com.primus.interfaces.CourseService;
import com.primus.interfaces.LecturerResultSheetService;
import com.primus.interfaces.TimetableService;
import com.primus.service.exceptions.PrimusServiceException;
import com.primus.serviceBean.AcademicSessionServiceBean;
import com.primus.serviceBean.CourseFormServiceBean;
import com.primus.serviceBean.DepartmentServiceBean;
import com.primus.serviceBean.LectureServiceBean;
import com.primus.serviceBean.PortalActivityServiceBean;
import com.primus.serviceBean.TimeTableDailyEntriesServiceBean;
import com.primus.serviceBean.TimeTableDayEntryServiceBean;
import com.primus.util.JqueryDataTableCourseFormUtil;
import com.primus.util.JqueryDataTableParamUtility;
import com.primus.util.JqueryDataTableUtilityUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 *
 * @author Ocheja Patrick Ileanwa
 */
public class LecturerAjaxServlet extends HttpServlet {

    @Autowired
    JqueryDataTableParamUtility dataTableParamUtility;
    @Autowired
    JqueryDataTableUtilityUtil jqueryDataTableUtilityUtil;
    @Autowired
    JqueryDataTableCourseFormUtil jqueryDataTableCourseFormUtil;
    @Autowired
    AcademicSessionServiceBean academicSessionServiceBean;
    @Autowired
    CourseService courseServiceBean;
    @Autowired
    CourseFormServiceBean courseFormServiceBean;
    @Autowired
    TimetableService timetableServiceBean;
    @Autowired
    TimeTableDayEntryServiceBean timeTableDayEntryServiceBean;
    @Autowired
    TimeTableDailyEntriesServiceBean timeTableDailyEntriesServiceBean;
    @Autowired
    DepartmentServiceBean departmentServiceBean;
    @Autowired
    LectureServiceBean lectureServiceBean;
    @Autowired
    PortalActivityServiceBean portalActivityServiceBean;
    @Autowired
    LecturerResultSheetService lecturerResultSheetServiceBean;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        Message message = new Message();
        LecturerState lecturerState = LecturerState.getInstance(request);
        Lecturer lecturer = lecturerState.getCurrentLecturer();
        PortalActivity portalActivity = new PortalActivity();
                System.out.println("course In: sEcho= ");
        if (action != null) {
            String id = request.getParameter("id");
            String columnPosition = request.getParameter("columnPosition");
            String sEcho = request.getParameter("sEcho");
                System.out.println("ID: " + id + " COlumn Position: " + columnPosition);
                System.out.println("course In: sEcho= " + action);

            if (action.equalsIgnoreCase("courseFormData")) {
                System.out.println("Entered here ");
                System.out.println("ID: " + id + " COlumn Position: " + columnPosition);
                System.out.println("course In: sEcho= " + sEcho);
                if (sEcho != null && !sEcho.equals("")) {
                    System.out.println("Inside Echo");
                    JqueryDataTableParamModel param = dataTableParamUtility.getParam(request);
                    String jSonResponse = jqueryDataTableCourseFormUtil.processDataTable(param);
                    PrintWriter out = response.getWriter();
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    out.write(jSonResponse);
                    System.out.println("Response: " + jSonResponse);
                }
            } else if (action.equalsIgnoreCase("loadCourseForms")) {
                String academicSession = request.getParameter("academicsession");
                String semester = request.getParameter("semester");
                String AAApproved = request.getParameter("academicadviser");
                String FOApproved = request.getParameter("facultyofficer");
                String HODApproved = request.getParameter("headofdepartment");
                String academicLevel = request.getParameter("academiclevel");
                System.out.println("Response: " + academicSession + semester);

                if (academicSession != null && semester != null && academicLevel != null) {
                    try {
                        JqueryDataTableParamModel.academicLevel = AcademicLevel.valueOf(academicLevel);
                        JqueryDataTableParamModel.departmentName =
                                lecturer.getDepartment().getDepartmentName();
                        JqueryDataTableParamModel.academicSession = academicSessionServiceBean
                                .findAcademicSession(Long.valueOf(academicSession));
                        JqueryDataTableParamModel.semester = Semester.valueOf(semester);
                    } catch (Exception ex) {
                        Logger.getLogger(LecturerAjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (JqueryDataTableParamModel.academicSession == null || JqueryDataTableParamModel.semester == null) {
                    } else {
                        if (AAApproved != null) {
                            JqueryDataTableParamModel.academicAdviserApproved = true;
                        } else {
                            JqueryDataTableParamModel.academicAdviserApproved = false;
                        }
                        if (FOApproved != null) {
                            JqueryDataTableParamModel.FOApproved = true;
                        } else {
                            JqueryDataTableParamModel.FOApproved = false;
                        }
                        if (HODApproved != null) {
                            JqueryDataTableParamModel.HODApproved = true;
                        } else {
                            JqueryDataTableParamModel.HODApproved = false;
                        }
                        try {
                            response.setContentType("text/html");
                            request.getRequestDispatcher("/usersarea/lecturer/viewcourseforms.jsp").include(
                                    request, response);

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            } else if (action.equalsIgnoreCase("studentCourseData")) {
                System.out.println("Entered here ");
                System.out.println("ID: " + id + " Column Position: " + columnPosition);
                System.out.println("course In: sEcho= " + sEcho);
                if (sEcho != null && !sEcho.equals("")) {
                    System.out.println("Inside Echo");
                    JqueryDataTableParamModel param = dataTableParamUtility.getParam(request);
                    String jSonResponse = jqueryDataTableCourseFormUtil.processDataTableCourseStudent(param, lecturer.getId());
                    PrintWriter out = response.getWriter();
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    out.write(jSonResponse);
                    System.out.println("Response: " + jSonResponse);
                }
            } else if (action.equalsIgnoreCase("loadListOfStudents")) {
                String academicSession = request.getParameter("academicsession");
                String course = request.getParameter("course");

                if (academicSession != null && course != null) {
                    try {
                        JqueryDataTableParamModel.academicSession = academicSessionServiceBean
                                .findAcademicSession(Long.valueOf(academicSession));
                        JqueryDataTableParamModel.course = courseServiceBean.findCourse(Long.valueOf(course));
                    } catch (Exception ex) {
                        Logger.getLogger(LecturerAjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        response.setContentType("text/html");
                        request.getRequestDispatcher("/usersarea/lecturer/viewstudents.jsp").include(
                                request, response);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            } else if (action.equalsIgnoreCase("manageresults")) {
                String academicSession = request.getParameter("academicsession");
                String academicLevel = request.getParameter("academiclevel");

                if (academicSession != null && academicLevel != null) {
                    try {
                        List<ResultSheet> resultSheets = lecturerResultSheetServiceBean.getNotApprovedSheet(lecturer.getDepartment().getDepartmentName(), academicSessionServiceBean
                                .findAcademicSession(Long.valueOf(academicSession)), AcademicLevel.valueOf(academicLevel));
                        request.setAttribute("resultSheets", resultSheets);

                    } catch (Exception ex) {
                        Logger.getLogger(LecturerAjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        response.setContentType("text/html");
                        request.getRequestDispatcher("/usersarea/lecturer/coursesresults.jsp").include(
                                request, response);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            } else if (action.equalsIgnoreCase("approveCourseForm")) {
                String courseFormID = request.getParameter("courseFormId");
                System.out.println("course In: cformid= " + courseFormID);
                if (courseFormID != null) {
                    try {
                        CourseForm courseForm = courseFormServiceBean.findCourseForm(Long.valueOf(courseFormID));
                System.out.println("course In: cccccccfffff= " + courseForm);
                        if (courseForm != null) {
                            AcademicLevel academicLevel = courseForm.getStudent().getCurrentAcademicLevel();
                            Department department = courseForm.getStudent().getDepartment();
                            Set<LecturerPosition> positions = lecturer.getLecturerType().getPositions();
                System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii ");
                            for (LecturerPosition position : positions) {
                System.out.println("ggggggggggggggggggggggggg " + position.name());
                                if (position.name().equalsIgnoreCase(LecturerPosition.ACADEMIC_ADVISER.name())) {
                                    courseForm.setAcademicAdviserDateStamp(new Date());
                                    message.setMessage("Approved");
                                } else if (position.name().equalsIgnoreCase(LecturerPosition.HOD.name())) {
                                    courseForm.setHeadOfDepartmentDateStamp(new Date());
                                    message.setMessage("Approved");
                                } else if (position.name().equalsIgnoreCase(LecturerPosition.FACULTY_OFFICER.name())) {
                                    courseForm.setFacultyOfficerDateStamp(new Date());
                                    message.setMessage("Approved");
                                }
                            }

                            Date aadviser = courseForm.getAcademicAdviserDateStamp();
                            Date hod = courseForm.getHeadOfDepartmentDateStamp();
                            Date fo = courseForm.getFacultyOfficerDateStamp();
                            if (aadviser != null && hod != null && fo != null) {
                                courseForm.setStatus(Status.APPROVED);
                            } else {
                                courseForm.setStatus(Status.IN_PROGRESS);
                            }
                            courseFormServiceBean.edit(courseForm);
                            portalActivity.setActionPerformed("Approved course for student with registration number: " + courseForm.getStudent().getRegNumber() + " for  "
                                    + courseForm.getAcademicSession().getStartYear() + "/" + courseForm.getAcademicSession().getEndYear() + " academic session.");
                            portalActivity.setDateOfAction(new Date());
                            portalActivity.setDescription("approve activity");
                            portalActivity.setLecturer(lecturer);
                            portalActivityServiceBean.create(portalActivity);
                            message.setMessage("Approved");
                        } else {
                            message.setMessage("Course Form not well formed. Please refresh page.");
                        }
                        PrintWriter out = response.getWriter();
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        out.write(new Gson().toJson(message));
                    } catch (Exception e) {
                        e.printStackTrace();
                        message.setMessage("Error occurred");
                    }
                }
            } else if (action.equalsIgnoreCase("disApproveCourseForm")) {
                String courseFormID = request.getParameter("courseFormId");
                System.out.println("courseformid[[[[[ " + courseFormID);
                if (courseFormID != null) {
                    try {
                        CourseForm courseForm = courseFormServiceBean.findCourseForm(Long.valueOf(courseFormID));
                        System.out.println("courseformidxxxxxxxxxxxxxxxxxxxxxx " + courseFormID);
                        if (courseForm != null) {
                            System.out.println("courseformidyyyyyyyyyyyyyyy " + courseFormID);
                            courseForm.setStatus(Status.NOT_APPROVED);
                            AcademicLevel academicLevel = courseForm.getStudent().getCurrentAcademicLevel();
                            Department department = courseForm.getStudent().getDepartment();
                            Set<LecturerPosition> positions = lecturer.getLecturerType().getPositions();
                            for (LecturerPosition position : positions) {
                                if (position.name().equalsIgnoreCase(LecturerPosition.ACADEMIC_ADVISER.name())) {
                                    if (academicLevel.equals(lecturer.getLecturerType().getAssignedStudentLevel())
                                            && department.equals(lecturer.getLecturerType().getDepartment())) {
                                        courseForm.setAcademicAdviserDateStamp(null);
                                        message.setMessage("disapproved");
                                    }
                                } else if (position.name().equalsIgnoreCase(LecturerPosition.HOD.name())) {
                                    if (department.getId() == (lecturer.getLecturerType().getDepartment() == null ? null : lecturer.getLecturerType().getDepartment().getFaculty().getId())) {
                                        courseForm.setHeadOfDepartmentDateStamp(null);
                                        message.setMessage("disapproved");
                                    }
                                } else if (position.name().equalsIgnoreCase(LecturerPosition.FACULTY_OFFICER.name())) {
                                    if (department.getFaculty().getId() == (lecturer.getLecturerType().getDepartment() == null ? null : lecturer.getLecturerType().getDepartment().getFaculty().getId())) {
                                        courseForm.setFacultyOfficerDateStamp(null);
                                        message.setMessage("disapproved");
                                    }
                                }
                            }
                            System.out.println("cccccccccccccccccccccccccccccccccccc " + courseFormID);
                            courseFormServiceBean.edit(courseForm);
                            portalActivity.setActionPerformed("Disapproved course for student with registration number: " + courseForm.getStudent().getRegNumber() + " for  "
                                    + courseForm.getAcademicSession().getStartYear() + "/" + courseForm.getAcademicSession().getEndYear() + " academic session.");
                            portalActivity.setDateOfAction(new Date());
                            portalActivity.setDescription("disapprove activity");
                            portalActivity.setLecturer(lecturer);
                            portalActivityServiceBean.create(portalActivity);
                            System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk " + courseFormID);
                        }
                        PrintWriter out = response.getWriter();
                        System.out.println("courseformidllllllllllllllllllllllll " + courseFormID);
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        out.write(new Gson().toJson(message));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else if (action.equalsIgnoreCase("addnewtimetable")) {
                String coursesCount = request.getParameter("coursescount");
                String dayOfTheWeek = request.getParameter("dayoftheweek");
                System.out.println("Response: 1");
                Object[] days = {"H", "SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY"};
                String lectPosition = null;
                for (LecturerPosition lp : lecturer.getLecturerType().getPositions()) {
                    if (lp.name().equalsIgnoreCase(LecturerPosition.STAFF_ADVISER.name())) {
                        lectPosition = LecturerPosition.STAFF_ADVISER.name();
                        break;
                    }
                }
                if (coursesCount != null && dayOfTheWeek != null && lectPosition != null) {
                    try {
                        Integer count = Integer.valueOf(coursesCount);
                        List<TimeTableDailyEntries> rawNewEntries = new ArrayList<>();
                        for (int i = 1; i <= count; i++) {
                            String courseID = request.getParameter("course" + i);
                            String venue = request.getParameter("venue" + i);
                            String sTime = request.getParameter("sTime" + i);
                            String eTime = request.getParameter("eTime" + i);
                            if (courseID != null && venue != null && sTime != null && eTime != null) {
                                Course course = courseServiceBean.findCourse(Long.valueOf(courseID));
                                TimeTableDailyEntries ttde = new TimeTableDailyEntries();
                                ttde.setStartTimeHour(Integer.valueOf(sTime.split(":")[0]));
                                ttde.setStartTimeMin(Integer.valueOf(sTime.split(":")[1]));
                                ttde.setEndTimeHour(Integer.valueOf(eTime.split(":")[0]));
                                ttde.setEndTimeMin(Integer.valueOf(eTime.split(":")[1]));
                                ttde.setCourse(course);
                                ttde.setLectureHall(LectureHall.valueOf(venue));
                                rawNewEntries.add(ttde);
                            }
                        }

                        boolean clashed = timetableServiceBean.isSelfClashing(rawNewEntries);

                        if (clashed) {
                            throw new Exception("Clash detected in current instance. Please resolve.");
                        }

                        System.out.println("Response: 2");
                        TimeTable timeTable = timetableServiceBean.
                                findTimeTableByDepartment(lecturer.getDepartment().getDepartmentName(),
                                academicSessionServiceBean.getCurrentAcademicSession());
                        if (timeTable == null) {
                            timeTable = new TimeTable();
                            portalActivity.setActionPerformed("Created new timetable for " + academicSessionServiceBean.getCurrentAcademicSession().getStartYear() + "/"
                                    + academicSessionServiceBean.getCurrentAcademicSession().getEndYear() + " academic session");
                            portalActivity.setDateOfAction(new Date());
                            portalActivity.setDescription("timetable activity");
                            portalActivity.setLecturer(lecturer);
                            portalActivityServiceBean.create(portalActivity);
                        }
                        String day = (String) days[Integer.valueOf(dayOfTheWeek)];
                        if (day.equalsIgnoreCase("SUNDAY")) {
                            Sunday sunday = timeTable.getSunday();
                            if (sunday == null) {
                                sunday = new Sunday();
                                sunday.setTimeTableDailyEntries(new ArrayList<TimeTableDailyEntries>());
                                sunday = (Sunday) timeTableDayEntryServiceBean.create(sunday);
                            }
                            List<TimeTableDailyEntries> dailyEntries = sunday.getTimeTableDailyEntries();
                            clashed = timetableServiceBean.isClashing(dailyEntries, rawNewEntries);
                            if (clashed) {
                                throw new Exception("Clash detected between this instance and existing timetable entries.");
                            }
                            for (TimeTableDailyEntries ttde : rawNewEntries) {
                                ttde.setDayOfWeek(day);
                                ttde = timeTableDailyEntriesServiceBean.create(ttde);
                                dailyEntries.add(ttde);
                            }
                            sunday.setTimeTableDailyEntries(dailyEntries);
                            sunday = (Sunday) timeTableDayEntryServiceBean.edit(sunday);
                            timeTable.setSunday(sunday);
                        } else if (day.equalsIgnoreCase("Monday")) {
                            Monday monday = timeTable.getMonday();
                            if (monday == null) {
                                monday = new Monday();
                                monday.setTimeTableDailyEntries(new ArrayList<TimeTableDailyEntries>());
                            }
                            List<TimeTableDailyEntries> dailyEntries = monday.getTimeTableDailyEntries();
                            clashed = timetableServiceBean.isClashing(dailyEntries, rawNewEntries);
                            if (clashed) {
                                throw new Exception("Clash detected between this instance and existing timetable entries.");
                            }
                            for (TimeTableDailyEntries ttde : rawNewEntries) {
                                ttde.setDayOfWeek(day);
                                ttde = timeTableDailyEntriesServiceBean.create(ttde);
                                dailyEntries.add(ttde);
                            }
                            monday.setTimeTableDailyEntries(dailyEntries);
                            monday.setTimeTable(timeTable);
                            monday = (Monday) timeTableDayEntryServiceBean.edit(monday);
                            timeTable.setMonday(monday);
                        } else if (day.equalsIgnoreCase("TUESDAY")) {
                            Tuesday tuesday = timeTable.getTuesday();
                            if (tuesday == null) {
                                tuesday = new Tuesday();
                                tuesday.setTimeTableDailyEntries(new ArrayList<TimeTableDailyEntries>());
                                tuesday = (Tuesday) timeTableDayEntryServiceBean.create(tuesday);
                            }
                            List<TimeTableDailyEntries> dailyEntries = tuesday.getTimeTableDailyEntries();
                            clashed = timetableServiceBean.isClashing(dailyEntries, rawNewEntries);
                            if (clashed) {
                                throw new Exception("Clash detected between this instance and existing timetable entries.");
                            }
                            for (TimeTableDailyEntries ttde : rawNewEntries) {
                                ttde.setDayOfWeek(day);
                                ttde = timeTableDailyEntriesServiceBean.create(ttde);
                                dailyEntries.add(ttde);
                            }
                            tuesday.setTimeTableDailyEntries(dailyEntries);
                            tuesday = (Tuesday) timeTableDayEntryServiceBean.edit(tuesday);
                            timeTable.setTuesday(tuesday);
                        } else if (day.equalsIgnoreCase("WEDNESDAY")) {
                            Wednesday wednesday = timeTable.getWednesday();
                            if (wednesday == null) {
                                wednesday = new Wednesday();
                                wednesday.setTimeTableDailyEntries(new ArrayList<TimeTableDailyEntries>());
                                wednesday = (Wednesday) timeTableDayEntryServiceBean.create(wednesday);
                            }
                            List<TimeTableDailyEntries> dailyEntries = wednesday.getTimeTableDailyEntries();
                            clashed = timetableServiceBean.isClashing(dailyEntries, rawNewEntries);
                            if (clashed) {
                                throw new Exception("Clash detected between this instance and existing timetable entries.");
                            }
                            for (TimeTableDailyEntries ttde : rawNewEntries) {
                                ttde.setDayOfWeek(day);
                                ttde = timeTableDailyEntriesServiceBean.create(ttde);
                                dailyEntries.add(ttde);
                            }
                            wednesday.setTimeTableDailyEntries(dailyEntries);
                            wednesday = (Wednesday) timeTableDayEntryServiceBean.edit(wednesday);
                            timeTable.setWednesday(wednesday);
                        } else if (day.equalsIgnoreCase("THURSDAY")) {
                            Thursday thursday = timeTable.getThursday();
                            if (thursday == null) {
                                thursday = new Thursday();
                                thursday.setTimeTableDailyEntries(new ArrayList<TimeTableDailyEntries>());
                                thursday = (Thursday) timeTableDayEntryServiceBean.create(thursday);
                            }
                            List<TimeTableDailyEntries> dailyEntries = thursday.getTimeTableDailyEntries();
                            clashed = timetableServiceBean.isClashing(dailyEntries, rawNewEntries);
                            if (clashed) {
                                throw new Exception("Clash detected between this instance and existing timetable entries.");
                            }
                            for (TimeTableDailyEntries ttde : rawNewEntries) {
                                ttde.setDayOfWeek(day);
                                ttde = timeTableDailyEntriesServiceBean.create(ttde);
                                dailyEntries.add(ttde);
                            }
                            thursday.setTimeTableDailyEntries(dailyEntries);
                            thursday = (Thursday) timeTableDayEntryServiceBean.edit(thursday);
                            timeTable.setThursday(thursday);
                        } else if (day.equalsIgnoreCase("FRIDAY")) {
                            Friday friday = timeTable.getFriday();
                            if (friday == null) {
                                friday = new Friday();
                                friday.setTimeTableDailyEntries(new ArrayList<TimeTableDailyEntries>());
                                friday = (Friday) timeTableDayEntryServiceBean.create(friday);
                            }
                            List<TimeTableDailyEntries> dailyEntries = friday.getTimeTableDailyEntries();
                            clashed = timetableServiceBean.isClashing(dailyEntries, rawNewEntries);
                            if (clashed) {
                                throw new Exception("Clash detected between this instance and existing timetable entries.");
                            }
                            for (TimeTableDailyEntries ttde : rawNewEntries) {
                                ttde.setDayOfWeek(day);
                                ttde = timeTableDailyEntriesServiceBean.create(ttde);
                                dailyEntries.add(ttde);
                            }
                            friday.setTimeTableDailyEntries(dailyEntries);
                            friday = (Friday) timeTableDayEntryServiceBean.edit(friday);
                            timeTable.setFriday(friday);
                        } else if (day.equalsIgnoreCase("SATURDAY")) {
                            Saturday saturday = timeTable.getSaturday();
                            if (saturday == null) {
                                saturday = new Saturday();
                                saturday.setTimeTableDailyEntries(new ArrayList<TimeTableDailyEntries>());
                                saturday = (Saturday) timeTableDayEntryServiceBean.create(saturday);
                            }
                            List<TimeTableDailyEntries> dailyEntries = saturday.getTimeTableDailyEntries();
                            clashed = timetableServiceBean.isClashing(dailyEntries, rawNewEntries);
                            if (clashed) {
                                throw new Exception("Clash detected between this instance and existing timetable entries.");
                            }
                            for (TimeTableDailyEntries ttde : rawNewEntries) {
                                ttde.setDayOfWeek(day);
                                ttde = timeTableDailyEntriesServiceBean.create(ttde);
                                dailyEntries.add(ttde);
                            }
                            saturday.setTimeTableDailyEntries(dailyEntries);
                            saturday = (Saturday) timeTableDayEntryServiceBean.edit(saturday);
                            timeTable.setSaturday(saturday);
                        }
                        timeTable.setDepartment(lecturer.getDepartment());
                        timeTable.setAcademicSession(academicSessionServiceBean.getCurrentAcademicSession());
                        try {
                            System.out.println("Response: y1");
                            timetableServiceBean.edit(timeTable);
                            portalActivity.setActionPerformed("Added new timetable events to the timetable for " + academicSessionServiceBean.getCurrentAcademicSession().getStartYear() + "/"
                                    + academicSessionServiceBean.getCurrentAcademicSession().getEndYear() + " academic session");
                            portalActivity.setDateOfAction(new Date());
                            portalActivity.setDescription("timetable activity");
                            portalActivity.setLecturer(lecturer);
                            portalActivityServiceBean.create(portalActivity);
                            message.setMessage("Timetable entry added successfully.");
                            System.out.println("Response: x1");
                        } catch (Exception ex) {
                            message.setMessage(ex.getLocalizedMessage());
                            ex.printStackTrace();
                        }
                        System.out.println("Response: x2");
                        PrintWriter out = response.getWriter();
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        out.write(new Gson().toJson(message));
                        System.out.println("Response: x3");
                    } catch (Exception e) {
                        try {
                            message.setMessage(e.getLocalizedMessage());
                            PrintWriter out = response.getWriter();
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            out.write(new Gson().toJson(message));
                            System.out.println("Response: x3");
                        } catch (Exception ex) {
                        }
                    }
                } else {
                    System.out.println("Response: xx");
                }
            } else if (action.equalsIgnoreCase("timetableevents")) {
                String nextDisplay = request.getParameter("nextDisplay");
                String department = request.getParameter("department");
                if (nextDisplay != null && department != null) {
                    List<TimeTableDailyEntries> dailyEntries = new ArrayList<>();
                    if (nextDisplay.equalsIgnoreCase("Sunday")) {
                        try {
                            dailyEntries = timeTableDayEntryServiceBean
                                    .findTimeTableDayEntryEntities(departmentServiceBean
                                    .findDepartmentName(Long.valueOf(department)),
                                    academicSessionServiceBean.getCurrentAcademicSession(), "Sunday");
                        } catch (Exception ex) {
                            Logger.getLogger(LecturerAjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (nextDisplay.equalsIgnoreCase("Monday")) {
                        try {
                            dailyEntries = timeTableDayEntryServiceBean
                                    .findTimeTableDayEntryEntities(departmentServiceBean
                                    .findDepartmentName(Long.valueOf(department)),
                                    academicSessionServiceBean.getCurrentAcademicSession(), "Monday");
                        } catch (Exception ex) {
                            Logger.getLogger(LecturerAjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (nextDisplay.equalsIgnoreCase("Tuesday")) {
                        try {
                            dailyEntries = timeTableDayEntryServiceBean
                                    .findTimeTableDayEntryEntities(departmentServiceBean
                                    .findDepartmentName(Long.valueOf(department)),
                                    academicSessionServiceBean.getCurrentAcademicSession(), "Tuesday");
                        } catch (Exception ex) {
                            Logger.getLogger(LecturerAjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (nextDisplay.equalsIgnoreCase("Wednesday")) {
                        try {
                            dailyEntries = timeTableDayEntryServiceBean
                                    .findTimeTableDayEntryEntities(departmentServiceBean
                                    .findDepartmentName(Long.valueOf(department)),
                                    academicSessionServiceBean.getCurrentAcademicSession(), "Wednesday");
                        } catch (Exception ex) {
                            Logger.getLogger(LecturerAjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (nextDisplay.equalsIgnoreCase("Thursday")) {
                        try {
                            dailyEntries = timeTableDayEntryServiceBean
                                    .findTimeTableDayEntryEntities(departmentServiceBean
                                    .findDepartmentName(Long.valueOf(department)),
                                    academicSessionServiceBean.getCurrentAcademicSession(), "Thursday");
                        } catch (Exception ex) {
                            Logger.getLogger(LecturerAjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (nextDisplay.equalsIgnoreCase("Friday")) {
                        try {
                            dailyEntries = timeTableDayEntryServiceBean
                                    .findTimeTableDayEntryEntities(departmentServiceBean
                                    .findDepartmentName(Long.valueOf(department)),
                                    academicSessionServiceBean.getCurrentAcademicSession(), "Friday");
                        } catch (Exception ex) {
                            Logger.getLogger(LecturerAjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (nextDisplay.equalsIgnoreCase("Saturday")) {
                        try {
                            dailyEntries = timeTableDayEntryServiceBean
                                    .findTimeTableDayEntryEntities(departmentServiceBean
                                    .findDepartmentName(Long.valueOf(department)),
                                    academicSessionServiceBean.getCurrentAcademicSession(), "Saturday");
                        } catch (Exception ex) {
                            Logger.getLogger(LecturerAjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    List<TimetableLecture> timetableLectures = new ArrayList<>();
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
                        if (timetableLecture.getToday().equalsIgnoreCase(nextDisplay)) {
                            Lecture lecture = null;
                            try {
                                lecture = lectureServiceBean.findLecture(ttde.getCourse(), ttde.getLectureHall(), lectureServiceBean.getTodaysDate(), lectureServiceBean.getTodaysMonth(), lectureServiceBean.getTodaysYear());
                            } catch (Exception ex) {
                                Logger.getLogger(LecturerAjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
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

                    request.setAttribute("timetableLectures", timetableLectures);
                    request.setAttribute("today", nextDisplay);
                    request.setAttribute("department", department);
                    request.setAttribute("departmentNames", departmentServiceBean.findDepartmentNameEntities());
                    System.out.println("Data about to push out");
                    try {
                        String value = "/usersarea/lecturer/timetableday.jsp";
                        PrintWriter out = response.getWriter();
                        response.setContentType("text/html;charset=UTF-8");
                        request.getRequestDispatcher(value).include(request, response);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            } else if (action.equalsIgnoreCase("approveresult")) {
                String resultSheetID = request.getParameter("resultsheetid");
                boolean approved = false;
                if (resultSheetID != null) {
                    try {
                        ResultSheet resultSheet = lecturerResultSheetServiceBean.findResultSheet(Long.valueOf(resultSheetID));
                        if (resultSheet != null) {
                            resultSheet.setHeadOfDepartmentDateStamp(new Date());
                            resultSheet.setStatus(Status.APPROVED);
                            lecturerResultSheetServiceBean.develop(resultSheet);
                            approved = true;
                        }
                    } catch (PrimusServiceException ex) {
                        Logger.getLogger(LecturerAjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(LecturerAjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (approved) {
                    message.setMessage("Result has been successfully approved.");
                } else {
                    message.setMessage("Oops! something didn't work out fine. Please refresh page.");
                }
                PrintWriter out = response.getWriter();
                String json = new Gson().toJson(message);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out.write(json);
            }
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
