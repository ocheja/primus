/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.data;

import com.primus.enums.Gender;
import com.primus.enums.AcademicLevel;
import com.primus.enums.Title;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author STUDENT
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "findStudent", query = "SELECT s FROM Student s WHERE s.regNumber=:studentID OR s.jambRegNumber = :studentR")
})
public class Student implements Serializable {
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<PortalActivity> portalActivities;

    @OneToMany(mappedBy = "student")
    private List<StudentResultSheet> studentResultSheets;
    @ManyToMany(mappedBy = "students")
    private List<LecturerStudents> lecturerStudentss;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private static long serialVersionUID = 1L;
    private byte[] studentImage = null;
    private Integer yearAdmitted;
    private Integer matricSerialNumber;
    private String regNumber;
    private String jambRegNumber;
    @Embedded
    private Name studentName;
    private Gender gender;
    @ElementCollection
    @MapKeyEnumerated(EnumType.STRING)
    private List<Title> titles;
    private String email;
    private String phoneNumber;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn()
    private Department department = null;
    @Enumerated(EnumType.STRING)
    @Column(name = "LEVELS")
    private AcademicLevel CurrentAcademicLevel;
    private int password;
    private String userName;
    @NotNull
    @OneToOne
    private AcademicSession currentSession;
    private String programme;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn()
    private Room room;
    @Embedded
    private Indicator StudentIndicator;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<CourseForm> courseforms;
    @OneToMany(mappedBy = "student", cascade = CascadeType.PERSIST)
    @JoinColumn()
    private Set<HostelFeeReceipt> hostelReceipts = null;
    @OneToMany(mappedBy = "student", cascade = CascadeType.PERSIST)
    @JoinColumn()
    private Set<SchoolFeesReceipt> schoolFeeReceipts = null;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Message> messages;
    private boolean active = true;
    @ManyToOne
   private Degree degree;
   @OneToMany(mappedBy = "student",cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<AcademicSessionLevel> academicSessionLevel;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;

        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "studentRegistrationEntities.Student[ id=" + getId() + " ]";
    }

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

    /**
     * @return the regNumber
     */
    public String getRegNumber() {
        return regNumber;
    }

