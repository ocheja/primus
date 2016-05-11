/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.interfaces;

import com.primus.data.CourseForm;
import java.util.List;

/**
 *
 * @author Olisa
 */
public interface Resultservice {
    
    public float computCGPA(List<CourseForm> courseForm)throws Exception;
    
}
