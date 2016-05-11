/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.util;

import com.primus.data.AcademicSession;
import com.primus.data.Course;
import com.primus.data.LecturerResultGrade;
import com.primus.data.ResultSheet;
import com.primus.data.Student;
import com.primus.enums.Status;
import com.primus.interfaces.CourseService;
import com.primus.interfaces.StudentService;
import com.primus.serviceBean.AcademicSessionServiceBean;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Olisa
 */
@Component("ReadResultsheetExcel")
public class ReadResultsheetExcel {

    @Autowired
    StudentService studentServiceBean;
    @Autowired
    AcademicSessionServiceBean academicSessionServiceBean;
    @Autowired
    CourseService courseServiceBean;
    Cell cell;
    List<Map<String, Object>> excelData;
    Map<String, Object> rowData;

    public ResultSheet readResultSheet(byte[] bytes) throws Exception {
        HSSFWorkbook workbook;
         excelData = new ArrayList<>();
        int rowCount = 0;
        int columnCount = 0;
        try (InputStream file = new ByteArrayInputStream(bytes)) {
            workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            System.out.println("sheet: "+sheet.getFirstRowNum()+" : "+sheet.getLastRowNum());
            while (rowIterator.hasNext()) {
              
                Row row = rowIterator.next();
                rowCount++;
                //For each row, iterate through each columns
                rowData = new HashMap<>();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    cell = cellIterator.next();
                    columnCount++;
                      System.out.println("point "+rowCount+": "+columnCount);
                    if (rowCount == 2 && columnCount == 2) {
                        rowData.put("courseCode", getValue());
                      System.out.println("courseCode : "+getValue());
                    }
                    if (rowCount == 3 && columnCount == 2) {
                        rowData.put("academicSession", getValue());
                          System.out.println(" academicSession : "+getValue());
                    }
                    if (rowCount >= 5 && columnCount <= 4) {
                        if (columnCount == 1) {
                            rowData.put("name", getValue());
                             System.out.println(" name : "+getValue());
                        }
                        if (columnCount == 2) {
                            rowData.put("regNo", getValue());
                             System.out.println(" regNo : "+getValue());
                        }
                        if (columnCount == 3) {
                            rowData.put("CA", getValue());
                             System.out.println(" CA : "+getValue());
                        }
                        if (columnCount == 4) {
                            rowData.put("exam", getValue());
                             System.out.println(" exam : "+getValue());
                        }
                    }

                }
                excelData.add(rowData);
                columnCount = 0;
                System.out.println("rowData: "+rowData);
            }
            rowCount = 0;
            
            System.out.println("excelData: "+ excelData);
        }
//     FileOutputStream out =
//                new FileOutputStream(new File("C:\\ResultSheetUpload.xls"));
//        workbook.write(out);
//        out.close();
        return createResultsheet(excelData);
    }

    Object getValue() {
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue();
            case Cell.CELL_TYPE_NUMERIC:
                return cell.getNumericCellValue();
            case Cell.CELL_TYPE_STRING:
               return cell.getStringCellValue();
            case Cell.CELL_TYPE_BLANK:
                return  new Object();
            default:
                return new Object();
        }
    }

    private ResultSheet createResultsheet(List<Map<String, Object>> excelD) throws Exception {
        ResultSheet resultSheet = new ResultSheet();
        System.out.println("course code: "+(String) excelD.get(1).get("courseCode"));
        Course course = courseServiceBean.findCourse((String) excelD.get(1).get("courseCode"));
        if (course == null) {
            throw new Exception(" Error creating Resultsheet. Course with code: " + excelD.get(1).get("courseCode") + " those not exist");
        }
        resultSheet.setCourse(course);
         resultSheet.setDeptName(course.getDepartmentName());
        AcademicSession academicSession = academicSessionServiceBean.findAcademicSession(((String) excelD.get(2).get("academicSession")).split("/")[0], ((String) excelD.get(2).get("academicSession")).split("/")[1]);
        if (academicSession == null) {
            throw new Exception(" Error creating Resultsheet. for the academicSession " + excelD.get(2).get("academicSession") + " those not exist");
        }
        resultSheet.setAcademicSession(academicSession);
        resultSheet.setDateOfUpdate(new Date());
        resultSheet.setStatus(Status.IN_PROGRESS);
        int studentNum = excelD.size() - 2;
        List<LecturerResultGrade> studentGrade = new ArrayList<>();
        for (int i = 4; i < excelD.size(); i++) {
            Map<String, Object> rowD = excelD.get(i);
            LecturerResultGrade grade = new LecturerResultGrade();
            Student student = studentServiceBean.findStudent((String) rowD.get("regNo"));
            if (student == null) {
                throw new Exception(" Error creating Resultsheet.The student \"" + (String) rowD.get("name") + "\" with Registration number" + (String) rowD.get("regNo") + " those not exist");
            }
            grade.setContinousAssesmentScore(Float.parseFloat( rowD.get("CA").toString()));
            grade.setExamScore(Float.parseFloat( rowD.get("exam").toString()));
            grade.setStudent(student);
            studentGrade.add(grade);
        }
        resultSheet.setLecturerResultGrade(studentGrade);
        return resultSheet;

    }
}
