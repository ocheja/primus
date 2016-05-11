<%-- 
    Document   : approvecourseforms
    Created on : 08-June-2014, 10:36:11
    Author     : Ocheja Patrick Ileanwa
--%>

<%@page import="com.primus.enums.Semester"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/usersarea/css/icheck/flat/green.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/usersarea/css/jquery.dataTables.css" />
<!DOCTYPE html>
<div class="row" id="courseform-arena">
    <div class="col-12">
        <div class="widget-box">
            <div class="widget-content nopadding">
                <form class="form-horizontal" method="get" action="" name="approvecourses" id="approvecourses" novalidate="novalidate">
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
                        <label class="control-label">Semester</label>
                        <div class="controls">
                            <select id="semester" name="semester" class="required">
                                <option value="" selected></option>
                                <c:forEach items="${semesters}"  var="semester">
                                    <option value="${semester}" > <c:out value="${semester}"/> </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Level</label>
                        <div class="controls">
                            <select id="academiclevel" name="academiclevel" class="required">
                                <option value="" selected></option>
                                <c:forEach items="${academicLevels}"  var="level">
                                    <option value="${level}" > <c:out value="${level}"/> </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Academic Adviser Approved</label>
                        <div class="controls">
                            <input type="checkbox" class="form-control input-small" name="academicadviser" id="academicadviser">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Faculty Officer Approved</label>
                        <div class="controls">
                            <input type="checkbox" class="form-control input-small" name="facultyofficer" id="facultyofficer">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Head of Department Approved</label>
                        <div class="controls">
                            <input type="checkbox" class="form-control input-small" name="headofdepartment" id="headofdepartment">
                        </div>
                    </div>
                    <div class="form-actions">
                        <input type="submit" value="Manage" id="manage_btn" class="btn btn-success btn-max">
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
    });
</script>