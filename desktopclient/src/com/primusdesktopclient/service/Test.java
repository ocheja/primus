/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primusdesktopclient.service;

import com.primusdesktopclient.gsondata.Lecturer;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author Olisa
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        URL url = ClassLoader.getSystemResource("com\\primusdesktopclient\\service\\log4j.properties");
        PropertyConfigurator.configure(url);
       Lecturer lecturer = new Lecturer();
        UIService service = new UIService();
       try {
           lecturer =    service.getLecturer("okpoko");
        } catch (Exception ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
       }
        boolean status = service.registerLecturer("CLASS1", Long.valueOf(lecturer.getLecturerID()));
        System.out.println("lecturer status is  "+status);
       
    }
}
