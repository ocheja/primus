/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.data;

import com.primus.enums.LecturerPosition;
import com.primus.enums.AcademicLevel;
import java.io.Serializable;
import java.util.Set;
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
public class LecturerType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Set<LecturerPosition> positions;
    private AcademicLevel assignedStudentLevel;  //Not so robust. some departments assign few students to a particular lecturer and not the entire class.
    @ManyToOne
   private Department department;
   
    
    
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
        if (!(object instanceof LecturerType)) {
            return false;
        }
        LecturerType other = (LecturerType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.primus.entity.LecturerType[ id=" + id + " ]";
    }

    /**
     * @return the positon
     */
    public Set<LecturerPosition> getPositions() {
        return positions;
    }

    /**
     * @param positon the positon to set
     */
    public void setPosition(Set<LecturerPosition> position) {
        this.positions = position;
    }

    /**
     * @return the assignedStudentLevel
     */
    public AcademicLevel getAssignedStudentLevel() {
        return assignedStudentLevel;
    }

    /**
     * @param assignedStudentLevel the assignedStudentLevel to set
     */
    public void setAssignedStudentLevel(AcademicLevel assignedStudentLevel) {
        this.assignedStudentLevel = assignedStudentLevel;
    }

    /**
     * @return the department
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(Department department) {
        this.department = department;
    }
    
}
