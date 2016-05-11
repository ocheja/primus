/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primusdesktopclient.gsondata;

/**
 *
 * @author Olisa
 */
public class HardwareMessage {

    private String macAdress;
    private int batteryLevel;
    private String message;
    private String messageType;
    private String location;

    /**
     * @return the macAdress
     */
    public String getMacAdress() {
        return macAdress;
    }

    /**
     * @param macAdress the macAdress to set
     */
    public void setMacAdress(String macAdress) {
        this.macAdress = macAdress;
    }

    /**
     * @return the batteryLevel
     */
    public int getBatteryLevel() {
        return batteryLevel;
    }

    /**
     * @param batteryLevel the batteryLevel to set
     */
    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the messageType
     */
    public String getMessageType() {
        return messageType;
    }

    /**
     * @param messageType the messageType to set
     */
    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
}
