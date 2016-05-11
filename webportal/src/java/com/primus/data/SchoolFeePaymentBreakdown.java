/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.data;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author STUDENT
 */
@Entity
public class SchoolFeePaymentBreakdown implements Serializable {
    
     private static long serialVersionUID = 1L;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  private double  Registration=  700;
private double Library	= 350;
private double Sports;
private double Exam;
private double Caution;
private double Medical;
private double Development_Fee;
private double ID_Card;
private double UNN_Calender;
private double Lab_Computer;
private double ICT;
private double Alumni;
private double Orientation_Mat;
private double SUG;
private double Faculty_Dues;
private double Department_Dues;
private double Course_Registation;
private double Acceptance_Fee;
private double Project_Fee;
private double Certificate_Fee;
private double Building_Levy;
private double Visual_Library_Access;
private double Student_WelfareScheme;
private double Tution_Fees;
private double Reparation_Charge;
private double Professional_Fees =  2500;
private double Internet_Access_Fees = 12000;

    /**
     * @return the Registration
     */
    public double getRegistration() {
        return Registration;
    }

    /**
     * @param Registration the Registration to set
     */
    public void setRegistration(double Registration) {
        this.Registration = Registration;
    }

    /**
     * @return the Library
     */
    public double getLibrary() {
        return Library;
    }

    /**
     * @param Library the Library to set
     */
    public void setLibrary(double Library) {
        this.Library = Library;
    }

    /**
     * @return the Sports
     */
    public double getSports() {
        return Sports;
    }

    /**
     * @param Sports the Sports to set
     */
    public void setSports(double Sports) {
        this.Sports = Sports;
    }

    /**
     * @return the Exam
     */
    public double getExam() {
        return Exam;
    }

    /**
     * @param Exam the Exam to set
     */
    public void setExam(double Exam) {
        this.Exam = Exam;
    }

    /**
     * @return the Caution
     */
    public double getCaution() {
        return Caution;
    }

    /**
     * @param Caution the Caution to set
     */
    public void setCaution(double Caution) {
        this.Caution = Caution;
    }

    /**
     * @return the Medical
     */
    public double getMedical() {
        return Medical;
    }

    /**
     * @param Medical the Medical to set
     */
    public void setMedical(double Medical) {
        this.Medical = Medical;
    }

    /**
     * @return the Development_Fee
     */
    public double getDevelopment_Fee() {
        return Development_Fee;
    }

    /**
     * @param Development_Fee the Development_Fee to set
     */
    public void setDevelopment_Fee(double Development_Fee) {
        this.Development_Fee = Development_Fee;
    }

    /**
     * @return the ID_Card
     */
    public double getID_Card() {
        return ID_Card;
    }

    /**
     * @param ID_Card the ID_Card to set
     */
    public void setID_Card(double ID_Card) {
        this.ID_Card = ID_Card;
    }

    /**
     * @return the UNN_Calender
     */
    public double getUNN_Calender() {
        return UNN_Calender;
    }

    /**
     * @param UNN_Calender the UNN_Calender to set
     */
    public void setUNN_Calender(double UNN_Calender) {
        this.UNN_Calender = UNN_Calender;
    }

    /**
     * @return the Lab_Computer
     */
    public double getLab_Computer() {
        return Lab_Computer;
    }

    /**
     * @param Lab_Computer the Lab_Computer to set
     */
    public void setLab_Computer(double Lab_Computer) {
        this.Lab_Computer = Lab_Computer;
    }

    /**
     * @return the ICT
     */
    public double getICT() {
        return ICT;
    }

    /**
     * @param ICT the ICT to set
     */
    public void setICT(double ICT) {
        this.ICT = ICT;
    }

    /**
     * @return the Alumni
     */
    public double getAlumni() {
        return Alumni;
    }

    /**
     * @param Alumni the Alumni to set
     */
    public void setAlumni(double Alumni) {
        this.Alumni = Alumni;
    }

    /**
     * @return the Orientation_Mat
     */
    public double getOrientation_Mat() {
        return Orientation_Mat;
    }

    /**
     * @param Orientation_Mat the Orientation_Mat to set
     */
    public void setOrientation_Mat(double Orientation_Mat) {
        this.Orientation_Mat = Orientation_Mat;
    }

    /**
     * @return the SUG
     */
    public double getSUG() {
        return SUG;
    }

