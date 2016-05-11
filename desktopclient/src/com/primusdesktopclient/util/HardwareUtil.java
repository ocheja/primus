/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primusdesktopclient.util;

import java.util.StringTokenizer;

/**
 *
 * @author Olisa
 */
public class HardwareUtil {
    
    public static String formatMacAddress(String MacAddress ){
        String  macAddress = new String();
        
    StringTokenizer st = new StringTokenizer(MacAddress, ",");
    for (int i = 0; i < 8; i++)
    {
           macAddress+=(st.nextToken().substring(2));
           macAddress+=" ";
              }
        return macAddress.trim();
    }
}
