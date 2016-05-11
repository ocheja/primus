/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.data;

import com.primus.enums.AcademicLevel;
import com.primus.enums.Semester;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Olisa
 */
@Entity
public class StudentResultSheet implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
     @NotNull
    private Student student;
   // @ElementCollection(targetClass = Grade.class)
    @JoinTable()
//  @Column(name = "Grade_id")
    @OneToMany(cascade = CascadeType.PERSIST)
 //   @MapKey() 
    private List<Grade> resgisteredCoursesGrades;
     @NotNull
    private AcademicLevel StudentLevel;
    @ManyToOne
     @NotNull
    private AcademicSession academicSession;
    @NotNull
    private Semester semester;
    
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
        if (!(object instanceof StudentResultSheet)) {
            return false;
        }
        StudentResultSheet other = (StudentResultSheet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.primus.data.StudentResultSheet[ id=" + id + " ]";
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
     * @return the resgisteredCoursesGrades
     */
    public List<Grade> getResgisteredCoursesGrades() {
        return resgisteredCoursesGrades;
    }

    /**
     * @param resgisteredCoursesGrades the resgisteredCoursesGrades to set
     */
    public void setResgisteredCoursesGrades(List<Grade> resgisteredCoursesGrades) {
        this.resgisteredCoursesGrades = resgisteredCoursesGrades;
    }

    /**
     * @return the StudentLevel
     */
    public AcademicLevel getStudentLevel() {
        return StudentLevel;
    }

    /**
     * @param StudentLevel the StudentLevel to set
     */
    public void setStudentLevel(AcademicLevel StudentLevel) {
        this.StudentLevel = StudentLevel;
    }

    /**
     * @return the AcademicSession
     */
    public AcademicSession getAcademicSession() {
        return academicSession;
    }

    /**
     * @param AcademicSession the AcademicSession to set
     */
    public void setAcademicSession(AcademicSession AcademicSession) {
        this.academicSession = AcademicSession;
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
    
}
