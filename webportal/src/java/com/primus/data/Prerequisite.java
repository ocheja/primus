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
import javax.persistence.OneToOne;

/**
 *
 * @author Olisa
 */
@Entity
public class Prerequisite implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Course courseID;
    @OneToOne
    private Course prerequisiteCourseID;    
    
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
        if (!(object instanceof Prerequisite)) {
            return false;
        }
        Prerequisite other = (Prerequisite) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oliks.test.entity.Prerequisite[ id=" + id + " ]";
    }

    /**
     * @return the courseID
     */
    public Course getCourseID() {
        return courseID;
    }

    /**
     * @param courseID the courseID to set
     */
    public void setCourseID(Course courseID) {
        this.courseID = courseID;
    }

    /**
     * @return the prerequisiteCourseID
     */
    public Course getPrerequisiteCourseID() {
        return prerequisiteCourseID;
    }

    /**
     * @param prerequisiteCourseID the prerequisiteCourseID to set
     */
    public void setPrerequisiteCourseID(Course prerequisiteCourseID) {
        this.prerequisiteCourseID = prerequisiteCourseID;
    }
    
}
