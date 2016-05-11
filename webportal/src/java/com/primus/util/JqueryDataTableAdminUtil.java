/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.primus.data.Administrator;
import com.primus.data.JqueryDataTableParamModel;
import com.primus.service.exceptions.PrimusServiceException;
import com.primus.serviceBean.AdministratorServiceBean;
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
@Named("jqueryDataTableAdminUtil")
public class JqueryDataTableAdminUtil {
    
    @Autowired
    AdministratorServiceBean administratorServiceBean;
    
    public  String processDataTable(JqueryDataTableParamModel param) {
        String sEcho = param.sEcho;
        String searchParam = param.sSearch.trim();
        searchParam = "%"+searchParam+"%";
        int iTotalRecords; // total number of records (unfiltered)
        int iTotalDisplayRecords; //value will be set when code filters administrators by keyword

        
        iTotalRecords = administratorServiceBean.getAdministratorCount();
        List<Administrator> administrators = null;
        try {
            administrators = administratorServiceBean.findAdministrator(searchParam, searchParam, searchParam, searchParam);
        } catch (PrimusServiceException ex) {
            Logger.getLogger(JqueryDataTableAdminUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        iTotalDisplayRecords = administrators.size();// number of administrators that match search criterion should be returned
        System.out.println("Total records: " + iTotalRecords + " Displayed: " + iTotalDisplayRecords);
        final int sortColumnIndex = param.iSortColumnIndex;
        final int sortDirection = param.sSortDirection.equals("asc") ? -1 : 1;

        Collections.sort(administrators, new Comparator<Administrator>() {
            @Override
            public int compare(Administrator administrator1, Administrator administrator2) {
                switch (sortColumnIndex) {
                    case 0:
                        return administrator1.getFirstName().compareTo(administrator2.getFirstName()) * sortDirection;
                    case 1:
                        return administrator1.getLastName().compareTo(administrator1.getLastName()) * sortDirection;
                    case 2:
                        return administrator1.getAdminType().name().compareTo(administrator1.getAdminType().name()) * sortDirection;
                }
                return 0;
            }
        });

        if (administrators.size() < param.iDisplayStart + param.iDisplayLength) {
            administrators = administrators.subList(param.iDisplayStart, administrators.size());
        } else {
            administrators = administrators.subList(param.iDisplayStart, param.iDisplayStart + param.iDisplayLength);
        }
        param.aaData = administrators;
        param.iTotalDisplayRecords = iTotalDisplayRecords;
        param.iTotalRecords = iTotalRecords;
        Gson adminData = new GsonBuilder().create();
        String jSonResponse = adminData.toJson(param);
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
