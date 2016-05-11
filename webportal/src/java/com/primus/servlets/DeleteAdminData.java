/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.servlets;

import com.google.gson.Gson;
import com.primus.appstates.StudentState;
import com.primus.data.AcademicSession;
import com.primus.data.Administrator;
import com.primus.data.Course;
import com.primus.data.CourseForm;
import com.primus.data.Department;
import com.primus.data.Faculty;
import com.primus.data.Lecturer;
import com.primus.data.Message;
import com.primus.data.PortalActivity;
import com.primus.data.StatusMessage;
import com.primus.data.Student;
import com.primus.interfaces.CourseService;
import com.primus.interfaces.LecturerService;
import com.primus.interfaces.StudentService;
import com.primus.service.exceptions.NonexistentEntityException;
import com.primus.service.exceptions.PrimusServiceException;
import com.primus.service.exceptions.RollbackFailureException;
import com.primus.serviceBean.AcademicSessionServiceBean;
import com.primus.serviceBean.AdministratorServiceBean;
import com.primus.serviceBean.CourseFormServiceBean;
import com.primus.serviceBean.DepartmentServiceBean;
import com.primus.serviceBean.FacultyServiceBean;
import com.primus.serviceBean.PortalActivityServiceBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
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
@WebServlet("/admin/delete")
public class DeleteAdminData extends HttpServlet {

