/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.data;

import com.primus.enums.Semester;
import com.primus.enums.Status;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author STUDENT
 */
@Entity
public class CourseForm implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne()
    @JoinColumn()
    //@NotNull
    private Student student;
    @Enumerated(EnumType.STRING)
    private Semester semester;
    @OneToOne
    private AcademicSession academicSession;
    @OneToMany()
    private List<Course> courses;
    private Status status = Status.SUBMITTED;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date AcademicAdviserDateStamp = null;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date headOfDepartmentDateStamp = null;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date facultyOfficerDateStamp = null;
    private byte[] academicAdviserSignature = null;
    private byte[] headOfDepartmentSignature = null;
    private byte[] facultyOfficerSignature = null;

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
        if (!(object instanceof CourseForm)) {
            return false;
        }
        CourseForm other = (CourseForm) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "studentRegistrationEntities.CourseForm[ id=" + id + " ]";
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
     * @return the courses
     */
    public List<Course> getCourses() {
        return courses;
    }

    /**
     * @param courses the courses to set
     */
    public void setCourses(List<Course> course) {
        this.courses = course;
    }

    /**
     * @return the semester
     */
    public Semester getSemester() {
        return semester;
    }

    /**
     * @param semester the semester to set
     */
    public void setSemester(Semester semester) {
        this.semester = semester;
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
    public void setAcademicSession(AcademicSession AcademicSession) {
        this.academicSession = AcademicSession;
    }

    /**
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * @return the AcademicAdviserDateStamp
     */
    public Date getAcademicAdviserDateStamp() {
        return AcademicAdviserDateStamp;
    }

    /**
     * @param AcademicAdviserDateStamp the AcademicAdviserDateStamp to set
     */
    public void setAcademicAdviserDateStamp(Date AcademicAdviserDateStamp) {
        this.AcademicAdviserDateStamp = AcademicAdviserDateStamp;
    }

    /**
     * @return the headOfDepartmentDateStamp
     */
    public Date getHeadOfDepartmentDateStamp() {
        return headOfDepartmentDateStamp;
    }

    /**
     * @param headOfDepartmentDateStamp the headOfDepartmentDateStamp to set
     */
    public void setHeadOfDepartmentDateStamp(Date headOfDepartmentDateStamp) {
        this.headOfDepartmentDateStamp = headOfDepartmentDateStamp;
    }

    /**
     * @return the facultyOfficerDateStamp
     */
    public Date getFacultyOfficerDateStamp() {
        return facultyOfficerDateStamp;
    }

    /**
     * @param facultyOfficerDateStamp the facultyOfficerDateStamp to set
     */
    public void setFacultyOfficerDateStamp(Date facultyOfficerDateStamp) {
        this.facultyOfficerDateStamp = facultyOfficerDateStamp;
    }

    /**
     * @return the academicAdviserSignature
     */
    public byte[] getAcademicAdviserSignature() {
        return academicAdviserSignature;
    }

    /**
     * @param academicAdviserSignature the academicAdviserSignature to set
     */
    public void setAcademicAdviserSignature(byte[] academicAdviserSignature) {
        this.academicAdviserSignature = academicAdviserSignature;
    }

    /**
     * @return the headOfDepartmentSignature
     */
    public byte[] getHeadOfDepartmentSignature() {
        return headOfDepartmentSignature;
    }

    /**
     * @param headOfDepartmentSignature the headOfDepartmentSignature to set
     */
    public void setHeadOfDepartmentSignature(byte[] headOfDepartmentSignature) {
        this.headOfDepartmentSignature = headOfDepartmentSignature;
    }

    /**
     * @return the facultyOfficerSignature
     */
    public byte[] getFacultyOfficerSignature() {
        return facultyOfficerSignature;
    }

    /**
     * @param facultyOfficerSignature the facultyOfficerSignature to set
     */
    public void setFacultyOfficerSignature(byte[] facultyOfficerSignature) {
        this.facultyOfficerSignature = facultyOfficerSignature;
    }

    public int getTotalUnitLoad() {
        int count = 0;
        for (Course course : courses) {
            count += course.getCourseUnitLoad();
        }
        return count;
    }
}
