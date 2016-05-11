/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.gsondata;

import java.util.Calendar;

/**
 *
 * @author Olisa
 */
public class DesktopClientMessage {

    private long lecturerId;
    private long lecturerFingerPrintId;
    private byte[] lecturerFingerPrintTemplate;
    private long courseId;
    private boolean inprogress;
    private Calendar time;
    private String message;
    private String action;
    private String departmentName;
    private String location;

    
    /**
     * @return the lecturerId
     */
    public long getLecturerId() {
        return lecturerId;
    }

    /**
     * @param lecturerId the lecturerId to set
     */
    public void setLecturerId(long lecturerId) {
        this.lecturerId = lecturerId;
    }

    /**
     * @return the lecturerFingerPrintId
     */
    public long getLecturerFingerPrintId() {
        return lecturerFingerPrintId;
    }

    /**
     * @param lecturerFingerPrintId the lecturerFingerPrintId to set
     */
    public void setLecturerFingerPrintId(long lecturerFingerPrintId) {
        this.lecturerFingerPrintId = lecturerFingerPrintId;
    }

    /**
     * @return the lecturerFingerPrintTemplate
     */
    public byte[] getLecturerFingerPrintTemplate() {
        return lecturerFingerPrintTemplate;
    }

    /**
     * @param lecturerFingerPrintTemplate the lecturerFingerPrintTemplate to set
     */
    public void setLecturerFingerPrintTemplate(byte[] lecturerFingerPrintTemplate) {
        this.lecturerFingerPrintTemplate = lecturerFingerPrintTemplate;
    }

    /**
     * @return the courseId
     */
    public long getCourseId() {
        return courseId;
    }

    /**
     * @param courseId the courseId to set
     */
    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    /**
     * @return the inprogress
     */
    public boolean isInprogress() {
        return inprogress;
    }

    /**
     * @param inprogress the inprogress to set
     */
    public void setInprogress(boolean inprogress) {
        this.inprogress = inprogress;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the action
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * @return the time
     */
    public Calendar getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(Calendar time) {
        this.time = time;
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
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }
}
