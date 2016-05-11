/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.primus.data.AcademicSession;
import com.primus.data.Degree;
import com.primus.data.JqueryDataTableParamModel;
import com.primus.interfaces.DegreeService;
import com.primus.serviceBean.AcademicSessionServiceBean;
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
@Named("jqueryDataTableUtilityUtil")
public class JqueryDataTableUtilityUtil {

    @Autowired
    DegreeService degreeServiceBean;
    @Autowired
    AcademicSessionServiceBean academicSessionServiceBean;

    public String processDataTable(JqueryDataTableParamModel param) {
        String sEcho = param.sEcho;
        String searchParam = param.sSearch.trim();
        searchParam = "%" + searchParam + "%";
        int iTotalRecords; // total number of records (unfiltered)
        int iTotalDisplayRecords; //value will be set when code filters administrators by keyword


        iTotalRecords = degreeServiceBean.getDegreeCount();
        List<Degree> degrees = null;
        try {
            degrees = (List<Degree>) degreeServiceBean.wildSearch(searchParam);
        } catch (Exception ex) {
            Logger.getLogger(JqueryDataTableUtilityUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        iTotalDisplayRecords = degrees.size();// number of administrators that match search criterion should be returned
        System.out.println("Total records: " + iTotalRecords + " Displayed: " + iTotalDisplayRecords);
        final int sortColumnIndex = param.iSortColumnIndex;
        final int sortDirection = param.sSortDirection.equals("asc") ? -1 : 1;

        Collections.sort(degrees, new Comparator<Degree>() {
            @Override
            public int compare(Degree degree1, Degree degree2) {
                switch (sortColumnIndex) {
                    case 0:
                        return degree1.getTitleOfDegree().compareTo(degree2.getTitleOfDegree()) * sortDirection;
                    case 1:
                        return degree1.getDepartmentName().getName().compareTo(degree2.getDepartmentName().getName()) * sortDirection;

                }
                return 0;
            }
        });

        if (degrees.size() < param.iDisplayStart + param.iDisplayLength) {
            degrees = degrees.subList(param.iDisplayStart, degrees.size());
        } else {
            degrees = degrees.subList(param.iDisplayStart, param.iDisplayStart + param.iDisplayLength);
        }
        JsonFormer former = new JsonFormer();
        TypeToken<List<?>> token = new TypeToken<List<?>>() {
        };

        param.aaData = new Gson().fromJson(former.getDegreeJsonForm(degrees), token.getType());
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

    public String processSessionDataTable(JqueryDataTableParamModel param) {
        String sEcho = param.sEcho;
        String searchParam = param.sSearch.trim();
        searchParam = "%" + searchParam + "%";
        int iTotalRecords; // total number of records (unfiltered)
        int iTotalDisplayRecords; //value will be set when code filters administrators by keyword


        iTotalRecords = academicSessionServiceBean.getAcademicSessionCount();
        List<AcademicSession> academicSessions = null;
        try {
            academicSessions = (List<AcademicSession>) academicSessionServiceBean.wildSearch(searchParam);
        } catch (Exception ex) {
            Logger.getLogger(JqueryDataTableUtilityUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        iTotalDisplayRecords = academicSessions.size();// number of administrators that match search criterion should be returned
        System.out.println("Total records: " + iTotalRecords + " Displayed: " + iTotalDisplayRecords);
        final int sortColumnIndex = param.iSortColumnIndex;
        final int sortDirection = param.sSortDirection.equals("asc") ? -1 : 1;

        Collections.sort(academicSessions, new Comparator<AcademicSession>() {
            @Override
            public int compare(AcademicSession academicSession1, AcademicSession academicSession2) {
                switch (sortColumnIndex) {
                    case 0:
                        Integer as1ey = academicSession1.getEndYear();
                        return as1ey.compareTo(academicSession2.getEndYear()) * sortDirection;
                    case 1:
                        Integer as1sy = academicSession1.getStartYear();
                        return as1sy.compareTo(academicSession2.getStartYear()) * sortDirection;

                }
                return 0;
            }
        });

        if (academicSessions.size() < param.iDisplayStart + param.iDisplayLength) {
            academicSessions = academicSessions.subList(param.iDisplayStart, academicSessions.size());
        } else {
            academicSessions = academicSessions.subList(param.iDisplayStart, param.iDisplayStart + param.iDisplayLength);
        }
        JsonFormer former = new JsonFormer();
        TypeToken<List<?>> token = new TypeToken<List<?>>() {
        };

        param.aaData = academicSessions;
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
