/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.data;

import com.primus.enums.DayOfWeek;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Olisa
 */
@Entity
public class TimeTableDayEntry implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private DayOfWeek dayOfWeek; 
    @OneToMany
   private List<TimeTableDailyEntries> TimeTableDailyEntries;
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
        if (!(object instanceof TimeTableDayEntry)) {
            return false;
        }
        TimeTableDayEntry other = (TimeTableDayEntry) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.primus.data.TimeTableDayEntry[ id=" + id + " ]";
    }

    /**
     * @return the dayOfWeek
     */
    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    /**
     * @param dayOfWeek the dayOfWeek to set
     */
    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    /**
     * @return the TimeTableDailyEntries
     */
    public List<TimeTableDailyEntries> getTimeTableDailyEntries() {
        return TimeTableDailyEntries;
    }

    /**
     * @param TimeTableDailyEntries the TimeTableDailyEntries to set
     */
    public void setTimeTableDailyEntries(List<TimeTableDailyEntries> TimeTableDailyEntries) {
        this.TimeTableDailyEntries = TimeTableDailyEntries;
    }
    
}
