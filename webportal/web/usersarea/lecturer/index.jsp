<%-- 
    Document   : index
    Created on : 10-Mar-2014, 18:34:36
    Author     : Ocheja Patrick Ileanwa
--%>
<%@page import="com.primus.data.Lecturer"%>
<%@page import="com.primus.enums.LecturerPosition"%>
<%@page import="com.primus.appstates.LecturerState"%>
<%
    LecturerState lecturerState = LecturerState.getInstance(request);
    Lecturer lecturer = lecturerState.getCurrentLecturer();
    request.setAttribute("lecHere", lecturer);
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/usersarea/css/custom-style.css" />
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Welcome... Home Page</title>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/usersarea/css/bootstrap.min.css" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/usersarea/css/bootstrap-glyphicons.css" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/usersarea/css/fullcalendar.css" />	
        <link rel="stylesheet" href="<%=request.getContextPath()%>/usersarea/css/unicorn.main.css" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/usersarea/css/unicorn.green.css" class="skin-color" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/usersarea/css/select2.css" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/usersarea/css/jquery-ui.css" />
        <script src="<%=request.getContextPath()%>/usersarea/js/jquery.min.js"></script>

    </head>
    <body>
        <div id="header">
            <h1><a href="<%=request.getContextPath()%>/">Welcome...Home page</a></h1>	
            <a id="menu-trigger" href="<%=request.getContextPath()%>/#"><i class="glyphicon glyphicon-align-justify"></i></a>	
        </div>

        <!-- <div id="search">
                <input type="text" placeholder="Search here..."/><button type="submit" class="tip-right" title="Search"><i class="glyphicon glyphicon-search"></i></button>
        </div> -->
        <div id="user-nav">
            <ul class="btn-group">
                <li class="btn" ><a title="View Profile" href="#profileModal" id="myProfileLink" data-toggle="modal"><i class="glyphicon glyphicon-user"></i> <span class="text">Profile</span></a></li>
                <li class="btn dropdown" id="menu-messages"><a href="<%=request.getContextPath()%>/#" data-toggle="dropdown" data-target="#menu-messages" class="dropdown-toggle"><i class="glyphicon glyphicon-envelope"></i> <span class="text">Messages</span> <span class="label label-danger">5</span> <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a class="sAdd" title="" href="<%=request.getContextPath()%>/#">new message</a></li>
                        <li><a class="sInbox" title="" href="<%=request.getContextPath()%>/#">inbox</a></li>
                        <li><a class="sOutbox" title="" href="<%=request.getContextPath()%>/#">outbox</a></li>
                        <li><a class="sTrash" title="" href="<%=request.getContextPath()%>/#">trash</a></li>
                    </ul>
                </li>
                <li class="btn"><a title="Change Password" href="#settingsModal" id="settingsLink" data-toggle="modal"><i class="glyphicon glyphicon-cog"></i> <span class="text">Settings</span></a></li>
                <li class="btn"><a title="" href="<%=request.getContextPath()%>/logout"><i class="glyphicon glyphicon-share-alt"></i> <span class="text">Logout</span></a></li>
            </ul>
        </div>

        <div id="sidebar">

            <ul>
                <div class="widget-box">
                    <div class="widget-title">
                        <span class="icon"><i class="glyphicon glyphicon-repeat"></i></span>
                        <h5>Recent Activities</h5>
                    </div>
                    <div class="widget-content nopadding">
                        <ul class="activity-list">
                            <c:forEach items="${lecHere.portalActivities}" end="10" var="activity">
                                <fmt:formatNumber value="${activity.milliSecondsElapsed/86400000}" var="days" type="number" maxFractionDigits="0"></fmt:formatNumber>
                                <fmt:formatNumber value="${activity.milliSecondsElapsed/1000}" var="seconds" type="number" maxFractionDigits="0"></fmt:formatNumber>
                                <fmt:formatNumber value="${activity.milliSecondsElapsed/3600000}" var="hours" type="number" maxFractionDigits="0"></fmt:formatNumber>
                                <fmt:formatNumber value="${activity.milliSecondsElapsed/31536000000}" var="years" type="number" maxFractionDigits="0"></fmt:formatNumber>
                                <c:choose>
                                    <c:when test="${activity.milliSecondsElapsed/1000 lt 60}">
                                        <li><a href="#">
                                                <i class="glyphicon glyphicon-user"></i>
                                                <strong> ${activity.actionPerformed} </strong> <span>${seconds} seconds ago</span>
                                            </a>
                                        </li>
                                    </c:when>
                                    <c:when test="${activity.milliSecondsElapsed/1000 ge 60 and  activity.milliSecondsElapsed/1000 lt 3600}">
                                        <li><a href="#">
                                                <i class="glyphicon glyphicon-user"></i>
                                                <strong> ${activity.actionPerformed} </strong> <span>about ${activity.milliSecondsElapsed/60000} minutes ago</span>
                                            </a>
                                        </li>
                                    </c:when>
                                    <c:when test="${activity.milliSecondsElapsed/1000 ge 3600 and  activity.milliSecondsElapsed/1000 lt 86400}">
                                        <li><a href="#">
                                                <i class="glyphicon glyphicon-user"></i>
                                                <strong> ${activity.actionPerformed} </strong> <span>about ${hours} hours ago</span>
                                            </a>
                                        </li>
                                    </c:when>
                                    <c:when test="${activity.milliSecondsElapsed/1000 ge 86400 and  activity.milliSecondsElapsed/1000 lt 31536000}">
                                        <li><a href="#">
                                                <i class="glyphicon glyphicon-user"></i>
                                                <strong> ${activity.actionPerformed} </strong> <span>about ${days} days ago</span>
                                            </a>
                                        </li>
                                    </c:when>
                                    <c:when test="${activity.milliSecondsElapsed/1000 ge 31536000}">
                                        <li><a href="#">
                                                <i class="glyphicon glyphicon-user"></i>
                                                <strong> ${activity.actionPerformed} </strong> <span>about ${years} years ago</span>
                                            </a>
                                        </li>
                                    </c:when>
                                </c:choose>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </ul>

        </div>

        <div id="content">
            <div id="content-header">
                <div class="row">
                    <div class="col-12 center" style="text-align: center;">					
                        <ul class="quick-actions">
                            <li>
                                <a href="<%= request.getContextPath()%>/view?parent=<%=LecturerState.LecturerParentViews.HOME.name()%>">
                                    <i class="icon-home"></i>
                                    <span>Home</span>
                                </a>
                            </li>
                            <%
                                if ((lecturerState.getCurrentLecturer().getLecturerType() != null
                                        && lecturerState.getCurrentLecturer().getLecturerType().getPositions() != null
                                        && lecturerState.getCurrentLecturer().getLecturerType().getPositions()
                                        .contains(LecturerPosition.ACADEMIC_ADVISER))
                                        || (lecturerState.getCurrentLecturer().getLecturerType() != null
                                        && lecturerState.getCurrentLecturer().getLecturerType().getPositions() != null
                                        && lecturerState.getCurrentLecturer().getLecturerType().getPositions()
                                        .contains(LecturerPosition.HOD))
                                        || (lecturerState.getCurrentLecturer().getLecturerType() != null
                                        && lecturerState.getCurrentLecturer().getLecturerType().getPositions() != null
                                        && lecturerState.getCurrentLecturer().getLecturerType().getPositions()
                                        .contains(LecturerPosition.FACULTY_OFFICER))) {
                            %>
                            <li>
                                <a href="<%= request.getContextPath()%>/view?parent=<%=LecturerState.LecturerParentViews.MANAGE_COURSE_FORM.name()%>">
                                    <i class="icon-book"></i>
                                    <span>Manage Course Forms</span>
                                </a>
                            </li>
                            <%
                                }
                            %>
                            <li>
                                <a href="<%= request.getContextPath()%>/view?parent=<%=LecturerState.LecturerParentViews.ENTER_CLASSROOM.name()%>">
                                    <i class="icon-cabinet"></i>
                                    <span>Enter Classroom</span>
                                </a>
                            </li>
                            <li>
                                <a href="<%= request.getContextPath()%>/view?parent=<%=LecturerState.LecturerParentViews.UPLOAD_RESULTS.name()%>">
                                    <i class="icon-database"></i>
                                    <span>Manage Results</span>
                                </a>
                            </li>
                            <li>
                                <a href="<%= request.getContextPath()%>/view?parent=<%=LecturerState.LecturerParentViews.VIEW_MESSAGES.name()%>">
                                    <i class="icon-mail"></i>
                                    <span>Messages</span>
                                </a>
                            </li>
                            <li>
                                <a href="<%= request.getContextPath()%>/view?parent=<%=LecturerState.LecturerParentViews.VIEW_TIMETABLE.name()%>">
                                    <i class="icon-piechart"></i>
                                    <span>Timetable</span>
                                </a>
                            </li>
                        </ul>
                    </div>	
                </div>
            </div>
            <div class="container-fluid">
                <%
            if(lecturerState.getSuccessMessage()!=null){
        %>
        <div class="row" style="margin-top:20px">
            <div class="col-12">
                <div class="alert alert-success">
                    <button class="close" data-dismiss="alert">×</button>
                    <strong>Success!</strong> <%= lecturerState.getSuccessMessage()%>
                </div>
            </div>
        </div>
        <%
                lecturerState.setSuccessMessage(null);
                request.getSession().setAttribute(LecturerState.class.getName(), lecturerState);
            }
            if(lecturerState.getErrorMessage()!=null){
        %>
        <div class="row" style="margin-top:20px">
            <div class="col-12">
                <div class="alert alert-error">
                    <button class="close" data-dismiss="alert">×</button>
                    <strong>Error!</strong> <%= lecturerState.getErrorMessage()%>
                </div>
            </div>
        </div>
        <%
                lecturerState.setErrorMessage(null);
                request.getSession().setAttribute(LecturerState.class.getName(), lecturerState);
            }
        %>
                <jsp:include flush="true" page="<%= lecturerState.getLecturerParentViews().getPage()%>"/>
            </div>

        </div>
        <div class="row">
            <div id="footer" class="col-12">
                2013 - 2014 &copy; University of Nigeria Portal. Brought to you by <a href="<%=request.getContextPath()%>/#">Project Primus</a>
            </div>
        </div>
        <div id="profileModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button data-dismiss="modal" class="close" type="button">×</button>
                        <h3>Profile</h3>
                    </div>
                    <div class="modal-body">
                        <div class="circular" style="background: url('/Primus/imagedisp/imagecontroller?id=${lecHere.id}&who=lecturer') no-repeat; background-size: 120px;">
                        </div> 
                        <div class="row">
                            <div class="span6 col-sm-6" >
                                <span class="label-name">Full Name</span>
                            </div>
                            <div class="span6 col-sm-6" >
                                <span class="label-value"><c:out value="${lecHere.lecturerName.surname} ${lecHere.lecturerName.firstName} ${lecHere.lecturerName.middleName}"></c:out> </span>
                                </div>
                            </div>
                            <div class="row">
                                <div class="span6 col-sm-6" >
                                    <span class="label-name">Faculty</span>
                                </div>
                                <div class="span6 col-sm-6" >
                                    <span class="label-value"><c:out value="${lecHere.department.faculty.facultyName.name}"></c:out></span>
                                </div>
                            </div>
                            <div class="row">
                                <div class="span6 col-sm-6" >
                                    <span class="label-name">Email</span>
                                </div>
                                <div class="span6 col-sm-6" >
                                    <span class="label-value"><c:out value="${lecHere.email}"></c:out></span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="settingsModal" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button data-dismiss="modal" class="close" type="button">×</button>
                            <h3>Change Password</h3>
                        </div>
                        <form class="form-horizontal" method="POST" action="" id="changepasswordform">
                            <input type="hidden"  name="user" id="user" value="lecturer">
                            <div class="form-group">
                                <label class="control-label">Old Password</label>
                                <div class="controls">
                                    <input type="password" class="required form-control input-small" name="oldpassword" id="oldpassword">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label">New Password</label>
                                <div class="controls">
                                    <input type="password" class="required form-control input-small" name="newpassword" id="newpassword">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label">Re-type New Password</label>
                                <div class="controls">
                                    <input type="password" class="required form-control input-small" name="renewpassword" id="renewpassword">
                                </div>
                            </div>
                            <div class="form-actions">
                                <input type="submit" value="Change" id="create_btn" class="btn btn-success btn-max">
                                <div id="loading_gif" style="display: none">
                                    <img src="<%=request.getContextPath()%>/usersarea/img/notice_spinner.gif"/>
                                <label id="message_on_end">Loading...</label>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <a href="#settingsResponseModal" id="settingsResponseModalLink" data-toggle="modal" style="display:none" class="btn btn-primary"></a>
        <div id="settingsResponseModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button data-dismiss="modal" class="close" type="button">×</button>
                        <h3>Notification</h3>
                    </div>
                    <div class="modal-body">
                        <p id="settings_message_content">Success</p>
                    </div>
                </div>
            </div>
        </div>

        <script src="<%=request.getContextPath()%>/usersarea/js/bootstrap.min.js"></script>
        <script src="<%=request.getContextPath()%>/usersarea/js/ajax-courseforms.js"></script>
        <script src="<%=request.getContextPath()%>/usersarea/js/ajax-timetable.js"></script>
        <script src="<%=request.getContextPath()%>/usersarea/js/jquery.validate.js"></script>
        <script src="<%=request.getContextPath()%>/usersarea/js/jquery.dataTables.min.js"></script>
        <script src="<%=request.getContextPath()%>/usersarea/js/select2.min.js"></script>
        <script src="<%=request.getContextPath()%>/usersarea/js/unicorn.form_validation.js"></script>
        <script src="<%=request.getContextPath()%>/usersarea/js/additional-methods.js"></script>
                <!--<script src="<%=request.getContextPath()%>/usersarea/js/unicorn.dashboard.js"></script>-->
    </body>
</html>