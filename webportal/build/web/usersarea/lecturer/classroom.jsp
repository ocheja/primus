<%-- 
    Document   : courseform-instance
    Created on : 08-July-2014, 17:36:11
    Author     : Ocheja Patrick Ileanwa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/usersarea/css/icheck/flat/green.css" />
<%@page  import="com.primus.enums.Status"  %>
<!DOCTYPE html>
<div class="row" id="courseform-arena">
    <div class="col-12">
        <div class="widget-box">
            <div class="widget-title" style="height: 0px">
                <c:choose>
                    <c:when test="${courseFormInstance eq null}">
                        <button class="btn btn-success btn-mini" href="#" onclick="doCourseReg('academicSession=${academicSessionX.id}&semester=${semesterX}&action=addCourses', 'addrequiredcoursesdiv', 'addRequiredCoursesModalLink');">Add Course(s)</button> 
                        <button class="btn btn-success btn-mini" href="#" onclick="doCourseReg('academicSession=${academicSessionX.id}&semester=${semesterX}&action=addPrevCourses', 'addrequiredcoursesdiv', 'addRequiredCoursesModalLink');">Add Previous Level Course(s)</button>
                        <a class="btn btn-success btn-mini" href="/Primus/student/download?academicSession=${academicSessionX.id}&semester=${semesterX}&action=courseform" >Download Course Form</a> 
                    </c:when>
                    <c:when test="${courseFormInstance.academicSession ne null and courseFormInstance.academicSession.courseRegistrationClosed}">
                        <button class="btn btn-success btn-mini" disabled href="#" onclick="">Add Course(s)</button> 
                        <button class="btn btn-success btn-mini" disabled href="#" onclick="">Add Previous Level Course(s)</button>
                        <a class="btn btn-success btn-mini" href="/Primus/student/download?academicSession=${courseFormInstance.academicSession.id}&semester=${courseFormInstance.semester}&action=courseform" >Download Course Form</a>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-success btn-mini" href="#" onclick="doCourseReg('academicSession=${courseFormInstance.academicSession.id}&semester=${courseFormInstance.semester}&action=addCourses', 'addrequiredcoursesdiv', 'addRequiredCoursesModalLink');">Add Course(s)</button> 
                        <button class="btn btn-success btn-mini" href="#" onclick="doCourseReg('academicSession=${courseFormInstance.academicSession.id}&semester=${courseFormInstance.semester}&action=addPrevCourses', 'addrequiredcoursesdiv', 'addRequiredCoursesModalLink');">Add Previous Level Course(s)</button>
                        <a class="btn btn-success btn-mini" href="/Primus/student/download?academicSession=${courseFormInstance.academicSession.id}&semester=${courseFormInstance.semester}&action=courseform" >Download Course Form</a>
                    </c:otherwise>
                </c:choose>
                <div style="margin: 0 40% 0 40%; text-align: center;">
                    <c:choose>
                        <c:when test="${courseFormInstance eq null}">
                            <h5 >
                                UNIVERSITY OF NIGERIA
                                <p>OFFICE OF THE REGISTRAR
                                    COURSE REGISTRATION FORM
                                    SESSION:<c:out value="${academicSessionX.startYear} - ${academicSessionX.endYear}"></c:out>
                                </h5>
                                <input id="academicSession" name="academicSession" value="${academicSessionX.id}" hidden="hidden"/>
                            <input id="semester" name="semester" value="${semesterX}" hidden="hidden"/>

                        </c:when>
                        <c:otherwise>
                            <h5 >
                                UNIVERSITY OF NIGERIA
                                <p>OFFICE OF THE REGISTRAR
                                    COURSE REGISTRATION FORM
                                    SESSION:${courseFormInstance.academicSession.startYear} - ${courseFormInstance.academicSession.endYear}
                            </h5>
                            <input id="academicSession" name="academicSession" value="${courseFormInstance.academicSession.id}" hidden="hidden"/>
                            <input id="semester" name="semester" value="${courseFormInstance.semester}" hidden="hidden"/>
                        </c:otherwise>
                    </c:choose> 
                </div>
            </div>
            <div class="widget-content nopadding">
                <table class="table table-bordered table-striped table-hover">
                    <thead>
                        <tr>
                            <th>S/N</th>
                            <th>Session</th>
                            <th>Course Code</th>
                            <th>Course Title</th>
                            <th>Semester</th>
                            <th>Units</th>
                            <th>Submitted</th>
                            <th>Approved</th>
                            <th colspan="3">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${courseFormInstance.courses}" var="course" >
                            <tr>
                                <td></td>
                                <td> <c:out value="${courseFormInstance.academicSession.startYear} - ${courseFormInstance.academicSession.endYear}"/> </td>
                                <td> <c:out value="${course.courseCode}"/> </td>
                                <td><c:out value="${course.courseTitle}"/></td>
                                <td><c:out value="${course.semester}"/></td>
                                <td><c:out value="${course.courseUnitLoad}"/></td>
                                <c:choose> 
                                    <c:when test="${courseFormInstance.status == 'SUBMITTED'} ">
                                        <td><c:out value="NO"/></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td><c:out value="YES"/></td>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose> 
                                    <c:when test="${courseFormInstance.status == 'APPROVED'} ">
                                        <td><c:out value="YES"/></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td><c:out value="NO"/></td>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose> 
                                    <c:when test="${courseFormInstance.status == 'APPROVED'  or courseFormInstance.status eq  'IN_PROGRESS'} ">
                                        <td><a class="btn-danger disabled" href="#" >REMOVE</a></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td><a class="btn-danger" onclick="deleteCourse('actionDelete=studentCourse&id=${courseFormInstance.id}&courseid=${course.id}');" href="#" >REMOVE</a></td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr>
                            <th>S/N</th>
                            <th>Session</th>
                            <th>Course Code</th>
                            <th>Course Title</th>
                            <th>Semester</th>
                            <th>Units</th>
                            <th>Submitted</th>
                            <th>Approved</th>
                            <th colspan="3">Action</th>
                        </tr>
                    </tfoot>
                </table>							
            </div>
        </div>
    </div>
</div>

<script src="<%=request.getContextPath()%>/usersarea/js/jquery.icheck.min.js"></script>
<script src="<%=request.getContextPath()%>/usersarea/js/jquery.validate.js"></script>
<script src="<%=request.getContextPath()%>/usersarea/js/select2.min.js"></script>
<script src="<%=request.getContextPath()%>/usersarea/js/jquery.dataTables.min.js"></script>
<script src="<%=request.getContextPath()%>/usersarea/js/unicorn.tables.js"></script>