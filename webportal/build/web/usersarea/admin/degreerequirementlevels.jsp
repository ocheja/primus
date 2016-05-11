<%-- 
    Document   : gradeschemefields
    Created on : 28-May-2014, 20:11:58
    Author     : Ocheja Patrick Ileanwa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<div id="levelfields">

    <%
        int count = (Integer) request.getAttribute("numberOfLevels");
        for (int i = 1; i <= count; i++) {
            request.setAttribute("i", i);
    %>

    <div class="row">
        <div class="col-12 col-sm-4">
            <label class="control-label"><%=i%>). Level</label>
            <div class="controls">
                <input type="text" value="${levels[i-1]}" class="required form-control input-box" readonly name="level<%=i%>" id="level<%=i%>">
            </div>
        </div> 
        <div class="col-12 col-sm-8">
            <label class="control-label">Courses</label>
            <div class="controls">
                <select multiple id="coursesforlevel<%=i%>" name="coursesforlevel<%=i%>" class="coursesforlevel required">
                    <c:forEach items="${courses}"  var="course">
                        <option value="${course.id}" > <c:out value="${course.courseTitle} (${course.courseCode})"/> </option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-12 col-sm-4">
            <label class="control-label">Number of Electives</label>
            <div class="controls">
                <input type="text" value="0" class="required digits form-control input-box" name="electivesforlevel<%=i%>" id="electivesforlevel<%=i%>">
            </div>
        </div>
    </div>
    <%        }
    %>
    <input type="hidden" name="numberoflevels" id="numberoflevels" value="<%=count%>"/>
</div>