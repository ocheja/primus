/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.servlets;

import com.primus.data.Administrator;
import com.primus.data.Lecturer;
import com.primus.data.Student;
import com.primus.interfaces.LecturerService;
import com.primus.interfaces.StudentService;
import com.primus.service.exceptions.PrimusServiceException;
import com.primus.serviceBean.AdministratorServiceBean;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 *
 * @author Ocheja Patrick Ileanwa
 */
@WebServlet("/imagedisp/imagecontroller")
public class DatatableImageServlet extends HttpServlet {

    @Autowired
    AdministratorServiceBean administratorServiceBean;
    @Autowired
    LecturerService lecturerServiceBean;
    @Autowired
    StudentService studentServiceBean;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String imageId = request.getParameter("id");
        String who = request.getParameter("who");
        if (who != null) {
            if (who.equalsIgnoreCase("admin")) {
                Administrator administrator;
                if (imageId != null) {
                    try {
                        administrator = administratorServiceBean.findAdministrator(Long.valueOf(imageId));
                        if (administrator == null) {

                            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
                            System.out.println("Admin not found");
                            return;
                        }

                    } catch (NumberFormatException | PrimusServiceException | IOException ex) {
                        Logger.getLogger(DatatableImageServlet.class.getName()).log(Level.SEVERE, null, ex);
                        response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
                        return;
                    }

                } else {
                    System.out.println("Image iD not found");
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }

                BufferedOutputStream output = null;


                try {
                    output = new BufferedOutputStream(response.getOutputStream());

                    // Write file contents to response.
                    int length;
                    byte[] buffer = administrator.getImage();
                    output.write(buffer);
                } finally {
                    // Gently close streams.
                    close(output);
                }
            } else if (who.equalsIgnoreCase("lecturer")) {
                Lecturer lecturer = null;
                if (imageId != null) {
                    try {
                        lecturer = lecturerServiceBean.findLecturer(Long.valueOf(imageId));
                        if (lecturer == null) {

                            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
                            System.out.println("Admin not found");
                            return;
                        }

                    } catch (NumberFormatException | PrimusServiceException | IOException ex) {
                        Logger.getLogger(DatatableImageServlet.class.getName()).log(Level.SEVERE, null, ex);
                        response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
                        return;
                    } catch (Exception ex) {
                        Logger.getLogger(DatatableImageServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    System.out.println("Image iD not found");
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }

                BufferedOutputStream output = null;


                try {
                    output = new BufferedOutputStream(response.getOutputStream());

                    // Write file contents to response.
                    int length;
                    byte[] buffer = lecturer.getImage();
                    output.write(buffer);
                } finally {
                    // Gently close streams.
                    close(output);
                }
            } else if (who.equalsIgnoreCase("student")) {
                Student student = null;
                if (imageId != null) {
                    try {
                        student = studentServiceBean.findActiveStudent(Long.valueOf(imageId));
                        if (student == null) {

                            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
                            System.out.println("Student not found");
                            return;
                        }

                    } catch (NumberFormatException | PrimusServiceException | IOException ex) {
                        Logger.getLogger(DatatableImageServlet.class.getName()).log(Level.SEVERE, null, ex);
                        response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
                        return;
                    } catch (Exception ex) {
                        Logger.getLogger(DatatableImageServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    System.out.println("Image iD not found");
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }

                BufferedOutputStream output = null;


                try {
                    output = new BufferedOutputStream(response.getOutputStream());

                    // Write file contents to response.
                    int length;
                    byte[] buffer = student.getStudentImage();
                    output.write(buffer);
                } finally {
                    // Gently close streams.
                    close(output);
                }
            }
        }
    }

    private void close(Closeable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (IOException e) {
                // Do your thing with the exception. Print it, log it or mail it.
                e.printStackTrace();
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
