/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.data;

import com.primus.enums.AcademicLevel;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author Ocheja Patrick Ileanwa
 */
@Entity
public class AcademicSessionLevel implements Serializable {
    @ManyToOne()
    private Student student;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private AcademicSession academicSession;
    private AcademicLevel academicLevel;

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
        if (!(object instanceof AcademicSessionLevel)) {
            return false;
        }
        AcademicSessionLevel other = (AcademicSessionLevel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.primus.data.AcademicSessionLevel[ id=" + id + " ]";
    }

    /**
     * @return the academicSession
     */
    public AcademicSession getAcademicSession() {
        return academicSession;
    }

    /**
     * @param academicSession the academicSession to set
     */
    public void setAcademicSession(AcademicSession academicSession) {
        this.academicSession = academicSession;
    }

    /**
     * @return the academicLevel
     */
    public AcademicLevel getAcademicLevel() {
        return academicLevel;
    }

    /**
     * @param academicLevel the academicLevel to set
     */
    public void setAcademicLevel(AcademicLevel academicLevel) {
        this.academicLevel = academicLevel;
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
    
}
