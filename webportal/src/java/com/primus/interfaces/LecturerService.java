/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.interfaces;

import com.primus.data.AcademicSession;
import com.primus.data.Course;
import com.primus.data.DepartmentName;
import com.primus.data.Lecturer;
import com.primus.data.Student;
import com.primus.enums.Semester;
import java.util.List;

/**
 *
 * @author Olisa
 */
public interface LecturerService {

    public void create(Lecturer lecturer) throws Exception;

    public void edit(Lecturer lecturer) throws Exception;

    public Lecturer findLecturer(String eMail) throws Exception;

    public Lecturer findLecturerLogin(String userId, int password) throws Exception;

    public Lecturer findLecturer(Long id) throws Exception;

    public Lecturer findLecturerByFingerPrintId(Long fingerPrintID, DepartmentName departmentName) throws Exception;

    public int getLecturerCount();

    public void destroy(Long id) throws Exception;

    public List<Lecturer> findLecturerEntities() throws Exception;

    public List<Lecturer> findLecturerEntities(int maxResults, int firstResult) throws Exception;

    public List<Lecturer> findLecturers(String DepartmentName) throws Exception;

    public void deactivateLecturer() throws Exception;

    public List<Lecturer> wildSearch(String searchString) throws Exception;

    public List<Course> getCoursesAssignedToLecturer(Long lecturerId, AcademicSession academicSessionr) throws Exception;

    public List<Course> getCoursesAssignedToLecturer(Long lecturerId, AcademicSession academicSessionr, Semester semester) throws Exception;

    public List<Student> getRegisteredStudentsForCourse(long lecturerId, Course course, AcademicSession academicSession) throws Exception;

    public List<Student> getRegisteredStudentsForCourseByDept(long lecturerId, Course course, AcademicSession academicSession, DepartmentName departmentName) throws Exception;
}
