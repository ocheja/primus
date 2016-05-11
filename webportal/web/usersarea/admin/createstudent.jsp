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
                <h5>New Student Form</h5>
            </div>
            <div class="widget-content nopadding">
                <form class="form-horizontal" method="post" enctype="multipart/form-data" action="/Primus/admin" name="create_student_form" id="create_student_form" novalidate="novalidate">
                    <input type="hidden" id="action" name="action" value="registerNewStudent">
                    <div class="form-group">
                        <label class="control-label">Jamb Registration (Matric No. will be auto-generated)</label>
                        <div class="controls">
                            <input type="text" class=" required form-control input-small" name="jregnum" id="jregnum">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">First Name</label>
                        <div class="controls">
                            <input type="text" class=" required form-control input-small" name="firstName" id="firstName">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label">Surname</label>
                        <div class="controls">
                            <input type="text" class=" required form-control input-small" name="surname" id="surname">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Middle Name</label>
                        <div class="controls">
                            <input type="text" class="form-control input-small" name="middleName" id="middleName">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Date of Birth</label>
                        <div class="controls">
                            <input type="text" data-date="12-02-2012" name="dateofbirth" id="dateofbirth"
                                   data-date-format="dd-mm-yyyy" value="12-02-2012" 
                                   class="datepicker form-control input-small" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Gender</label>
                        <div class="controls">
                            <select  class="required" id="gender" name="gender">
                                <option selected="selected" value=""></option>
                                <option  value="Female">Female</option>
                                <option  value="Male">Male</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Email</label>
                        <div class="controls">
                            <input type="text" class="form-control input-small" name="email" id="email">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Password (default: "unn" without quotes)</label>
                        <div class="controls">
                            <input type="password" value="unn" class="form-control input-small" name="password" id="password">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Confirm password</label>
                        <div class="controls">
                            <input type="password" min="3" value="unn" class="form-control input-small" name="confirmPassword" id="confirmPassword">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Date admitted</label>
                        <div class="controls">
                            <input type="text" data-date="12-02-2009" id="dateadmitted" name="dateadmitted" 
                                   data-date-format="dd-mm-yyyy" value="12-02-2009" 
                                   class="required datepicker form-control input-small" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Passport Photograph</label>
                        <div class="controls">
                            <input type="file" name="profileimage" id="profileimage">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Scan Finger Print</label>
                        <div class="controls">
                            <input type="file" name="fingerPrint" id="fingerPrint" disabled="disabled">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Phone Number</label>
                        <div class="controls">
                            <input type="text" class="form-control input-small" name="phoneNumber" id="phoneNumber">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Programme</label>
                        <div class="controls">
                            <select  class="required" id="programme" name="programme">
                                <option selected="selected" value=""></option>
                                <option  value="Undergraduate">Undergraduate</option>
                                <option  value="Masters">Masters</option>
                                <option  value="Doctorate">Doctorate</option>
                                <option  value="Undergraduate/Masters">Undergraduate/Masters</option>
                                <option  value="Masters/PhD">Masters/Doctorate</option>
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
                    <div class="form-group">
                        <label class="control-label">Level</label>
                        <div class="controls">
                            <select  class="required" id="level" name="level">
                                <option selected="selected" value=""></option>
                                <c:forEach items="${levels}"  var="level">
                                    <option value="${level}" > <c:out value="${level}"/> </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Short Personal Statement</label>
                        <div class="controls">
                            <textarea cols="6" rows="10"  class=" form-control" name="careerdescription" id="careerdescription">

                            </textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Profile/Career description documents</label>
                        <div class="controls">
                            <input type="file" name="profiledocs" id="profiledocs" disabled="disabled">
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
                <h5>Batch Lecturer Creation (EXCEL)</h5>
            </div>
            <div class="widget-content nopadding">
                <form class="form-horizontal" method="post" action="/Primus/admin" name="create_batch_lecturer_form" id="create_batch_lecturer_form" novalidate="novalidate">

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
        $('.datepicker').datepicker();
    });
</script>