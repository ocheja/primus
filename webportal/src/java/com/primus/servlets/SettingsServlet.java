/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.servlets;

import com.google.gson.Gson;
import com.primus.appstates.AdministratorState;
import com.primus.appstates.LecturerState;
import com.primus.appstates.StudentState;
import com.primus.data.Administrator;
import com.primus.data.Lecturer;
import com.primus.data.Message;
import com.primus.data.PortalActivity;
import com.primus.data.Student;
import com.primus.interfaces.LecturerService;
import com.primus.interfaces.StudentService;
import com.primus.service.exceptions.NonexistentEntityException;
import com.primus.service.exceptions.RollbackFailureException;
import com.primus.serviceBean.AdministratorServiceBean;
import com.primus.serviceBean.PortalActivityServiceBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
public class SettingsServlet extends HttpServlet {

    @Autowired
    AdministratorServiceBean administratorServiceBean;
    @Autowired
    LecturerService lecturerServiceBean;
    @Autowired
    StudentService studentServiceBean;
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
            out.println("<title>Servlet SettingsServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SettingsServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("/view").forward(request, response);
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
        String user = request.getParameter("user");
        Message message = new Message();
        PortalActivity portalActivity = new PortalActivity();
        System.out.println("Hitting here");
        if (user != null) {
            if (user.equalsIgnoreCase("administrator")) {
                Administrator administrator = AdministratorState.getInstance(request).getCurrentAdministrator();
                if (administrator == null) {
                    request.getRequestDispatcher("/view").forward(request, response);
                } else {
                    String oldPassword = request.getParameter("oldpassword");
                    String newPassword = request.getParameter("newpassword");
                    String renewPassword = request.getParameter("renewpassword");
                    if (oldPassword == null || newPassword == null || renewPassword == null) {
                        message.setMessage("All fields must be completely filled.");
                    } else {
                        if (newPassword.equals(renewPassword)) {
                            int currentPassword = administrator.getPassword();
                            int inputCurrentPassword = oldPassword.hashCode();
                            if (currentPassword == inputCurrentPassword) {
                                administrator.setPassword(newPassword.hashCode());
                                try {
                                    administratorServiceBean.edit(administrator);
                                    AdministratorState.getInstance(request).setCurrentAdministrator(administrator);
                            portalActivity.setActionPerformed("Changed Password.");
                            portalActivity.setDateOfAction(new Date());
                            portalActivity.setDescription("Password change activity");
                            portalActivity.setAdministrator(administrator);
                            portalActivityServiceBean.create(portalActivity);
                                    message.setMessage("Password changed successfully.");
                                } catch (Exception ex) {
                                    message.setMessage("Fatal error. Please try again.");
                                    Logger.getLogger(SettingsServlet.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else {
                                message.setMessage("Wrong password.");
                            }
                        } else {
                            message.setMessage("The new password not correctly confirmed.");
                        }
                    }

                    try {
                        PrintWriter out = response.getWriter();
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        out.write(new Gson().toJson(message));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else if (user.equalsIgnoreCase("lecturer")) {
                Lecturer lecturer = LecturerState.getInstance(request).getCurrentLecturer();
                System.out.println("Hitting here222");
                if (lecturer == null) {
                    request.getRequestDispatcher("/view").forward(request, response);
                } else {
                    String oldPassword = request.getParameter("oldpassword");
                    String newPassword = request.getParameter("newpassword");
                    String renewPassword = request.getParameter("renewpassword");
                    if (oldPassword == null || newPassword == null || renewPassword == null) {
                        message.setMessage("All fields must be completely filled.");
                        System.out.println("Hitting here3333");
                    } else {
                        System.out.println("Hitting here444444444444");
                        if (newPassword.equals(renewPassword)) {
                            int currentPassword = lecturer.getPassword();
                            int inputCurrentPassword = oldPassword.hashCode();
                            if (currentPassword == inputCurrentPassword) {
                                lecturer.setPassword(newPassword.hashCode());
                                try {
                                    lecturerServiceBean.edit(lecturer);
                                    LecturerState.getInstance(request).setCurrentLecturer(lecturer);
                            portalActivity.setActionPerformed("Changed Password.");
                            portalActivity.setDateOfAction(new Date());
                            portalActivity.setDescription("Password change activity");
                            portalActivity.setLecturer(lecturer);
                            portalActivityServiceBean.create(portalActivity);
                                    message.setMessage("Password changed successfully.");
                                } catch (Exception ex) {
                                    message.setMessage("Fatal error. Please try again.");
                                    Logger.getLogger(SettingsServlet.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else {
                                message.setMessage("Wrong password.");
                            }
                        } else {
                            message.setMessage("The new password not correctly confirmed.");
                        }
                    }

                    try {
                        PrintWriter out = response.getWriter();
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        out.write(new Gson().toJson(message));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else if (user.equalsIgnoreCase("student")) {
                Student student = StudentState.getInstance(request).getCurrentStudent();
                if (student == null) {
                    request.getRequestDispatcher("/view").forward(request, response);
                } else {
                    String oldPassword = request.getParameter("oldpassword");
                    String newPassword = request.getParameter("newpassword");
                    String renewPassword = request.getParameter("renewpassword");
                    if (oldPassword == null || newPassword == null || renewPassword == null) {
                        message.setMessage("All fields must be completely filled.");
                    } else {
                        if (newPassword.equals(renewPassword)) {
                            int currentPassword = student.getPassword();
                            int inputCurrentPassword = oldPassword.hashCode();
                            if (currentPassword == inputCurrentPassword) {
                                student.setPassword(newPassword.hashCode());
                                try {
                                    studentServiceBean.register(student);
                                    StudentState.getInstance(request).setCurrentStudent(student);
                            portalActivity.setActionPerformed("Changed Password.");
                            portalActivity.setDateOfAction(new Date());
                            portalActivity.setDescription("Password change activity");
                            portalActivity.setStudent(student);
                            portalActivityServiceBean.create(portalActivity);
                                    message.setMessage("Password changed successfully.");
                                } catch (Exception ex) {
                                    message.setMessage("Fatal error. Please try again.");
                                    Logger.getLogger(SettingsServlet.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else {
                                message.setMessage("Wrong password.");
                            }
                        } else {
                            message.setMessage("The new password not correctly confirmed.");
                        }
                    }

                    try {
                        PrintWriter out = response.getWriter();
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        out.write(new Gson().toJson(message));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                request.getRequestDispatcher("/view").forward(request, response);
            }
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
