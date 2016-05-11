/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.webListener;

import com.primus.data.Administrator;
import com.primus.data.DepartmentName;
import com.primus.data.FacultyName;
import com.primus.enums.AdminType;
import com.primus.enums.DepartmentNameEnum;
import com.primus.enums.FacultyNameEnum;
import com.primus.serviceBean.AdministratorServiceBean;
import com.primus.serviceBean.DepartmentServiceBean;
import com.primus.serviceBean.FacultyServiceBean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author Olisa
 */
@WebListener
public class WebContextListener implements ServletContextListener {

    ServletContext servletContext;
    ApplicationContext applicationContext;
    FacultyServiceBean facultyService;
     DepartmentServiceBean departmentService;
     AdministratorServiceBean administratorServiceBean;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        servletContext = sce.getServletContext();
        this.applicationContext = WebApplicationContextUtils.getWebApplicationContext(this.servletContext);
        facultyService = ((FacultyServiceBean) this.applicationContext.getBean("facultyServiceBean"));
        departmentService = ((DepartmentServiceBean) this.applicationContext.getBean("departmentServiceBean"));
        administratorServiceBean = ((AdministratorServiceBean) this.applicationContext.getBean("administratorServiceBean"));
        creatDefaultAdmin();
        creatFacultyNames();
        creatDepartmentNames() ;
        
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
    
    private void  creatDefaultAdmin(){
        if(administratorServiceBean.getAdministratorCount()<1){
            Administrator administrator= new Administrator();
             administrator.setPassword("admin".hashCode());
              administrator.setAdminType(AdminType.Super);
               administrator.setActive(true);
                administrator.setEmailAddress("admin@gmail.com");
            try {
                administratorServiceBean.create(administrator);
            } catch (Exception ex) {
                Logger.getLogger(WebContextListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void creatDepartmentNames() {
        DepartmentName departmentName;

        for (DepartmentNameEnum departmentNamee : DepartmentNameEnum.values()) {
           departmentName = new DepartmentName();
            departmentName.setName(departmentNamee.toString().replaceAll("_", " ").toLowerCase());
            try {
                    departmentService.createDepartmentName(departmentName);
                
            } catch (Exception ex) {
                Logger.getLogger(WebContextListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
     
    private void creatFacultyNames() {
        FacultyName facultyName;

        for (FacultyNameEnum facultyNamee : FacultyNameEnum.values()) {
            facultyName = new FacultyName();
            facultyName.setName(facultyNamee.toString().replaceAll("_", " ").toLowerCase());
            try {
                    facultyService.createFacultyName(facultyName);
                
            } catch (Exception ex) {
                Logger.getLogger(WebContextListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
