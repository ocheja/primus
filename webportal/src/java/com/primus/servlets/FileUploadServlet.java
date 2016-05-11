/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.servlets;

import com.primus.appstates.AdministratorState;
import com.primus.appstates.LecturerState;
import com.primus.data.Administrator;
import com.primus.data.Lecturer;
import com.primus.data.Student;
import com.primus.enums.AcademicLevel;
import com.primus.interfaces.LecturerResultSheetService;
import com.primus.serviceBean.AcademicSessionServiceBean;
import com.primus.util.ManageUpload;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 *
 * @author Ocheja Patrick Ileanwa
 */
public class FileUploadServlet extends HttpServlet {

    @Autowired
    ManageUpload manageUpload;
    @Autowired
    LecturerResultSheetService lecturerResultSheetServiceBean;
    @Autowired
    AcademicSessionServiceBean academicSessionServiceBean;
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
            out.println("<title>Servlet FileUploadServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FileUploadServlet at " + request.getContextPath() + "</h1>");
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
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        LecturerState lecturerState = LecturerState.getInstance(request);
        String action = null;
        List<?> items;
        boolean uploaded = false;
        System.out.println("step 1");
        if (isMultipart) {
            System.out.println("is multipart");
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            try {
                items = upload.parseRequest(request);

                try {
                    Map<String, Object> processedData = manageUpload.administratorDataExtractor(items);
                    for (Map.Entry<String, Object> entry : processedData.entrySet()) {
                        String key = entry.getKey();
                        Object value = entry.getValue();
                        System.out.println("Key is : " + key);
                        if (key.equalsIgnoreCase("action")) {
                            action = (String) value;
                        }
                    }
                    System.out.println("Action is : " + action);
                    if (action != null) {
                        if (action.equalsIgnoreCase("resultupload")) {
                            byte[] result = (byte[]) processedData.get("resultsheet");
                            String departmentName = (String) processedData.get("departmentname");
                            String academicSessionID = (String) processedData.get("academicsession");
                            String academicLevel = (String) processedData.get("academiclevel");
                            String dateOfExam = (String) processedData.get("dateofexam");
                            if (result != null && departmentName!=null&& 
                                    academicSessionID!=null&academicLevel!=null&&dateOfExam!=null) {
                                Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("GMT+1"));
                                String [] values = dateOfExam.split("-");
                                calendar.set(Calendar.DAY_OF_MONTH, Integer.valueOf(values[0]));
                                calendar.set(Calendar.MONTH, Integer.valueOf(values[1]));
                                calendar.set(Calendar.YEAR, Integer.valueOf(values[2]));
                                lecturerResultSheetServiceBean.uploadResultSheet(result, departmentName,
                                        academicSessionServiceBean.findAcademicSession(Long.valueOf(academicSessionID)),
                                        AcademicLevel.valueOf(academicLevel), calendar.getTime());
                                uploaded = true;
                            }
                        }
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    lecturerState.setErrorMessage(e.getLocalizedMessage());
                    if (action != null && action.equalsIgnoreCase("resultupload")) {
                        request.setAttribute("parent", LecturerState.LecturerParentViews.UPLOAD_RESULTS.name());
                    }
                }
            } catch (FileUploadException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                lecturerState.setErrorMessage(e.getLocalizedMessage());
            }

        } else {
        }
        if (uploaded) {
            lecturerState.setSuccessMessage("ResultSheet successfully uploaded. Awaiting approval.");
        } else {
            lecturerState.setSuccessMessage("ResultSheet upload failed. ");
        }
        try {
            request.getRequestDispatcher("/view").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
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
