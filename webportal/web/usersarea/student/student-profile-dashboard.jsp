<%-- 
    Document   : profile-dashboard
    Created on : 12-May-2014, 17:59:27
    Author     : Ocheja Patrick Ileanwa
--%>

<%@page import="com.primus.appstates.StudentState"%>
<%@page import="com.primus.data.Student"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="col-12">
    <div> 
        <div class="circular" style="background: url('/Primus/imagedisp/imagecontroller?id=${studHere.id}&who=student') no-repeat; background-size: 120px;">
        </div> 
        <div class="profile-centered">
            <div class="row">
                <div class="span6 col-sm-6" >
                    <span class="label-name">Full Name</span>
                </div>
                <div class="span6 col-sm-6" >
                    <span class="label-value"><c:out value="${studHere.studentName.surname} ${studHere.studentName.firstName} ${studHere.studentName.middleName}"></c:out> </span>
                </div>
            </div>
            <div class="row">
                <div class="span6 col-sm-6" >
                    <span class="label-name">Faculty</span>
                </div>
                <div class="span6 col-sm-6" >
                    <span class="label-value"><c:out value="${studHere.department.faculty.facultyName.name}"></c:out></span>
                </div>
            </div>
            <div class="row">
                <div class="span6 col-sm-6" >
                    <span class="label-name">Matriculation Number</span>
                </div>
                <div class="span6 col-sm-6" >
                    <span class="label-value"><c:out value="${studHere.regNumber}"></c:out></span>
                </div>
            </div>
            <div class="row">
                <div class="span6 col-sm-6" >
                    <span class="label-name">Level</span>
                </div>
                <div class="span6 col-sm-6" >
                    <span class="label-value"><c:out value="${studHere.currentAcademicLevelStr}"></c:out></span>
                </div>
            </div>
        </div>
    </div>
    
</div>
