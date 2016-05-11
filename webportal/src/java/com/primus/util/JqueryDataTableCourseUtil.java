/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.primus.data.Course;
import com.primus.data.JqueryDataTableParamModel;
import com.primus.interfaces.CourseService;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Ocheja Patrick Ileanwa
 */
@Named("jqueryDataTableCourseUtil")
public class JqueryDataTableCourseUtil {
    
    @Autowired
    CourseService courseServiceBean;
    
    public  String processDataTable(JqueryDataTableParamModel param) {
        String sEcho = param.sEcho;
        String searchParam = param.sSearch.trim();
        searchParam = "%"+searchParam+"%";
        int iTotalRecords; // total number of records (unfiltered)
        int iTotalDisplayRecords; //value will be set when code filters administrators by keyword

        
        iTotalRecords = courseServiceBean.getCourseCount();
        List<Course> courses = null;
        try {
            courses = (List<Course>) courseServiceBean.wildSearch(searchParam);
        } catch (Exception ex) {
            Logger.getLogger(JqueryDataTableCourseUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        iTotalDisplayRecords = courses.size();// number of administrators that match search criterion should be returned
        System.out.println("Total records: " + iTotalRecords + " Displayed: " + iTotalDisplayRecords);
        final int sortColumnIndex = param.iSortColumnIndex;
        final int sortDirection = param.sSortDirection.equals("asc") ? -1 : 1;

        Collections.sort(courses, new Comparator<Course>() {
            @Override
            public int compare(Course course1, Course course2) {
                switch (sortColumnIndex) {
                    case 0:
                        return course1.getCourseTitle().compareTo(course2.getCourseTitle()) * sortDirection;
                    case 1:
                        return course1.getDepartmentName().getName().compareTo(course2.getDepartmentName().getName()) * sortDirection;
                    case 2:
                        return course1.getDescription().compareTo(course2.getDescription()) * sortDirection;
                }
                return 0;
            }
        });

        if (courses.size() < param.iDisplayStart + param.iDisplayLength) {
            courses = courses.subList(param.iDisplayStart, courses.size());
        } else {
            courses = courses.subList(param.iDisplayStart, param.iDisplayStart + param.iDisplayLength);
        }
        JsonFormer former = new JsonFormer();
        TypeToken<List<?>> token = new TypeToken<List<?>>(){};

        param.aaData = new Gson().fromJson(former.getCourseJsonForm(courses), token.getType());
        param.iTotalDisplayRecords = iTotalDisplayRecords;
        param.iTotalRecords = iTotalRecords;
        String jSonResponse = new Gson().toJson(param);
        return jSonResponse;
//        try {
//            JsonObject jsonResponse = new JsonObject();
//            jsonResponse.addProperty("sEcho", sEcho);
//            jsonResponse.addProperty("iTotalRecords", iTotalRecords);
//            jsonResponse.addProperty("iTotalDisplayRecords", iTotalDisplayRecords);
//            Gson gson = new Gson();
//            jsonResponse.add("aaData", gson.toJsonTree(companies));
//
//            response.setContentType("application/Json");
//            response.getWriter().print(jsonResponse.toString());
//
//        } catch (JsonIOException e) {
//            e.printStackTrace();
//            response.setContentType("text/html");
//            response.getWriter().print(e.getMessage());
//        }
    }
}
