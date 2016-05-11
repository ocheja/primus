/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.data;

/**
 *
 * @author Ocheja Patrick Ileanwa
 */
public class TimetableLecture {
    private String startEndTime;
    private String venue;
    private boolean started = false;
    private boolean shldStarted = false;
    private String department;
    private String today;
    private String startedAt;
    private String courseCode;

    /**
     * @return the startEndTime
     */
    public String getStartEndTime() {
        return startEndTime;
    }

    /**
     * @param startEndTime the startEndTime to set
     */
    public void setStartEndTime(String startEndTime) {
        this.startEndTime = startEndTime;
    }

    /**
     * @return the venue
     */
    public String getVenue() {
        return venue;
    }

    /**
     * @param venue the venue to set
     */
    public void setVenue(String venue) {
        this.venue = venue;
    }

    /**
     * @return the started
     */
    public boolean isStarted() {
        return started;
    }

    /**
     * @param started the started to set
     */
    public void setStarted(boolean started) {
        this.started = started;
    }

    /**
     * @return the shldStarted
     */
    public boolean isShldStarted() {
        return shldStarted;
    }

    /**
     * @param shldStarted the shldStarted to set
     */
    public void setShldStarted(boolean shldStarted) {
        this.shldStarted = shldStarted;
    }

    /**
     * @return the department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * @return the today
     */
    public String getToday() {
        return today;
    }

    /**
     * @param today the today to set
     */
    public void setToday(String today) {
        this.today = today;
    }

    /**
     * @return the startedAt
     */
    public String getStartedAt() {
        return startedAt;
    }

    /**
     * @param startedAt the startedAt to set
     */
    public void setStartedAt(String startedAt) {
        this.startedAt = startedAt;
    }

    /**
     * @return the courseCode
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * @param courseCode the courseCode to set
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
}
