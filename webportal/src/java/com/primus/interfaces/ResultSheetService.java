/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.interfaces;

import com.primus.data.AcademicSession;
import com.primus.data.Course;
import com.primus.data.DepartmentName;
import com.primus.data.ResultSheet;
import com.primus.enums.AcademicLevel;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Olisa
 */
public interface ResultSheetService {

    /**
     * @param resultSheet Can create and Edit a ResultSheet entity
     */
    public void develop(ResultSheet resultSheet) throws Exception;

    /**
     * @param resultSheet
     * @return GradeStatistics The statistics of the result in a map containing
     * the gradeLetter and the number of occurrence eg. number of A = 10,B= 4
     */
    public Map<String, Integer> getGradeStatistics(ResultSheet resultSheet) throws Exception;

   public ResultSheet getApprovedSheet(DepartmentName deptName, Course course,AcademicLevel level) throws Exception;
   
    public List<ResultSheet> getNotApprovedSheet(DepartmentName deptName,AcademicSession academicSession,AcademicLevel level) throws Exception;

    public List<ResultSheet> getResultSheets(DepartmentName deptName,AcademicSession academicSession,AcademicLevel level)throws Exception;
    /**
     * @param course bytes uploads a ResultSheet into the database
     */
    public void uploadResultSheet(Course course, Byte[] bytes) throws Exception;
}
