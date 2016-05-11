/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.util;

import com.primus.data.ResultSheet;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author Olisa
 */
public class ReadExcel {
    String courseCode;
    String academicSession;
    ResultSheet resultSheet;
    
    
    public static void readResultSheet(byte[] bytes) throws FileNotFoundException, IOException {
         HSSFWorkbook workbook;
         int rowTraker=0;
         int columnTraker=0;
        try (InputStream file = new ByteArrayInputStream(bytes)) {
            workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
          
            while(rowIterator.hasNext()) {
                Row row = rowIterator.next();
                 rowTraker++;
                //For each row, iterate through each columns
                Iterator<Cell> cellIterator = row.cellIterator();
                while(cellIterator.hasNext()) {
                     
                    Cell cell = cellIterator.next();
                     
                    switch(cell.getCellType()) {
                        case Cell.CELL_TYPE_BOOLEAN:
                            System.out.print(cell.getBooleanCellValue() + "\t\t");
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            System.out.print(cell.getNumericCellValue() + "\t\t");
                            break;
                        case Cell.CELL_TYPE_STRING:
                            System.out.print(cell.getStringCellValue() + "\t\t");
                            break;
                    }
                }
                System.out.println("");
            }
        }
    
        
        FileOutputStream out =
        new FileOutputStream(new File("C:\\ResultSheetUpload.xls"));
         workbook.write(out);
        out.close();
     
     }
     
  
}
