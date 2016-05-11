/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.interfaces;

import com.primus.data.AcademicSession;
import com.primus.data.DepartmentName;
import com.primus.data.FacultyName;
import com.primus.data.TimeTable;
import com.primus.data.TimeTableDailyEntries;
import com.primus.enums.LectureHall;
import com.primus.gsondata.GsonLecture;
import java.util.Calendar;
import java.util.List;
import javax.inject.Named;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ocheja Patrick Ileanwa
 */
@Transactional
@Named("timetableServiceBean")
public interface TimetableService {

    public void create(TimeTable timeTable) throws Exception;

    public void edit(TimeTable timeTable) throws Exception;

    public List<TimeTable> findAllTimeTables();

    public TimeTable findTimeTableByDepartment(DepartmentName departmentName, AcademicSession academicSession) throws Exception;

    public List<TimeTable> findTimeTablesByDepartment(DepartmentName departmentName) throws Exception;

    public List<TimeTable> findTimeTablesByFaculty(FacultyName facultyName, AcademicSession academicSession) throws Exception;

    public boolean isClashing(List<TimeTableDailyEntries> currentValues, List<TimeTableDailyEntries> newValues) throws Exception;

    public boolean isSelfClashing(List<TimeTableDailyEntries> otherValues) throws Exception;

    public List<GsonLecture> getLecturesForDay(String deptName, AcademicSession academicSession, String dayOfWeek) throws Exception;
}
