/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.servlets;

import com.primus.appstates.AdministratorState;
import com.primus.appstates.LecturerState;
import com.primus.appstates.StudentState;
import com.primus.util.ViewHelper;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 *
 * @author Ocheja Patrick Ileanwa
 */
public class LogoutServlet extends HttpServlet {

    @Autowired
    ViewHelper viewHelper;

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
        request.getRequestDispatcher("/index.jsp").forward(request, response);
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
        Object currentState = viewHelper.processState(request, response);
        HttpSession httpSession = request.getSession();
        if (currentState != null) {
            if (currentState instanceof AdministratorState) {
                AdministratorState administratorState = (AdministratorState) currentState;
                administratorState.setCurrentAdministrator(null);
                administratorState.setSignedIn(false);
                httpSession.setAttribute(AdministratorState.class.getName(), administratorState);
            } else if (currentState instanceof LecturerState) {
                LecturerState lecturerState = (LecturerState) currentState;
                lecturerState.setCurrentLecturer(null);
                lecturerState.setSignedIn(false);
                httpSession.setAttribute(LecturerState.class.getName(), lecturerState);
            } else if (currentState instanceof StudentState) {
                StudentState studentState = (StudentState) currentState;
                studentState.setCurrentStudent(null);
                studentState.setSignedIn(false);
                httpSession.setAttribute(StudentState.class.getName(), studentState);
            } else {
            }
        }
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

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
}
