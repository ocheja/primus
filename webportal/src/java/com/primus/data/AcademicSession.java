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

/**
 *
 * @author Olisa
 */
@Entity
public class AcademicSession implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int startYear;
    private int endYear;
   private boolean currentSession = false; // identifys the current session
    private boolean active = true; // identifys the current session
    private boolean courseRegistrationClosed = false;
    
    
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
        if (!(object instanceof AcademicSession)) {
            return false;
        }
        AcademicSession other = (AcademicSession) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.primus.entity.AcademicSession[ id=" + id + " ]";
    }

     public String getString() {
        return this.startYear+"/"+this.endYear;
    }
    /**
     * @return the startYear
     */
    public int getStartYear() {
        return startYear;
    }

    /**
     * @param startYear the startYear to set
     */
    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    /**
     * @return the endYear
     */
    public int getEndYear() {
        return endYear;
    }

    /**
     * @param endYear the endYear to set
     */
    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @return the currentSession
     */
    public boolean isCurrentSession() {
        return currentSession;
    }

    /**
     * @param currentSession the currentSession to set
     */
    public void setCurrentSession(boolean currentSession) {
        this.currentSession = currentSession;
    }

    /**
     * @return the courseRegistrationClosed
     */
    public boolean isCourseRegistrationClosed() {
        return courseRegistrationClosed;
    }

    /**
     * @param courseRegistrationClosed the courseRegistrationClosed to set
     */
    public void setCourseRegistrationClosed(boolean courseRegistrationClosed) {
        this.courseRegistrationClosed = courseRegistrationClosed;
    }
    
}
