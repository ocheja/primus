/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.gsondata;

import com.primus.enums.Status;
import java.util.Calendar;

/**
 *
 * @author Ocheja Patrick Ileanwa
 */
public class GsonLecture {

    private String location;
    private String courseCodeTitle;
    private String startTime;
    private String endTime;
   private Status status;
    private Calendar lectureNoticeTime;
    private String departmentName;
    private String action;
    private String dayOfweek;

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

    /**
     * @return the courseCodeTitle
     */
    public String getCourseCodeTitle() {
        return courseCodeTitle;
    }

    /**
     * @param courseCodeTitle the courseCodeTitle to set
     */
    public void setCourseCodeTitle(String courseCodeTitle) {
        this.courseCodeTitle = courseCodeTitle;
    }

    /**
     * @return the startTime
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
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
     * @return the dayOfweek
     */
    public String getDayOfweek() {
        return dayOfweek;
    }

    /**
     * @param dayOfweek the dayOfweek to set
     */
    public void setDayOfweek(String dayOfweek) {
        this.dayOfweek = dayOfweek;
    }

    /**
     * @return the lectureNoticeTime
     */
    public Calendar getLectureNoticeTime() {
        return lectureNoticeTime;
    }

    /**
     * @param lectureNoticeTime the lectureNoticeTime to set
     */
    public void setLectureNoticeTime(Calendar lectureNoticeTime) {
        this.lectureNoticeTime = lectureNoticeTime;
    }

    /**
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Status status) {
        this.status = status;
    }
}
