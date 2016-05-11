/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.appstates;

import com.primus.data.Administrator;
import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/** 
 *
 * @author Ocheja Patrick Ileanwa
 */
public class AdministratorState implements Serializable {

    private Administrator currentAdministrator;
    private boolean signedIn = false;
    private String errorMessage;
    private String successMessage;
    private AdminParentViews adminParentViews = AdminParentViews.ADMIN_MANAGER;
    private AdminNodeViews adminNodeViews = AdminNodeViews.CREATE;

    public static AdministratorState getInstance(HttpServletRequest httpServletRequest) {
        HttpSession httpSession = httpServletRequest.getSession();
        AdministratorState lecturerState = (AdministratorState) httpSession.getAttribute(AdministratorState.class.getName());
        if (lecturerState == null) {
            lecturerState = new AdministratorState();
            httpSession.setAttribute(AdministratorState.class.getName(), lecturerState);
        }
        return lecturerState;
    }

    public static enum AdminParentViews {

        HOME("/usersarea/admin/adminhomepage.jsp"),
        ADMIN_MANAGER("/usersarea/admin/adminmanager.jsp"),
        FACULTY_MANAGER("/usersarea/admin/facultymanager.jsp"),
        DEPARTMENT_MANAGER("/usersarea/admin/departmentmanager.jsp"),
        COURSE_MANAGER("/usersarea/admin/coursemanager.jsp"),
        LECTURER_MANAGER("/usersarea/admin/lecturermanager.jsp"),
        UTILITY_MANAGER("/usersarea/admin/utilitymanager.jsp"),
        STUDENT_MANAGER("/usersarea/admin/studentmanager.jsp"),
        OTHERS("");
        private final String page;

        AdminParentViews(String page) {
            this.page = page;
        }

        public String getPage() {
            return page;
        }
    }

    public static enum AdminNodeViews {

        HOME_PROFILE("/usersarea/admin/admin-profile-dashboard.jsp"),
        CREATE("/usersarea/admin/createadmin.jsp"),
        CREATE_FACULTY("/usersarea/admin/createfaculty.jsp"),
        CREATE_DEPARTMENT("/usersarea/admin/createdepartment.jsp"),
        CREATE_COURSE("/usersarea/admin/createcourse.jsp"),
        CREATE_LECTURER("/usersarea/admin/createlecturer.jsp"),
        CREATE_UTILITY("/usersarea/admin/createutils.jsp"),
        CREATE_STUDENT("/usersarea/admin/createstudent.jsp"),
        VIEW("/usersarea/admin/viewadmins.jsp"),
        VIEW_FACULTIES("/usersarea/admin/viewfaculties.jsp"),
        VIEW_DEPARTMENTS("/usersarea/admin/viewdepartments.jsp"),
        VIEW_COURSES("/usersarea/admin/viewcourses.jsp"),
        VIEW_LECTURERS("/usersarea/admin/viewlecturers.jsp"),
        VIEW_UTILITIES("/usersarea/admin/viewutilities.jsp"),
        VIEW_STUDENTS("/usersarea/admin/viewstudents.jsp"),
        OTHERS("");
        private final String page;

        AdminNodeViews(String page) {
            this.page = page;
        }

        public String getPage() {
            return page;
        }
    }

    /**
     * @return the currentAdministrator
     */
    public Administrator getCurrentAdministrator() {
        return currentAdministrator;
    }

    /**
     * @param currentAdministrator the currentAdministrator to set
     */
    public void setCurrentAdministrator(Administrator currentAdministrator) {
        this.currentAdministrator = currentAdministrator;
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
     * @return the adminParentViews
     */
    public AdminParentViews getAdminParentViews() {
        return adminParentViews;
    }

    /**
     * @param adminParentViews the adminParentViews to set
     */
    public void setAdminParentViews(AdminParentViews adminParentViews) {
        this.adminParentViews = adminParentViews;
    }

    /**
     * @return the adminNodeViews
     */
    public AdminNodeViews getAdminNodeViews() {
        return adminNodeViews;
    }

    /**
     * @param adminNodeViews the adminNodeViews to set
     */
    public void setAdminNodeViews(AdminNodeViews adminNodeViews) {
        this.adminNodeViews = adminNodeViews;
    }
}
