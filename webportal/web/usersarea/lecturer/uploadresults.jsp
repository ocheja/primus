<%-- 
    Document   : viewresults
    Created on : 20-Sep-2014, 11:54:32
    Author     : Ocheja Patrick Ileanwa
--%>

<%@page import="com.primus.appstates.LecturerState"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.primus.enums.AcademicLevel"%>
<%@page import="java.util.List"%>
<%@page import="com.primus.data.Student"%>
<%@page import="com.primus.appstates.StudentState"%>
<%@page import="com.primus.enums.Semester"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/usersarea/css/icheck/flat/green.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/usersarea/css/jquery.dataTables.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/usersarea/css/datepicker.css" />
<!DOCTYPE html>
<div class="row" id="courseform-arena">
    <div class="col-12">
        <div class="widget-box">
            <div class="widget-content nopadding">
                <a class="btn btn-success btn-mini" href="<%= request.getContextPath()%>/view?parent=<%=LecturerState.LecturerParentViews.MANAGE_RESULTS.name()%>" id="manageresults" >View Result</a>
                <form class="form-horizontal" method="POST" action="/Primus/lecturer/upload" name="uploadresults" id="uploadresults" enctype="multipart/form-data" novalidate="novalidate">
                    <div class="form-group">
                        <label class="control-label">Department</label>
                        <div class="controls">
                            <select id="departmentname" name="departmentname" class="required">
                                <option value="" selected></option>
                                <c:forEach items="${departmentNames}"  var="departmentName">
                                    <option value="${departmentName.name}" > <c:out value="${departmentName.name}"/> </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Academic Session</label>
                        <div class="controls">
                            <select id="academicsession" name="academicsession" class="required">
                                <option value="" selected></option>
                                <c:forEach items="${academicSessions}"  var="session">
                                    <option value="${session.id}" > <c:out value="${session.startYear}/${session.endYear}"/> </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Academic Level</label>
                        <div class="controls">
                            <select id="academiclevel" name="academiclevel" class="required">
                                <option value="" selected></option>
                                <c:forEach items="<%=AcademicLevel.values()%>"  var="level">
                                    <option value="${level}" > <c:out value="${level}"/> </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Date of Exam</label>
                        <div class="controls">
                            <input type="text" data-date="12-02-2012" name="dateofexam" id="dateofexam"
                                   data-date-format="dd-mm-yyyy" value="12-02-2012" 
                                   class="datepicker form-control input-small required" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Select File</label>
                        <div class="controls">
                            <input type="file" accept="application/vnd.ms-excel" name="resultsheet" id="resultsheet" class="required">
                        </div>
                    </div>
                    <input type="hidden" id="action" name="action" value="resultupload"/>
                    <div class="form-actions">
                        <input type="submit" value="UPLOAD" id="view_btn" class="btn btn-success btn-max">
                        <div id="loading_gif" style="display: none">
                            <img src="<%=request.getContextPath()%>/usersarea/img/notice_spinner.gif"/>
                            <label id="message_on_end">Loading...</label>
                        </div>
                    </div>
                </form>						
            </div>
        </div>
    </div>
</div>
<div id="viewcoursesdiv">

</div>
<a href="#addRequiredCoursesModal" id="addRequiredCoursesModalLink" data-toggle="modal" style="display:none" class="btn btn-primary"></a>
<a href="#myAlertH" id="myAlertHModalLink" data-toggle="modal" style="display:none" class="btn btn-primary"></a>
<a href="#InformerModal" id="informerModalLink" data-toggle="modal" style="display:none" class="btn btn-primary"></a>
<div id="InformerModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button">×</button>
                <h3>Notification</h3>
            </div>
            <div class="modal-body">
                <p id="message_content">Success</p>
            </div>
        </div>
    </div>
</div>
<div id="myAlertH" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" id="myAlertHClose" class="close" type="button">×</button>
                <h3>Confirmation</h3>
            </div>
            <div class="modal-body">
                <p id="alert_content" >Submit Form?</p>
            </div>
            <div class="modal-footer">
                <a  id="regConfirmed" class="btn btn-primary btn-small" onclick="submitFormCF('requiredCoursesForm', 'action=registerCourses', 'messageAlert');" href="#">YES</a>
                <a data-dismiss="modal" class="btn btn-default btn-small" href="#">NO</a>
            </div>
        </div>
    </div>
</div>
<script>
                    $(document).ready(function() {
                        $('select').select2();
                        $('.datepicker').datepicker();
                    });
</script>
<script src = "<%=request.getContextPath()%>/usersarea/js/ajax-submission.js" >
</script>
<script src="<%=request.getContextPath()%>/usersarea/js/bootstrap-datepicker.js"></script>