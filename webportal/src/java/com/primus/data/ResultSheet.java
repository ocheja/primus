/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.data;

import com.primus.enums.AcademicLevel;
import com.primus.enums.Status;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Olisa
 */
@Entity
public class ResultSheet implements Serializable {
  private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Course course;
    @JoinTable()
    @OneToMany(cascade = CascadeType.ALL)
    private  List<LecturerResultGrade> lecturerResultGrade;
    @Temporal(javax.persistence.TemporalType.DATE )
    private Date dateOfUpdate = new Date();
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date examinationDate;
    @OneToOne
    private DepartmentName deptName;
    private AcademicLevel academiclevel;
    @ManyToOne
    private AcademicSession academicSession;
    private Status status;
     @Temporal(javax.persistence.TemporalType.DATE)
    private Date headOfDepartmentDateStamp = null;
     private boolean approvedByOther = false;
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
        if (!(object instanceof ResultSheet)) {
            return false;
        }
        ResultSheet other = (ResultSheet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.primus.data.ResultSheet[ id=" + id + " ]";
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
     * @return the dateOfUpdate
     */
    public Date getDateOfUpdate() {
        return dateOfUpdate;
    }

    /**
     * @param dateOfUpdate the dateOfUpdate to set
     */
    public void setDateOfUpdate(Date dateOfUpdate) {
        this.dateOfUpdate = dateOfUpdate;
    }

    /**
     * @return the examinationDate
     */
    public Date getExaminationDate() {
        return examinationDate;
    }

    /**
     * @param examinationDate the examinationDate to set
     */
    public void setExaminationDate(Date examinationDate) {
        this.examinationDate = examinationDate;
    }

    /**
     * @return the deptName
     */
    public DepartmentName getDeptName() {
        return deptName;
    }

    /**
     * @param deptName the deptName to set
     */
    public void setDeptName(DepartmentName deptName) {
        this.deptName = deptName;
    }

    /**
     * @return the academiclevel
     */
    public AcademicLevel getAcademiclevel() {
        return academiclevel;
    }

    /**
     * @param academiclevel the academiclevel to set
     */
    public void setAcademiclevel(AcademicLevel academiclevel) {
        this.academiclevel = academiclevel;
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
     * @return the approvedByOther
     */
    public boolean isApprovedByOther() {
        return approvedByOther;
    }

    /**
     * @param approvedByOther the approvedByOther to set
     */
    public void setApprovedByOther(boolean approvedByOther) {
        this.approvedByOther = approvedByOther;
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
     * @return the lecturerResultGrade
     */
    public List<LecturerResultGrade> getLecturerResultGrade() {
        return lecturerResultGrade;
    }

    /**
     * @param lecturerResultGrade the lecturerResultGrade to set
     */
    public void setLecturerResultGrade(List<LecturerResultGrade> lecturerResultGrade) {
        this.lecturerResultGrade = lecturerResultGrade;
    }
}
