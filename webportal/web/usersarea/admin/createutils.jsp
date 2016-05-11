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
        <div class="widget-box collapsible">
            <a href="#sessionForm" data-toggle="collapse">
                <div class="widget-title">
                    <span class="icon">
                        <i class="glyphicon glyphicon-align-justify"></i>									
                    </span>
                    <h5>New Session Form</h5>
                </div>

            </a>
            <div class="widget-content nopadding collapse" id="sessionForm">
                <form class="form-horizontal" method="post" action="" name="create_session_form" id="create_session_form" novalidate="novalidate">
                    <div class="row">
                        <div class="col-12 col-sm-6 form-group">
                            <label class="control-label">Start Year</label>
                            <div class="controls">
                                <input type="text" class=" required digits form-control input-small" name="startYear" id="startYear">
                            </div>
                        </div>
                        <div class="col-12 col-sm-6 form-group">
                            <label class="control-label">End Year</label>
                            <div class="controls">
                                <input type="text" class=" required digits form-control input-small" name="endYear" id="endYear">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12 col-sm-6 form-group">
                            <label class="control-label">Use Existing Grading Scheme</label>
                            <div class="controls">
                                <input type="checkbox" class="form-control" name="useExistingGradingScheme" id="useExistingGradingScheme">
                            </div>
                        </div>
                        <div class="col-12 col-sm-6 form-group">
                            <label class="control-label">Select Result Grading Scheme</label>
                            <div class="controls">
                                <select id="gradingscheme" name="gradingscheme" class="required" disabled>
                                    <option value="" selected></option>
                                    <c:forEach items="${gradingschemes}"  var="gradingscheme">
                                        <option value="${gradingscheme.id}" > <c:out value="${gradingscheme.academicSession.startYear}/${gradingscheme.academicSession.endYear}"/> </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12 col-sm-12 form-group">
                            <label class="control-label">Enter Number of Grade Letters (e.g A,B,C,D,E,F means 6 grade letters)</label>
                            <div class="controls">
                                <input type="text" class="form-control required" name="numberOfGradeLetters" id="numberOfGradeLetters">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12 col-sm-6 form-group">
                            <label class="control-label">Fail Grade Letter </label>
                            <div class="controls">
                                <input type="text" class="form-control required" name="failgradeletter" id="failgradeletter">
                            </div>
                        </div>
                        <div class="col-12 col-sm-6 form-group">
                            <label class="control-label">Maximum Total Units Load </label>
                            <div class="controls">
                                <input type="number" class="form-control required digits" name="maximumUnits" id="maximumUnits">
                            </div>
                        </div>
                    </div>
                    <div id="gradeschemefields">

                    </div>
                    <div class="row">
                        <div class="col-12 col-sm-6 form-actions">
                            <input type="submit" value="Create" id="create_btn" class="btn btn-success btn-max">
                            <div id="loading_gif" style="display: none">
                                <img src="<%=request.getContextPath()%>/usersarea/img/notice_spinner.gif"/>
                                <label id="message_on_end">Loading...</label>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>			
    </div>
</div>

<div class="row">
    <div class="col-12">
        <div class="widget-box collapsible">
            <a href="#degreerequirmentformdiv" data-toggle="collapse">
                <div class="widget-title">
                    <span class="icon">
                        <i class="glyphicon glyphicon-align-justify"></i>									
                    </span>
                    <h5>New Degree Requirement Form</h5>
                </div>

            </a>
            <div class="widget-content nopadding collapse" id="degreerequirmentformdiv">
                <form class="form-horizontal" method="post" action="" name="create_degree_requirement_form" id="create_degree_requirement_form" novalidate="novalidate">
                    <div class="row">
                        <div class="col-12 col-sm-6 form-group">
                            <label class="control-label">Title of Degree</label>
                            <div class="controls">
                                <select id="titleofdegree" name="titleofdegree" class="required">
                                    <option value="" selected></option>
                                    <c:forEach items="${titleofdegrees}"  var="titleofdegree">
                                        <option value="${titleofdegree}" > <c:out value="${titleofdegree}"/> </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col-12 col-sm-6 form-group">
                            <label class="control-label">Department</label>
                            <div class="controls">
                                <select id="department" name="department" class="required">
                                    <option value="" selected></option>
                                    <c:forEach items="${departments}"  var="department">
                                        <option value="${department.id}" > <c:out value="${department.departmentName.name}"/> </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12 col-sm-6 form-group">
                            <label class="control-label">Min. Year</label>
                            <div class="controls">
                                <input type="text" class=" required digits form-control input-small" name="minYear" id="minYear">
                            </div>
                        </div>
                        <div class="col-12 col-sm-6 form-group">
                            <label class="control-label">Max. Year</label>
                            <div class="controls">
                                <input type="text" class=" required digits form-control input-small" name="maxYear" id="maxYear">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12 col-sm-12 form-group">
                            <label class="control-label">Enter Number of Levels (max. 7)</label>
                            <div class="controls">
                                <input type="text" class="form-control required" name="numberOfLevels" id="numberOfLevels">
                            </div>
                        </div>
                    </div>
                    <div id="levelfields">

                    </div>
                    <div class="row">
                        <div class="col-12 col-sm-6 form-actions">
                            <input type="submit" value="Create" id="create_btn" class="btn btn-success btn-max">
                            <div id="loading_gif" style="display: none">
                                <img src="<%=request.getContextPath()%>/usersarea/img/notice_spinner.gif"/>
                                <label id="message_on_end">Loading...</label>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>			
    </div>
