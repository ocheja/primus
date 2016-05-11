<%-- 
    Document   : adminhomepage
    Created on : 14-May-2014, 06:03:50
    Author     : Ocheja Patrick Ileanwa
--%>
<%@page import="com.primus.appstates.AdministratorState"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<link rel="stylesheet" href="<%=request.getContextPath()%>/usersarea/css/jquery.dataTables.css" />
<script src="<%=request.getContextPath()%>/usersarea/js/unicorn.interface.js"></script>
<script src="<%=request.getContextPath()%>/usersarea/js/jquery.dataTables.min.js"></script>
<script src="<%=request.getContextPath()%>/usersarea/js/jquery.jeditable.js"></script>
<script src="<%=request.getContextPath()%>/usersarea/js/jquery.dataTables.editable.js"></script>
<input type="hidden" id="nodeView" name="nodeView" value="view">
<div class="row">
    <div class="col-12">

        <div class="widget-box">
            <div class="widget-title">
                <span class="icon">
                    <i class="glyphicon glyphicon-th"></i>
                </span>
                <h5>Courses</h5>
            </div>
            <div class="widget-content">
                <table id="courseData" class="display" cellspacing="0" width="100%">
                    <thead>
                        <tr>
                            <th>Serial No.</th>
                            <th>Course Title</th>
                            <th>Unit Load</th>
                            <th>Semester</th>
                            <th>Description</th>
                            <th>Department</th>
                            <th>Level</th>
                            <th>Prerequisites</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>

                    </tbody>
                    <tfoot>
                        <tr>
                            <th>Serial No.</th>
                            <th>Course Title</th>
                            <th>Unit Load</th>
                            <th>Semester</th>
                            <th>Description</th>
                            <th>Department</th>
                            <th>Level</th>
                            <th>Prerequisites</th>
                            <th>Action</th>
                        </tr>
                    </tfoot>
                </table>
            </div>
        </div>  		
    </div>
</div>
<script src="<%=request.getContextPath()%>/usersarea/js/datatable/coursedata.js"></script>
<a href="#addPreCourseModal" id="addpreCourseModalLink" data-toggle="modal" style="display:none" class="btn btn-primary"></a>
<a href="#PreCourseModal" id="preCourseModalLink" data-toggle="modal" style="display:none" class="btn btn-primary"></a>
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
<div id="addPreCourseModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" id="viewCoursesCloseXtra" type="button">×</button>
                <h3>New Prerequisite Course for <h4 id="owning_course_space" ></h4></h3>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" method="post" action="" name="add_pre_course_form" id="add_pre_course_form" novalidate="novalidate">

                    <input type="hidden" id="owningCourse" name="owningCourse"/>

                    <div class="form-group">
                        <label class="control-label">Pre-requisite (s)</label>
                        <div class="controls">
                            <select multiple id="precourses" name="precourses" class="required">
                                <c:forEach items="${courses}"  var="course">
                                    <option value="${course.id}" > <c:out value="${course.courseTitle} (${course.courseCode})"/> </option>
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



<div id="precoursesdiv">

</div>
<script>
    $(document).ready(function (){
        $('select').select2();
    });
    function preparePreCourseForm(modalID, courseID, courseTitle) {
        $('#preViewClose').click();
        $("#owning_course_space").text(courseTitle);
        document.getElementById('owningCourse').value = courseID;
        $("#" + modalID).click();
        $("#deleteConfirmed").click(function() {
            $.ajax({
                url: "/Primus/admin/delete",
                data: "&id=" + courseID,
                async: false,
                dataType: 'json',
                success: function(resp) {
                    $("#message_content").text("Delete action finished. Status - " + resp.message);
                    $("#informerModalLink").click();
                    drawTable();
                },
                error: function(request, status, errorThrown) {
                    $("#message_content").text("Delete action finished. Status - " + status);
                    $("#informerModalLink").click();

                }
            });
        });
        return false;
    }
    
    function deletePreRow(data, extra) {
    $.ajax
            ({
                url: '/Primus/admin/ajax',
                data: data,
                async: false,
                type: 'POST',
                dataType: 'json',
                beforeSend: function() {
                },
                success: function(resp) {
                    $("#message_content").text("Action completed. Status --- " + resp.message);
                    if (extra !== null) {
                        $("#"+extra).click();
                    }
                    $("#informerModalLink").click();
                    return false;
                },
                error: function(request, status, errorThrown) {
                    console.log(errorThrown);
                    var d = $(status);
                }
            });
    return false;
}
</script>