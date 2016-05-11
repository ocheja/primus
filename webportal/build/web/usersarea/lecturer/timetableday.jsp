<%-- 
    Document   : register-course
    Created on : 22-August-2014, 12:08:27
    Author     : Ocheja Patrick Ileanwa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/usersarea/css/icheck/flat/green.css" />
<!DOCTYPE html>

<div class="widget-content nopadding" id="timetableevents">
    <input type="hidden" id="currentDisplay" name="currentDisplay" value="${today}"/>
    <input type="hidden" id="department" name="department" value="${department}"/>
    <table class="fc-header" style="width:100%">
        <tbody>
            <tr>
                <td class="fc-header-left">
                    <span class="fc-button fc-button-prev fc-state-default fc-corner-left" unselectable="on">
                        <a href="#" onclick="timetableNavigator('previousDay', 'action=timetableevents', 'timetableevents', '/Primus/lecturer/ajax');" ><span class="fc-text-arrow">&lsaquo;</span></a>

                    </span>
                    <span class="fc-button fc-button-next fc-state-default fc-corner-right" unselectable="on">
                        <a href="#" onclick="timetableNavigator('nextDay', 'action=timetableevents', 'timetableevents', '/Primus/lecturer/ajax');" ><span class="fc-text-arrow">&rsaquo;</span></a>

                    </span>
                </td>
                <td class="fc-header-center">
                    <span class="fc-header-title">
                        <h2> <c:out value="${today}"></c:out> </h2>
                        </span>
                    </td>
                </tr>
            </tbody>
        </table>
        <div id="timetableevents" class="fc-content" style="position: relative;">
            <div class="fc-view fc-view-month fc-grid" style="position:relative" unselectable="on">
                <div class="fc-event-container" style="position:absolute;z-index:8;top:0;left:0">
                </div>
                <ul class="stat-boxes">
                <c:forEach items="${timetableLectures}" var="entry">
                    <c:choose>
                        <c:when test="${entry.shldStarted}">
                            <li class="popover-visits">
                                <div class="left sparkline_bar_bad"><strong> <c:out value="${entry.startEndTime}"></c:out> </strong>
                                    <span style="font-size: 12px;"><c:out value="${entry.venue}"></c:out></span>
                                </div>
                                <div class="right">
                                    <strong><c:out value="${entry.courseCode}"></c:out></strong>
                                    Lecturer Absent
                                </div>
                            </li>
                        </c:when>
                        <c:when test="${entry.started}">
                            <li class="popover-visits">
                                <div class="left sparkline_bar_good"><strong> <c:out value="${entry.startEndTime}"></c:out> </strong>
                                    <span style="font-size: 12px;"><c:out value="${entry.venue}"></c:out></span>
                                </div>
                                <div class="right">
                                    <strong><c:out value="${entry.courseCode}"></c:out></strong>
                                    Started at <c:out value="${entry.startedAt}"></c:out>
                                </div>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="popover-visits">
                                <div class="left sparkline_bar_neutral"><strong> <c:out value="${entry.startEndTime}"></c:out> </strong>
                                    <span style="font-size: 12px;"><c:out value="${entry.venue}"></c:out></span>
                                </div>
                                <div class="right">
                                    <strong><c:out value="${entry.courseCode}"></c:out></strong>
                                    Not Started 
                                </div>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>