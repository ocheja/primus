<%-- 
    Document   : login
    Created on : 13-May-2014, 16:34:09
    Author     : Ocheja Patrick Ileanwa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Login Page...UNN</title>
		<meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/usersarea/css/bootstrap.min.css" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/usersarea/css/bootstrap-glyphicons.css" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/usersarea/css/unicorn.login.css" />
    </head>
    <body>
        <div id="container">
            <div id="logo">
                <a href="/Primus"><img src="<%=request.getContextPath()%>/usersarea/img/logo.png" alt="" /></a>
            </div>
            <div id="loginbox">            
                <form id="loginform" action="/Primus/login" method="post">
    				<p>Enter username and password to continue.</p>
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <input class="form-control" type="text" id="userId" name="userId" required="required" placeholder="email or registration number" />
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                        <input class="form-control"  required="required" name="loginPassword" id="loginPassword" type="password" placeholder="Password" />
                    </div>
                        <!-- <div class="row">
                            <div class="col-4 col-lg-4"><a class="btn btn-small btn-block btn-primary" href="#">Facebook</a></div>
                            <div class="col-4 col-lg-4"><a class="btn btn-small btn-block btn-info" href="#"><i class="glyphicon glyphicon-sign-in"></i> Twitter</a></div>
                            <div class="col-4 col-lg-4"><a class="btn btn-small btn-block btn-danger" href="#">Google Plus</a></div>
                        </div> -->
                    <hr />
                    <div class="form-actions">
                        <div class="pull-left">
                            <a href="#" class="flip-link to-recover">Lost password?</a><br />
                            <a href="#" class="flip-link to-register">Need account? Register here!</a>
                        </div>
                        <div class="pull-right"><input type="submit" class="btn btn-default" value="Login" /></div>
                    </div>
                </form>
                <form id="recoverform" action="#">
    				<p>Enter your e-mail address below and we will send you instructions how to recover a password.</p>
    				<div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span><input class="form-control" type="text" placeholder="E-mail address" />
                    </div>
                    <hr />
                    <div class="form-actions">
                        <div class="pull-left">
                            <a href="#" class="flip-link to-login">&laquo; Back to login</a><br />
                            <a href="#" class="flip-link to-register">Need account? Register here!</a>
                        </div>
                        <div class="pull-right"><input type="submit" class="btn btn-default" value="Recover" /></div>
                    </div>
                </form>
                <form id="registerform" action="#">
                    <p>Enter information required to register:</p>
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span><input class="form-control" type="text" placeholder="Enter Username" />
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span><input class="form-control" type="password" placeholder="Choose Password" />
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span><input class="form-control" type="password" placeholder="Confirm password" />
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span><input class="form-control" type="text" placeholder="Enter E-mail address" />
                    </div>
                    <hr />
                    <div class="form-actions">
                        <div class="pull-left">
                            <a href="#" class="flip-link to-login">&laquo; Back to login</a><br />
                            <a href="#" class="flip-link to-recover">Lost password?</a>
                        </div>
                        <div class="pull-right"><input type="submit" class="btn btn-default" value="Register" /></div>
                    </div>
                </form>
            </div>
        </div>
        
        <script src="<%=request.getContextPath()%>/usersarea/js/jquery.min.js"></script>  
        <script src="<%=request.getContextPath()%>/usersarea/js/unicorn.login.js"></script> 
    </body>
</html>
