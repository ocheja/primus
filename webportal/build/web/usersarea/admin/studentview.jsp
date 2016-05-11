<%-- 
    Document   : studentview
    Created on : 13-May-2014, 08:38:14
    Author     : Ocheja Patrick Ileanwa
--%>

<%@page import="com.primus.appstates.StudentState"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    StudentState studentState = StudentState.getInstance(request);
%>
<jsp:include page="<%=studentState.getStudentParentViews().getPage()%>" flush="true"/>
