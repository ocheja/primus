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
import com.primus.service.exceptions.PrimusServiceException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;

/**
 *
 * @author Olisa
 */
public interface LecturerResultSheetService {

    /**
     * Can create and Edit a ResultSheet entity
     *
     * @param resultSheet
     */
    public void develop(ResultSheet resultSheet) throws Exception;

    /**
     *
     * @param resultSheet
     * @throws Exception
     */
    public void create(ResultSheet resultSheet) throws Exception;

    /**
     * Returns The statistics of the result in a map containing the gradeLetter
     * and the number of occurrence eg. number of A = 10,B= 4
     *
     * @param resultSheet
     * @return GradeStatistics
     */
    public Map<String, Integer> getGradeStatistics(ResultSheet resultSheet) throws Exception;

    public ResultSheet findResultSheet(Long id) throws PrimusServiceException;

    public ResultSheet getApprovedSheet(DepartmentName deptName, AcademicSession academicSession, Course course, AcademicLevel level) throws Exception;

    public List<ResultSheet> getApprovedResultSheets(DepartmentName deptName, AcademicSession academicSession, AcademicLevel level) throws Exception;

    public List<ResultSheet> getNotApprovedSheet(DepartmentName deptName, AcademicSession academicSession, AcademicLevel level) throws Exception;

    public List<ResultSheet> getResultSheets(DepartmentName deptName, AcademicSession academicSession, AcademicLevel level) throws Exception;

    public ResultSheet getNotApprovedSheet(Course course, DepartmentName deptName, AcademicSession academicSession, AcademicLevel level) throws Exception;

    public ResultSheet getResultSheet(Course course, DepartmentName deptName, AcademicSession academicSession, AcademicLevel level) throws Exception;

    /**
     * Uploads a ResultSheet into the database
     *
     * @param course bytes
     */
    public void uploadResultSheet(byte[] bytes, String deptName, AcademicSession academicSession, AcademicLevel level, Date examDate) throws Exception;

    public byte[] getResultSheetFormat(long courseId, AcademicSession academicSession, Long LecturerId) throws Exception;

    public byte[] getResultSheetFormatByDept(long courseId, AcademicSession academicSession, Long LecturerId, DepartmentName departmentName) throws Exception;

    public byte[] getLecturerResultSheetPDF(Course course,DepartmentName deptName, AcademicLevel academicLevel, AcademicSession academicSession, ServletContext servletContext) throws Exception;
}
