/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.servlets;

import com.primus.data.Administrator;
import com.primus.data.Department;
import com.primus.data.DepartmentName;
import com.primus.data.Faculty;
import com.primus.data.FacultyName;
import com.primus.enums.AdminType;
import com.primus.service.exceptions.NonexistentEntityException;
import com.primus.service.exceptions.PrimusServiceException;
import com.primus.service.exceptions.RollbackFailureException;
import com.primus.serviceBean.AdministratorServiceBean;
import com.primus.serviceBean.DepartmentServiceBean;
import com.primus.serviceBean.FacultyServiceBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 *
 * @author Ocheja Patrick Ileanwa
 */
@WebServlet("/admin/update")
public class UpdateAdminData extends HttpServlet {

    @Autowired
    AdministratorServiceBean administratorServiceBean;
    @Autowired
    FacultyServiceBean facultyServiceBean;
    @Autowired
    DepartmentServiceBean departmentServiceBean;

     @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String value = request.getParameter("value");
        String id = request.getParameter("id");
        String columnPosition = request.getParameter("columnPosition");
        String actionPerformed = request.getParameter("actionPerformed");
        if (actionPerformed != null) {
            System.out.println("ID: " + id);
            System.out.println("Value is: " + value);
            if (actionPerformed.equalsIgnoreCase("adminDataEdit")) {
                if (id != null) {
                    Administrator administrator = null;
                    try {
                        administrator = administratorServiceBean.findAdministrator(Long.valueOf(id));
                    } catch (PrimusServiceException ex) {
                        Logger.getLogger(UpdateAdminData.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (administrator != null) {
                        System.out.println("Admin found");
                        String adminID = String.valueOf(administrator.getId());
                        if (columnPosition.equalsIgnoreCase("1")) {
                            administrator.setFirstName(value);
                        } else if (columnPosition.equalsIgnoreCase("2")) {
                            administrator.setLastName(value);
                        } else if (columnPosition.equalsIgnoreCase("3")) {
                            administrator.setMiddleName(value);
                        } else if (columnPosition.equalsIgnoreCase("4")) {
                            administrator.setPhoneNumber(value);
                        } else if (columnPosition.equalsIgnoreCase("5")) {
                            administrator.setEmailAddress(value);
                        } else if (columnPosition.equalsIgnoreCase("6")) {
                            administrator.setAdminType(AdminType.valueOf(value));
                        }
                        try {
                            administratorServiceBean.edit(administrator);
                        } catch (NonexistentEntityException ex) {
                            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (RollbackFailureException ex) {
                            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (Exception ex) {
                            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.out.println("Column Position: " + columnPosition);
                        System.out.println("Accesed action admin data edit");
                        System.out.println("Value is: " + value);
                        try {
                            PrintWriter out = response.getWriter();
                            response.setContentType("text/html;charset=UTF-8");
                            out.print(value);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            } else if (actionPerformed.equalsIgnoreCase("facultyDataEdit")) {
                if (id != null) {
                    Faculty faculty = null;
                    try {
                        faculty = facultyServiceBean.findFaculty(Long.valueOf(id));
                    } catch (Exception ex) {
                        Logger.getLogger(UpdateAdminData.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (faculty != null) {
                        System.out.println("Faculty found");
                        String adminID = String.valueOf(faculty.getId());
                        if (columnPosition.equalsIgnoreCase("1")) {
                            faculty.setFacultyName(new FacultyName(value));
                        } else if (columnPosition.equalsIgnoreCase("2")) {
                            faculty.setDescription(value);
                        }
                        try {
                            facultyServiceBean.edit(faculty);
                        } catch (NonexistentEntityException ex) {
                            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (RollbackFailureException ex) {
                            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (Exception ex) {
                            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.out.println("Column Position: " + columnPosition);
                        System.out.println("Accesed action faculty data edit");
                        System.out.println("Value is: " + value);
                        try {
                            PrintWriter out = response.getWriter();
                            response.setContentType("text/html;charset=UTF-8");
                            out.print(value);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            } else if (actionPerformed.equalsIgnoreCase("departmentDataEdit")) {
                if (id != null) {
                    Department department = null;
                    try {
                        department = departmentServiceBean.findDepartment(Long.valueOf(id));
                    } catch (Exception ex) {
                        Logger.getLogger(UpdateAdminData.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (department != null) {
                        System.out.println("Department found");
                        String adminID = String.valueOf(department.getId());
                        if (columnPosition.equalsIgnoreCase("1")) {
                            department.setDepartmentName(new DepartmentName(value));
                        } else if (columnPosition.equalsIgnoreCase("2")) {
                            department.setDescription(value);
                        }
                        try {
                            departmentServiceBean.edit(department);
                        } catch (NonexistentEntityException ex) {
                            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (RollbackFailureException ex) {
                            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (Exception ex) {
                            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.out.println("Column Position: " + columnPosition);
                        System.out.println("Accesed action faculty data edit");
                        System.out.println("Value is: " + value);
                        try {
                            PrintWriter out = response.getWriter();
                            response.setContentType("text/html;charset=UTF-8");
                            out.print(value);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Get methodcklllllllllllllllllllllllllllll");
        doPost(request, response);
    }

   
}
