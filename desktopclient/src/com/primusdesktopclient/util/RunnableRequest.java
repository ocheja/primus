/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primusdesktopclient.util;

import com.primusdesktopclient.gsondata.HardwareMessage;

/**
 *
 * @author Olisa
 */
public class RunnableRequest {
  private  String header;
   private HardwareMessage body; 
   private boolean action= false; // means keep recieving by default 

    /**
     * @return the header
     */
    public String getHeader() {
        return header;
    }

    /**
     * @param header the header to set
     */
    public void setHeader(String header) {
        this.header = header;
    }

    /**
     * @return the body
     */
    public HardwareMessage getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(HardwareMessage body) {
        this.body = body;
    }

    /**
     * @return the action
     * To transmit data set action= true; else receive
     */
    public boolean isAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(boolean action) {
        this.action = action;
    }
    
    
}
