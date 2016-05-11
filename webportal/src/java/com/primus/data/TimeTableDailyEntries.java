/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.data;

import com.primus.enums.LectureHall;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Olisa
 */
@Entity
public class TimeTableDailyEntries implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     @ManyToOne
    private Course course;
    private int startTimeHour;
    private int startTimeMin;
      private int endTimeHour;
      private int endTimeMin;
    private LectureHall lectureHall;
    private String dayOfWeek;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TimeTableDailyEntries)) {
            return false;
        }
        TimeTableDailyEntries other = (TimeTableDailyEntries) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.primus.data.TimeTableDailyEntries[ id=" + id + " ]";
    }

    /**
     * @return the course
     */
    public Course getCourse() {
        return course;
    }

    /**
     * @param course the course to set
     */
    public void setCourse(Course course) {
        this.course = course;
    }

    /**
     * @return the lectureHall
     */
    public LectureHall getLectureHall() {
        return lectureHall;
    }

    /**
     * @param lectureHall the lectureHall to set
     */
    public void setLectureHall(LectureHall lectureHall) {
        this.lectureHall = lectureHall;
    }

    /**
     * @return the startTimeHour
     */
    public int getStartTimeHour() {
        return startTimeHour;
    }

    /**
     * @param startTimeHour the startTimeHour to set
     */
    public void setStartTimeHour(int startTimeHour) {
        this.startTimeHour = startTimeHour;
    }

    /**
     * @return the startTimeMin
     */
    public int getStartTimeMin() {
        return startTimeMin;
    }

    /**
     * @param startTimeMin the startTimeMin to set
     */
    public void setStartTimeMin(int startTimeMin) {
        this.startTimeMin = startTimeMin;
    }

    /**
     * @return the endTimeHour
     */
    public int getEndTimeHour() {
        return endTimeHour;
    }

    /**
     * @param endTimeHour the endTimeHour to set
     */
    public void setEndTimeHour(int endTimeHour) {
        this.endTimeHour = endTimeHour;
    }

    /**
     * @return the endTimeMin
     */
    public int getEndTimeMin() {
        return endTimeMin;
    }

    /**
     * @param endTimeMin the endTimeMin to set
     */
    public void setEndTimeMin(int endTimeMin) {
        this.endTimeMin = endTimeMin;
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
