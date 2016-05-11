/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.data;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author STUDENT
 */
@Entity
public class Room implements Serializable {
   private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   private long roomNumber;
    
    @OneToMany(mappedBy="room",cascade=CascadeType.ALL)
    private List<Student> students;
    
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn()
    private Hostel hostel;

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
        if (!(object instanceof Room)) {
            return false;
        }
       Room other = (Room) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "studentRegistrationEntities.Room[ id=" + id + " ]";
    }
    /**
     * @return the roomNumber
     */
    public long getRoomNumber() {
        return roomNumber;
    }

    /**
     * @param roomNumber the roomNumber to set
     */
    public void setRoomNumber(long roomNumber) {
        this.roomNumber = roomNumber;
    }

    /**
     * @return the students
     */
    public List<Student> getStudents() {
        return students;
    }

    

    /**
     * @param students the students to set
     */
    public void setStudents(List<Student> students) {
        this.students = students;
    }

    /**
     * @return the hostel
     */
    public Hostel getHostel() {
        return hostel;
    }

    /**
     * @param hostel the hostel to set
     */
    public void setHostel(Hostel hostel) {
        this.hostel = hostel;
    }
    
}
