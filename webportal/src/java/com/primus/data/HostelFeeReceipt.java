/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.data;

import com.primus.enums.HostelName;
import com.primus.enums.AcademicLevel;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author STUDENT
 */
@Entity
public class HostelFeeReceipt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="\"STUD_HostelREPT\"")
    private Student student;
    
   
    @Enumerated(EnumType.STRING)
    private AcademicLevel academicLevel;
    
   @Enumerated(EnumType.STRING) 
    private HostelName hostelName;
  
    @ManyToOne
    private AcademicSession AcademicSession;
   private  double amountPayed;
    @Temporal(TemporalType.DATE)
    private  Date dateOfPayment;
    
    
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
        if (!(object instanceof HostelFeeReceipt)) {
            return false;
        }
        HostelFeeReceipt other = (HostelFeeReceipt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "studentRegistrationEntities.HostelFeeReceipt[ id=" + id + " ]";
    }
    /**
     * @return the hostel
     */
    public  HostelName getHostelName() {
        return hostelName;
    }

    /**
     * @param aHostel the hostel to set
     */
    public  void setHostelName(HostelName aHostel) {
        hostelName = aHostel;
    }

    /**
     * @return the amountPayed
     */
    public  double getAmountPayed() {
        return amountPayed;
    }

    /**
     * @param aAmountPayed the amountPayed to set
     */
    public void setAmountPayed(double aAmountPayed) {
        amountPayed = aAmountPayed;
    }

    /**
     * @return the DateTime
     */
    public  Date getDateOfPayment() {
        return dateOfPayment;
    }

    /**
     * @param aDateTime the DateTime to set
     */
    public  void setDateOfPayment(Date DateTime) {
        dateOfPayment = DateTime;
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
     * @return the AcademicSession
     */
    public AcademicSession getAcademicSession() {
        return AcademicSession;
    }

    /**
     * @param AcademicSession the AcademicSession to set
     */
    public void setAcademicSession(AcademicSession AcademicSession) {
        this.AcademicSession = AcademicSession;
    }
    
    
}
