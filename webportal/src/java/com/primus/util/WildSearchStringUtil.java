/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.util;

import org.springframework.stereotype.Component;

/**
 *
 * @author Olisa
 */
public class WildSearchStringUtil {

    public static Object checkInteger(String string) {
        Object obj;
        try {
            return obj = Integer.parseInt(string.replace("%", ""));
        } catch (Exception e) {
            return 0;
        }
    }

    public static Object checkLong(String string) {
        Object obj;
        try {
            return obj = Long.parseLong(string.replace("%", ""));
        } catch (Exception e) {
            return null;
        }
    }

    public static Object checkFloat(String string) {
        Object obj;
        try {
            return obj = Float.parseFloat(string.replace("%", ""));
        } catch (Exception e) {
            return null;
        }
    }

    public static Object checkEnum(Class cls,String string) {
        Object obj;
        try {
            return obj = Enum.valueOf(cls, string);
        } catch (Exception e) {
            //System.out.println(e.getLocalizedMessage());
           return null;
        }
    }
    
//    public static void main(String[] agrs){
//        System.out.println("The result is : " + ((Float)checkFloat("23")) );
//    }
}
