/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.util;

import com.primus.data.Faculty;
import com.primus.serviceBean.AdministratorServiceBean;
import com.primus.serviceBean.DepartmentServiceBean;
import com.primus.serviceBean.FacultyServiceBean;
import java.util.List;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Ocheja Patrick Ileanwa
 */

@Named("listUtil")
public class ListUtil {
    @Autowired
    AdministratorServiceBean administratorServiceBean;
    @Autowired
    FacultyServiceBean facultyServiceBean;
    @Autowired
    DepartmentServiceBean departmentServiceBean;
    
    public  List<Faculty> getFaculties(){
        
        return facultyServiceBean.findFacultyEntities();
    }
    
}
