/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primusdesktopclient.data;

import com.primusdesktopclient.enums.DepartmentNameEnum;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Olisa
 */
@Entity
public class Location implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   @OneToOne(mappedBy = "location")
    private FingerPrintModule fingerPrintModule;
    private DepartmentNameEnum departmentName;
    private String address;
    
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
        if (!(object instanceof Location)) {
            return false;
        }
        Location other = (Location) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.primusdesktopclient.data.Location[ id=" + id + " ]";
    }

    /**
     * @return the departmentName
     */
    public DepartmentNameEnum getDepartmentName() {
        return departmentName;
    }

    /**
     * @param departmentName the departmentName to set
     */
    public void setDepartmentName(DepartmentNameEnum departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the fingerPrintModule
     */
    public FingerPrintModule getFingerPrintModule() {
        return fingerPrintModule;
    }

    /**
     * @param fingerPrintModule the fingerPrintModule to set
     */
    public void setFingerPrintModule(FingerPrintModule fingerPrintModule) {
        this.fingerPrintModule = fingerPrintModule;
    }
    
}
