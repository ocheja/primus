<%-- 
    Document   : prerequisitecourses
    Created on : 28-May-2014, 20:11:58
    Author     : Ocheja Patrick Ileanwa
--%>

<%@page import="com.primus.appstates.LecturerState"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<div id="studentcoursesdiv" style="display: none;">
    <div id="studentCoursesModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button data-dismiss="modal" id="preViewPositionsClose" class="close" type="button">×</button>
                    <h5 >
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;UNIVERSITY OF NIGERIA
                        <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;COURSE REGISTRATION FORM
                        <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;SESSION:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${courseForm.academicSession.startYear} - ${courseForm.academicSession.endYear}"></c:out>
                        </h5>
                    </div>
                    <div class="modal-body">
                        <a class='btn btn-success btn-mini' 
                           onclick="validateCourseForm('action=approveCourseForm&courseFormId=${courseForm.id}','/Primus/lecturer/ajax','preViewPositionsClose','myAlertLink');"
                        href='#'>
                        Approve
                    </a>
                        <a class='btn btn-danger btn-mini' 
                           onclick="validateCourseForm('action=disApproveCourseForm&courseFormId=${courseForm.id}','/Primus/lecturer/ajax','preViewPositionsClose','myAlertLink');"
                        href='#'>
                       Reject
                    </a>
                        <table class="table table-bordered table-striped table-hover">
                            <tr>
                                <th>Course Code</th>
                                <th>Course Title</th>
                                <th>Unit</th>
                            </tr>
                        <c:forEach items="${courseForm.courses}" var="course">
                            <tr>
                                <td>
                                    <c:out value="${course.courseCode}">

                                    </c:out> 
                                </td>
                                <td>
                                    <c:out value="${course.courseTitle}">

                                    </c:out> 
                                </td>
                                <td>
                                    <c:out value="${course.courseUnitLoad}">

                                    </c:out>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="myAlert" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button">×</button>
                <h3>Confirmation</h3>
            </div>
            <div class="modal-body">
                <p id="alert_content" >Confirm Delete</p>
            </div>
            <div class="modal-footer">
                <a data-dismiss="modal" id="deleteConfirmed" class="btn btn-primary btn-small" href="#">Confirm</a>
                <a data-dismiss="modal" class="btn btn-default btn-small" href="#">Cancel</a>
            </div>
        </div>
    </div>
</div>