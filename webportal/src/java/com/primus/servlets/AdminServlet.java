/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.servlets;

import com.primus.appstates.AdministratorState;
import com.primus.data.Administrator;
import com.primus.data.JqueryDataTableParamModel;
import com.primus.data.Lecturer;
import com.primus.data.Student;
import com.primus.interfaces.LecturerService;
import com.primus.interfaces.StudentService;
import com.primus.serviceBean.AcademicSessionServiceBean;
import com.primus.serviceBean.AdministratorServiceBean;
import com.primus.serviceBean.StudentServiceBean;
import com.primus.util.ExtractFormDataToAdministrator;
import com.primus.util.JqueryDataTableAdminUtil;
import com.primus.util.JqueryDataTableCourseUtil;
import com.primus.util.JqueryDataTableDepartmentUtil;
import com.primus.util.JqueryDataTableFacultyUtil;
import com.primus.util.JqueryDataTableLecturerUtil;
import com.primus.util.JqueryDataTableParamUtility;
import com.primus.util.JqueryDataTableStudentUtil;
import com.primus.util.JqueryDataTableUtilityUtil;
import com.primus.util.ManageUpload;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
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
public class AdminServlet extends HttpServlet {

    @Autowired
    AdministratorServiceBean administratorServiceBean;
    @Autowired
    JqueryDataTableAdminUtil jqueryDataTableAdminUtil;
    @Autowired
    JqueryDataTableParamUtility dataTableParamUtility;
    @Autowired
    JqueryDataTableFacultyUtil jqueryDataTableFacultyUtil;
    @Autowired
    JqueryDataTableDepartmentUtil jqueryDataTableDepartmentUtil;
    @Autowired
    JqueryDataTableCourseUtil jqueryDataTableCourseUtil;
    @Autowired
    ExtractFormDataToAdministrator extractFormDataToAdministrator;
    @Autowired
    LecturerService lecturerServiceBean;
    @Autowired
    JqueryDataTableLecturerUtil jqueryDataTableLecturerUtil;
    @Autowired
    JqueryDataTableStudentUtil jqueryDataTableStudentUtil;
    @Autowired
    StudentService studentServiceBean;
    @Autowired
    JqueryDataTableUtilityUtil jqueryDataTableUtilityUtil;
    @Autowired
    AcademicSessionServiceBean academicSessionServiceBean;
    @Autowired
    ManageUpload manageUpload;

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
        System.out.println("redirecting...");
        String actionPerformed = request.getParameter("actionPerformed");
        if (actionPerformed == null) {
            actionPerformed = (String) request.getAttribute("actionPerformed");
        }
        if (actionPerformed != null && !actionPerformed.isEmpty()) {
            System.out.println("Accesed action not null");
            if (actionPerformed.equalsIgnoreCase("adminViewData")) {
                System.out.println("Accesed action admin data");
                String jSonResponse = (String) request.getAttribute("adminDataResponse");
                try {
                    PrintWriter out = response.getWriter();
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    out.write(jSonResponse);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (actionPerformed.equalsIgnoreCase("adminEditData")) {
                System.out.println("Accesed action admin data edit");
                String value = (String) request.getParameter("value");
                System.out.println("Value is: " + value);
                try {
                    PrintWriter out = response.getWriter();
                    response.setContentType("text/html;charset=UTF-8");
                    out.print(value);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (actionPerformed.equalsIgnoreCase("facultyViewData")) {
                System.out.println("Accesed action faculty view data");
                String jSonResponse = (String) request.getAttribute("adminDataResponse");
                try {
                    PrintWriter out = response.getWriter();
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    out.write(jSonResponse);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (actionPerformed.equalsIgnoreCase("facultyEditData")) {
                System.out.println("Accesed action faculty data edit");
                String value = (String) request.getParameter("value");
                System.out.println("Value is: " + value);
                try {
                    PrintWriter out = response.getWriter();
                    response.setContentType("text/html;charset=UTF-8");
                    out.print(value);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (actionPerformed.equalsIgnoreCase("departmentViewData")) {
                System.out.println("Accesed action department view data");
                String jSonResponse = (String) request.getAttribute("adminDataResponse");
                try {
                    PrintWriter out = response.getWriter();
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    out.write(jSonResponse);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (actionPerformed.equalsIgnoreCase("departmentEditData")) {
                System.out.println("Accesed action department data edit");
                String value = (String) request.getParameter("value");
                System.out.println("Value is: " + value);
                try {
                    PrintWriter out = response.getWriter();
                    response.setContentType("text/html;charset=UTF-8");
                    out.print(value);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (actionPerformed.equalsIgnoreCase("courseViewData")) {
                System.out.println("Accesed action course view data");
                String jSonResponse = (String) request.getAttribute("adminDataResponse");
                try {
                    PrintWriter out = response.getWriter();
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    out.write(jSonResponse);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (actionPerformed.equalsIgnoreCase("courseEditData")) {
                System.out.println("Accesed action course data edit");
                String value = (String) request.getParameter("value");
                System.out.println("Value is: " + value);
                try {
                    PrintWriter out = response.getWriter();
                    response.setContentType("text/html;charset=UTF-8");
                    out.print(value);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (actionPerformed.equalsIgnoreCase("lecturerViewData")) {
                System.out.println("Accesed action lecturer view data");
                String jSonResponse = (String) request.getAttribute("adminDataResponse");
                try {
                    PrintWriter out = response.getWriter();
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    out.write(jSonResponse);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (actionPerformed.equalsIgnoreCase("lecturerEditData")) {
                System.out.println("Accesed action lecturer data edit");
                String value = (String) request.getParameter("value");
                System.out.println("Value is: " + value);
                try {
                    PrintWriter out = response.getWriter();
                    response.setContentType("text/html;charset=UTF-8");
                    out.print(value);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }else if (actionPerformed.equalsIgnoreCase("studentViewData")) {
                System.out.println("Accesed action student view data");
                String jSonResponse = (String) request.getAttribute("adminDataResponse");
                try {
                    PrintWriter out = response.getWriter();
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    out.write(jSonResponse);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (actionPerformed.equalsIgnoreCase("studentEditData")) {
                System.out.println("Accesed action student data edit");
                String value = (String) request.getParameter("value");
                System.out.println("Value is: " + value);
                try {
                    PrintWriter out = response.getWriter();
                    response.setContentType("text/html;charset=UTF-8");
                    out.print(value);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }else if (actionPerformed.equalsIgnoreCase("sessionViewData")) {
                System.out.println("Accesed action sesion view data");
                String jSonResponse = (String) request.getAttribute("adminDataResponse");
                try {
                    PrintWriter out = response.getWriter();
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    out.write(jSonResponse);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (actionPerformed.equalsIgnoreCase("sessionEditData")) {
                System.out.println("Accesed action session data edit");
                String value = (String) request.getParameter("value");
                System.out.println("Value is: " + value);
                try {
                    PrintWriter out = response.getWriter();
                    response.setContentType("text/html;charset=UTF-8");
                    out.print(value);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }else if (actionPerformed.equalsIgnoreCase("DegreeReqViewData")) {
                System.out.println("Accesed action sesion view data");
                String jSonResponse = (String) request.getAttribute("adminDataResponse");
                try {
                    PrintWriter out = response.getWriter();
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    out.write(jSonResponse);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (actionPerformed.equalsIgnoreCase("DegreeEditData")) {
                System.out.println("Accesed action session data edit");
                String value = (String) request.getParameter("value");
                System.out.println("Value is: " + value);
                try {
                    PrintWriter out = response.getWriter();
                    response.setContentType("text/html;charset=UTF-8");
                    out.print(value);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {

                try {
                    request.getRequestDispatcher("/view").forward(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {

            try {
                request.getRequestDispatcher("/view").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
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
        String action = request.getParameter("action");
        if (action != null) {
            String id = request.getParameter("id");
            String columnPosition = request.getParameter("columnPosition");
            String sEcho = request.getParameter("sEcho");
            if (action.equalsIgnoreCase("adminData")) {
                System.out.println("ID: " + id + " COlumn Position: " + columnPosition);
                System.out.println("Admin In: sEcho= " + sEcho);
                if (sEcho != null && !sEcho.equals("")) {
                    System.out.println("Inside Echo");
                    JqueryDataTableParamModel param = dataTableParamUtility.getParam(request);
                    String jSonResponse = jqueryDataTableAdminUtil.processDataTable(param);
                    String actionPerformed = request.getParameter("actionPerformed");
                    if (actionPerformed == null) {
                        request.setAttribute("actionPerformed", "adminViewData");
                    }
                    request.setAttribute("adminDataResponse", jSonResponse);
//            System.out.println("Response: " + jSonResponse);
                }
            } else if (action.equalsIgnoreCase("facultyData")) {
                System.out.println("ID: " + id + " COlumn Position: " + columnPosition);
                System.out.println("Faculty In: sEcho= " + sEcho);
                if (sEcho != null && !sEcho.equals("")) {
                    System.out.println("Inside Echo");
                    JqueryDataTableParamModel param = dataTableParamUtility.getParam(request);
                    String jSonResponse = jqueryDataTableFacultyUtil.processDataTable(param);
                    String actionPerformed = request.getParameter("actionPerformed");
                    if (actionPerformed == null) {
                        request.setAttribute("actionPerformed", "facultyViewData");
                    }
                    request.setAttribute("adminDataResponse", jSonResponse);
                    System.out.println("Response: " + jSonResponse);
                }
            } else if (action.equalsIgnoreCase("departmentData")) {
                System.out.println("ID: " + id + " COlumn Position: " + columnPosition);
                System.out.println("Faculty In: sEcho= " + sEcho);
                if (sEcho != null && !sEcho.equals("")) {
                    System.out.println("Inside Echo");
                    JqueryDataTableParamModel param = dataTableParamUtility.getParam(request);
                    String jSonResponse = jqueryDataTableDepartmentUtil.processDataTable(param);
                    String actionPerformed = request.getParameter("actionPerformed");
                    if (actionPerformed == null) {
                        request.setAttribute("actionPerformed", "departmentViewData");
                    }
                    request.setAttribute("adminDataResponse", jSonResponse);
//            System.out.println("Response: " + jSonResponse);
                }
            } else if (action.equalsIgnoreCase("courseData")) {
                System.out.println("ID: " + id + " COlumn Position: " + columnPosition);
                System.out.println("course In: sEcho= " + sEcho);
                if (sEcho != null && !sEcho.equals("")) {
                    System.out.println("Inside Echo");
                    JqueryDataTableParamModel param = dataTableParamUtility.getParam(request);
                    String jSonResponse = jqueryDataTableCourseUtil.processDataTable(param);
                    String actionPerformed = request.getParameter("actionPerformed");
                    if (actionPerformed == null) {
                        request.setAttribute("actionPerformed", "courseViewData");
                    }
                    request.setAttribute("adminDataResponse", jSonResponse);
                    System.out.println("Response: " + jSonResponse);
                }
            } else if (action.equalsIgnoreCase("lecturerData")) {
                System.out.println("ID: " + id + " COlumn Position: " + columnPosition);
                System.out.println("course In: sEcho= " + sEcho);
                if (sEcho != null && !sEcho.equals("")) {
                    System.out.println("Inside Echo");
                    JqueryDataTableParamModel param = dataTableParamUtility.getParam(request);
                    String jSonResponse = jqueryDataTableLecturerUtil.processDataTable(param);
                    String actionPerformed = request.getParameter("actionPerformed");
                    if (actionPerformed == null) {
                        request.setAttribute("actionPerformed", "lecturerViewData");
                    }
                    request.setAttribute("adminDataResponse", jSonResponse);
                    System.out.println("Response: " + jSonResponse);
                }
            }else if (action.equalsIgnoreCase("studentData")) {
                System.out.println("ID: " + id + " COlumn Position: " + columnPosition);
                System.out.println("course In: sEcho= " + sEcho);
                if (sEcho != null && !sEcho.equals("")) {
                    System.out.println("Inside Echo");
                    JqueryDataTableParamModel param = dataTableParamUtility.getParam(request);
                    String jSonResponse = jqueryDataTableStudentUtil.processDataTable(param);
                    String actionPerformed = request.getParameter("actionPerformed");
                    if (actionPerformed == null) {
                        request.setAttribute("actionPerformed", "studentViewData");
                    }
                    request.setAttribute("adminDataResponse", jSonResponse);
                    System.out.println("Response: " + jSonResponse);
                }
            }else if (action.equalsIgnoreCase("sessionData")) {
                System.out.println("ID: " + id + " COlumn Position: " + columnPosition);
                System.out.println("course In: sEcho= " + sEcho);
                if (sEcho != null && !sEcho.equals("")) {
                    System.out.println("Inside Echo");
                    JqueryDataTableParamModel param = dataTableParamUtility.getParam(request);
                    String jSonResponse = jqueryDataTableUtilityUtil.processSessionDataTable(param);
                    String actionPerformed = request.getParameter("actionPerformed");
                    if (actionPerformed == null) {
                        request.setAttribute("actionPerformed", "sessionViewData");
                    }
                    request.setAttribute("adminDataResponse", jSonResponse);
                    System.out.println("Response: " + jSonResponse);
                }
            }else if (action.equalsIgnoreCase("DegreeReqData")) {
                System.out.println("ID: " + id + " COlumn Position: " + columnPosition);
                System.out.println("course In: sEcho= " + sEcho);
                if (sEcho != null && !sEcho.equals("")) {
                    System.out.println("Inside Echo");
                    JqueryDataTableParamModel param = dataTableParamUtility.getParam(request);
                    String jSonResponse = jqueryDataTableUtilityUtil.processDataTable(param);
                    String actionPerformed = request.getParameter("actionPerformed");
                    if (actionPerformed == null) {
                        request.setAttribute("actionPerformed", "DegreeReqViewData");
                    }
                    request.setAttribute("adminDataResponse", jSonResponse);
                    System.out.println("Response: " + jSonResponse);
                }
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
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        AdministratorState administratorState = AdministratorState.getInstance(request);
        String action = null;
        List<?> items;
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
                        if (action.equalsIgnoreCase("registerNewAdmin")) {
                            Administrator administrator = extractFormDataToAdministrator.adminFormFieldData(processedData);
                            administratorServiceBean.create(administrator);
                            System.out.println("created");
                            administratorState.setSuccessMessage("Administrator created successfully");
                            HttpSession session = request.getSession();
                            session.setAttribute(AdministratorState.class.getName(), administratorState);
                        } else if (action.equalsIgnoreCase("registerNewLecturer")) {
                            Lecturer lecturer = extractFormDataToAdministrator.lecturerFormormFieldData(processedData);
                            lecturerServiceBean.create(lecturer);
                            System.out.println("created");
                            administratorState.setSuccessMessage("Lecturer created successfully");
                            request.setAttribute("parent", AdministratorState.AdminParentViews.LECTURER_MANAGER.name());
                            request.setAttribute("node", AdministratorState.AdminNodeViews.CREATE_LECTURER.name());
                            HttpSession session = request.getSession();
                            session.setAttribute(AdministratorState.class.getName(), administratorState);
                        } else if (action.equalsIgnoreCase("registerNewStudent")) {
                            Student student = extractFormDataToAdministrator.studentFormormFieldData(processedData);
                            studentServiceBean.register(student);
                            System.out.println("created");
                            administratorState.setSuccessMessage("Student created successfully");
                            request.setAttribute("parent", AdministratorState.AdminParentViews.STUDENT_MANAGER.name());
                            request.setAttribute("node", AdministratorState.AdminNodeViews.CREATE_STUDENT.name());
                            HttpSession session = request.getSession();
                            session.setAttribute(AdministratorState.class.getName(), administratorState);
                        }
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    administratorState.setErrorMessage(e.getLocalizedMessage());
                    if (action != null && action.equalsIgnoreCase("registerNewStudent")) {
                        request.setAttribute("parent", AdministratorState.AdminParentViews.STUDENT_MANAGER.name());
                        request.setAttribute("node", AdministratorState.AdminNodeViews.CREATE_STUDENT.name());
                    }
                    if (action != null && action.equalsIgnoreCase("registerNewLecturer")) {
                        request.setAttribute("parent", AdministratorState.AdminParentViews.LECTURER_MANAGER.name());
                        request.setAttribute("node", AdministratorState.AdminNodeViews.CREATE_LECTURER.name());
                    }
                }
            } catch (FileUploadException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                administratorState.setErrorMessage(e.getLocalizedMessage());
            }

        } else {
        }
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
