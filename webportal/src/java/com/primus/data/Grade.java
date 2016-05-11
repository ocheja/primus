/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.data;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Olisa
 */
@Entity
public class Grade implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float continousAssesmentScore=0;
    private float examScore=0;
    private float totalScore;
    @ManyToOne
    @NotNull
   private Course course;

    /**
     * @return the continousAssesmentScore
     */
    public float getContinousAssesmentScore() {
        return continousAssesmentScore;
    }

    /**
     * @param continousAssesmentScore the continousAssesmentScore to set
     */
    public void setContinousAssesmentScore(float continousAssesmentScore) {
        this.continousAssesmentScore = continousAssesmentScore;
        this.totalScore = continousAssesmentScore + this.examScore;
    }

    /**
     * @return the examScore
     */
    public float getExamScore() {
        return examScore;
    }

    /**
     * @param examScore the examScore to set
     */
    public void setExamScore(float examScore) {
        this.examScore = examScore;
        this.totalScore = this.continousAssesmentScore + examScore;
    }

    /**
     * @return the totalScore
     */
    public float getTotalScore() {
        return this.totalScore;
    }
     public void setTotalScore(float totalScore) {
        this.totalScore = totalScore;
    }
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
        if (!(object instanceof Grade)) {
            return false;
        }
        Grade other = (Grade) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.primus.data.Grade[ id=" + id + " ]";
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

   
    
}
