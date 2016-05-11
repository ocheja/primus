<%-- 
    Document   : adminhomepage
    Created on : 14-May-2014, 06:03:50
    Author     : Ocheja Patrick Ileanwa
--%>

<%@page import="com.primus.appstates.AdministratorState"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/usersarea/css/select2.css" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/usersarea/css/jquery-ui.css" />
<!DOCTYPE html>
<%
    AdministratorState administratorState = AdministratorState.getInstance(request);
    %>
<input type="hidden" id="parentView" name="parentView" value="faculty_mgr">
<div id="content">
            <div id="content-header">
                <h1>Faculty Manager</h1>
            </div>
            <div id="breadcrumb">
                <a href="<%=request.getContextPath()%>/#" title="Go to Home" class="tip-bottom"><i class="glyphicon glyphicon-home"></i> Faculty Manager</a>
                <a href="<%=request.getContextPath()%>/#" class="current">Home</a>
            </div>
            <div class="container-fluid">
        <%
            if(administratorState.getSuccessMessage()!=null){
        %>
        <div class="row" style="margin-top:20px">
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
            else if(administratorState.getErrorMessage()!=null){
        %>
        <div class="row" style="margin-top:20px">
            <div class="col-12">
                <div class="alert alert-error">
                    <button class="close" data-dismiss="alert">×</button>
                    <strong>Error!</strong> <%= administratorState.getErrorMessage()%>
                </div>
            </div>
        </div>
        <%
                administratorState.setErrorMessage(null);
                request.getSession().setAttribute(AdministratorState.class.getName(), administratorState);
            }
        %>
                <jsp:include flush="true" page="<%= administratorState.getAdminNodeViews().getPage()%>"/>

            </div>

        </div>
        <script src="<%=request.getContextPath()%>/usersarea/js/jquery.validate.js"></script>
        <script src="<%=request.getContextPath()%>/usersarea/js/ajax-submission.js"></script>
        <script src="<%=request.getContextPath()%>/usersarea/js/unicorn.form_validation.js"></script>
        <script src="<%=request.getContextPath()%>/usersarea/js/select2.min.js"></script>
        <script src="<%=request.getContextPath()%>/usersarea/js/additional-methods.js"></script>