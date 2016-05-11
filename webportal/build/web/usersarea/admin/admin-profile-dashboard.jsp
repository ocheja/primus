<%-- 
    Document   : profile-dashboard
    Created on : 12-May-2014, 17:59:27
    Author     : Ocheja Patrick Ileanwa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<div class="col-12">
    <div> 
        <div class="circular" style="background: url('/Primus/imagedisp/imagecontroller?id=${administrator.id}&who=admin') no-repeat; background-size: 120px;">
        </div> 
        <div class="profile-centered">
            <div class="row">
                <div class="span6 col-sm-6" >
                    <span class="label-name">Full Name</span>
                </div>
                <div class="span6 col-sm-6" >
                    <span class="label-value"> ${administrator.firstName} ${administrator.lastName} ${administrator.middleName} </span>
                </div>
            </div>
            <div class="row">
                <div class="span6 col-sm-6" >
                    <span class="label-name">Admin Type</span>
                </div>
                <div class="span6 col-sm-6" >
                    <span class="label-value">${administrator.adminType}</span>
                </div>
            </div>
            <div class="row">
                <div class="span6 col-sm-6" >
                    <span class="label-name">Email</span>
                </div>
                <div class="span6 col-sm-6" >
                    <span class="label-value">${administrator.emailAddress}</span>
                </div>
            </div>
        </div>
    </div>

</div>
