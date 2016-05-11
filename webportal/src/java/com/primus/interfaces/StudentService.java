/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.interfaces;

import com.primus.data.Student;
import com.primus.enums.AcademicLevel;
import java.util.List;

/**
 *
 * @author Olisa
 */
public interface StudentService {

    /**
     * @param Student
     *
     * @return the student
     */
    Student register(Student studnt) throws Exception;

    public Student findActiveAndNonactiveStudent(Long id) throws Exception;

     public Student findActiveStudent(Long id) throws Exception;
     
     public Student studentLogin(String regNum, int password) throws Exception;
    
    public Student findStudent(Student student) throws Exception;

    public Student findStudent(String studentRegNumberOrMatric) throws Exception;

    public List<Student> findStudentsInFaculty(String facultyName) throws Exception;

    public List<Student> findStudentsInDepartment(String departmentName) throws Exception;

    public List<Student> findStudentsInLevel(String departmentName, AcademicLevel level) throws Exception;

    public int getStudentCount();
    
    public int generateMatricSerialNumber(int yearAdmitted)throws Exception;
    
    public void deactivate(Long studentId)throws Exception;
    
     public void activate(Long studentId) throws Exception;
    
   public abstract List<Student> wildSearch(String searchString) throws Exception;
  
    public List<Student> findStudentEntities();
    
  
}
