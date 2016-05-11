<%-- 
    Document   : studentview
    Created on : 13-May-2014, 08:38:14
    Author     : Ocheja Patrick Ileanwa
--%>

<%@page import="com.primus.appstates.AdministratorState"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    AdministratorState administratorState = AdministratorState.getInstance(request);
%>
<jsp:include page="<%=administratorState.getAdminParentViews().getPage()%>" flush="true"/>
