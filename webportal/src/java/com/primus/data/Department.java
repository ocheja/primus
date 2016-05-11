/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.data;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author STUDENT
 */
@Entity
public class Department implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private static final long serialVersionUID = 1L;
    
    @OneToOne
    private DepartmentName departmentName;
    @OneToMany(mappedBy = "department", cascade = CascadeType.PERSIST)
    private List<Student> studentsID;
    @ManyToOne()
    @JoinColumn()
    private Faculty faculty;
    @OneToMany(mappedBy = "department")
    private List<Lecturer> lecturers;
    private boolean active = true;
    private String description;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (departmentName != null ? departmentName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Department)) {
            return false;
        }
        Department other = (Department) object;
        if ((this.departmentName == null && other.departmentName != null)
                || (this.departmentName != null && !this.departmentName.equals(other.departmentName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "studentRegistrationEntities.Department[ id=" + departmentName + " ]";
    }

    /**
     * @return the departmentName
     */
    public DepartmentName getDepartmentName() {
        return departmentName;
    }

    /**
     * @param departmentName the departmentName to set
     */
    public void setDepartmentName(DepartmentName departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * @return the studentsBio
     */
    public List<Student> getStudentsID() {
        return studentsID;
    }

    /**
     * @param studentsBio the studentsBio to set
     */
    public void setStudentsID(List<Student> studentsBio) {
        this.studentsID = studentsBio;
    }

    /**
     * @return the faculty
     */
    public Faculty getFaculty() {
        return faculty;
    }

    /**
     * @param faculty the faculty to set
     */
    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    /**
     * @return the lecturers
     */
    public List<Lecturer> getLecturers() {
        return lecturers;
    }

    /**
     * @param lecturers the lecturers to set
     */
    public void setLecturers(List<Lecturer> lecturers) {
        this.lecturers = lecturers;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
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

}
