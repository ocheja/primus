/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.controller;

import com.primus.data.LecturerResultGrade;
import com.primus.data.ResultSheet;
import com.primus.interfaces.CourseRegistrationService;
import com.primus.interfaces.CourseService;
import com.primus.interfaces.LecturerResultSheetService;
import com.primus.interfaces.StudentResultSheetService;
import com.primus.interfaces.StudentService;
import com.primus.serviceBean.AcademicSessionServiceBean;
import com.primus.serviceBean.AdministratorServiceBean;
import com.primus.serviceBean.CourseFormServiceBean;
import com.primus.serviceBean.DepartmentServiceBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
public class test extends HttpServlet {

    @Autowired
    CourseService courseServiceBean;
    @Autowired
    CourseRegistrationService  courseRegistrationServiceBean;
     @Autowired
     AcademicSessionServiceBean academicSessionServiceBean;
    @Autowired
    DepartmentServiceBean departmentServiceBean;
    @Autowired
     AdministratorServiceBean  administratorServiceBean ;
    @Autowired
    StudentService studentServiceBean;
    @Autowired
    StudentResultSheetService studentResultSheetServiceBean;
    @Autowired
    CourseFormServiceBean courseFormServiceBean;
     @Autowired
    LecturerResultSheetService lecturerResultSheetServiceBean;

   
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
            throws ServletException, IOException, Exception {
      response.setContentType("text/html;charset=UTF-8");
        String errorMeg = null;
        PrintWriter out = response.getWriter();
       ResultSheet resultSheet = lecturerResultSheetServiceBean.findResultSheet(1l);
     List<LecturerResultGrade> grade =  resultSheet.getLecturerResultGrade();
      
        try {
 //    List<Course> courses=courseRegistrationServiceBean.getRequiredCourses(5l, Semester.FIRST);
//   CourseForm courseForm = courseFormServiceBean.findCourseForm(5l, academicSessionServiceBean.getCurrentAcademicSession(),Semester.FIRST);
//          courseForm.setAcademicAdviserDateStamp(new Date());
//          courseForm.setFacultyOfficerDateStamp(new Date());
//          courseForm.setHeadOfDepartmentDateStamp(new Date());
//          courseFormServiceBean.edit(courseForm);
//          
// courses.isEmpty();
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet test</title>");
            out.println("</head>");
            out.println("<body>");
         /// out.println("<h1>Servlet test at "+ courses+" the course with code : </h1>");
            grade.isEmpty();
            out.println("<h1>ERROR MESSAGE:  " +  grade+ "</h1>");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception ex) {
                Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
            }finally {
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
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