</div>
<div class="row">
    <div class="col-12">
        <div class="widget-box collapsible">
            <a href="#batchutilsForms" data-toggle="collapse">
                <div class="widget-title">
                    <span class="icon">
                        <i class="glyphicon glyphicon-align-justify"></i>									
                    </span>
                <h5>Batch Utils Creation (EXCEL)</h5>
                </div>

            </a>

            <div class="widget-content nopadding collapse" id="batchutilsForms">
                <form class="form-horizontal" method="post" action="/Primus/admin" name="create_batch_admin_form" id="create_batch_admin_form" novalidate="novalidate">

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
<script>
    $(document).ready(function() {
        $('select').select2();
        $('#numberOfLevels').bind('blur keyup change', function() {
            var n = this.value || 0;
            var data = 'action=levelFields&numberOfLevels=' + n;
            if (n + 1) {
                $.ajax
                        ({
                            url: '/Primus/admin/pull',
                            data: data,
                            async: false,
                            type: 'GET',
                            success: function(resp, status, xhr) {
                                var ct = xhr.getResponseHeader("content-type") || "";
                                if (ct.indexOf('html') > -1) {
                                    var d = $(resp);
                                    var htmlStr = "";
                                    for (i = 0; i < d.length; i++) {
                                        var node = d[i];
                                        if (node.id === 'levelfields') {
                                            htmlStr = $(node).html();
                                            break;
                                        }
                                    }
                                    if (htmlStr.length > 0) {
                                        $('#levelfields').html(htmlStr);
                                        $('.coursesforlevel').select2();
                                    } else {

                                    }
                                }
                                if (ct.indexOf('json') > -1) {
                                    console.log(resp);
                                }
                                return false;
                            },
                            error: function(request, status, errorThrown) {
                                console.log(errorThrown);
                                var d = $(status);
                            }
                        });
            }
        });
        $('#numberOfGradeLetters').bind('blur keyup change', function() {
            var n = this.value || 0;
            var data = 'action=gradeSchemeFields&numberOfGradeLetters=' + n;
            if (n + 1) {
                $.ajax
                        ({
                            url: '/Primus/admin/pull',
                            data: data,
                            async: false,
                            type: 'GET',
                            success: function(resp, status, xhr) {
                                var ct = xhr.getResponseHeader("content-type") || "";
                                if (ct.indexOf('html') > -1) {
                                    var d = $(resp);
                                    var htmlStr = "";
                                    for (i = 0; i < d.length; i++) {
                                        var node = d[i];
                                        if (node.id === 'gradeschemefields') {
                                            htmlStr = $(node).html();
                                            break;
                                        }
                                    }
                                    if (htmlStr.length > 0) {
                                        $('#gradeschemefields').html(htmlStr);
                                    } else {

                                    }
                                }
                                if (ct.indexOf('json') > -1) {
                                    console.log(resp);
                                }
                                return false;
                            },
                            error: function(request, status, errorThrown) {
                                console.log(errorThrown);
                                var d = $(status);
                            }
                        });
            }
        });

        $("#useExistingGradingScheme").change(function() {
            toggleFields();
        });
    });
    function toggleFields() {
        if (document.getElementById('useExistingGradingScheme').checked) {
            $("#gradingscheme").removeAttr('disabled');
            $("#numberOfGradeLetters").attr('value', '');
            $("#numberOfGradeLetters").blur();
            $("#numberOfGradeLetters").attr('disabled', 'true');
//			$("#studentfields").hide();
        } else {
            $("#gradingscheme").attr('disabled', 'true');
            ;
            $("#gradingscheme").attr('selected', '');
            ;
            $("#numberOfGradeLetters").removeAttr('disabled');
//	    	$("#studentfields").hide();
        }
    }
</script>

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