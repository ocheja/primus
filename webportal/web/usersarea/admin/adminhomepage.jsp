<%-- 
    Document   : adminhomepage
    Created on : 14-May-2014, 06:03:50
    Author     : Ocheja Patrick Ileanwa
--%>

<%@page import="com.primus.appstates.AdministratorState"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    AdministratorState administratorState = AdministratorState.getInstance(request);
%>
<input type="hidden" id="parentView" name="parentView" value="homepage">
<input type="hidden" id="nodeView" name="nodeView" value="homepage">
<div id="content">
    <div id="content-header">
        <h1>Home</h1>
    </div>
    <div id="breadcrumb">
        <a href="<%=request.getContextPath()%>/#" title="Go to Home" class="tip-bottom"><i class="glyphicon glyphicon-home"></i> Home</a>
        <a href="<%=request.getContextPath()%>/#" class="current">Home</a>
    </div>
    <div class="container-fluid">
        <%
            if(administratorState.getSuccessMessage()!=null){
        %>
        <div class="row">
            <div class="col-12">
                <div class="alert alert-success">
                    <button class="close" data-dismiss="alert">×</button>
                    <strong>Success!</strong> <%= administratorState.getSuccessMessage()%>
                </div>
            </div>
        </div>
        <%
                administratorState.setSuccessMessage(null);
                request.getSession().setAttribute(AdministratorState.class.getName(), administratorState);
            }
        %>
        <jsp:include flush="true" page="<%= administratorState.getAdminNodeViews().getPage()%>"/>

    </div>

</div>