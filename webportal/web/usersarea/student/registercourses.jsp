<%-- 
    Document   : register-course
    Created on : 08-May-2014, 15:36:11
    Author     : Ocheja Patrick Ileanwa
--%>

<%@page import="com.primus.appstates.StudentState"%>
<%@page import="com.primus.enums.Semester"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/usersarea/css/icheck/flat/green.css" />
<!DOCTYPE html>
<%
    request.setAttribute("sessionsRegistered", StudentState.getInstance(request).getCurrentStudent().getAcademicSessionLevel());
%>
<div class="row" id="courseform-arena">
    <div class="col-12">
        <div class="widget-box">
            <div class="widget-content nopadding">
                <table class="table table-bordered table-striped table-hover">
                    <thead>
                        <tr>
                            <th>Paid Session</th>
                            <th>Paid Semester</th>
                            <th>Level</th>
                            <th colspan="3">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${sessionsRegistered}" var="academicSessionLevel">
                            <tr>
                                <td> <c:out value="${academicSessionLevel.academicSession.startYear}/${academicSessionLevel.academicSession.endYear}"></c:out> </td>
                            <td>Full Session</td>
                            <td> <c:out value="${academicSessionLevel.academicLevel}"></c:out> </td>
                            <td><a href="#" onclick="doCourseReg('academicSession=${academicSessionLevel.academicSession.id}&semester=<%=Semester.FIRST%>&action=prepareCourseRegForm', 'courseform-arena');">Register 1st Semester</a></td>
                            <td><a href="#" onclick="doCourseReg('academicSession=${academicSessionLevel.academicSession.id}&semester=<%=Semester.SECOND%>&action=prepareCourseRegForm', 'courseform-arena');">Register 2nd Semester</a></td>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>							
            </div>
        </div>
    </div>
</div>
<div id="addrequiredcoursesdiv">

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
                <a  id="regConfirmed" class="btn btn-primary btn-small" onclick="submitFormCR('requiredCoursesForm','action=registerCourses','messageAlert');" href="#">YES</a>
                <a data-dismiss="modal" class="btn btn-default btn-small" href="#">NO</a>
            </div>
        </div>
    </div>
</div>

<script src="<%=request.getContextPath()%>/usersarea/js/ajax-coursereg-submission.js"></script>
<script src="<%=request.getContextPath()%>/usersarea/js/jquery.icheck.min.js"></script>
<script src="<%=request.getContextPath()%>/usersarea/js/jquery.validate.js"></script>
<script src="<%=request.getContextPath()%>/usersarea/js/select2.min.js"></script>
<script src="<%=request.getContextPath()%>/usersarea/js/jquery.dataTables.min.js"></script>
<script src="<%=request.getContextPath()%>/usersarea/js/unicorn.tables.js"></script>