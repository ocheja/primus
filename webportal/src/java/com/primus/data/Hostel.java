/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.data;

import com.primus.enums.Gender;
import com.primus.enums.HostelName;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author STUDENT
 */
@Entity
public class Hostel implements Serializable {

   private static long serialVersionUID = 1L;
 
    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private HostelName hostelName;
    private Gender hostelType;
    private boolean hostelStatus;
    @OneToMany(mappedBy = "hostel", cascade = CascadeType.ALL)
    private List<Room> rooms;
     private boolean active;
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hostelName != null ? hostelName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hostel)) {
            return false;
        }
        Hostel other = (Hostel) object;
        if ((this.hostelName == null && other.hostelName != null)
                || (this.hostelName != null && !this.hostelName.equals(other.hostelName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "studentRegistrationEntities.Hostel[ id=" + hostelName + " ]";
    }

    /**
     * @return the hostelName
     */
    public HostelName getHostelName() {
        return hostelName;
    }

    /**
     * @param hostelName the hostelName to set
     */
    public void setHostelName(HostelName hostelName) {
        this.hostelName = hostelName;
    }

    /**
     * @return the Rooms
     */
    public List<Room> getRooms() {
        return rooms;
    }

    /**
     * @param Rooms the Rooms to set
     */
    public void setRooms(List<Room> Rooms) {
        this.rooms = Rooms;
    }

    /**
     * @return the hostelStatus
     */
    public boolean getHostelStatus() {
        return hostelStatus;
    }

    /**
     * @param hostelStatus the hostelStatus to set
     */
    public void setHostelStatus(boolean hostelStatus) {
        this.hostelStatus = hostelStatus;
    }

    /**
     * @return the hostelType
     */
    public Gender getHostelType() {
        return hostelType;
    }

    /**
     * @param hostelType the hostelType to set
     */
    public void setHostelType(Gender hostelType) {
        this.hostelType = hostelType;
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
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
}
