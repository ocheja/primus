/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.data;

import com.primus.enums.AcademicLevel;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author STUDENT
 */
@Entity
public class SchoolFeesReceipt implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn()
    private Student student;
    @Enumerated(EnumType.STRING)
    private AcademicLevel studLevel;
    private double amountPayed;
    @OneToOne
    private AcademicSession academicSession;
    @Temporal(TemporalType.DATE)
    private Date DateOfPayment; 
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn()
   private SchoolFeePaymentBreakdown schoolFeeBreakdown;

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
        if (!(object instanceof SchoolFeesReceipt)) {
            return false;
        }
        SchoolFeesReceipt other = (SchoolFeesReceipt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "studentRegistrationEntities.SchoolFeeReceipt[ id=" + id + " ]";
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
     * @return the amountPayed
     */
    public double getAmountPayed() {
        return amountPayed;
    }

    /**
     * @param amountPayed the amountPayed to set
     */
    public void setAmountPayed(double amountPayed) {
        this.amountPayed = amountPayed;
    }

    /**
     * @return the DateTime
     */
    public Date getDateOfPayment() {
        return DateOfPayment;
    }

    /**
     * @param DateTime the DateTime to set
     */
    public void setDateOfPayment(Date DateTime) {
        this.DateOfPayment = DateTime;
    }

    /**
     * @return the studLevel
     */
    public AcademicLevel getStudLevel() {
        return studLevel;
    }

    /**
     * @param studLevel the studLevel to set
     */
    public void setStudLevel(AcademicLevel studLevel) {
        this.studLevel = studLevel;
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
    public void setAcademicSession(AcademicSession session) {
        this.academicSession = session;
    }

    /**
     * @return the schoolFeeBreakdown
     */
    public SchoolFeePaymentBreakdown getSchoolFeeBreakdown() {
        return schoolFeeBreakdown;
    }

    /**
     * @param schoolFeeBreakdown the schoolFeeBreakdown to set
     */
    public void setSchoolFeeBreakdown(SchoolFeePaymentBreakdown schoolFeeBreakdown) {
        this.schoolFeeBreakdown = schoolFeeBreakdown;
    }
}
