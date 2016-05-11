<%-- 
    Document   : adminhomepage
    Created on : 14-May-2014, 06:03:50
    Author     : Ocheja Patrick Ileanwa
--%>
<%@page import="com.primus.appstates.AdministratorState"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<input type="hidden" id="nodeView" name="nodeView" value="view">
<div class="row" id="courseform-arena">
    <div class="col-12">

        <div class="widget-box">
            <div class="widget-title">
                <span class="icon">
                    <i class="glyphicon glyphicon-th"></i>
                </span>
                <h5>Lecturers</h5>
            </div>
            <div class="widget-content">
                <table id="courseFormData" class="display" cellspacing="0" width="100%">
                    <thead>
                        <tr>
                            <th>Serial No.</th>
                            <th>Full Name</th>
                            <th>Reg Number</th>
                            <th>Level</th>
                            <th>Academic Session</th>
                            <th>Semester</th>
                            <th>Passport</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>

                    </tbody>
                    <tfoot>
                        <tr>
                            <th>Serial No.</th>
                            <th>Full Name</th>
                            <th>Reg Number</th>
                            <th>Level</th>
                            <th>Academic Session</th>
                            <th>Semester</th>
                            <th>Passport</th>
                            <th>Action</th>
                        </tr>
                    </tfoot>
                </table>
            </div>
        </div>  		
    </div>
</div>
<script src="<%=request.getContextPath()%>/usersarea/js/datatable/courseformdata.js"></script>
