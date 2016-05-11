/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.data;

import com.primus.enums.Title;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Olisa
 */
@Entity
public class Lecturer implements Serializable {
    @OneToMany(mappedBy = "lecturer", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<PortalActivity> portalActivities;

    @OneToMany(mappedBy = "lecturer",cascade = CascadeType.ALL)
    private List<LecturerStudents> lecturerStudents;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    @NotNull
    private Name lecturerName;
    private byte[] image;
    private byte[] fingerPrint;
    private byte[] signature = null;
    private long fingerPrintId;
    private int password;
    private String phoneNumber;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn()
    private Department department;
    private String CareerDescription;
    private String email;
    private String lastLectureId;
    private boolean lectureInProgress;
    @ManyToOne(cascade = CascadeType.ALL)
    private LecturerType lecturerType;
    @ElementCollection
    @MapKeyEnumerated(EnumType.STRING)
    private List<Title> titles;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Course> courses;
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
        if (!(object instanceof Lecturer)) {
            return false;
        }
        Lecturer other = (Lecturer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.primus.entity.Lecturer[ id=" + id + " ]";
    }

    /**
     * @return the image
     */
    public byte[] getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(byte[] image) {
        this.image = image;
    }

    /**
     * @return the fingerPrint
     */
    public byte[] getFingerPrint() {
        return fingerPrint;
    }

    /**
     * @param fingerPrint the fingerPrint to set
     */
    public void setFingerPrint(byte[] fingerPrint) {
        this.fingerPrint = fingerPrint;
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
     * @return the CareerDescription
     */
    public String getCareerDescription() {
        return CareerDescription;
    }

    /**
     * @param CareerDescription the CareerDescription to set
     */
    public void setCareerDescription(String CareerDescription) {
        this.CareerDescription = CareerDescription;
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
    public void setEmail(String eMail) {
        this.email = eMail;
    }

    /**
     * @return the lastLectureId
     */
    public String getLastLectureId() {
        return lastLectureId;
    }

    /**
     * @param lastLectureId the lastLectureId to set
     */
    public void setLastLectureId(String lastLectureId) {
        this.lastLectureId = lastLectureId;
    }

    /**
     * @return the lectureInProgress
     */
    public boolean isLectureInProgress() {
        return lectureInProgress;
    }

    /**
     * @param lectureInProgress the lectureInProgress to set
     */
    public void setLectureInProgress(boolean lectureInProgress) {
        this.lectureInProgress = lectureInProgress;
    }

    /**
     * @return the lecturerType
     */
    public LecturerType getLecturerType() {
        return lecturerType;
    }

    /**
     * @param lecturerType the lecturerType to set
     */
    public void setLecturerType(LecturerType lecturerType) {
        this.lecturerType = lecturerType;
    }

    /**Gets the list of courses the lecturer is currently handling
     * @return the courses
     */
    public List<Course> getCourses() {
        return courses;
    }

    /**Sets the list of courses the lecturer is currently handling
     * @param courses the courses to set
     */
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    /**
     * @return the lecturerName
     */
    public Name getLecturerName() {
        return lecturerName;
    }

    /**
     * @param lecturerName the lecturerName to set
     */
    public void setLecturerName(Name lecturerName) {
        this.lecturerName = lecturerName;
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
     * @return the lecturerStudents
     */
    public List<LecturerStudents> getLecturerStudents() {
        return lecturerStudents;
    }

    /**
     * @param lecturerStudents the lecturerStudents to set
     */
    public void setLecturerStudents(List<LecturerStudents> lecturerStudents) {
        this.lecturerStudents = lecturerStudents;
    }

    /**
     * @return the signature
     */
    public byte[] getSignature() {
        return signature;
    }

    /**
     * @param signature the signature to set
     */
    public void setSignature(byte[] signature) {
        this.signature = signature;
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
     * @return the fingerPrintId
     */
    public long getFingerPrintId() {
        return fingerPrintId;
    }

    /**
     * @param fingerPrintId the fingerPrintId to set
     */
    public void setFingerPrintId(long fingerPrintId) {
        this.fingerPrintId = fingerPrintId;
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
}
