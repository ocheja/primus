/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.interfaces;

import com.primus.data.AcademicSession;
import com.primus.data.Course;
import com.primus.data.StudentResultSheet;
import com.primus.enums.AcademicLevel;
import com.primus.enums.Semester;
import java.util.List;
import javax.servlet.ServletContext;

/**
 *
 * @author Olisa
 */
public interface StudentResultSheetService {

    public void develop(StudentResultSheet studentResultSheet) throws Exception;

     public byte[] getStudentResultSheetPDF(long studentId, Semester semester,AcademicLevel academicLevel,AcademicSession academicSession,ServletContext servletContext) throws Exception;

    public StudentResultSheet findStudentResultSheet(long studentId, AcademicLevel level, Semester semester) throws Exception;

    public StudentResultSheet findStudentResultSheet(Long id) throws Exception;
    
    public List<StudentResultSheet> getStudentResultSheets(long studentId, AcademicLevel level) throws Exception;

    public List<?> getGrade(long StudentId,Course course) throws Exception;
    
    public List<StudentResultSheet> getAllStudentResultSheets(long studentId) throws Exception;
      /**
     * @param student
     * @return CGPA the CGPA of a student
     */
    public Float getCGPA(long studentId,AcademicSession academicSession) throws Exception;

    /**
     * @param studentResultSheets
     * @return GPA the GPA of d resultSheets
     */
    public Float getGPA(StudentResultSheet studentResultSheets) throws Exception;
}
