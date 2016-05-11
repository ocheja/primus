/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.servlets;

import com.google.gson.Gson;
import com.primus.data.Lecturer;
import com.primus.gsondata.DesktopClientMessage;
import com.primus.gsondata.GsonLecturer;
import com.primus.interfaces.LecturerService;
import com.primus.enums.Action;
import com.primus.enums.LectureHall;
import com.primus.gsondata.GsonLecture;
import com.primus.gsondata.LectureList;
import com.primus.interfaces.TimetableService;
import com.primus.serviceBean.AcademicSessionServiceBean;
import com.primus.serviceBean.DepartmentServiceBean;
import com.primus.serviceBean.LectureServiceBean;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
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
public class DesktopClientServlet extends HttpServlet {

    @Autowired
    LecturerService lecturerServiceBean;
    @Autowired
    DepartmentServiceBean departmentServiceBean;
    @Autowired
    LectureServiceBean lectureServiceBean;
    @Autowired
    TimetableService  timetableServiceBean ;
    @Autowired
    AcademicSessionServiceBean AcademicSessionService;

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
        String action = request.getParameter("action");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();

        BufferedReader br = new BufferedReader(request.getReader());
        String json = "";
        if (br != null) {
            json += br.readLine();
        }
        if (action.equalsIgnoreCase(Action.lectureNotification.name())) {
            DesktopClientMessage clientMessage = new DesktopClientMessage();
            Lecturer lecturer;
            try {
                clientMessage = gson.fromJson(json, DesktopClientMessage.class);
                lecturer = lecturerServiceBean.findLecturerByFingerPrintId(clientMessage.getLecturerFingerPrintId(), departmentServiceBean.findDepartmentName(clientMessage.getDepartmentName()));
                lectureServiceBean.merge(lecturer.getId(), LectureHall.valueOf(clientMessage.getLocation()));
                clientMessage.setAction("Successful");
                out.println(gson.toJson(clientMessage));
            } catch (Exception e) {
                clientMessage.setAction("Unsuccessful");
                out.println(gson.toJson(clientMessage));
            } finally {
                out.close();
            }
        } else if (action.equalsIgnoreCase(Action.getLecturesForDay.name())) {
           GsonLecture lecture;
            LectureList lectureList= new LectureList();
            try {
                lecture = gson.fromJson(json, GsonLecture.class);
                       lectureList.setLectures( timetableServiceBean.getLecturesForDay(lecture.getDepartmentName(),AcademicSessionService.getCurrentAcademicSession(), lecture.getDayOfweek()));
                       lectureList.setAction("Successful");
               lectureList.setDayOfWeek(lecture.getDayOfweek());
                out.println(gson.toJson( lectureList));
            } catch (Exception e) {
                lectureList.setAction("Unsuccessful");
                out.println(gson.toJson(lectureList));
            } finally {
                out.close();
            }
        } else if (action.equalsIgnoreCase(Action.getLecturer.name())) {
            GsonLecturer Gslecturer = gson.fromJson(json, GsonLecturer.class);
            Lecturer lecturer= new Lecturer();
            GsonLecturer Glecturer= new GsonLecturer() ;
            try {
                lecturer = lecturerServiceBean.wildSearch("%" + Gslecturer.getEmailAddress() + "%").get(0);
            } catch (Exception ex) {
                Logger.getLogger(DesktopClientServlet.class.getName()).log(Level.SEVERE, null, ex);
                Glecturer.setAction("UnSuccessful");
                try {
                    out.println(gson.toJson(Glecturer));
                } finally {
                    out.close();
                }
            }
            Glecturer.setDepartmentName(lecturer.getDepartment().getDepartmentName().getName());
            Glecturer.setName(lecturer.getLecturerName().getSurname() + " " + lecturer.getLecturerName().getFirstName() + " " + lecturer.getLecturerName().getMiddleName() + " ");
            Glecturer.setLecturerID(Long.toString(lecturer.getId()));
            Glecturer.setFingerPrintID(Long.toString(lecturer.getFingerPrintId()));
            Glecturer.setEmailAddress(lecturer.getEmail());
            Glecturer.setAction("Successful");
            try {
                out.println(gson.toJson(Glecturer));
            } finally {
                out.close();
            }
        } else if (action.equalsIgnoreCase(Action.editLecturer.name())) {
            GsonLecturer Glecturer = gson.fromJson(json, GsonLecturer.class);
            Lecturer lecturer;
            try {
                lecturer = lecturerServiceBean.findLecturer(Long.parseLong(Glecturer.getLecturerID()));
                lecturer.setFingerPrintId(Long.parseLong(Glecturer.getFingerPrintID()));
                lecturerServiceBean.edit(lecturer);
            } catch (Exception ex) {
                Logger.getLogger(DesktopClientServlet.class.getName()).log(Level.SEVERE, null, ex);
                Glecturer.setAction("UnSuccessful");
                try {
                    out.println(gson.toJson(Glecturer));
                } finally {
                    out.close();
                }
            }
            Glecturer.setAction("Successful");
            try {
                out.println(gson.toJson(Glecturer));
            } finally {
                out.close();
            }

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
