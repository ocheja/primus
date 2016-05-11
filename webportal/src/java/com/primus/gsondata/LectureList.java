/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.gsondata;

import java.util.List;

/**
 *
 * @author Olisa
 */
public class LectureList {
  private  List<GsonLecture> lectures;
  private String action;
  private String dayOfWeek;

    /**
     * @return the lectures
     */
    public List<GsonLecture> getLectures() {
        return lectures;
    }

    /**
     * @param lectures the lectures to set
     */
    public void setLectures(List<GsonLecture> lectures) {
        this.lectures = lectures;
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
     * @return the dayOfWeek
     */
    public String getDayOfWeek() {
        return dayOfWeek;
    }

    /**
     * @param dayOfWeek the dayOfWeek to set
     */
    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
}
