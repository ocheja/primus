/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.interfaces;

import com.primus.data.Course;
import com.primus.data.Degree;
import com.primus.data.DepartmentName;
import com.primus.enums.AcademicLevel;
import com.primus.enums.TitleOfDegree;
import com.primus.service.exceptions.PrimusServiceException;
import java.util.List;

/**
 *
 * @author Olisa
 */
public interface DegreeService {

    public void create(Degree degree) throws Exception;

    public void edit(Degree degree) throws Exception;
    
    public void destroy(Long id) throws Exception;

    public List<Degree> findDegreeEntities();
    
    public List<Degree> wildSearch(String searchParam) throws PrimusServiceException;

    public Degree findDegree( DepartmentName departmentName);
    
    public Degree findDegree(Long id) throws Exception;
    
     public Degree findDegree(TitleOfDegree degreeTitle,DepartmentName departmentName) ;

    public int getDegreeCount();
    
    public void setDegreeRequirement(Degree degree,List<Course> courses, AcademicLevel academiclevel)throws Exception;
}
