<%-- 
    Document   : adminhomepage
    Created on : 14-May-2014, 06:03:50
    Author     : Ocheja Patrick Ileanwa
--%>

<%@page import="com.primus.appstates.AdministratorState"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<input type="hidden" id="nodeView" name="nodeView" value="create">
<div class="row">
    <div class="col-12">
        <div class="widget-box">
            <div class="widget-title">
                <span class="icon">
                    <i class="glyphicon glyphicon-align-justify"></i>									
                </span>
                <h5>New Administrator Form</h5>
            </div>
            <div class="widget-content nopadding">
                <form class="form-horizontal" method="post" enctype="multipart/form-data" action="/Primus/admin" name="create_admin_form" id="create_admin_form" novalidate="novalidate">
                    <input type="hidden" id="action" name="action" value="registerNewAdmin">
                    <div class="row">
                        <div class="col-12 col-sm-6 form-group">
                            <label class="control-label">First Name</label>
                            <div class="controls">
                                <input type="text" class=" required form-control input-small" name="firstName" id="firstName">
                            </div>
                        </div>
                        <div class="col-12 col-sm-6 form-group">
                            <label class="control-label">Surname</label>
                            <div class="controls">
                                <input type="text" class=" required form-control input-small" name="surname" id="surname">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12 col-sm-6 form-group">
                            <label class="control-label">Middle Name</label>
                            <div class="controls">
                                <input type="text" class="form-control input-small" name="middleName" id="middleName">
                            </div>
                        </div>
                        <div class="col-12 col-sm-6 form-group">
                            <label class="control-label">Email</label>
                            <div class="controls">
                                <input type="text" class="form-control input-small" name="email" id="email">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12 col-sm-6 form-group">
                            <label class="control-label">Password</label>
                            <div class="controls">
                                <input type="password" class="form-control input-small" name="password" id="password">
                            </div>
                        </div>
                        <div class="col-12 col-sm-6 form-group">
                            <label class="control-label">Confirm password</label>
                            <div class="controls">
                                <input type="password" class="form-control input-small" name="confirmPassword" id="confirmPassword">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12 col-sm-6 form-group">
                            <label class="control-label">Passport Photograph</label>
                            <div class="controls">
                                <input type="file" name="profileimage" id="profileimage">
                            </div>
                        </div>
                        <div class="col-12 col-sm-6 form-group">
                            <label class="control-label">Scan Finger Print</label>
                            <div class="controls">
                                <input type="file" name="fingerPrint" id="fingerPrint" disabled="disabled">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12 col-sm-6 form-group">
                            <label class="control-label">Phone Number</label>
                            <div class="controls">
                                <input type="text" class="form-control input-small" name="phoneNumber" id="phoneNumber">
                            </div>
                        </div>
                        <div class="col-12 col-sm-6 form-group">
                            <label class="control-label">Admin Type</label>
                            <div class="controls">
                                <label><input type="radio" name="adminType"  class="required" id="adminTypeSuper" value="superAdmin" /> Super Admin</label>
                                <label><input type="radio" name="adminType" id="adminTypeModerator"  class="required" value="moderatorAdmin" /> Moderator</label>
                            </div>
                        </div>
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
        <div class="widget-box">
            <div class="widget-title">
                <span class="icon">
                    <i class="glyphicon glyphicon-align-justify"></i>									
                </span>
                <h5>Batch Administrator Creation (EXCEL)</h5>
            </div>
            <div class="widget-content nopadding">
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
</div>