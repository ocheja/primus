/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.data;

import com.primus.enums.*;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author STUDENT
 */
@Entity
public class Course implements Serializable {

    @OneToMany(mappedBy = "course" ,cascade = CascadeType.ALL)
    private List<LecturerStudents> lecturerStudents;
    @OneToOne(mappedBy = "prerequisiteCourseID", cascade = CascadeType.ALL)
    private Prerequisite prerequisite;
    @OneToOne(mappedBy = "courseID", cascade = CascadeType.ALL)
    private Prerequisite prerequisite1;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private static final long serialVersionUID = 1L;
    @ManyToOne()
    private DegreeRequirement degreeRequirement;
    @OneToOne
    private DepartmentName departmentName;
    @Enumerated(EnumType.STRING)
    private Semester semester;
    @Enumerated(EnumType.STRING)
    private AcademicLevel courseLevel;
    @NotNull
    private int courseUnitLoad;
    private String Description;
    @NotNull
    private String courseTitle;
    @NotNull
    private String courseCode;
    private boolean courseStatus = false;
    @ManyToMany(mappedBy = "courses")
    private List<Lecturer> tutors;
    private boolean prerequisitesCompulsory = true;
    private int requiredLectureHours;
    private boolean active = true;
      private boolean elective = false;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the courseUnit to set
     */
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
        if (!(object instanceof Course)) {
            return false;
        }
        Course other = (Course) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "studentRegistrationEntities.Course[ id=" + id + " ]";
    }

    /**
     * @return the courseUnit
     */
    public int getCourseUnitLoad() {
        return courseUnitLoad;

    }

    /**
     * @param courseUnit the courseUnit to set
     */
    public void setCourseUnitLoad(int courseUnit) {
        this.courseUnitLoad = courseUnit;
    }

//    public static void main(String[] args) {
//        Course introToBiology = new Course();
//    introToBiology.setFaculty("Faculty of Science");
//    }
    /**
     * @return the courseLevel
     */
    public AcademicLevel getCourseLevel() {
        return courseLevel;
    }

    /**
     * @param courseLevel the courseLevel to set
     */
    public void setCourseLevel(AcademicLevel courseLevel) {
        this.courseLevel = courseLevel;
    }

    /**
     * @return the courseTitle
     */
    public String getCourseTitle() {
        return courseTitle;
    }

    /**
     * @param courseTitle the courseTitle to set
     */
    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    /**
     * @return the courseStatus
     */
    public boolean isCourseStatus() {
        return courseStatus;
    }

    /**
     * @param courseStatus the courseStatus to set
     */
    public void setCourseStatus(boolean courseStatus) {
        this.courseStatus = courseStatus;
    }

    /**
     * @return the courseCode
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * @param courseCode the courseCode to set
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
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
     * @return the departmentName
     */
    public DepartmentName getDepartmentName() {
        return departmentName;
    }

    /**
     * @param departmentName the departmentName to set
     */
    public void setDepartmentName(DepartmentName departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * @return the Description
     */
    public String getDescription() {
        return Description;
    }

    /**
     * @param Description the Description to set
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }

    /**
     * @return the tutors
     */
    public List<Lecturer> getTutors() {
        return tutors;
    }

    /**
     * @param tutors the tutors to set
     */
    public void setTutors(List<Lecturer> tutors) {
        this.tutors = tutors;
    }

    /**
     * @return the degreeRequirement
     */
    public DegreeRequirement getDegreeRequirement() {
        return degreeRequirement;
    }

    /**
     * @param degreeRequirement the degreeRequirement to set
     */
    public void setDegreeRequirement(DegreeRequirement degreeRequirement) {
        this.degreeRequirement = degreeRequirement;
    }

    /**
     * @return the prerequisitesCompulsory
     */
    public boolean isPrerequisitesCompulsory() {
        return prerequisitesCompulsory;
    }

    /**
     * @param prerequisitesCompulsory the prerequisitesCompulsory to set
     */
    public void setPrerequisitesCompulsory(boolean prerequisitesCompulsory) {
        this.prerequisitesCompulsory = prerequisitesCompulsory;
    }

    /**
     * @return the requiredLectureHours
     */
    public int getRequiredLectureHours() {
        return requiredLectureHours;
    }

    /**
     * @param requiredLectureHours the requiredLectureHours to set
     */
    public void setRequiredLectureHours(int requiredLectureHours) {
        this.requiredLectureHours = requiredLectureHours;
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
     * @return the elective
     */
    public boolean isElective() {
        return elective;
    }

    /**
     * @param elective the elective to set
     */
    public void setElective(boolean elective) {
        this.elective = elective;
    }
}
