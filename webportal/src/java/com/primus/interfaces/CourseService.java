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
import com.primus.enums.AcademicLevel;
import com.primus.enums.Semester;
import java.util.List;
import javax.inject.Named;
import javax.transaction.Transactional;

/**
 *
 * @author Olisa
 */
@Transactional
@Named("courseService")
public abstract interface CourseService {

    public void create(Course course) throws Exception;

    public void edit(Course course) throws Exception;

    public void destroy(Long id) throws Exception;

    public abstract void develop(Course course) throws Exception;

    public abstract Course findCourse(String courseCode) throws Exception;

    public abstract Course findCourse(Long id) throws Exception;

    public abstract List<Course> wildSearch(String searchString) throws Exception;

    public abstract List<Course> getCoursesForLevel(DepartmentName departmentName, AcademicLevel level, Semester sems) throws Exception;

    public int getCourseCount();

    public abstract List<Course> getAllCourses(DepartmentName departmentName) throws Exception;

    public abstract List<Course> getCoursesForSemester(DepartmentName departmentName, Semester sems) throws Exception;

    public abstract List<Student> getStudentsOfferingCourse(Course course, AcademicSession academicSession, Long LecturerId) throws Exception;

    public List<Student> getStudentsOfferingCourseByDept(Course course, AcademicSession academicSession, Long LecturerId, DepartmentName departmentName) throws Exception;

    public abstract void setPrerequisites(Course course, List<Course> prerequisites) throws Exception;// check level and semester

    public abstract List<Course> getPrerequisites(Long courseId) throws Exception;

    public List<Course> findCourseEntities();

    public abstract void removePrerequisites(Course course, List<Long> prerequisitesID) throws Exception;

    public abstract void assignCourseToLecturer(List<Course> courses, Lecturer lecturer) throws Exception;

    public void removeCourseFromLecturer(List<Course> courses, Lecturer lecturer) throws Exception;

    public List<Course> getCoursesAssignedToLecturer(Long lecturerId, AcademicSession academicSessionr) throws Exception;

    public List<Course> getCoursesAssignedToLecturer(Long lecturerId, AcademicSession academicSessionr, Semester semester) throws Exception;

    public List<Course> findByDepartment(DepartmentName departmentName)  throws Exception;
}