    /**
     * @param regNumber the regNumber to set
     */
    private void setRegNumber() {
        this.regNumber = this.yearAdmitted + "/" + this.matricSerialNumber;
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
     * @return the CurrentAcademicLevel
     */
    public AcademicLevel getCurrentAcademicLevel() {
        return CurrentAcademicLevel;
    }
    
    public String getCurrentAcademicLevelStr() {
        return CurrentAcademicLevel.name().split("l")[1];
    }
    /**
     * @param CurrentAcademicLevel the CurrentAcademicLevel to set
     */
    public void setCurrentAcademicLevel(AcademicLevel level) {
        this.CurrentAcademicLevel = level;
    }

    /**
     * @return the room
     */
    public Room getRoom() {
        return room;
    }

    /**
     * @param room the room to set
     */
    public void setRoom(Room room) {
        this.room = room;
    }

    /**
     * @return the jambRegNumber
     */
    public String getJambRegNumber() {
        return jambRegNumber;
    }

    /**
     * @param jambRegNumber the jambRegNumber to set
     */
    public void setJambRegNumber(String jambRegNumber) {
        this.jambRegNumber = jambRegNumber;
    }

    /**
     * @return the hostelReceipts
     */
    public Set<HostelFeeReceipt> getHostelFeeReceipt() {
        return hostelReceipts;
    }

    /**
     * @param hostelReceipts the hostelReceipts to set
     */
    public void setHostelFeeReceipt(Set<HostelFeeReceipt> hostelFeeReceipt) {
        this.hostelReceipts = hostelFeeReceipt;
    }

    /**
     * @return the schoolFeeReceipts
     */
    public Set<SchoolFeesReceipt> getSchoolfeeReceipt() {
        return schoolFeeReceipts;
    }

    /**
     * @param schoolFeeReceipts the schoolFeeReceipts to set
     */
    public void setSchoolfeeReceipt(Set<SchoolFeesReceipt> schoolfeeReceipt) {
        this.schoolFeeReceipts = schoolfeeReceipt;
    }

    /**
     * @return the course_form
     */
    public Set<CourseForm> getCourseforms() {
        return courseforms;
    }

    /**
     * @param courseforms the course_form to set
     */
    public void setCourseforms(Set<CourseForm> courseforms) {
        this.courseforms = courseforms;
    }

    /**
     * @return the currentSession
     */
    public AcademicSession getCurrentSession() {
        return currentSession;
    }

    /**
     * @param currentSession the currentSession to set
     */
    public void setCurrentSession(AcademicSession currentSession) {
        this.currentSession = currentSession;
    }

    /**
     * @return the studentImage
     */
    public byte[] getStudentImage() {
        return studentImage;
    }

    /**
     * @param studentImage the studentImage to set
     */
    public void setStudentImage(byte[] image) {
        this.studentImage = image;
    }

    /**
     * @return the password
     */
    public int getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(int password) {
        this.password = password;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the programme
     */
    public String getProgramme() {
        return programme;
    }

    /**
     * @param programme the programme to set
     */
    public void setProgramme(String Programme) {
        this.programme = Programme;
    }

    /**
     * @return the studentName
     */
    public Name getStudentName() {
        return studentName;
    }

    /**
     * @param studentName the studentName to set
     */
    public void setStudentName(Name studentName) {
        this.studentName = studentName;
    }

    /**
     * @return the gender
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return the lecturerStudentss
     */
    public List<LecturerStudents> getLecturerStudentss() {
        return lecturerStudentss;
    }

    /**
     * @param lecturerStudentss the lecturerStudentss to set
     */
    public void setLecturerStudentss(List<LecturerStudents> lecturerStudentss) {
        this.lecturerStudentss = lecturerStudentss;
    }

    /**
     * @return the messages
     */
    public List<Message> getMessages() {
        return messages;
    }

    /**
     * @param messages the messages to set
     */
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    /**
     * @return the StudentIndicator
     */
    public Indicator getStudentIndicator() {
        return StudentIndicator;
    }

    /**
     * @param StudentIndicator the StudentIndicator to set
     */
    public void setStudentIndicator(Indicator StudentIndicator) {
        this.StudentIndicator = StudentIndicator;
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
     * @return the studentResultSheets
     */
    public List<StudentResultSheet> getStudentResultSheets() {
        return studentResultSheets;
    }

    /**
     * @param studentResultSheets the studentResultSheets to set
     */
    public void setStudentResultSheets(List<StudentResultSheet> studentResultSheets) {
        this.studentResultSheets = studentResultSheets;
    }

    /**
     * @return the titles
     */
    public List<Title> getTitles() {
        return titles;
    }

    /**
     * @param titles the titles to set
     */
    public void setTitles(List<Title> titles) {
        this.titles = titles;
    }

    /**
     * @return the yearAdmitted
     */
    public int getYearAdmitted() {
        return yearAdmitted;
    }

    /**
     * @param yearAdmitted the yearAdmitted to set
     */
    public void setYearAdmitted(int yearAdmitted) {
        this.yearAdmitted = yearAdmitted;
        setRegNumber();
    }

    /**
     * @return the matricSerialNumber
     */
    public int getMatricSerialNumber() {
        return matricSerialNumber;
    }

    /**
     * @param matricSerialNumber the matricSerialNumber to set
     */
    public void setMatricSerialNumber(int matricSerialNumber) {
        this.matricSerialNumber = matricSerialNumber;
        setRegNumber();

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

   
      public int getNumberOfYearsSpent() {
        return this.academicSessionLevel.size();
    }

    /**
     * @return the portalActivities
     */
    public List<PortalActivity> getPortalActivities() {
        return portalActivities;
    }

    /**
     * @param portalActivities the portalActivities to set
     */
    public void setPortalActivities(List<PortalActivity> portalActivities) {
        this.portalActivities = portalActivities;
    }

    /**
     * @return the academicSessionLevel
     */
    public List<AcademicSessionLevel> getAcademicSessionLevel() {
        return academicSessionLevel;
    }

    /**
     * @param academicSessionLevel the academicSessionLevel to set
     */
    public void setAcademicSessionLevel(List<AcademicSessionLevel> academicSessionLevel) {
        this.academicSessionLevel = academicSessionLevel;
    }
}
