<%-- 
    Document   : classmembers
    Created on : 08-August-2014, 12:11:01
    Author     : Ocheja Patrick Ileanwa
--%>

<%@page import="com.primus.appstates.LecturerState"%>
<%@page import="com.primus.enums.Semester"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/usersarea/css/icheck/flat/green.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/usersarea/css/jquery.dataTables.css" />
<!DOCTYPE html>
<%
    LecturerState lecturerState = LecturerState.getInstance(request);
    request.setAttribute("currentLec", lecturerState.getCurrentLecturer());
%>
<div class="row" id="courseform-arena">
    <div class="col-12">
        <div class="widget-box">
            <div class="widget-content nopadding">
                <form class="form-horizontal" method="get" action="" name="viewstudents" id="viewstudents" novalidate="novalidate">
                    <div class="form-group">
                        <label class="control-label">Academic Session</label>
                        <div class="controls">
                            <select id="academicsession" name="academicsession" class="required">
                                <option value="" selected></option>
                                <c:forEach items="${academicSessions}"  var="academicSession">
                                    <option value="${academicSession.id}" > <c:out value="${academicSession.startYear}-${academicSession.endYear}"/> </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Course</label>
                        <div class="controls">
                            <select id="course" name="course" class="required">
                                <option value="" selected></option>
                                <c:forEach items="${currentLec.courses}"  var="course">
                                    <option value="${course.id}" > <c:out value="${course.courseTitle}(${course.courseCode})"/> </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-actions">
                        <input type="submit" value="View" id="manage_btn" class="btn btn-success btn-max">
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
                <a  id="regConfirmed" class="btn btn-primary btn-small" onclick="submitForm('requiredCoursesForm', 'action=registerCourses', 'messageAlert');" href="#">YES</a>
                <a data-dismiss="modal" class="btn btn-default btn-small" href="#">NO</a>
            </div>
        </div>
    </div>
</div>
<script>
                    $(document).ready(function() {
                        $('select').select2();
                    });
</script>