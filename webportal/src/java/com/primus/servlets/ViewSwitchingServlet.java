/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.servlets;

import com.primus.util.ViewHelper;
import java.io.IOException;
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
public class ViewSwitchingServlet extends HttpServlet {

    @Autowired
    ViewHelper viewHelper;
    
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
        String redirectPage = "/usersarea/login.jsp";
        String userArea = (String) request.getAttribute("UserArea");
        if (userArea != null) {
            if (userArea.equalsIgnoreCase("Admin")) {
                System.out.println("Administrator page loading");
                redirectPage = "/usersarea/admin/adminview.jsp";
            } else if (userArea.equalsIgnoreCase("Lecturer")) {
                redirectPage = "/usersarea/lecturer/lecturerview.jsp";
            } else if (userArea.equalsIgnoreCase("Student")) {
                redirectPage = "/usersarea/student/studentview.jsp";
            }
        } else {
            System.out.println("Userarea null");
        }
        request.getRequestDispatcher(redirectPage).forward(request, response);
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
        System.out.println("View Making in Get");
        Object currentUserState = viewHelper.processState(request, response);
        request.setAttribute("currentUserState", currentUserState);
        viewHelper.processView(request, response);
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
        System.out.println("View Making in Post");
        Object currentUserState = viewHelper.processState(request, response);
        request.setAttribute("currentUserState", currentUserState);
        viewHelper.processView(request, response);
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
