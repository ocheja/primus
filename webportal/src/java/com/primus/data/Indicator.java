/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.data;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author Olisa
 */
@Embeddable
public class Indicator implements Serializable {
    private boolean fromAcademicAdvicer;
    private boolean fromDepartment;
     private boolean fromFaculty;
    private boolean fromOthers;

    /**
     * @return the fromAcademicAdvicer
     */
    public boolean isFromAcademicAdvicer() {
        return fromAcademicAdvicer;
    }

    /**
     * @param fromAcademicAdvicer the fromAcademicAdvicer to set
     */
    public void setFromAcademicAdvicer(boolean fromAcademicAdvicer) {
        this.fromAcademicAdvicer = fromAcademicAdvicer;
    }

    /**
     * @return the fromDepartment
     */
    public boolean isFromDepartment() {
        return fromDepartment;
    }

    /**
     * @param fromDepartment the fromDepartment to set
     */
    public void setFromDepartment(boolean fromDepartment) {
        this.fromDepartment = fromDepartment;
    }

    /**
     * @return the fromOthers
     */
    public boolean isFromOthers() {
        return fromOthers;
    }

    /**
     * @param fromOthers the fromOthers to set
     */
    public void setFromOthers(boolean fromOthers) {
        this.fromOthers = fromOthers;
    }

    /**
     * @return the fromFaculty
     */
    public boolean isFromFaculty() {
        return fromFaculty;
    }

    /**
     * @param fromFaculty the fromFaculty to set
     */
    public void setFromFaculty(boolean fromFaculty) {
        this.fromFaculty = fromFaculty;
    }
}
