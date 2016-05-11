/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primusdesktopclient.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Olisa
 */
@Entity
public class FingerPrintModule implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    private Location location;
    private int BatteryLevel;
    private boolean active;
     private String moduleVersion;
     private String macAddress;
    @Temporal(javax.persistence.TemporalType.DATE)
     private Date lastCommunicationTime;
     


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FingerPrintModule)) {
            return false;
        }
        FingerPrintModule other = (FingerPrintModule) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.primusdesktopclient.data.FingerPrintModule[ id=" + id + " ]";
    }

    /**
     * @return the location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * @return the BatteryLevel
     */
    public int getBatteryLevel() {
        return BatteryLevel;
    }

    /**
     * @param BatteryLevel the BatteryLevel to set
     */
    public void setBatteryLevel(int BatteryLevel) {
        this.BatteryLevel = BatteryLevel;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @return the moduleVersion
     */
    public String getModuleVersion() {
        return moduleVersion;
    }

    /**
     * @param moduleVersion the moduleVersion to set
     */
    public void setModuleVersion(String moduleVersion) {
        this.moduleVersion = moduleVersion;
    }

    /**
     * @return the macAddress
     */
    public String getMacAddress() {
        return macAddress;
    }

    /**
     * @param macAddress the macAddress to set
     */
    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    /**
     * @return the lastCommunicationTime
     */
    public Date getLastCommunicationTime() {
        return lastCommunicationTime;
    }

    /**
     * @param lastCommunicationTime the lastCommunicationTime to set
     */
    public void setLastCommunicationTime(Date lastCommunicationTime) {
        this.lastCommunicationTime = lastCommunicationTime;
    }
    
}
