/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.controller;

import com.primus.data.Grade;
import com.primus.data.Student;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 *
 * @author Olisa
 */
public class tester {
    
    public static void main(String args[]){
//       Student stud = new Student();
//      Grade g = new Grade();
//      Grade c = new Grade();
//      g.setTotalScore(60);
//      c.setTotalScore(60);
//   
//      if(c.equals(g)){
//        System.out.println("a knknnk: ");
//      }
        System.out.println("Date: "+new GregorianCalendar(TimeZone.getTimeZone("GMT+1")).get(Calendar.HOUR_OF_DAY));
    }
    
}
