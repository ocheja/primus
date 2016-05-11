/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.util;

import com.primus.data.Indicator;
import com.primus.data.StudentResultSheet;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Olisa
 */
public class ExcelUtil {

    public static void getExcelTemplate(Class<?> cls, FileOutputStream out) throws Exception {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(cls.getSimpleName().toUpperCase() + "S TEMPLATE");
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

        String[] tableTitles = removeCollectionTypes(getEntityProperties(cls)).keySet().toArray(new String[getEntityProperties(cls).keySet().size()]);

        int rownum = 0;
        // int count = 0, count1 = 0;
        Row row = sheet.createRow(++rownum);
        row.setHeight((short) 0x249);
        Cell cell = row.createCell(0);
        cell.setCellValue(cls.getSimpleName().toUpperCase() + "S TEMPLATE");
        cell.setCellStyle(titleStyle);
        Cell cell1;
        Row row1 = sheet.createRow((int) ++rownum);
        for (int i = 0; i < tableTitles.length; i++) {
            cell1 = row1.createCell(i);
            cell1.setCellStyle(titleStyle);
            sheet.autoSizeColumn(1);
            cell1.setCellValue(tableTitles[i]);
        }
        workbook.write(out);
        out.close();
        System.out.println("Activation List Excel Generated successfully..At :" + out.getFD().toString());;

    }
    public static void getExcelTemplate(String[] tableTtles,String templateName, FileOutputStream out) throws Exception {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(templateName + "S TEMPLATE");
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

        String[] tableTitles = tableTtles;
        int rownum = 0;
        // int count = 0, count1 = 0;
        Row row = sheet.createRow(++rownum);
        row.setHeight((short) 0x249);
        Cell cell = row.createCell(0);
        cell.setCellValue(templateName + "S TEMPLATE");
        cell.setCellStyle(titleStyle);
        Cell cell1;
        Row row1 = sheet.createRow((int) ++rownum);
        for (int i = 0; i < tableTitles.length; i++) {
            cell1 = row1.createCell(i);
            cell1.setCellStyle(titleStyle);
            sheet.autoSizeColumn(1);
            cell1.setCellValue(tableTitles[i]);
        }
        workbook.write(out);
        out.close();
        System.out.println("Activation List Excel Generated successfully..At :" + out.getFD().toString());;

    }

    private static Map<String, Class<?>> getModelProperties(Class<?> cls) throws Exception {
        Map<String, Class<?>> properties = new HashMap<>();

        for (Method method : cls.getDeclaredMethods()) {
            String methodName = method.getName();
            if (methodName.startsWith("get")) {
                // if the property type is a collection, change the Type to long
                properties.put(Character.toLowerCase(methodName.charAt(3)) + methodName.substring(4), method.getReturnType());

            }

        }
        return properties;
    }

    private static Map<String, Class<?>> getEntityProperties(Class<?> cls) throws Exception {

//        if (!cls.isAnnotationPresent(Entity.class)) {
//            throw new Exception("Class must be an Entity Annotated class");
//        }

        Map<String, Class<?>> properties = new HashMap<>();
        for (Field field : cls.getDeclaredFields()) {
            String fieldName = field.getName();
            Class<?> fieldClass = field.getType();
            if (!(fieldName.equalsIgnoreCase("Id") || fieldName.equalsIgnoreCase("serialVersionUID"))) { // dont put id and serialVersionUID fields

                if (field.isAnnotationPresent(javax.persistence.Embedded.class) && !field.getType().isAssignableFrom(Indicator.class)) {
                    properties.putAll(getEntityProperties(field.getType()));
                    //  throw new Exception("Class must be an Entity Annotated class");
                } else {
                    properties.put(fieldName, fieldClass);
                }
            }
        }
        return properties;
    }

    private static Map<String, Class<?>> removeCollectionTypes(Map<String, Class<?>> prop) {
        Map<String, Class<?>> properties = new HashMap<>();
        for (String string : prop.keySet()) {
            Class<?> cls = prop.get(string);
            if (!(cls.isAssignableFrom(Collection.class) || cls.isAssignableFrom(List.class) || cls.isAssignableFrom(Set.class))) {
                properties.put(string, cls);
            }
        }
        return properties;
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        
        FileOutputStream out = null;
        String outputLocation = "C:\\Users\\Olisa\\Desktop\\template.xls";
        try {
            out = new FileOutputStream(new File(outputLocation));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExcelUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            getExcelTemplate(StudentResultSheet.class, out);
        } catch (Exception ex) {
            Logger.getLogger(ExcelUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
