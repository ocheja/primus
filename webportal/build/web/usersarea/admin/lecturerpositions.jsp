<%-- 
    Document   : prerequisitecourses
    Created on : 28-May-2014, 20:11:58
    Author     : Ocheja Patrick Ileanwa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<div id="lecturerpositionsdiv" style="display: none;">

    <div id="LecturerPositionModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button data-dismiss="modal" id="preViewPositionsClose" class="close" type="button">×</button>
                    <h3> Current Positions Held By <c:out value="${lecturer.lecturerName.firstName} 
                           ${lecturer.lecturerName.middleName} ${lecturer.lecturerName.surname}"></c:out> </h3>
                    </div>
                    <div class="modal-body">
                        <a class='btn btn-success btn-mini' 
                           onclick="prepareLecturerCourseForm('addLecturerPositionModalLink', '${lecturer.id}', '${lecturer.lecturerName.firstName} ${lecturer.lecturerName.middleName} ${lecturer.lecturerName.surname}','preViewPositionsClose');"
                        href='#'>
                        Add More
                    </a>
                    <table class="table table-bordered table-striped table-hover">
                        <tr>
                            <th>Position</th>
                            <th>Department</th>
                            <th>Level</th>
                            <th>Action</th>
                        </tr>
                        <c:forEach items="${lecturer.lecturerType.positions}" var="position">
                            <tr>
                                <td>
                                    <c:out value="${position}">

                                    </c:out> 
                                </td>
                                <td>
                                    <c:out value="${lecturer.department.departmentName.name}">

                                    </c:out> 
                                </td>
                                <td>
                                    <c:if test="${position eq 'ACADEMIC_ADVISER'}">
                                        <c:out value="${lecturer.lecturerType.assignedStudentLevel}">

                                    </c:out>
                                    </c:if> 
                                </td>
                                <td>
                                    <a class='btn btn-danger btn-mini' 
                                       onclick="deleteLecturerCourseRow('lecturerid=${lecturer.id}&ownedid=${position}&action=lecturer_position_delete', 'preViewPositionsClose');" href='#'>
                                        Delete
                                    </a>
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