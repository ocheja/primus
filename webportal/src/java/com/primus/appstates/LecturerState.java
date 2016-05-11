/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.appstates;

import com.primus.data.Lecturer;
import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ocheja Patrick Ileanwa
 */
public class LecturerState implements Serializable {

    private Lecturer currentLecturer;
    private boolean signedIn = false;
    private String errorMessage;
    private String successMessage;
    private LecturerParentViews lecturerParentViews = LecturerParentViews.HOME;
    private LecturerNodeViews lecturerNodeViews = LecturerNodeViews.HOME_PROFILE;

    public static LecturerState getInstance(HttpServletRequest httpServletRequest) {
        HttpSession httpSession = httpServletRequest.getSession();
        LecturerState lecturerState = (LecturerState) httpSession.getAttribute(LecturerState.class.getName());
        if (lecturerState == null) {
            lecturerState = new LecturerState();
            httpSession.setAttribute(LecturerState.class.getName(), lecturerState);
        }
        return lecturerState;
    }

    public static enum LecturerParentViews {

        HOME("/usersarea/lecturer/lecturer-profile-dashboard.jsp"), 
        MANAGE_COURSE_FORM("/usersarea/lecturer/managecourseforms.jsp"),
        ENTER_CLASSROOM("/usersarea/lecturer/classroom-options.jsp"),
        MANAGE_RESULTS("/usersarea/lecturer/viewresults.jsp"),
        UPLOAD_RESULTS("/usersarea/lecturer/uploadresults.jsp"),
        VIEW_MESSAGES("/usersarea/lecturer/studenthomepage.jsp"),
        VIEW_TIMETABLE("/usersarea/lecturer/timetable.jsp"),
        ADD_NEW_TIMETABLE("/usersarea/lecturer/addnewtimetable.jsp"),
        EDIT_TIMETABLE("/usersarea/lecturer/edittimetable.jsp"),
        OTHERS("");
        private final String page;

        LecturerParentViews(String page) {
            this.page = page;
        }

        public String getPage() {
            return page;
        }
    }

    public static enum LecturerNodeViews {

        HOME_PROFILE(""),OTHERS("");
        private final String page;

        LecturerNodeViews(String page) {
            this.page = page;
        }

        public String getPage() {
            return page;
        }
    }

    /**
     * @return the currentLecturer
     */
    public Lecturer getCurrentLecturer() {
        return currentLecturer;
    }

    /**
     * @param currentLecturer the currentLecturer to set
     */
    public void setCurrentLecturer(Lecturer currentLecturer) {
        this.currentLecturer = currentLecturer;
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
     * @return the lecturerParentViews
     */
    public LecturerParentViews getLecturerParentViews() {
        return lecturerParentViews;
    }

    /**
     * @param lecturerParentViews the lecturerParentViews to set
     */
    public void setLecturerParentViews(LecturerParentViews lecturerParentViews) {
        this.lecturerParentViews = lecturerParentViews;
    }

    /**
     * @return the lecturerNodeViews
     */
    public LecturerNodeViews getLecturerNodeViews() {
        return lecturerNodeViews;
    }

    /**
     * @param lecturerNodeViews the lecturerNodeViews to set
     */
    public void setLecturerNodeViews(LecturerNodeViews lecturerNodeViews) {
        this.lecturerNodeViews = lecturerNodeViews;
    }
}
