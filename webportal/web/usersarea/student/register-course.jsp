<%-- 
    Document   : register-course
    Created on : 08-May-2014, 15:36:11
    Author     : Ocheja Patrick Ileanwa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link rel="stylesheet" href="css/icheck/flat/blue.css" />
<div id="content">
    <div id="content-header">
        <h1>Course Form wizard</h1>
        <div class="btn-group">
            <a class="btn" title="Manage Files"><i class="glyphicon glyphicon-file"></i></a>
            <a class="btn" title="Manage Users"><i class="glyphicon glyphicon-user"></i></a>
            <a class="btn" title="Manage Comments"><i class="glyphicon glyphicon-comment"></i><span class="label label-danger">5</span></a>
            <a class="btn" title="Manage Orders"><i class="glyphicon glyphicon-shopping-cart"></i></a>
        </div>
    </div>
    <div id="breadcrumb">
        <a href="#" title="Go to Home" class="tip-bottom"><i class="glyphicon glyphicon-home"></i> Home</a>
        <a href="#">Form elements</a>
        <a href="#" class="current">Form wizard</a>
    </div>
    <div class="row">
        <div class="col-12">
            <div class="widget-box">
                <div class="widget-title">
                    <span class="icon">
                        <i class="glyphicon glyphicon-pencil"></i>
                    </span>
                    <h5>Form wizard with validation</h5>
                </div>
                <div class="widget-content nopadding">
                    <form id="form-wizard" class="form-horizontal" method="post">
                        <div id="form-wizard-1" class="step">
                            <div class="form-group">
                                <label class="control-label">Username</label>
                                <div class="controls">
                                    <input class="form-control input-small" id="username" type="text" name="username" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label">Password</label>
                                <div class="controls">
                                    <input class="form-control input-small" id="password" type="password" name="password" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label">Confirm Password</label>
                                <div class="controls">
                                    <input class="form-control input-small" id="password2" type="password" name="password2" />
                                </div>
                            </div>

                        </div>
                        <div id="form-wizard-2" class="step">
                            <div class="form-group">
                                <label class="control-label">Email</label>
                                <div class="controls">
                                    <input class="form-control input-small" id="email" type="text" name="email" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label">EULA Acceptation</label>
                                <div class="controls">
                                    <input id="eula" type="checkbox" name="eula" />
                                </div>
                            </div>
                        </div>
                        <div class="form-actions">
                            <input id="back" class="btn btn-primary" type="reset" value="Back" />
                            <input id="next" class="btn btn-primary" type="submit" value="Next" />
                            <div id="status"></div>
                        </div>
                        <div id="submitted"></div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


<script src="js/jquery.icheck.min.js"></script>
<script src="js/jquery.validate.js"></script>
<script src="js/jquery.wizard.js"></script>
<script src="js/unicorn.wizard.js"></script>