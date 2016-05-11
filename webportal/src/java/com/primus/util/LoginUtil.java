/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.util;

import com.primus.appstates.AdministratorState;
import com.primus.appstates.LecturerState;
import com.primus.appstates.StudentState;
import com.primus.data.Administrator;
import com.primus.data.Lecturer;
import com.primus.data.Student;
import com.primus.interfaces.LecturerService;
import com.primus.interfaces.StudentService;
import com.primus.service.exceptions.PrimusServiceException;
import com.primus.serviceBean.AdministratorServiceBean;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Ocheja Patrick Ileanwa
 */
@Component
public class LoginUtil {

    @Autowired
    AdministratorServiceBean administratorServiceBean;
    @Autowired
    StudentService studentServiceBean;
    @Autowired
    LecturerService lecturerServiceBean;
    public String userIdOwner(String userId) {
        if (userId != null) {
            if (userId.contains("@")) {
                return "notStudent";
            } else if (userId.contains("/")) {
                return "student";
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public  void studentSignIn(String userId, String password, HttpServletRequest request, UserTransaction utx) {
        StudentState studentState = StudentState.getInstance(request);
        Student student = null;
        try {
            student = studentServiceBean.studentLogin(userId, password.hashCode());
        } catch (PrimusServiceException ex) {
            Logger.getLogger(LoginUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(LoginUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (student != null) {
            System.out.println("Valid user found");
            studentState.setCurrentStudent(student);
            studentState.setSignedIn(true);
            request.getSession().setAttribute(StudentState.class.getName(), studentState);
        } else {
            System.out.println("No valid user");
        }
    }

    public void othersSignIn(String userId, String password, HttpServletRequest request, UserTransaction utx) {
        AdministratorState administratorState = AdministratorState.getInstance(request);
        LecturerState lecturerState = LecturerState.getInstance(request);
        Administrator administrator =null;
        Lecturer lecturer = null;
        try {
            administrator = administratorServiceBean.findAdministratorLogin(userId, password.hashCode());
            lecturer = lecturerServiceBean.findLecturerLogin(userId, password.hashCode());
            
        } catch (Exception ex) {
            Logger.getLogger(LoginUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (administrator != null) {
//            System.out.println("Valid user found");
//            System.out.println("Details :"+ administrator.getEmailAddress());
//            System.out.println("Details :"+ administrator.getFirstName());
//            System.out.println("Details :"+ administrator.getImage());
//            System.out.println("Details :"+ administrator.getLastName());
//            System.out.println("Details :"+ administrator.getMiddleName());
//            System.out.println("Details :"+ administrator.getPhoneNumber());
//            System.out.println("Details :"+ administrator.getAdminType());
//            System.out.println("Details :"+ administrator.getFingerPrint());
//            System.out.println("Details :"+ administrator.getId());
//            System.out.println("Details :"+ administrator.isActive());
//            System.out.println("Details :"+ administrator.getPassword());
//            for(Field field:Administrator.class.getDeclaredFields()){
//                
//            System.out.println("Field : "+ field.getName());
//        }
            
            administratorState.setCurrentAdministrator(administrator);
            administratorState.setSignedIn(true);
            HttpSession session = request.getSession();
            session.setAttribute(AdministratorState.class.getName(), administratorState);
        }
        else if (lecturer != null) {
            lecturerState.setCurrentLecturer(lecturer);
            lecturerState.setSignedIn(true);
            HttpSession session = request.getSession();
            session.setAttribute(LecturerState.class.getName(), lecturerState);
        } else {
            System.out.println("No valid user");
        }
    }
}
