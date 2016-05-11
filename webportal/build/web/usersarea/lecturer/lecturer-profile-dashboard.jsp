<%-- 
    Document   : profile-dashboard
    Created on : 12-May-2014, 17:59:27
    Author     : Ocheja Patrick Ileanwa
--%>

<%@page import="com.primus.appstates.LecturerState"%>
<%@page import="com.primus.data.Lecturer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<div class="col-12">
    <div> 
        <div class="circular" style="background: url('/Primus/imagedisp/imagecontroller?id=${lecHere.id}&who=lecturer') no-repeat; background-size: 120px;">
        </div> 
        <div class="profile-centered">
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
