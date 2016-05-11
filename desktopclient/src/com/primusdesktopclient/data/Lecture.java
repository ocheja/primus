/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primusdesktopclient.data;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

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
    private long lecturerId;
    private long lecturerFingerPrintId;
    private byte[] lecturerFingerPrintTemplate;
    private long courseId;
    private boolean inprogress;
    private int timeElasped;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar startTime;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar endTime;

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
        return "com.primusdesktopclient.data.Lecture[ id=" + id + " ]";
    }

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
    public Calendar getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public Calendar getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }
}
