/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.servlets;

import com.google.gson.Gson;
import com.primus.appstates.LecturerState;
import com.primus.appstates.StudentState;
import com.primus.data.AcademicSession;
import com.primus.data.Course;
import com.primus.data.DepartmentName;
import com.primus.data.JqueryDataTableParamModel;
import com.primus.data.Lecturer;
import com.primus.data.Message;
import com.primus.data.PortalActivity;
import com.primus.data.Student;
import com.primus.enums.AcademicLevel;
import com.primus.enums.Semester;
import com.primus.interfaces.CourseRegistrationService;
import com.primus.interfaces.CourseService;
import com.primus.interfaces.LecturerResultSheetService;
import com.primus.interfaces.StudentResultSheetService;
import com.primus.serviceBean.AcademicSessionServiceBean;
import com.primus.serviceBean.DepartmentServiceBean;
import com.primus.serviceBean.PortalActivityServiceBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
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
public class FileDownloadServlet extends HttpServlet {

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
    @Autowired
    CourseRegistrationService courseRegistrationServiceBean;
    @Autowired
    AcademicSessionServiceBean academicSessionServiceBean;
    @Autowired
    LecturerResultSheetService lecturerResultSheetServiceBean;
    @Autowired
    DepartmentServiceBean departmentServiceBean;
    @Autowired
    PortalActivityServiceBean portalActivityServiceBean;
    @Autowired
    StudentResultSheetService studentResultSheetServiceBean;
    @Autowired
    CourseService courseServiceBean;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FileDownloadServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FileDownloadServlet at " + request.getContextPath() + "</h1>");
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
        StudentState studentState = StudentState.getInstance(request);
        Student student = studentState.getCurrentStudent();
        LecturerState lecturerState = LecturerState.getInstance(request);
        Lecturer lecturer = lecturerState.getCurrentLecturer();
        ServletContext servletContext = request.getServletContext();
        PortalActivity portalActivity = new PortalActivity();
        byte[] stream = null;
        if (student != null || lecturer != null) {
            String academicSessionID = request.getParameter("academicSession");
            String semester = request.getParameter("semester");
            String action = request.getParameter("action");
            if (action != null) {
                if (action.equalsIgnoreCase("courseform")) {
                    System.out.println("Course form not null");
                    AcademicSession academicSession = null;
                    if (academicSessionID != null && semester != null) {
                        try {
                            academicSession =
                                    academicSessionServiceBean.findAcademicSession(Long.valueOf(academicSessionID));
                            stream = courseRegistrationServiceBean.getCourseFormPDF(student.getId(),
                                    Semester.valueOf(semester), academicSession, servletContext);
                        } catch (Exception ex) {
                            Logger.getLogger(FileDownloadServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if (stream != null && academicSession != null) {
                            System.out.println("Stream not null");
                            response.setContentType("application/pdf");
                            response.setHeader("Content-disposition", "attachment;filename=Course Registration Form (" + academicSession.getString() + "-" + semester + " Semester)");  // To pop dialog box

                            try {
                                portalActivity.setActionPerformed("Downloaded courseform for " + academicSession.getStartYear() + "/" + academicSession.getEndYear());
                                portalActivity.setDateOfAction(new Date());
                                portalActivity.setDescription("download activity");
                                portalActivity.setStudent(student);
                                portalActivityServiceBean.create(portalActivity);
                                response.getOutputStream().write(stream);
                            } catch (Exception ex) {
                                response.getOutputStream().write(ex.getMessage().getBytes());
                                Logger.getLogger(FileDownloadServlet.class.getName()).log(Level.SEVERE, null, ex);
                            } finally {
                                response.getOutputStream().close();
                            }

                        } else {
                            System.out.println("Stream null");
                        }
                    }
                } else if (action.equalsIgnoreCase("resultsheetformat")) {
                    AcademicSession academicSession = JqueryDataTableParamModel.academicSession;
                    Course course = JqueryDataTableParamModel.course;
                    String departmentName = request.getParameter("departmentName");
                    DepartmentName dn = null;
                    if (academicSession != null && course != null && departmentName != null) {
                        try {
                            dn = departmentServiceBean.findDepartmentName(departmentName);
                            stream = lecturerResultSheetServiceBean.getResultSheetFormatByDept(course.getId(),
                                    academicSession, lecturer.getId(), dn);
                        } catch (Exception ex) {
                            Logger.getLogger(FileDownloadServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if (stream != null && dn != null) {
                            System.out.println("Stream not null");
                            response.setContentType("application/vnd.ms-excel");
                            response.setHeader("Content-disposition", "attachment;filename=Result Sheet Format");  // To pop dialog box
                            try {
                                portalActivity.setActionPerformed("Downloaded result for " + dn.getName() + " department " + academicSession.getStartYear() + "/" + academicSession.getEndYear());
                                portalActivity.setDateOfAction(new Date());
                                portalActivity.setDescription("download activity");
                                portalActivity.setLecturer(lecturer);
                                portalActivityServiceBean.create(portalActivity);
                                response.getOutputStream().write(stream);
                            } catch (Exception ex) {
                                response.getOutputStream().write(ex.getMessage().getBytes());
                                Logger.getLogger(FileDownloadServlet.class.getName()).log(Level.SEVERE, null, ex);
                            } finally {
                                response.getOutputStream().close();
                            }

                        }
                    }
                } else if (action.equalsIgnoreCase("viewresults")) {
                    String academicLevel = request.getParameter("academiclevel");
                    semester = request.getParameter("semester");
                    if (academicLevel != null && semester != null) {
                        AcademicSession academicSession = null;
                        try {
                            academicSession = academicSessionServiceBean.getCurrentAcademicSession();
                            stream = studentResultSheetServiceBean.getStudentResultSheetPDF(student.getId(),
                                    Semester.valueOf(semester), AcademicLevel.valueOf(academicLevel), academicSession, getServletContext());
                        } catch (Exception ex) {
                            Logger.getLogger(FileDownloadServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if (stream != null && academicSession != null) {
                            System.out.println("Stream not null");
                            response.setContentType("application/pdf");
                            response.setHeader("Content-disposition", "attachment;filename= Results for (" + academicSession.getString() + "-" + semester + " Semester)");  // To pop dialog box
                            try {
                                portalActivity.setActionPerformed("Downloaded result for " + academicSession.getStartYear() + "/" + academicSession.getEndYear());
                                portalActivity.setDateOfAction(new Date());
                                portalActivity.setDescription("download activity");
                                portalActivity.setStudent(student);
                                portalActivityServiceBean.create(portalActivity);
                                response.getOutputStream().write(stream);
                            } catch (Exception ex) {
                                response.getOutputStream().write(ex.getMessage().getBytes());
                                Logger.getLogger(FileDownloadServlet.class.getName()).log(Level.SEVERE, null, ex);
                            } finally {
                                response.getOutputStream().close();
                            }

                        } else {
                            System.out.println("Stream null");
                            try {
                                Message message = new Message();
                                message.setMessage("No result found.");
                                PrintWriter out = response.getWriter();
                                String json = new Gson().toJson(message);
                                response.setContentType("application/json");
                                response.setCharacterEncoding("UTF-8");
                                out.write(json);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                } else if (action.equalsIgnoreCase("hodviewresults")) {
                    String academicLevel = request.getParameter("academiclevel");
                    String session = request.getParameter("academicsession");
                    semester = request.getParameter("semester");
                    String courseid = request.getParameter("courseid");
                    if (academicLevel != null && semester != null) {
                        AcademicSession academicSession = null;
                        Course course = null;
                        try {
                            academicSession = academicSessionServiceBean.findAcademicSession(Long.valueOf(session));
                            course = courseServiceBean.findCourse(Long.valueOf(courseid));
                            stream = lecturerResultSheetServiceBean.
                                    getLecturerResultSheetPDF(course, lecturer.getDepartment().getDepartmentName(),
                                    AcademicLevel.valueOf(academicLevel), academicSession, getServletContext());

                        } catch (Exception ex) {
                            Logger.getLogger(FileDownloadServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if (stream != null && academicSession != null && course!=null) {
                            System.out.println("Stream not null");
                            response.setContentType("application/pdf");
                            response.setHeader("Content-disposition", "attachment;filename="+course.getCourseCode()+" Result (" + academicSession.getString() + "-" + semester + " Semester)");  // To pop dialog box
                            try {
                                portalActivity.setActionPerformed("Downloaded result for " + academicSession.getStartYear() + "/" + academicSession.getEndYear());
                                portalActivity.setDateOfAction(new Date());
                                portalActivity.setDescription("download activity");
                                portalActivity.setLecturer(lecturer);
                                portalActivityServiceBean.create(portalActivity);
                                response.getOutputStream().write(stream);
                            } catch (Exception ex) {
                                response.getOutputStream().write(ex.getMessage().getBytes());
                                Logger.getLogger(FileDownloadServlet.class.getName()).log(Level.SEVERE, null, ex);
                            } finally {
                                response.getOutputStream().close();
                            }

                        } else {
                            System.out.println("Stream null");
                            try {
                                lecturerState.setErrorMessage("No result found.");
                                request.setAttribute("parent", "MANAGE_RESULTS");
                                request.getRequestDispatcher("/view").forward(request, response);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }
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
