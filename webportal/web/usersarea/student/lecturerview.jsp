<%-- 
    Document   : studentview
    Created on : 13-May-2014, 08:38:14
    Author     : Ocheja Patrick Ileanwa
--%>

<%@page import="com.primus.appstates.LecturerState"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    LecturerState lecturerState = LecturerState.getInstance(request);
%>
<jsp:include page="<%=lecturerState.getLecturerParentViews().getPage()%>" flush="true"/>
