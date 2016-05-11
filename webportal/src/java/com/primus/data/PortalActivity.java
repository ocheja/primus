/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Ocheja Patrick Ileanwa
 */
@Entity
public class PortalActivity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date dateOfAction;
    private String actionPerformed;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    private Lecturer lecturer;
    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;
    @ManyToOne(fetch = FetchType.LAZY)
    private Administrator administrator;
    private Long milliSecondsElapsed = 0L;

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
        if (!(object instanceof PortalActivity)) {
            return false;
        }
        PortalActivity other = (PortalActivity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.primus.data.PortalActivity[ id=" + id + " ]";
    }

    /**
     * @return the dateOfAction
     */
    public Date getDateOfAction() {
        return dateOfAction;
    }

    /**
     * @param dateOfAction the dateOfAction to set
     */
    public void setDateOfAction(Date dateOfAction) {
        this.dateOfAction = dateOfAction;
    }

    /**
     * @return the actionPerformed
     */
    public String getActionPerformed() {
        return actionPerformed;
    }

    /**
     * @param actionPerformed the actionPerformed to set
     */
    public void setActionPerformed(String actionPerformed) {
        this.actionPerformed = actionPerformed;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the lecturer
     */
    public Lecturer getLecturer() {
        return lecturer;
    }

    /**
     * @param lecturer the lecturer to set
     */
    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    /**
     * @return the student
     */
    public Student getStudent() {
        return student;
    }

    /**
     * @param student the student to set
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * @return the administrator
     */
    public Administrator getAdministrator() {
        return administrator;
    }

    /**
     * @param administrator the administrator to set
     */
    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    /**
     * @return the milliSecondsElapsed
     */
    public Long getMilliSecondsElapsed() {
        return milliSecondsElapsed;
    }

    /**
     * @param milliSecondsElapsed the milliSecondsElapsed to set
     */
    public void setMilliSecondsElapsed(Long milliSecondsElapsed) {
        this.milliSecondsElapsed = milliSecondsElapsed;
    }

}
