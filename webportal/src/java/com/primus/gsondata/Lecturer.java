/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.gsondata;

/**
 *
 * @author Ocheja Patrick Ileanwa
 */
public class Lecturer {
    private String name;
    private String departmentName;
    private String emailAddress;
    private String fingerPrintID;
    private String lecturerID;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the departmentName
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * @param departmentName the departmentName to set
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * @return the emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * @param emailAddress the emailAddress to set
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * @return the fingerPrintID
     */
    public String getFingerPrintID() {
        return fingerPrintID;
    }

    /**
     * @param fingerPrintID the fingerPrintID to set
     */
    public void setFingerPrintID(String fingerPrintID) {
        this.fingerPrintID = fingerPrintID;
    }

    /**
     * @return the lecturerID
     */
    public String getLecturerID() {
        return lecturerID;
    }

    /**
     * @param lecturerID the lecturerID to set
     */
    public void setLecturerID(String lecturerID) {
        this.lecturerID = lecturerID;
    }
}
