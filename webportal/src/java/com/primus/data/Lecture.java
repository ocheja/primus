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
import javax.persistence.OneToOne;

/**
 *
 * @author Olisa
 */
@Entity
public class Lecture implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Course course;
    private boolean inProgress;
    private LectureHall lectureHall;
    private int dateOfLecture=0;
    private int monthOfLecture=0;
    private int yearOfLecture=0;
    private int startHour=0;
    private int endHour=0;
    private int startMin=0;
    private int endMin=0;
    

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
        if (!(object instanceof Lecture)) {
            return false;
        }
        Lecture other = (Lecture) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.primus.data.Lecture[ id=" + id + " ]";
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
     * @return the inProgress
     */
    public boolean isInProgress() {
        return inProgress;
    }

    /**
     * @param inProgress the inProgress to set
     */
    public void setInProgress(boolean inProgress) {
        this.inProgress = inProgress;
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
     * @return the dateOfLecture
     */
    public int getDateOfLecture() {
        return dateOfLecture;
    }

    /**
     * @param dateOfLecture the dateOfLecture to set
     */
    public void setDateOfLecture(int dateOfLecture) {
        this.dateOfLecture = dateOfLecture;
    }

    /**
     * @return the monthOfLecture
     */
    public int getMonthOfLecture() {
        return monthOfLecture;
    }

    /**
     * @param monthOfLecture the monthOfLecture to set
     */
    public void setMonthOfLecture(int monthOfLecture) {
        this.monthOfLecture = monthOfLecture;
    }

    /**
     * @return the yearOfLecture
     */
    public int getYearOfLecture() {
        return yearOfLecture;
    }

    /**
     * @param yearOfLecture the yearOfLecture to set
     */
    public void setYearOfLecture(int yearOfLecture) {
        this.yearOfLecture = yearOfLecture;
    }

    /**
     * @return the startHour
     */
    public int getStartHour() {
        return startHour;
    }

    /**
     * @param startHour the startHour to set
     */
    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    /**
     * @return the endHour
     */
    public int getEndHour() {
        return endHour;
    }

    /**
     * @param endHour the endHour to set
     */
    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    /**
     * @return the startMin
     */
    public int getStartMin() {
        return startMin;
    }

    /**
     * @param startMin the startMin to set
     */
    public void setStartMin(int startMin) {
        this.startMin = startMin;
    }

    /**
     * @return the endMin
     */
    public int getEndMin() {
        return endMin;
    }

    /**
     * @param endMin the endMin to set
     */
    public void setEndMin(int endMin) {
        this.endMin = endMin;
    }
    
}
