/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.servlets;

import com.google.gson.Gson;
import com.primus.data.Course;
import com.primus.data.CourseForm;
import com.primus.data.Degree;
import com.primus.data.Lecturer;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.primus.data.StatusMessage;
import com.primus.enums.AcademicLevel;
import com.primus.interfaces.CourseService;
import com.primus.interfaces.DegreeService;
import com.primus.interfaces.LecturerService;
import com.primus.serviceBean.CourseFormServiceBean;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 *
 * @author Ocheja Patrick Ileanwa
 */
public class PullData extends HttpServlet {

    @Autowired
    CourseService courseServiceBean;
    @Autowired
    LecturerService lecturerServiceBean;
    @Autowired
    DegreeService degreeServiceBean;
    @Autowired
    CourseFormServiceBean courseFormServiceBean;

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
            out.println("<title>Servlet PullData</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PullData at " + request.getContextPath() + "</h1>");
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
        StatusMessage sm = new StatusMessage();
        String html = null;

        if (action != null) {
            if (action.equalsIgnoreCase("pullprecourses")) {
                String id = request.getParameter("id");
                if (id != null) {
                    try {
                        Course course = courseServiceBean.findCourse(Long.valueOf(id));
                        if (course != null) {
                            List courses = courseServiceBean
                                    .getPrerequisites(Long.valueOf(id));
                            request.setAttribute("prerequisiteCourses", courses);
                            request.setAttribute("owningCourse", course);
                            html = "/usersarea/admin/prerequisitecourses.jsp";
                            sm.setMessage(null);
                        }else
                            sm.setMessage("Course is null");
                    } catch (Exception ex) {
                        Logger.getLogger(PullData.class.getName()).log(Level.SEVERE, null, ex);
                        sm.setMessage("Error Occurred--" + ex.getLocalizedMessage());
                    }
                } else {
                    sm.setMessage("No prerequisites for this course");
                }
            } else if (action.equalsIgnoreCase("pulllecturercourses")) {
                String id = request.getParameter("id");
                if (id != null) {
                    try {
                        List<Course> courses = courseServiceBean.getCoursesAssignedToLecturer(Long.valueOf(id), null);
                        Lecturer lecturer = lecturerServiceBean.findLecturer(Long.valueOf(id));
                            request.setAttribute("lecturer", lecturer);
                        if (courses != null) {
                            request.setAttribute("courses", courses);
                            html = "/usersarea/admin/lecturercourses.jsp";
                            sm.setMessage(null);
                        }else
                            sm.setMessage("Lecturer is null");
                    } catch (Exception ex) {
                        Logger.getLogger(PullData.class.getName()).log(Level.SEVERE, null, ex);
                        sm.setMessage("Error Occurred--" + ex.getLocalizedMessage());
                    }
                } else {
                    sm.setMessage("No course for this lecturer");
                }
            } else if (action.equalsIgnoreCase("pullcoursesfordegreereq")) {
                String id = request.getParameter("id");
                if (id != null) {
                    try {
                        Degree degree = degreeServiceBean.findDegree(Long.valueOf(id));
                        if (degree != null) {
                            System.out.println("Size of degree requirments---"+degree.getDegreeRequirement().size());
                            request.setAttribute("degree", degree);
                            html = "/usersarea/admin/degreecourses.jsp";
                            sm.setMessage(null);
                        }else
                            sm.setMessage("Degree is null");
                    } catch (Exception ex) {
                        Logger.getLogger(PullData.class.getName()).log(Level.SEVERE, null, ex);
                        sm.setMessage("Error Occurred--" + ex.getLocalizedMessage());
                    }
                } else {
                    sm.setMessage("No course for this degree");
                }
            } else if (action.equalsIgnoreCase("pulllecturerpositions")) {
                String id = request.getParameter("id");
                if (id != null) {
                    try {
                        Lecturer lecturer = lecturerServiceBean.findLecturer(Long.valueOf(id));
                        if (lecturer != null) {
                            request.setAttribute("lecturer", lecturer);
                            html = "/usersarea/admin/lecturerpositions.jsp";
                            sm.setMessage(null);
                        }else
                            sm.setMessage("Lecturer is null");
                    } catch (Exception ex) {
                        Logger.getLogger(PullData.class.getName()).log(Level.SEVERE, null, ex);
                        sm.setMessage("Error Occurred--" + ex.getLocalizedMessage());
                    }
                } else {
                    sm.setMessage("No course for this lecturer");
                }
            }if (action.equalsIgnoreCase("gradeSchemeFields")) {
                String numberofletters = request.getParameter("numberOfGradeLetters");
                if (numberofletters != null) {
                    try {
                        request.setAttribute("numberofletters", Integer.valueOf(numberofletters));
                            html = "/usersarea/admin/gradeschemefields.jsp";
                            sm.setMessage(null);
                    } catch (Exception ex) {
                        Logger.getLogger(PullData.class.getName()).log(Level.SEVERE, null, ex);
                        sm.setMessage("Error Occurred--" + ex.getLocalizedMessage());
                    }
                } else {
                    sm.setMessage("No prerequisites for this course");
                }
            }if (action.equalsIgnoreCase("levelFields")) {
                String numberOfLevels = request.getParameter("numberOfLevels");
                if (numberOfLevels != null) {
                    try {
                        request.setAttribute("numberOfLevels", Integer.valueOf(numberOfLevels));
                        request.setAttribute("courses", courseServiceBean.findCourseEntities());
                        request.setAttribute("levels", AcademicLevel.values());
                            html = "/usersarea/admin/degreerequirementlevels.jsp";
                            sm.setMessage(null);
                    } catch (Exception ex) {
                        Logger.getLogger(PullData.class.getName()).log(Level.SEVERE, null, ex);
                        sm.setMessage("Error Occurred--" + ex.getLocalizedMessage());
                    }
                } else {
                    sm.setMessage("No prerequisites for this course");
                }
            }
            else if (action.equalsIgnoreCase("pullstudentcourses")) {
                String id = request.getParameter("id");
                if (id != null) {
                    try {
                        CourseForm courseForm = courseFormServiceBean.findCourseForm(Long.valueOf(id));
                        if (courseForm != null) {
                            request.setAttribute("courseForm", courseForm);
                            html = "/usersarea/lecturer/studentcourses.jsp";
                            sm.setMessage(null);
                        }else
                            sm.setMessage("Course Form is null");
                    } catch (Exception ex) {
                        Logger.getLogger(PullData.class.getName()).log(Level.SEVERE, null, ex);
                        sm.setMessage("Error Occurred--" + ex.getLocalizedMessage());
                    }
                } else {
                    sm.setMessage("No course presented by this student.");
                }
            }
        } else {
            sm.setMessage("Action not explicitly specified.");
        }

        if (sm.getMessage() != null) {
            try {
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                String jsonResp = new Gson().toJson(sm);
                PrintWriter out = response.getWriter();
                out.write(jsonResp);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                response.setContentType("text/html");
                request.getRequestDispatcher(html).include(
                        request, response);
            } catch (Exception ex) {
                ex.printStackTrace();
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
        processRequest(request, response);
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
