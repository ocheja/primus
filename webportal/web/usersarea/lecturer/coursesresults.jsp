<%-- 
    Document   : adminhomepage
    Created on : 14-May-2014, 06:03:50
    Author     : Ocheja Patrick Ileanwa
--%>
<%@page import="com.primus.appstates.AdministratorState"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<div class="row" id="courseform-arena">
    <div class="col-12">

        <div class="widget-box">
            <div class="widget-title">
                <span class="icon">
                    <i class="glyphicon glyphicon-th"></i>
                </span>
                <h5>Results</h5>
            </div>
            <div class="widget-content">
                <table id="results" class="display" cellspacing="0" width="100%">
                    <thead>
                        <tr>
                            <th>Course Code</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${resultSheets ne null}" >
                                <c:forEach items="${resultSheets}" var="result">
                                    <tr>
                                        <td>
                                            <c:out value="${result.course.courseCode}"></c:out>
                                            </td>
                                            <td>
                                                <a class="btn btn-success btn-mini" href="/Primus/student/download?action=hodviewresults&courseid=${result.course.id}&semester=${result.course.semester}&academiclevel=${result.academiclevel}&academicsession=${result.academicSession.id}">Download</a>
                                        </td>
                                        <td>
                                            <a class="btn btn-danger btn-mini" onclick="submitFormCF('', 'action=approveresult&resultsheetid=${result.id}', '');" href="#">Approve</a>
                                        </td>
                                    </tr>   
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                            <div class="row" style="margin-top:20px">
                                <div class="col-12">
                                    <div class="alert alert-error">
                                        <button class="close" data-dismiss="alert">×</button>
                                        <strong>No result found for the parameters supplied.</strong> 
                                    </div>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>

                    </tbody>
                    <tfoot>
                        <tr>
                            <th>Course Code</th>
                            <th>Actions</th>
                        </tr>
                    </tfoot>
                </table>
            </div>
        </div>  		
    </div>
</div>

<a href="#myAlertH" id="myAlertHModalLink" data-toggle="modal" style="display:none" class="btn btn-primary"></a>
<a href="#InformerModal" id="informerModalLink" data-toggle="modal" style="display:none" class="btn btn-primary"></a>
<div id="InformerModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button">×</button>
                <h3>Notification</h3>
            </div>
            <div class="modal-body">
                <p id="message_content">Success</p>
            </div>
        </div>
    </div>
</div>
<div id="myAlertH" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" id="myAlertHClose" class="close" type="button">×</button>
                <h3>Confirmation</h3>
            </div>
            <div class="modal-body">
                <p id="alert_content" >Submit Form?</p>
            </div>
            <div class="modal-footer">
                <a  id="regConfirmed" class="btn btn-primary btn-small" onclick="submitFormCF('requiredCoursesForm', 'action=registerCourses', 'messageAlert');" href="#">YES</a>
                <a data-dismiss="modal" class="btn btn-default btn-small" href="#">NO</a>
            </div>
        </div>
    </div>
</div>