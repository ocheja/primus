/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.data;

import com.primus.enums.TitleOfDegree;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Olisa
 */
@Entity
public class Degree implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private TitleOfDegree titleOfDegree;
    @NotNull
    private int minAllowableYears;
    @NotNull
    private int maxAllowableYears;
    @OneToMany(mappedBy = "degree",cascade = CascadeType.ALL)
   private List<DegreeRequirement> degreeRequirementOfEachLevel;
    @OneToOne
    private DepartmentName departmentName;
    
    
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
        if (!(object instanceof Degree)) {
            return false;
        }
        Degree other = (Degree) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.primus.entity.Degree[ id=" + id + " ]";
    }

    /**
     * @return the titleOfDegree
     */
    public TitleOfDegree getTitleOfDegree() {
        return titleOfDegree;
    }

    /**
     * @param titleOfDegree the titleOfDegree to set
     */
    public void setTitleOfDegree(TitleOfDegree titleOfDegree) {
        this.titleOfDegree = titleOfDegree;
    }

    /**
     * @return the minAllowableYears
     */
    public int getMinAllowableYears() {
        return minAllowableYears;
    }

    /**
     * @param minAllowableYears the minAllowableYears to set
     */
    public void setMinAllowableYears(int minAllowableYears) {
        this.minAllowableYears = minAllowableYears;
    }

    /**
     * @return the maxAllowableYears
     */
    public int getMaxAllowableYears() {
        return maxAllowableYears;
    }

    /**
     * @param maxAllowableYears the maxAllowableYears to set
     */
    public void setMaxAllowableYears(int maxAllowableYears) {
        this.maxAllowableYears = maxAllowableYears;
    }

    /**
     * @return the degreeRequirementOfEachLevel
     */
    public List<DegreeRequirement> getDegreeRequirement() {
        return degreeRequirementOfEachLevel;
    }

    /**
     * @param degreeRequirementOfEachLevel the degreeRequirementOfEachLevel to
     * set
     */
    public void setDegreeRequirement(List<DegreeRequirement> degreeRequirement) {
        this.degreeRequirementOfEachLevel = degreeRequirement;
    }


    /**
     * @return the departmentName
     */
    public DepartmentName getDepartmentName() {
        return departmentName;
    }

    /**
     * @param departmentName the departmentName to set
     */
    public void setDepartmentName(DepartmentName departmentName) {
        this.departmentName = departmentName;
    }
}
