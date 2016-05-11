/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.data;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Ocheja Patrick Ileanwa
 */
@Entity
public class Saturday implements Serializable {
    @OneToOne(mappedBy = "saturday")
    private TimeTable timeTable;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TimeTableDailyEntries> timeTableDailyEntries;
    private String status;

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
        if (!(object instanceof Saturday)) {
            return false;
        }
        Saturday other = (Saturday) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.primus.data.Monday[ id=" + id + " ]";
    }

    /**
     * @return the timeTableDailyEntries
     */
    public List<TimeTableDailyEntries> getTimeTableDailyEntries() {
        return timeTableDailyEntries;
    }

    /**
     * @param timeTableDailyEntries the timeTableDailyEntries to set
     */
    public void setTimeTableDailyEntries(List<TimeTableDailyEntries> timeTableDailyEntries) {
        this.timeTableDailyEntries = timeTableDailyEntries;
    }
    
    
    /**
     * @return the timeTable
     */
    public TimeTable getTimeTable() {
        return timeTable;
    }

    /**
     * @param timeTable the timeTable to set
     */
    public void setTimeTable(TimeTable timeTable) {
        this.timeTable = timeTable;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
}
