
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.interfaces;

import com.primus.data.AcademicSession;
import com.primus.data.Course;
import com.primus.data.CourseForm;
import com.primus.data.DepartmentName;
import com.primus.enums.AcademicLevel;
import com.primus.enums.Semester;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletContext;

/**
 *
 * @author Olisa
 */
public interface CourseRegistrationService {

    public List<Course> getRequiredCourses(long studentId, Semester semester) throws Exception;

    public void registerCourses(long studentId, Semester semester, AcademicSession academicSession, List<Course> courses) throws Exception;

    public byte[] getCourseFormPDF(long studentId, Semester semester,AcademicSession academicSession,ServletContext servletContext) throws Exception;

    public List<Semester> getRegisteredSemester(long studentId, AcademicSession session) throws Exception;

    public List<AcademicSession> getRegisteredAcademicSession(long studentId) throws Exception;

    public CourseForm getApprovedCourseForm(long studentId, AcademicSession session, Semester semester) throws Exception;

    public CourseForm getCourseForm(long studentId, AcademicSession session, Semester semester) throws Exception ;
   
    public List<CourseForm> getApprovedCourseForms(long studentId) throws Exception;

    public List<CourseForm> getNonApprovedCourseForms(long studentId) throws Exception;

    public List<CourseForm> getApprovedCourseForms(long studentId, AcademicSession session) throws Exception;

    public List<CourseForm> getNonApprovedCourseForms(long studentId, AcademicSession session) throws Exception;

    public List<CourseForm> getCourseForms(long studentId, AcademicSession session) throws Exception;

    public List<CourseForm> getCourseForms(String sString, DepartmentName departmentName, AcademicSession academicSession, Semester semester, AcademicLevel AcademicLevel, boolean AcademicAdviserAprroved, boolean HODAprroved, boolean FacultyOfficerAprroved) throws Exception;

    public Set<Course> getFailedCourses(Long StudentId, Semester semester) throws Exception;
    
 public List<AcademicLevel> getRegisteredLevel(long studentId) throws Exception;
}
