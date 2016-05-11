<%-- 
    Document   : courseform-session-options
    Created on : 08-July-2014, 15:36:11
    Author     : Ocheja Patrick Ileanwa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="row" id="courseform-arena">
    <jsp:include page="/usersarea/lecturer/approvecourseforms.jsp" flush="false"/>    
</div>
<a href="#addLecturerCourseModal" id="addLecturerCourseModalLink" data-toggle="modal" style="display:none" class="btn btn-primary"></a>
<a href="#addLecturerPositionModal" id="addLecturerPositionModalLink" data-toggle="modal" style="display:none" class="btn btn-primary"></a>
<a href="#LecturerCourseModal" id="LecturerCourseModalLink" data-toggle="modal" style="display:none" class="btn btn-primary"></a>
<a href="#studentCoursesModal" id="studentCoursesModalLink" data-toggle="modal" style="display:none" class="btn btn-primary"></a>
<a href="#LecturerPositionModal" id="LecturerPositionModalLink" data-toggle="modal" style="display:none" class="btn btn-primary"></a>
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
                <p id="alert_content" >Confirm Action</p>
            </div>
            <div class="modal-footer">
                <a data-dismiss="modal" id="actionConfirmed" class="btn btn-primary btn-small" href="#">Confirm</a>
                <a data-dismiss="modal" class="btn btn-default btn-small" href="#">Cancel</a>
            </div>
        </div>
    </div>
</div>
<div id="addLecturerCourseModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" id="viewCoursesCloseXtra" type="button">×</button>
                <h3>New Course (s) for <h4 class="owning_lecturer_space" ></h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" method="post" action="" name="add_lecturer_course_form" id="add_lecturer_course_form" novalidate="novalidate">

                    <input type="hidden" id="owningLecturer" name="owningLecturer"/>

                    <div class="form-group">
                        <label class="control-label">Course (s)</label>
                        <div class="controls">
                            <select multiple id="courses" name="courses" class="required">
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

<div id="addLecturerPositionModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" id="viewPositionCloseXtra" type="button">×</button>
                <h3>New Position (s) for <h4 class="owning_lecturer_space" ></h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" method="post" action="" name="add_lecturer_position_form" id="add_lecturer_position_form" novalidate="novalidate">

                    <input type="hidden" id="owningLecturer" name="owningLecturer"/>

                    <div class="form-group">
                        <label class="control-label">Position (s)</label>
                        <div class="controls">
                            <select multiple id="positions" name="positions" class="required">
                                <c:forEach items="${positions}"  var="position">
                                    <option value="${position}" > <c:out value="${position}"/> </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Level</label>
                        <div class="controls">
                            <select id="academiclevel" name="academiclevel" disabled="disabled" class="required">
                                <option value="" selected=""></option>
                                <c:forEach items="${levels}"  var="level">
                                    <option value="${level}" > <c:out value="${level}"/> </option>
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


<div id="studentcoursesdiv">

</div>
<div id="lecturerpositionsdiv">

</div>
<script>
    $(document).ready(function() {
        $('select').select2();
        $("#positions").change(function() {
            toggleacademiclevelFields();
        });
    });
    function validateCourseForm(data, url, preViewBoxToClose,modalID) {
        $('#' + preViewBoxToClose).click();
        $("#" + modalID).click();
        $("#actionConfirmed").click(function() {
            
            console.log('dadddsdsd: '+data+'Urrrrrlrllll:'+url);
            $.ajax({
                url: url,
                type: 'GET',
                data: data,
                async: false,
                dataType: 'json',
                success: function(resp) {
                    $("#message_content").text("Status - " + resp.message);
                    $("#informerModalLink").click();
                },
                error: function(request, status, errorThrown) {
                    $("#message_content").text("Status - " + status);
                    $("#informerModalLink").click();

                }
            });
        });
        return false;
    }

    function deleteLecturerCourseRow(data, extra) {
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
                            $("#" + extra).click();
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

    function toggleacademiclevelFields() {
        var selectedItems = [];
        var containsPosition = false;
//selector uses id substring
        $("#positions option:selected").each(function(i, obj) {
            
            console.log('position:'+$(this).val());
            if($(this).val()==='ACADEMIC_ADVISER'){
                containsPosition = true;
        }
        });
        if(containsPosition){
            $("#academiclevel").removeAttr('disabled');
        }else {
            $("#academiclevel").attr('disabled', 'true');
            $("#academiclevel").attr('value', '');
        }
    }
    
    
</script>