    @Autowired
    AdministratorServiceBean administratorServiceBean;
    @Autowired
    FacultyServiceBean facultyServiceBean;
    @Autowired
    DepartmentServiceBean departmentServiceBean;
    @Autowired
    CourseService courseServiceBean;
    @Autowired
    LecturerService lecturerServiceBean;
    @Autowired
    StudentService studentServiceBean;
    @Autowired
    AcademicSessionServiceBean academicSessionServiceBean;
    @Autowired
    CourseFormServiceBean courseFormServiceBean;
    @Autowired
    PortalActivityServiceBean portalActivityServiceBean;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String action = request.getParameter("actionDelete");
        System.out.println("Delete class hit " + action);
        Message message = new Message();
        PortalActivity portalActivity = new PortalActivity();
        if (action != null) {
            System.out.println("action not null in delete");
            if (id != null) {
                System.out.println("id not null");
                if (action.equalsIgnoreCase("admin_delete")) {
                    Administrator administrator = null;
                    try {
                        administrator = administratorServiceBean.findAdministrator(Long.valueOf(id));
                    } catch (PrimusServiceException ex) {
                        Logger.getLogger(UpdateAdminData.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (administrator != null) {
                        System.out.println("Admin found");
                        try {
                            administratorServiceBean.destroy(administrator.getId());
                        } catch (NonexistentEntityException ex) {
                            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (RollbackFailureException ex) {
                            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (Exception ex) {
                            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            StatusMessage statusMessage = new StatusMessage();
                            PrintWriter out = response.getWriter();
                            statusMessage.setMessage("Successful");
                            response.setContentType("text/html;charset=UTF-8");
                            out.print("");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                } else if (action.equalsIgnoreCase("faculty_delete")) {
                    System.out.println("faculty deleting...");
                    Faculty faculty = null;
                    try {
                        faculty = facultyServiceBean.findFaculty(Long.valueOf(id));
                    } catch (Exception ex) {
                        Logger.getLogger(UpdateAdminData.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (faculty != null) {
                        System.out.println("Faculty found. Deleting...");
                        try {
                            facultyServiceBean.destroy(faculty.getId());
                        } catch (NonexistentEntityException ex) {
                            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (RollbackFailureException ex) {
                            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (Exception ex) {
                            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            StatusMessage statusMessage = new StatusMessage();
                            PrintWriter out = response.getWriter();
                            statusMessage.setMessage("Successful");
                            response.setContentType("text/html;charset=UTF-8");
                            out.print("");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                } else if (action.equalsIgnoreCase("department_delete")) {
                    System.out.println("department deleting...");
                    Department department = null;
                    try {
                        department = departmentServiceBean.findDepartment(Long.valueOf(id));
                    } catch (Exception ex) {
                        Logger.getLogger(UpdateAdminData.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (department != null) {
                        System.out.println("Department found. Deleting...");
                        try {
                            departmentServiceBean.destroy(department.getId());
                        } catch (NonexistentEntityException ex) {
                            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (RollbackFailureException ex) {
                            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (Exception ex) {
                            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            StatusMessage statusMessage = new StatusMessage();
                            PrintWriter out = response.getWriter();
                            statusMessage.setMessage("Successful");
                            response.setContentType("text/html;charset=UTF-8");
                            out.print("");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                } else if (action.equalsIgnoreCase("course_delete")) {
                    System.out.println("course deleting...");
                    Course course = null;
                    try {
                        course = courseServiceBean.findCourse(Long.valueOf(id));
                    } catch (Exception ex) {
                        Logger.getLogger(UpdateAdminData.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (course != null) {
                        System.out.println("Course found. Deleting...");
                        try {
                            courseServiceBean.destroy(course.getId());
                        } catch (NonexistentEntityException ex) {
                            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (RollbackFailureException ex) {
                            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (Exception ex) {
                            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            StatusMessage statusMessage = new StatusMessage();
                            PrintWriter out = response.getWriter();
                            statusMessage.setMessage("Successful");
                            response.setContentType("text/html;charset=UTF-8");
                            out.print("");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                } else if (action.equalsIgnoreCase("lecturer_delete")) {
                    System.out.println("lecturer deleting...");
                    Lecturer lecturer = null;
                    try {
                        lecturer = lecturerServiceBean.findLecturer(Long.valueOf(id));
                    } catch (Exception ex) {
                        Logger.getLogger(UpdateAdminData.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (lecturer != null) {
                        System.out.println("Course found. Deleting...");
                        try {
                            lecturerServiceBean.destroy(lecturer.getId());
                        } catch (NonexistentEntityException ex) {
                            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (RollbackFailureException ex) {
                            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (Exception ex) {
                            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            StatusMessage statusMessage = new StatusMessage();
                            PrintWriter out = response.getWriter();
                            statusMessage.setMessage("Successful");
                            response.setContentType("text/html;charset=UTF-8");
                            out.print("");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                } else if (action.equalsIgnoreCase("student_delete")) {
                    System.out.println("student deleting...");
                    Student student = null;
                    try {
                        student = studentServiceBean.findActiveStudent(Long.valueOf(id));
                    } catch (Exception ex) {
                        Logger.getLogger(UpdateAdminData.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (student != null) {
                        System.out.println("student found. Deleting...");
                        try {
                            studentServiceBean.deactivate(student.getId());
                        } catch (NonexistentEntityException ex) {
                            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (RollbackFailureException ex) {
                            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (Exception ex) {
                            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            StatusMessage statusMessage = new StatusMessage();
                            PrintWriter out = response.getWriter();
                            statusMessage.setMessage("Successful");
                            response.setContentType("text/html;charset=UTF-8");
                            out.print("");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                } else if (action.equalsIgnoreCase("session_delete")) {
                    System.out.println("session deleting...");
                    AcademicSession academicSession = null;
                    try {
                        academicSession = academicSessionServiceBean.findAcademicSession(Long.valueOf(id));
                    } catch (Exception ex) {
                        Logger.getLogger(UpdateAdminData.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (academicSession != null) {
                        System.out.println("session found. Deleting...");
                        try {
                            academicSessionServiceBean.destroy(academicSession.getId());
                        } catch (NonexistentEntityException ex) {
                            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (RollbackFailureException ex) {
                            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (Exception ex) {
                            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            StatusMessage statusMessage = new StatusMessage();
                            PrintWriter out = response.getWriter();
                            statusMessage.setMessage("Successful");
                            response.setContentType("text/html;charset=UTF-8");
                            out.print("");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                } else if (action.equalsIgnoreCase("studentCourse")) {
                    String courseID = request.getParameter("courseid");
                    Student student = StudentState.getInstance(request).getCurrentStudent();
                    try {
                        CourseForm courseForm = courseFormServiceBean.findCourseForm(Long.valueOf(id));
                        boolean isStudents = false;
                        if(courseForm!=null && student!=null){
                            for(CourseForm cf : student.getCourseforms()){
                                if(cf.getId() == courseForm.getId()){
                                    isStudents = true;
                                    break;
                                }
                            }
                        }
                        if (isStudents) {
                            List<Course> courses = courseForm.getCourses();
                            if (courses != null) {
                                for (Course course : courses) {
                                    if (course.getId() == Long.valueOf(courseID)) {
                                        courses.remove(course);
                                        courseForm.setCourses(courses);
                                        courseFormServiceBean.edit(courseForm);
                                        portalActivity.setActionPerformed("Unregistered " + course.getCourseCode());
                                        portalActivity.setDateOfAction(new Date());
                                        portalActivity.setDescription("delete activity");
                                        portalActivity.setStudent(student);
                                        portalActivityServiceBean.create(portalActivity);
                                        message.setMessage("Course Successfully removed. Please refresh the page.");
                                    }
                                }
                            }
                        }else{
                                        message.setMessage("Something seems to be wrong; this courseform is not yours!");
                        }
                        System.out.println("Message: " + message.getMessage());
                        PrintWriter out = response.getWriter();
                        String json = new Gson().toJson(message);
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        out.write(json);
                    } catch (Exception e) {
                        try {
                            message.setMessage(e.getLocalizedMessage());
                            PrintWriter out = response.getWriter();
                            String json = new Gson().toJson(message);
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            out.write(json);
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
        doPost(request, response);
    }
}
