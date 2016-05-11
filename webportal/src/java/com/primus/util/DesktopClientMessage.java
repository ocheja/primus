/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.util;

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
    private int timeElasped;
    private int startTime;
    private int endTime;
    private String message;

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
     * @return the timeElasped
     */
    public int getTimeElasped() {
        return timeElasped;
    }

    /**
     * @param timeElasped the timeElasped to set
     */
    public void setTimeElasped(int timeElasped) {
        this.timeElasped = timeElasped;
    }

    /**
     * @return the startTime
     */
    public int getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public int getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(int endTime) {
        this.endTime = endTime;
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
}
