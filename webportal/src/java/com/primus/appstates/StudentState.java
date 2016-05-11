/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.appstates;

import com.primus.data.Student;
import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ocheja Patrick Ileanwa
 */
public class StudentState implements Serializable {

    private Student currentStudent;
    private boolean signedIn = false;
    private String errorMessage;
    private String successMessage;
    private StudentParentViews studentParentViews = StudentParentViews.HOME;
    private StudentNodeViews studentNodeViews = StudentNodeViews.HOME_PROFILE;

    public static StudentState getInstance(HttpServletRequest httpServletRequest) {
        HttpSession httpSession = httpServletRequest.getSession();
        StudentState studentState = (StudentState) httpSession.getAttribute(StudentState.class.getName());
        if (studentState == null) {
            studentState = new StudentState();
            httpSession.setAttribute(StudentState.class.getName(), studentState);
        }
        return studentState;

    }

    public static enum StudentParentViews {

        HOME("/usersarea/student/student-profile-dashboard.jsp"), 
        REGISTER_COURSES("/usersarea/student/registercourses.jsp"),
        VIEW_COURSE_FORM("/usersarea/student/studenthomepage.jsp"),
        VIEW_RESULTS("/usersarea/student/viewresults.jsp"),
        VIEW_MESSAGES("/usersarea/student/studenthomepage.jsp"),
        VIEW_TIMETABLE("/usersarea/student/timetable.jsp"),
        OTHERS("");
        private final String page;

        StudentParentViews(String page) {
            this.page = page;
        }

        public String getPage() {
            return page;
        }
    }

    public static enum StudentNodeViews {

        HOME_PROFILE(""),OTHERS("");
        private final String page;

        StudentNodeViews(String page) {
            this.page = page;
        }

        public String getPage() {
            return page;
        }
    }

    /**
     * @return the currentStudent
     */
    public Student getCurrentStudent() {
        return currentStudent;
    }

    /**
     * @param currentStudent the currentStudent to set
     */
    public void setCurrentStudent(Student currentStudent) {
        this.currentStudent = currentStudent;
    }

    /**
     * @return the signedIn
     */
    public boolean isSignedIn() {
        return signedIn;
    }

    /**
     * @param signedIn the signedIn to set
     */
    public void setSignedIn(boolean signedIn) {
        this.signedIn = signedIn;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * @return the successMessage
     */
    public String getSuccessMessage() {
        return successMessage;
    }

    /**
     * @param successMessage the successMessage to set
     */
    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    /**
     * @return the studentParentViews
     */
    public StudentParentViews getStudentParentViews() {
        return studentParentViews;
    }

    /**
     * @param studentParentViews the studentParentViews to set
     */
    public void setStudentParentViews(StudentParentViews studentParentViews) {
        this.studentParentViews = studentParentViews;
    }

    /**
     * @return the studentNodeViews
     */
    public StudentNodeViews getStudentNodeViews() {
        return studentNodeViews;
    }

    /**
     * @param studentNodeViews the studentNodeViews to set
     */
    public void setStudentNodeViews(StudentNodeViews studentNodeViews) {
        this.studentNodeViews = studentNodeViews;
    }
}
