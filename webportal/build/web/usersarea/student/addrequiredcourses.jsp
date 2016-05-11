<%-- 
    Document   : prerequisitecourses
    Created on : 28-May-2014, 20:11:58
    Author     : Ocheja Patrick Ileanwa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<div id="addrequiredcoursesdiv"  >

    <div id="addRequiredCoursesModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button data-dismiss="modal" id="preViewClose" class="close" type="button">×</button>
                    <h3> YOU MAY SELECT COURSES YOU WISH TO OFFER </h3>
                </div>
                <div class="modal-body">
                    <a class='btn btn-success btn-mini' 
                       onclick="confirmReg();"
                       href='#'>
                        Submit
                    </a>
                    <form id="requiredCoursesForm" name="requiredCoursesForm" action="">
                        <table class="table table-bordered table-striped table-hover">
                            <tr>
                                <th>Course Title</th>
                                <th>Course Code</th>
                                <th>Unit Load</th>
                                <th>About</th>
                                <th><span class="icon">Add
                                        <input type="checkbox" id="title-checkbox" name="title-checkbox" />
                                    </span></th>
                            </tr>
                            <c:forEach items="${requiredCourses}" var="course">
                                <tr>
                                    <td>
                                        <c:out value="${course.courseTitle}">

                                        </c:out> 
                                    </td>
                                    <td>
                                        <c:out value="${course.courseCode}">

                                        </c:out> 
                                    </td>
                                    <td>
                                        <c:out value="${course.courseUnitLoad}">

                                        </c:out> 
                                    </td>
                                    <td>
                                        <c:out value="${course.description}">

                                        </c:out> 
                                    </td>
                                    <td>
                                        <!--                                    <a class='btn btn-danger btn-mini' 
                                                                               onclick="deleteLecturerCourseRow('lecturerid=${lecturer.id}&ownedid=${course.id}&action=lecturer_course_delete', 'preViewClose');" href='#'>
                                                                                Delete
                                                                            </a>-->
                                        <input type="checkbox" id="${course.id}" name="requiredCoursesSelected" value="${course.id}" />
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
