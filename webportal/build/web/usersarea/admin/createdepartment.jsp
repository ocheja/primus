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
                <h5>New Department Form</h5>
            </div>
            <div class="widget-content nopadding">
                <form class="form-horizontal" method="post" action="" name="create_department_form" id="create_department_form" novalidate="novalidate">
                    <div class="form-group">
                        <label class="control-label">Name of Department</label>
                        <div class="controls">
                            <input type="hidden" class="required" id="departmentName" name="departmentName"/>

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Description</label>
                        <div class="controls">
                            <input type="text" class=" required form-control input-small" name="description" id="description">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Faculty</label>
                        <div class="controls">
                            <select  class="required" id="faculty" name="faculty">
                                <option selected="selected" value=""></option>
                                <c:forEach items="${faculties}"  var="faculty">
                                    <option value="${faculty.id}" > <c:out value="${faculty.facultyName.name}"/> </option>
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
                <h5>Batch Department Creation (EXCEL)</h5>
            </div>
            <div class="widget-content nopadding">
                <form class="form-horizontal" method="post" action="/Primus/faculty" name="create_batch_faculty_form" id="create_batch_faculty_form" novalidate="novalidate">

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
        var lastResults = [];
        $('select').select2();
        $("#departmentName").select2({
            placeholder: "Search for a department name or add new",
            minimumInputLength: 3,
            multiple: false,
                ajax: {
                url: "/Primus/admin/ajax",
                dataType: 'json',
                type: "GET",
                    data: function(term, page) {
                        return {
                        q: term, // search term
                    action: "departmentNames"
                };
                },
                    results: function(data, page) {
                    lastResults = data;
                return {results: data};
                }
                },
                    createSearchChoice: function(term) {
                        var text = term + (lastResults.some(function(r) {
                    return r.text === term;
                    }) ? "" : " (new)");
            return {id: term, text: text};
        }
    });
    });
</script>