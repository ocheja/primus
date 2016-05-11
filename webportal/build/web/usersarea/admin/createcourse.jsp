<%-- 
    Document   : adminhomepage
    Created on : 14-May-2014, 06:03:50
    Author     : Ocheja Patrick Ileanwa
--%>

<%@page import="com.primus.appstates.AdministratorState"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<input type="hidden" id="nodeView" name="nodeView" value="create">
<div class="row">
    <div class="col-12">
        <div class="widget-box">
            <div class="widget-title">
                <span class="icon">
                    <i class="glyphicon glyphicon-align-justify"></i>									
                </span>
                <h5>New Course Form</h5>
            </div>
            <div class="widget-content nopadding">
                <form class="form-horizontal" method="post" action="" name="create_course_form" id="create_course_form" novalidate="novalidate">
                    <div class="form-group">
                        <label class="control-label">Course Title</label>
                        <div class="controls">
                            <input type="text" class="required" id="courseTitle" name="courseTitle"/>

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Course Code</label>
                        <div class="controls">
                            <input type="text" class="required" id="courseCode" name="courseCode"/>

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Level Defined For</label>
                        <div class="controls">
                            <select  class="required" id="courseLevel" name="courseLevel">
                                <option selected="selected" value=""></option>
                                <c:forEach items="${levels}"  var="level">
                                    <option value="${level}" > <c:out value="${level}"/> </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Semester Defined For</label>
                        <div class="controls">
                            <select  class="required" id="semester" name="semester">
                                <option selected="selected" value=""></option>
                                <c:forEach items="${semesters}"  var="semester">
                                    <option value="${semester}" > <c:out value="${semester}"/> </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Course Unit</label>
                        <div class="controls">
                            <input type="number" class="required digits" id="courseUnit" name="courseUnit"/>

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Course Description</label>
                        <div class="controls">
                            <input type="text" class="required" id="description" name="description"/>

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Elective</label>
                        <div class="controls">
                            <input type="checkbox" class=" form-control input-small" name="elective" id="elective">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Pre-requisite (s)</label>
                        <div class="controls">
                            <select multiple id="precourses" name="precourses">
                                <c:forEach items="${courses}"  var="course">
                                    <option value="${course.courseCode}" > <c:out value="${course.courseTitle} (${course.courseCode})"/> </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Department</label>
                        <div class="controls">
                            <select  class="required" id="department" name="department">
                                <option selected="selected" value=""></option>
                                <c:forEach items="${departments}"  var="department">
                                    <option value="${department.id}" > <c:out value="${department.departmentName.name}"/> </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-actions">
                        <input type="submit" value="Create" id="create_btn" class="btn btn-success btn-max">
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

<div class="row">
    <div class="col-12">
        <div class="widget-box">
            <div class="widget-title">
                <span class="icon">
                    <i class="glyphicon glyphicon-align-justify"></i>									
                </span>
                <h5>Batch Course Creation (EXCEL)</h5>
            </div>
            <div class="widget-content nopadding">
                <form class="form-horizontal" method="post" action="" name="create_batch_course_form" id="create_batch_course_form" novalidate="novalidate">

                    <div class="form-group">
                        <label class="control-label">Excel file</label>
                        <div class="controls">
                            <input type="file" name="excelFile" id="excelFile">
                        </div>
                    </div>
                    <div class="form-actions">
                        <input type="submit" value="Create" id="create_btn" class="btn btn-success btn-max">
                    </div>
            </div>
            </form>
        </div>
    </div>			
</div>
<a href="#InformerModal" id="informerModalLink" data-toggle="modal" style="display:none" class="btn btn-primary"></a>
<a href="#myAlert" id="myAlertLink" data-toggle="modal" style="display:none" class="btn btn-danger">Alert</a>
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
<div id="myAlert" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button">×</button>
                <h3>Confirmation</h3>
            </div>
            <div class="modal-body">
                <p id="alert_content" >Confirm Delete</p>
            </div>
            <div class="modal-footer">
                <a data-dismiss="modal" id="deleteConfirmed" class="btn btn-primary btn-small" href="#">Confirm</a>
                <a data-dismiss="modal" class="btn btn-default btn-small" href="#">Cancel</a>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function() {
        $('select').select2();
    });
</script>