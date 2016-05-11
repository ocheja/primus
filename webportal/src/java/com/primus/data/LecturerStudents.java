/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.data;

import com.primus.enums.LecturerPosition;
import com.primus.enums.Semester;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author Olisa
 */
@Entity
public class LecturerStudents implements Serializable {
   
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private AcademicSession academicSession;
    private Semester semster;
    @ManyToOne
    private Lecturer lecturer;
     @ManyToOne
    private Course course;
    @ManyToMany
    private List<Student> students;
   private LecturerPosition LecturerPosition ; 

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
        if (!(object instanceof LecturerStudents)) {
            return false;
        }
        LecturerStudents other = (LecturerStudents) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.primus.entity.LecturerStudent[ id=" + id + " ]";
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
     * @return the semster
     */
    public Semester getSemster() {
        return semster;
    }

    /**
     * @param semster the semster to set
     */
    public void setSemster(Semester semster) {
        this.semster = semster;
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
     * @return the students
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * @param students the students to set
     */
    public void setStudents(List<Student> students) {
        this.students = students;
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

    /**
     * @return the LecturerPosition
     */
    public LecturerPosition getLecturerPosition() {
        return LecturerPosition;
    }

    /**
     * @param LecturerPosition the LecturerPosition to set
     */
    public void setLecturerPosition(LecturerPosition LecturerPosition) {
        this.LecturerPosition = LecturerPosition;
    }
    
}
