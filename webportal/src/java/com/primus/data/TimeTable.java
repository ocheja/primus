/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.data;

import com.primus.enums.Semester;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Olisa
 */ 
@Entity
public class TimeTable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Department department;
    @ManyToOne
    private AcademicSession academicSession;
    private Semester academicSemester;
    @OneToOne
    private Monday monday;
    @OneToOne
    private Tuesday tuesday;
    @OneToOne
    private Wednesday wednesday;
    @OneToOne
    private Thursday thursday;
    @OneToOne
    private Friday friday;
    @OneToOne
    private Saturday saturday;
    @OneToOne
    private Sunday sunday;
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
        if (!(object instanceof TimeTable)) {
            return false;
        }
        TimeTable other = (TimeTable) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.primus.data.TimeTable[ id=" + id + " ]";
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
     * @return the academicSemester
     */
    public Semester getAcademicSemester() {
        return academicSemester;
    }

    /**
     * @param academicSemester the academicSemester to set
     */
    public void setAcademicSemester(Semester academicSemester) {
        this.academicSemester = academicSemester;
    }

    /**
     * @return the monday
     */
    public Monday getMonday() {
        return monday;
    }

    /**
     * @param monday the monday to set
     */
    public void setMonday(Monday monday) {
        this.monday = monday;
    }

    /**
     * @return the tuesday
     */
    public Tuesday getTuesday() {
        return tuesday;
    }

    /**
     * @param tuesday the tuesday to set
     */
    public void setTuesday(Tuesday tuesday) {
        this.tuesday = tuesday;
    }

    /**
     * @return the wednesday
     */
    public Wednesday getWednesday() {
        return wednesday;
    }

    /**
     * @param wednesday the wednesday to set
     */
    public void setWednesday(Wednesday wednesday) {
        this.wednesday = wednesday;
    }

    /**
     * @return the thursday
     */
    public Thursday getThursday() {
        return thursday;
    }

    /**
     * @param thursday the thursday to set
     */
    public void setThursday(Thursday thursday) {
        this.thursday = thursday;
    }

    /**
     * @return the friday
     */
    public Friday getFriday() {
        return friday;
    }

    /**
     * @param friday the friday to set
     */
    public void setFriday(Friday friday) {
        this.friday = friday;
    }

    /**
     * @return the saturday
     */
    public Saturday getSaturday() {
        return saturday;
    }

    /**
     * @param saturday the saturday to set
     */
    public void setSaturday(Saturday saturday) {
        this.saturday = saturday;
    }

    /**
     * @return the sunday
     */
    public Sunday getSunday() {
        return sunday;
    }

    /**
     * @param sunday the sunday to set
     */
    public void setSunday(Sunday sunday) {
        this.sunday = sunday;
    }
}
