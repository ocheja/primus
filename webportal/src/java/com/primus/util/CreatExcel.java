/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.util;

import com.primus.data.AcademicSession;
import com.primus.data.Course;
import com.primus.data.Student;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author STUDENT
 */
public class CreatExcel {

    public static byte[] create(List<Student> studentsList, Course course, AcademicSession academicSession) throws FileNotFoundException, IOException {
        List< Object[]> data = ConvertStudentToArray(studentsList);
        String[] tableTitles = {"Student Name", "Reg No", "CA", "Exam"};
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(course.getCourseCode() + " Result Sheet (" + academicSession.getString().replaceAll("/", "_") + ")");

        CellStyle titleStyle = workbook.createCellStyle();

// create 2 fonts objects
        Font titleFont = workbook.createFont();


//set titleFont 1 to 12 point type
        titleFont.setFontHeightInPoints((short) 11);
//make it blue
        titleFont.setColor((short) Font.COLOR_NORMAL);
// make it bold
//arial is the default titleFont
        titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        titleStyle.setFont(titleFont);

        int rownum = 0;
        int count = 0, count1 = 0;

        long keyset = data.size();

        Row row = sheet.createRow(++rownum);
        row.setHeight((short) 0x249);
        Cell cell = row.createCell(0);
        String title = course.getCourseCode() + " Result Sheet (" + academicSession.getString() + ")";
        cell.setCellValue(title.toUpperCase());
        cell.setCellStyle(titleStyle);
        

        Row courseRow = sheet.createRow(++rownum);
        row.setHeight((short) 0x249);
        Cell courseCell = courseRow.createCell(0);
        courseCell.setCellValue(" COURSE :");
        courseCell.setCellStyle(titleStyle);
        courseCell = courseRow.createCell(1);
        courseCell.setCellValue(course.getCourseCode());
        //System.out.println("code :" + course.getCourseCode());
        courseCell.setCellStyle(titleStyle);

        Row academicSessionRow = sheet.createRow(++rownum);
        row.setHeight((short) 0x249);
        Cell academicSessionCell = academicSessionRow.createCell(0);
        academicSessionCell.setCellValue(" ACADEMIC SESSION: ");
        academicSessionCell.setCellStyle(titleStyle);
        academicSessionCell = academicSessionRow.createCell(1);
        academicSessionCell.setCellValue(academicSession.getString());
     //   System.out.println("code :" + course.getCourseCode());
        academicSessionCell.setCellStyle(titleStyle);
        sheet.createRow(++rownum);
        Cell cell1;
        Row row1 = sheet.createRow((int) ++rownum);
        for (int i = 0; i < tableTitles.length; i++) {
            cell1 = row1.createCell(i);
            cell1.setCellStyle(titleStyle);
            sheet.autoSizeColumn(1);
            cell1.setCellValue(tableTitles[i]);
        }

        for (rownum = 6; rownum <keyset+6; ++rownum) {
            count1++;
            row = sheet.createRow((int) rownum);
            Object[] objArr = data.get((int) count);
            int cellnum = 0;
            for (Object obj : objArr) {
                cell = row.createCell(cellnum++);
                if (obj instanceof Date) {
                    cell.setCellValue((Date) obj);
                } else if (obj instanceof Boolean) {
                    cell.setCellValue((Boolean) obj);
                } else if (obj instanceof String) {
                    cell.setCellValue((String) obj);
                } else if (obj instanceof Double) {
                    cell.setCellValue((Double) obj);
                }
            }
            count++;
        }

        ByteArrayOutputStream ByteArray = new ByteArrayOutputStream();
        workbook.write(ByteArray);
        return ByteArray.toByteArray();
    }

    
       
    private static LinkedList<Object[]> ConvertStudentToArray(List<Student> studs) {
        LinkedList<Object[]> students = new LinkedList();
        for (Student student : studs) {
             Object[] studentFields = {student.getStudentName().getSurname()+" " + student.getStudentName().getFirstName()+" "
                + student.getStudentName().getMiddleName(), student.getRegNumber()};
            students.add(studentFields);
        }
        return students;
    }
}
