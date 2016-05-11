/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.servlets;

import com.google.gson.Gson;
import com.primus.appstates.StudentState;
import com.primus.data.AcademicSession;
import com.primus.data.Course;
import com.primus.data.CourseForm;
import com.primus.data.Lecture;
import com.primus.data.Message;
import com.primus.data.PortalActivity;
import com.primus.data.Student;
import com.primus.data.TimeTableDailyEntries;
import com.primus.data.TimetableLecture;
import com.primus.enums.Semester;
import com.primus.interfaces.CourseRegistrationService;
import com.primus.interfaces.CourseService;
import com.primus.interfaces.StudentService;
import com.primus.interfaces.TimetableService;
import com.primus.serviceBean.AcademicSessionServiceBean;
import com.primus.serviceBean.DepartmentServiceBean;
import com.primus.serviceBean.LectureServiceBean;
import com.primus.serviceBean.PortalActivityServiceBean;
import com.primus.serviceBean.TimeTableDailyEntriesServiceBean;
import com.primus.serviceBean.TimeTableDayEntryServiceBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
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
public class StudentAjaxServlet extends HttpServlet {

    @Autowired
    CourseService courseServiceBean;
    @Autowired
    StudentService studentServiceBean;
    @Autowired
    AcademicSessionServiceBean academicSessionServiceBean;
    @Autowired
    CourseRegistrationService courseRegistrationServiceBean;
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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet StudentAjaxServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StudentAjaxServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
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
        StudentState studentState = StudentState.getInstance(request);
        Student student = studentState.getCurrentStudent();
        PortalActivity portalActivity = new PortalActivity();

        if (action != null) {
            if (action.equalsIgnoreCase("prepareCourseRegForm")) {
                String semesterSelected = request.getParameter("semester");
                String sessionSelected = request.getParameter("academicSession");
                boolean courseFormSet = false;
                AcademicSession academicSessionSelected = null;
                if (sessionSelected != null) {
                    try {
                        academicSessionSelected = academicSessionServiceBean
                                .findAcademicSession(Long.valueOf(sessionSelected));
                        if (academicSessionSelected != null) {
                            if (semesterSelected != null) {

                                CourseForm workingCopyCF = courseRegistrationServiceBean
                                        .getCourseForm(student.getId(), academicSessionSelected,
                                        Semester.valueOf(semesterSelected));
                                if (workingCopyCF != null) {
                                    request.setAttribute("courseFormInstance", workingCopyCF);
                                    courseFormSet = true;
                                }
                            } else {
                                List<CourseForm> courseForm = courseRegistrationServiceBean
                                        .getCourseForms(student.getId(), academicSessionSelected);
                                Iterator<CourseForm> courseFormIterator = courseForm.iterator();
                                while (courseFormIterator.hasNext()) {
                                    CourseForm workingCopyCF = courseFormIterator.next();
                                    List<Course> courses = workingCopyCF.getCourses();
                                    Collections.sort(courses, new Comparator<Course>() {
                                        @Override
                                        public int compare(Course course1, Course course2) {
                                            return course1.getSemester().compareTo(course2.getSemester());
                                        }
                                    });
                                    workingCopyCF.setCourses(courses);
                                    request.setAttribute("courseFormInstance", workingCopyCF);
                                    courseFormSet = true;
                                }
                            }
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(StudentAjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (!courseFormSet) {
                    //try {
                    request.setAttribute("academicSessionX", academicSessionSelected);
                    request.setAttribute("semesterX", semesterSelected);
                }

                try {
                    response.setContentType("text/html");
                    request.getRequestDispatcher("/usersarea/student/courseform-instance.jsp").include(
                            request, response);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (action.equalsIgnoreCase("addCourses")) {
                String semesterSelected = request.getParameter("semester");
                String sessionSelected = request.getParameter("academicSession");
                if (sessionSelected != null) {
                    try {
                        AcademicSession academicSessionSelected = academicSessionServiceBean
                                .findAcademicSession(Long.valueOf(sessionSelected));
                        if (academicSessionSelected != null) {
                            if (semesterSelected != null) {
                                request.setAttribute("requiredCourses", courseRegistrationServiceBean
                                        .getRequiredCourses(student.getId(), Semester.valueOf(semesterSelected)));
                            } else {
                                request.setAttribute("requiredCourses", courseRegistrationServiceBean
                                        .getRequiredCourses(student.getId(), Semester.valueOf(semesterSelected)));
                            }
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(StudentAjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                try {
                    response.setContentType("text/html");
                    request.getRequestDispatcher("/usersarea/student/addrequiredcourses.jsp").include(
                            request, response);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (action.equalsIgnoreCase("addPrevCourses")) {
                String semesterSelected = request.getParameter("semester");
                try {
                    request.setAttribute("requiredCourses", courseRegistrationServiceBean
                            .getFailedCourses(student.getId(), Semester.valueOf(semesterSelected)));

                } catch (Exception ex) {
                    Logger.getLogger(StudentAjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    response.setContentType("text/html");
                    request.getRequestDispatcher("/usersarea/student/addrequiredcourses.jsp").include(
                            request, response);
                } catch (Exception ex) {
                    ex.printStackTrace();
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
            }

        } else {
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
        String action = request.getParameter("action");
        Message message = new Message();
        StudentState studentState = StudentState.getInstance(request);
        Student student = studentState.getCurrentStudent();
        PortalActivity portalActivity = new PortalActivity();

        if (action != null) {
            if (action.equalsIgnoreCase("registerCourses")) {
                String[] coursesIDs = request.getParameterValues("requiredCoursesSelected");
                String academicSession = request.getParameter("academicSession");
                String semester = request.getParameter("semester");
                if (coursesIDs != null && academicSession != null && semester != null) {
                    List<Course> courses = new ArrayList<>();
                    String courseStr = "";
                    for (String id : coursesIDs) {
                        try {
                            Course course = courseServiceBean.findCourse(Long.valueOf(id));
                            courses.add(course);
                            courseStr = courseStr+" "+course.getCourseCode();
                        } catch (Exception ex) {
                            Logger.getLogger(StudentAjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (courses.size() > 0) {
                        try {
                            AcademicSession session = academicSessionServiceBean.findAcademicSession(
                                    Long.valueOf(academicSession));
                            courseRegistrationServiceBean
                                    .registerCourses(student.getId(), Semester.valueOf(semester),
                                    session, courses);
                            portalActivity.setActionPerformed("Registered "+courseStr);
                            portalActivity.setDateOfAction(new Date());
                            portalActivity.setDescription("registration activity");
                            portalActivity.setStudent(student);
                            portalActivityServiceBean.create(portalActivity);
                            message.setMessage("Courses registered successfully. Awaiting Approval");
                        } catch (Exception ex) {
                            Logger.getLogger(StudentAjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
                            message.setMessage("An error occured. Please try again. " + ex.getLocalizedMessage());
                        }
                    } else {
                        message.setMessage("You must select atleast one course before submitting.");
                    }
                }
                //courseRegistrationServiceBean.
            }

        } else {
        }
        try {
            System.out.println("Message: " + message.getMessage());
            PrintWriter out = response.getWriter();
            String json = new Gson().toJson(message);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.write(json);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
