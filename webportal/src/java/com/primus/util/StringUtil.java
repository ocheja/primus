/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.util;

import java.util.StringTokenizer;

/**
 *
 * @author Olisa
 */
public class StringUtil {

    public static String capitalizeFirstLetter(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
    }

    public static String capitalizeFirstLetters(String string) {
        StringTokenizer st = new StringTokenizer(string, " ");
        String meg = new String();
        while (st.hasMoreTokens()) {
            meg += capitalizeFirstLetter(st.nextToken().trim())+" ";
        }
        return meg.trim();
    }
    
}