    /**
     * @param SUG the SUG to set
     */
    public void setSUG(double SUG) {
        this.SUG = SUG;
    }

    /**
     * @return the Faculty_Dues
     */
    public double getFaculty_Dues() {
        return Faculty_Dues;
    }

    /**
     * @param Faculty_Dues the Faculty_Dues to set
     */
    public void setFaculty_Dues(double Faculty_Dues) {
        this.Faculty_Dues = Faculty_Dues;
    }

    /**
     * @return the Department_Dues
     */
    public double getDepartment_Dues() {
        return Department_Dues;
    }

    /**
     * @param Department_Dues the Department_Dues to set
     */
    public void setDepartment_Dues(double Department_Dues) {
        this.Department_Dues = Department_Dues;
    }

    /**
     * @return the Course_Registation
     */
    public double getCourse_Registation() {
        return Course_Registation;
    }

    /**
     * @param Course_Registation the Course_Registation to set
     */
    public void setCourse_Registation(double Course_Registation) {
        this.Course_Registation = Course_Registation;
    }

    /**
     * @return the Acceptance_Fee
     */
    public double getAcceptance_Fee() {
        return Acceptance_Fee;
    }

    /**
     * @param Acceptance_Fee the Acceptance_Fee to set
     */
    public void setAcceptance_Fee(double Acceptance_Fee) {
        this.Acceptance_Fee = Acceptance_Fee;
    }

    /**
     * @return the Project_Fee
     */
    public double getProject_Fee() {
        return Project_Fee;
    }

    /**
     * @param Project_Fee the Project_Fee to set
     */
    public void setProject_Fee(double Project_Fee) {
        this.Project_Fee = Project_Fee;
    }

    /**
     * @return the Certificate_Fee
     */
    public double getCertificate_Fee() {
        return Certificate_Fee;
    }

    /**
     * @param Certificate_Fee the Certificate_Fee to set
     */
    public void setCertificate_Fee(double Certificate_Fee) {
        this.Certificate_Fee = Certificate_Fee;
    }

    /**
     * @return the Building_Levy
     */
    public double getBuilding_Levy() {
        return Building_Levy;
    }

    /**
     * @param Building_Levy the Building_Levy to set
     */
    public void setBuilding_Levy(double Building_Levy) {
        this.Building_Levy = Building_Levy;
    }

    /**
     * @return the Visual_Library_Access
     */
    public double getVisual_Library_Access() {
        return Visual_Library_Access;
    }

    /**
     * @param Visual_Library_Access the Visual_Library_Access to set
     */
    public void setVisual_Library_Access(double Visual_Library_Access) {
        this.Visual_Library_Access = Visual_Library_Access;
    }

    /**
     * @return the Student_WelfareScheme
     */
    public double getStudent_WelfareScheme() {
        return Student_WelfareScheme;
    }

    /**
     * @param Student_WelfareScheme the Student_WelfareScheme to set
     */
    public void setStudent_WelfareScheme(double Student_WelfareScheme) {
        this.Student_WelfareScheme = Student_WelfareScheme;
    }

    /**
     * @return the Tution_Fees
     */
    public double getTution_Fees() {
        return Tution_Fees;
    }

    /**
     * @param Tution_Fees the Tution_Fees to set
     */
    public void setTution_Fees(double Tution_Fees) {
        this.Tution_Fees = Tution_Fees;
    }

    /**
     * @return the Reparation_Charge
     */
    public double getReparation_Charge() {
        return Reparation_Charge;
    }

    /**
     * @param Reparation_Charge the Reparation_Charge to set
     */
    public void setReparation_Charge(double Reparation_Charge) {
        this.Reparation_Charge = Reparation_Charge;
    }

    /**
     * @return the Professional_Fees
     */
    public double getProfessional_Fees() {
        return Professional_Fees;
    }

    /**
     * @param Professional_Fees the Professional_Fees to set
     */
    public void setProfessional_Fees(double Professional_Fees) {
        this.Professional_Fees = Professional_Fees;
    }

    /**
     * @return the Internet_Access_Fees
     */
    public double getInternet_Access_Fees() {
        return Internet_Access_Fees;
    }

    /**
     * @param Internet_Access_Fees the Internet_Access_Fees to set
     */
    public void setInternet_Access_Fees(double Internet_Access_Fees) {
        this.Internet_Access_Fees = Internet_Access_Fees;
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

 
    
}
