/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.primus.data.Administrator;
import com.primus.data.Faculty;
import com.primus.data.JqueryDataTableParamModel;
import com.primus.service.exceptions.PrimusServiceException;
import com.primus.serviceBean.AdministratorServiceBean;
import com.primus.serviceBean.FacultyServiceBean;
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
@Named("jqueryDataTableFacultyUtil")
public class JqueryDataTableFacultyUtil {
    
    @Autowired
    FacultyServiceBean facultyServiceBean;
    
    public  String processDataTable(JqueryDataTableParamModel param) {
        String sEcho = param.sEcho;
        String searchParam = param.sSearch.trim();
        searchParam = "%"+searchParam+"%";
        int iTotalRecords; // total number of records (unfiltered)
        int iTotalDisplayRecords; //value will be set when code filters administrators by keyword

        
        iTotalRecords = facultyServiceBean.getFacultyCount();
        List<Faculty> faculties = null;
        try {
            faculties = (List<Faculty>) facultyServiceBean.findLikeFacultyName(searchParam);
        } catch (Exception ex) {
            Logger.getLogger(JqueryDataTableFacultyUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        iTotalDisplayRecords = faculties.size();// number of administrators that match search criterion should be returned
        System.out.println("Total records: " + iTotalRecords + " Displayed: " + iTotalDisplayRecords);
        final int sortColumnIndex = param.iSortColumnIndex;
        final int sortDirection = param.sSortDirection.equals("asc") ? -1 : 1;

        Collections.sort(faculties, new Comparator<Faculty>() {
            @Override
            public int compare(Faculty faculty1, Faculty faculty2) {
                switch (sortColumnIndex) {
                    case 0:
                        return faculty1.getFacultyName().getName().compareTo(faculty2.getFacultyName().getName()) * sortDirection;
                    case 1:
                        return faculty1.getDescription().compareTo(faculty2.getDescription()) * sortDirection;
                }
                return 0;
            }
        });

        if (faculties.size() < param.iDisplayStart + param.iDisplayLength) {
            faculties = faculties.subList(param.iDisplayStart, faculties.size());
        } else {
            faculties = faculties.subList(param.iDisplayStart, param.iDisplayStart + param.iDisplayLength);
        }
        JsonFormer former = new JsonFormer();
        TypeToken<List<?>> token = new TypeToken<List<?>>(){};

        param.aaData = new Gson().fromJson(former.getFacultyJsonForm(faculties), token.getType());
        param.iTotalDisplayRecords = iTotalDisplayRecords;
        param.iTotalRecords = iTotalRecords;
        Gson facultyData = new GsonBuilder().create();
        String jSonResponse = facultyData.toJson(param);
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
