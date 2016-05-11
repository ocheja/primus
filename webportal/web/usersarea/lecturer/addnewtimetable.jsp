<%-- 
    Document   : register-course
    Created on : 08-May-2014, 15:36:11
    Author     : Ocheja Patrick Ileanwa
--%>

<%@page import="java.util.Calendar"%>
<%@page import="com.primus.appstates.LecturerState"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/usersarea/css/icheck/flat/green.css" />
<!DOCTYPE html>
<div class="row" id="courseform-arena">
    <div class="col-12">
        <div class="widget-box">
            <div class="widget-content nopadding">
                <a class="btn btn-success btn-mini" href="<%= request.getContextPath()%>/view?parent=<%=LecturerState.LecturerParentViews.VIEW_TIMETABLE.name()%>" id="addTimetable" >View Timetable</a>
                <a class="btn btn-success btn-mini" href="<%= request.getContextPath()%>/view?parent=<%=LecturerState.LecturerParentViews.EDIT_TIMETABLE.name()%>" id="editTimetable">Edit Timetable</a>
                <form class="form-horizontal" method="get" action="" name="addnewtimetable" id="addnewtimetable" novalidate="novalidate">
                    <div class="form-group">
                        <label class="control-label">Select Day</label>
                        <div class="controls">
                            <select id="dayoftheweek" name="dayoftheweek" class="required">
                                <option value="" selected></option>
                                <option value="<%=Calendar.SUNDAY%>" > <c:out value="SUNDAY"/> </option>
                                <option value="<%=Calendar.MONDAY%>" > <c:out value="MONDAY"/> </option>
                                <option value="<%=Calendar.TUESDAY%>" > <c:out value="TUESDAY"/> </option>
                                <option value="<%=Calendar.WEDNESDAY%>" > <c:out value="WEDNESDAY"/> </option>
                                <option value="<%=Calendar.THURSDAY%>" > <c:out value="THURSDAY"/> </option>
                                <option value="<%=Calendar.FRIDAY%>" > <c:out value="FRIDAY"/> </option>
                                <option value="<%=Calendar.SATURDAY%>" > <c:out value="SATURDAY"/> </option>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12 col-sm-6">
                            <label class="control-label">Course</label>
                            <div class="controls">
                                <select id="course1" name="course1" class="required">
                                    <option value="" selected></option>
                                    <c:forEach items="${courses}"  var="course">
                                        <option value="${course.id}" > <c:out value="${course.courseTitle}(${course.courseCode})"/> </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col-12 col-sm-6">
                            <label class="control-label">Venue</label>
                            <div class="controls">
                                <select id="venue1" name="venue1" class="required">
                                    <option value="" selected></option>
                                    <c:forEach items="${venues}"  var="venue">
                                        <option value="${venue}" > <c:out value="${venue}"/> </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col-12 col-sm-6">
                            <label class="control-label">Start Time</label>
                            <div class="controls">
                                <input type="time" class=" required form-control" name="sTime1" id="sTime1">
                            </div>
                        </div>
                        <div class="col-12 col-sm-6">
                            <label class="control-label">End Time</label>
                            <div class="controls">
                                <input type="time" class=" required form-control" name="eTime1" id="eTime1">
                            </div>
                        </div>
                    </div>
                    <input type="hidden" value="1" id="coursescount" name="coursescount">
                    <a href="#" onclick="addOneMore();" style="float: right;" class="glyphicon glyphicon-plus-sign " id="addLink" >Add one more course</a>
                    <a href="<%= request.getContextPath()%>/view?parent=<%=LecturerState.LecturerParentViews.ADD_NEW_TIMETABLE.name()%>"  style="float: right;" class="glyphicon glyphicon-refresh" id="addLink" >Refresh</a>
                    <div class="form-actions">
                        <input type="submit" value="Add" id="manage_btn" class="btn btn-success btn-max">
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
                <a  id="regConfirmed" class="btn btn-primary btn-small" onclick="submitForm('requiredCoursesForm', 'action=registerCourses', 'messageAlert');" href="#">YES</a>
                <a data-dismiss="modal" class="btn btn-default btn-small" href="#">NO</a>
            </div>
        </div>
    </div>
</div>

<script src="<%=request.getContextPath()%>/usersarea/js/ajax-submission.js"></script>
<script src="<%=request.getContextPath()%>/usersarea/js/jquery.icheck.min.js"></script>
<script src="<%=request.getContextPath()%>/usersarea/js/jquery.validate.js"></script>
<script src="<%=request.getContextPath()%>/usersarea/js/select2.min.js"></script>
<script src="<%=request.getContextPath()%>/usersarea/js/jquery.dataTables.min.js"></script>
<script src="<%=request.getContextPath()%>/usersarea/js/unicorn.tables.js"></script>

<script>
                        function addOneMore() {
                            var counterID = document.getElementById('coursescount');
                            if (counterID !== null) {
                                var count = counterID.value;
                                count++;
                                console.log(count);
                                var content = '<div class="row"><div class="col-12 col-sm-6"><label class="control-label">Course</label>' +
                                        '<div class="controls"><select id="course' + count + '" name="course' + count + '" class="required"><option value="" selected></option>' +
                                        '<c:forEach items="${courses}"  var="course"><option value="${course.id}" > <c:out value="${course.courseTitle}(${course.courseCode})"/> </option></c:forEach>' +
                                        '</select></div></div><div class="col-12 col-sm-6"><label class="control-label">Venue</label><div class="controls">' +
                                        '<select id="venue' + count + '" name="venue' + count + '" class="required"><option value="" selected></option><c:forEach items="${venues}"  var="venue">' +
                                        '<option value="${venue}" > <c:out value="${venue}"/> </option></c:forEach></select></div></div><div class="col-12 col-sm-6">' +
                                        '<label class="control-label">Start Time</label><div class="controls"><input type="time" class=" required form-control" name="sTime' + count + '" id="sTime' + count + '">' +
                                        '</div></div><div class="col-12 col-sm-6"><label class="control-label">End Time</label><div class="controls">' +
                                        '<input type="time" class=" required form-control" name="eTime' + count + '" id="eTime' + count + '"></div></div></div>';
                                counterID.value = count;
                                $(content).insertBefore('#coursescount');
                                $('#course' + count).select2();
                                $('#venue' + count).select2();
                            }
                        }

</script>