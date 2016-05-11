/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.controller;

import com.primus.enums.AcademicLevel;
import com.primus.enums.Semester;
import com.primus.interfaces.CourseRegistrationService;
import com.primus.interfaces.CourseService;
import com.primus.interfaces.LecturerResultSheetService;
import com.primus.interfaces.StudentResultSheetService;
import com.primus.serviceBean.AcademicSessionServiceBean;
import com.primus.serviceBean.DepartmentServiceBean;
import java.io.IOException;
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
 * @author Olisa
 */
public class report extends HttpServlet {

    @Autowired
    CourseRegistrationService courseRegistrationServiceBean;
    @Autowired
    LecturerResultSheetService lecturerResultSheetServiceBean;
    @Autowired
    AcademicSessionServiceBean academicSessionServiceBean;
     @Autowired
    StudentResultSheetService studentResultSheetServiceBean;
      @Autowired
     CourseService courseServiceBean;
       @Autowired
      DepartmentServiceBean departmentServiceBean;
    private org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(test.class);

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
       //  response.setContentType("application/vnd.ms-excel");
       //  response.setContentType("text/html;charset=UTF-8");
         response.setContentType("application/pdf");
        //PrintWriter out = response.getWriter();
       response.setHeader("Content-disposition", "attachment;filename=ResultSheet");  // To pop dialog box


        try {
           // courseRegistrationServiceBean.registerCourses(1l, Semester.FIRST, academicSessionServiceBean.getCurrentAcademicSession(), courseRegistrationServiceBean.getRequiredCourses(1l, Semester.FIRST));
            response.getOutputStream().write(lecturerResultSheetServiceBean.getLecturerResultSheetPDF(courseServiceBean.findCourse(1l),departmentServiceBean.findDepartmentName(12l), AcademicLevel.l100, academicSessionServiceBean.getCurrentAcademicSession(), request.getServletContext()));
           // response.getOutputStream().write(lecturerResultSheetServiceBean.getResultSheetFormat(1l, academicSessionServiceBean.getCurrentAcademicSession(), 1l));
        } catch (Exception ex) {
            response.getOutputStream().write(ex.getMessage().getBytes());
            Logger.getLogger(report.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            response.getOutputStream().close();
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
        processRequest(request, response);
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
