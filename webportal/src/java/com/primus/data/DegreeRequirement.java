/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.data;

import com.primus.enums.AcademicLevel;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Olisa
 */
@Entity
public class DegreeRequirement implements Serializable {

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private Degree degree;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private AcademicLevel StudLevel;
    private int numberOfElectiveCourses;
    @OneToMany(mappedBy = "degreeRequirement")
    private Set<Course> coursesForLevel;///  elements in the list gives the number of courses for each levels
    private boolean active = true;

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
        if (!(object instanceof DegreeRequirement)) {
            return false;
        }
        DegreeRequirement other = (DegreeRequirement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.primus.entity.DegreeRequirement[ id=" + id + " ]";
    }

    /**
     * @return the StudLevel
     */
    public AcademicLevel getStudLevel() {
        return StudLevel;
    }

    /**
     * @param StudLevel the StudLevel to set
     */
    public void setStudLevel(AcademicLevel StudLevel) {
        this.StudLevel = StudLevel;
    }

    /**
     * @return the coursesForLevel
     */
    public Set<Course> getCoursesForLevel() {
        return coursesForLevel;
    }

    /**
     * @param coursesForLevel the coursesForLevel to set
     */
    public void setCoursesForLevel(Set<Course> coursesForLevel) {
        this.coursesForLevel = coursesForLevel;
    }

    /**
     * @return the degree
     */
    public Degree getDegree() {
        return degree;
    }

    /**
     * @param degree the degree to set
     */
    public void setDegree(Degree degree) {
        this.degree = degree;
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
     * @return the numberOfElectiveCourses
     */
    public int getNumberOfElectiveCourses() {
        return numberOfElectiveCourses;
    }

    /**
     * @param numberOfElectiveCourses the numberOfElectiveCourses to set
     */
    public void setNumberOfElectiveCourses(int numberOfElectiveCourses) {
        this.numberOfElectiveCourses = numberOfElectiveCourses;
    }
}
