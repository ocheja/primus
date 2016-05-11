<%-- 
    Document   : index
    Created on : 10-Mar-2014, 18:34:36
    Author     : Ocheja Patrick Ileanwa
--%><%@page import="com.primus.appstates.AdministratorState"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    AdministratorState administratorState = AdministratorState.getInstance(request);
    request.setAttribute("administrator", administratorState.getCurrentAdministrator());
%>
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
        <link rel="stylesheet" href="<%=request.getContextPath()%>/usersarea/css/custom-style.css" />
        <script src="<%=request.getContextPath()%>/usersarea/js/jquery.min.js"></script>
    </head>
    <body>
        <div id="header">
            <h1><a href="<%=request.getContextPath()%>/">Welcome...Home page</a></h1>	
            <a id="menu-trigger" href="<%=request.getContextPath()%>/#"><i class="glyphicon glyphicon-align-justify"></i></a>	
        </div>
        <input type="hidden" id="baseView" name="baseView" value="admin">
        <!-- <div id="search">
                <input type="text" placeholder="Search here..."/><button type="submit" class="tip-right" title="Search"><i class="glyphicon glyphicon-search"></i></button>
        </div> -->
        <div id="user-nav">
            <ul class="btn-group">
                <li class="btn" ><a title="" href="#profileModal" id="myProfileLink" data-toggle="modal"><i class="glyphicon glyphicon-user"></i> <span class="text">Profile</span></a></li>
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
            <!--<a href="<%=request.getContextPath()%>/#" class="hide"><i class="glyphicon glyphicon-home"></i> Dashboard</a>-->
            <ul>
                <li id="home_link"><a href="<%=request.getContextPath()%>/view?parent=<%=AdministratorState.AdminParentViews.HOME.name()%>&node=<%=AdministratorState.AdminNodeViews.HOME_PROFILE.name()%>">
                        <i class="glyphicon glyphicon-home"></i> 
                        <span>Home</span></a>
                </li>
                <li class="submenu" id="admin_manager_main">
                    <a href="<%=request.getContextPath()%>/#"><i class="glyphicon glyphicon-th-list"></i> 
                        <span>Admin Manager</span> 
                        <span class="label">2</span></a>
                    <ul>
                        <li id="create_admin_manager"><a href="<%=request.getContextPath()%>/view?parent=<%=AdministratorState.AdminParentViews.ADMIN_MANAGER.name()%>&node=<%=AdministratorState.AdminNodeViews.CREATE.name()%>">Create New Administrator</a></li>
                        <li id="view_admin_manager"><a href="<%=request.getContextPath()%>/view?parent=<%=AdministratorState.AdminParentViews.ADMIN_MANAGER.name()%>&node=<%=AdministratorState.AdminNodeViews.VIEW.name()%>">Manage Administrators</a></li>
                    </ul>
                </li>
                <li class="submenu" id="lecturer_manager_main">
                    <a href="<%=request.getContextPath()%>/#"><i class="glyphicon glyphicon-th-list"></i> 
                        <span>Lecturer Manager</span> 
                        <span class="label">2</span></a>
                    <ul>
                        <li id="create_lecturer_manager"><a href="<%=request.getContextPath()%>/view?parent=<%=AdministratorState.AdminParentViews.LECTURER_MANAGER.name()%>&node=<%=AdministratorState.AdminNodeViews.CREATE_LECTURER.name()%>">Create New Lecturer</a></li>
                        <li id="view_lecturer_manager"><a href="<%=request.getContextPath()%>/view?parent=<%=AdministratorState.AdminParentViews.LECTURER_MANAGER.name()%>&node=<%=AdministratorState.AdminNodeViews.VIEW_LECTURERS.name()%>">Manage Lecturers</a></li>
                    </ul>
                </li>
                <li class="submenu" id="student_manager_main">
                    <a href="<%=request.getContextPath()%>/#"><i class="glyphicon glyphicon-th-list"></i> <span>Student Manager</span> 
                        <span class="label">4</span></a>
                    <ul>
                        <li id="create_student_manager"><a href="<%=request.getContextPath()%>/view?parent=<%=AdministratorState.AdminParentViews.STUDENT_MANAGER.name()%>&node=<%=AdministratorState.AdminNodeViews.CREATE_STUDENT.name()%>">Create New Student</a></li>
                        <li id="view_student_manager"><a href="<%=request.getContextPath()%>/view?parent=<%=AdministratorState.AdminParentViews.STUDENT_MANAGER.name()%>&node=<%=AdministratorState.AdminNodeViews.VIEW_STUDENTS.name()%>">Manage Students</a></li>
                    </ul>
                </li>
                <li class="submenu" id="faculty_manager_main">
                    <a href="<%=request.getContextPath()%>/#"><i class="glyphicon glyphicon-th-list"></i> <span>Faculty Manager</span> 
                        <span class="label">2</span></a>
                    <ul>
                        <li id="create_faculty_manager"><a href="<%=request.getContextPath()%>/view?parent=<%=AdministratorState.AdminParentViews.FACULTY_MANAGER.name()%>&node=<%=AdministratorState.AdminNodeViews.CREATE_FACULTY.name()%>">Create New Faculty</a></li>
                        <li id="view_faculty_manager"><a href="<%=request.getContextPath()%>/view?parent=<%=AdministratorState.AdminParentViews.FACULTY_MANAGER.name()%>&node=<%=AdministratorState.AdminNodeViews.VIEW_FACULTIES.name()%>">Manage Faculties</a></li>
                    </ul>
                </li>  
                <li class="submenu" id="department_manager_main">
                    <a href="<%=request.getContextPath()%>/#"><i class="glyphicon glyphicon-th-list"></i> <span>Department Manager</span> 
                        <span class="label">2</span></a>
                    <ul>
                        <li id="create_department_manager"><a href="<%=request.getContextPath()%>/view?parent=<%=AdministratorState.AdminParentViews.DEPARTMENT_MANAGER.name()%>&node=<%=AdministratorState.AdminNodeViews.CREATE_DEPARTMENT.name()%>">Create New Department</a></li>
                        <li id="view_department_manager"><a href="<%=request.getContextPath()%>/view?parent=<%=AdministratorState.AdminParentViews.DEPARTMENT_MANAGER.name()%>&node=<%=AdministratorState.AdminNodeViews.VIEW_DEPARTMENTS.name()%>">Manage Departments</a></li>
                    </ul>
                </li>
                <li class="submenu" id="course_manager_main">
                    <a href="<%=request.getContextPath()%>/#"><i class="glyphicon glyphicon-th-list"></i> <span>Course Manager</span> 
                        <span class="label">2</span></a>
                    <ul>
                        <li id="create_course_manager"><a href="<%=request.getContextPath()%>/view?parent=<%=AdministratorState.AdminParentViews.COURSE_MANAGER.name()%>&node=<%=AdministratorState.AdminNodeViews.CREATE_COURSE.name()%>">Create New Course</a></li>
                        <li id="view_course_manager"><a href="<%=request.getContextPath()%>/view?parent=<%=AdministratorState.AdminParentViews.COURSE_MANAGER.name()%>&node=<%=AdministratorState.AdminNodeViews.VIEW_COURSES.name()%>">Manage Courses</a></li>
                    </ul>
                </li>
                <li class="submenu" id="utility_manager_main">
                    <a href="<%=request.getContextPath()%>/#"><i class="glyphicon glyphicon-th-list"></i> <span>Utility Manager</span> 
                        <span class="label">2</span></a>
                    <ul>
                        <li id="create_utility_manager"><a href="<%=request.getContextPath()%>/view?parent=<%=AdministratorState.AdminParentViews.UTILITY_MANAGER.name()%>&node=<%=AdministratorState.AdminNodeViews.CREATE_UTILITY.name()%>">Create New Utility</a></li>
                        <li id="view_utility_manager"><a href="<%=request.getContextPath()%>/view?parent=<%=AdministratorState.AdminParentViews.UTILITY_MANAGER.name()%>&node=<%=AdministratorState.AdminNodeViews.VIEW_UTILITIES.name()%>">Manage Utilities</a></li>
                    </ul>
                </li>
            </ul>

        </div>

        <jsp:include page="<%=administratorState.getAdminParentViews().getPage()%>" flush="true"/>
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
                        <div class="circular" style="background: url('/Primus/imagedisp/imagecontroller?id=${administrator.id}&who=admin') no-repeat; background-size: 120px;">
                        </div> 
                        <div class="row">
                            <div class="span6 col-sm-6" >
                                <span class="label-name"><strong>Full Name</strong></span>
                            </div>
                            <div class="span6 col-sm-6" >
                                <span class="label-value"> ${administrator.firstName} ${administrator.lastName} ${administrator.middleName} </span>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                        <div class="row">
                            <div class="span6 col-sm-6" >
                                <span class="label-name"><strong>Admin Type</strong></span>
                            </div>
                            <div class="span6 col-sm-6" >
                                <span class="label-value">${administrator.adminType}</span>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                        <div class="row">
                            <div class="span6 col-sm-6" >
                                <span class="label-name "><strong>Email</strong></span>
                            </div>
                            <div class="span6 col-sm-6" >
                                <span class="label-value">${administrator.emailAddress}</span>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                        <div class="clearfix"></div>
                        <div class="row">
                            <div class="span6 col-sm-6" >
                                <span class="label-name "><strong>Phone Number</strong></span>
                            </div>
                            <div class="span6 col-sm-6" >
                                <span class="label-value">${administrator.phoneNumber}</span>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                        <div class="clearfix"></div>
                        <div class="row">
                            <div class="span6 col-sm-6" >
                                <span class="label-name "><strong>Active</strong></span>
                            </div>
                            <div class="span6 col-sm-6" >
                                <span class="label-value">${administrator.active}</span>
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
                        <input type="hidden"  name="user" id="user" value="administrator">
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

        <script src="<%=request.getContextPath()%>/usersarea/js/excanvas.min.js"></script>
        <script src="<%=request.getContextPath()%>/usersarea/js/jquery-ui.custom.js"></script>
        <script src="<%=request.getContextPath()%>/usersarea/js/bootstrap.min.js"></script>
        <script src="<%=request.getContextPath()%>/usersarea/js/jquery.flot.min.js"></script>
        <script src="<%=request.getContextPath()%>/usersarea/js/jquery.flot.resize.min.js"></script>
        <script src="<%=request.getContextPath()%>/usersarea/js/jquery.sparkline.min.js"></script>
        <script src="<%=request.getContextPath()%>/usersarea/js/fullcalendar.min.js"></script>
        <script src="<%=request.getContextPath()%>/usersarea/js/jquery.validate.js"></script>
        <script src="<%=request.getContextPath()%>/usersarea/js/jquery.jpanelmenu.min.js"></script>
        <script src="<%=request.getContextPath()%>/usersarea/js/unicorn.form_validation.js"></script>
        <script src="<%=request.getContextPath()%>/usersarea/js/viewhandler.js"></script>
        <script src="<%=request.getContextPath()%>/usersarea/js/unicorn.js"></script>
        <!--<script src="<%=request.getContextPath()%>/usersarea/js/unicorn.dashboard.js"></script>-->
    </body>
</html>
