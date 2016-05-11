/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.primus.data.Department;
import com.primus.data.JqueryDataTableParamModel;
import com.primus.serviceBean.DepartmentServiceBean;
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
@Named("jqueryDataTableDepartmentUtil")
public class JqueryDataTableDepartmentUtil {
    
    @Autowired
    DepartmentServiceBean departmentServiceBean;
    
    public  String processDataTable(JqueryDataTableParamModel param) {
        String sEcho = param.sEcho;
        String searchParam = param.sSearch.trim();
        searchParam = "%"+searchParam+"%";
        int iTotalRecords; // total number of records (unfiltered)
        int iTotalDisplayRecords; //value will be set when code filters administrators by keyword

        
        iTotalRecords = departmentServiceBean.getDepartmentCount();
        List<Department> departments = null;
        try {
            departments = (List<Department>) departmentServiceBean.findLikeDepartmentName(searchParam);
        } catch (Exception ex) {
            Logger.getLogger(JqueryDataTableDepartmentUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        iTotalDisplayRecords = departments.size();// number of administrators that match search criterion should be returned
        System.out.println("Total records: " + iTotalRecords + " Displayed: " + iTotalDisplayRecords);
        final int sortColumnIndex = param.iSortColumnIndex;
        final int sortDirection = param.sSortDirection.equals("asc") ? -1 : 1;

        Collections.sort(departments, new Comparator<Department>() {
            @Override
            public int compare(Department department1, Department department2) {
                switch (sortColumnIndex) {
                    case 0:
                        return department1.getDepartmentName().getName().compareTo(department2.getDepartmentName().getName()) * sortDirection;
                    case 1:
                        return department1.getDescription().compareTo(department2.getDescription()) * sortDirection;
                }
                return 0;
            }
        });

        if (departments.size() < param.iDisplayStart + param.iDisplayLength) {
            departments = departments.subList(param.iDisplayStart, departments.size());
        } else {
            departments = departments.subList(param.iDisplayStart, param.iDisplayStart + param.iDisplayLength);
        }
        JsonFormer former = new JsonFormer();
        TypeToken<List<?>> token = new TypeToken<List<?>>(){};

        param.aaData = new Gson().fromJson(former.getDepartmentJsonForm(departments), token.getType());
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
