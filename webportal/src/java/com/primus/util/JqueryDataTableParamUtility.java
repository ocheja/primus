/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.util;

import com.primus.data.JqueryDataTableParamModel;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Ocheja Patrick Ileanwa
 */
@Named("jqueryDataTableParamUtility")
public class JqueryDataTableParamUtility {

    public JqueryDataTableParamModel getParam(HttpServletRequest request) {
        
        JqueryDataTableParamModel param = new JqueryDataTableParamModel();
        param.sEcho = request.getParameter("sEcho");
        param.sSearch = request.getParameter("sSearch");
        param.sColumns = request.getParameter("sColumns");
        param.iDisplayStart = Integer.parseInt(request.getParameter("iDisplayStart"));
        param.iDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
        param.iColumns = Integer.parseInt(request.getParameter("iColumns"));
        param.iSortingCols = Integer.parseInt(request.getParameter("iSortingCols"));
        param.iSortColumnIndex = Integer.parseInt(request.getParameter("iSortCol_0"));
        param.sSortDirection = request.getParameter("sSortDir_0");
        return param;
    }
}
