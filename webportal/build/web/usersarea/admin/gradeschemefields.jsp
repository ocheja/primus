<%-- 
    Document   : gradeschemefields
    Created on : 28-May-2014, 20:11:58
    Author     : Ocheja Patrick Ileanwa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<div id="gradeschemefields">
    
    <%
        int count = (Integer)request.getAttribute("numberofletters");
        for (int i = 1; i <= count; i++) {
    %>

    <div class="row">
        <div class="col-12 col-sm-6">
            <label class="control-label"><%=i%>). Grade Letter</label>
            <div class="controls">
                <input type="text" class=" required form-control input-box" name="gradeletter<%=i%>" id="gradeletter<%=i%>">
            </div>
        </div>
        <div class="col-12 col-sm-6">
            <label class="control-label">Min. Mark</label>
            <div class="controls">
                <input type="number" class=" required form-control input-box" name="minmark<%=i%>" id="minmark<%=i%>">
            </div>
        </div>
        <div class="col-12 col-sm-6">
            <label class="control-label">Grade Point</label>
            <div class="controls">
                <input type="number" class=" required form-control" name="gradepoint<%=i%>" id="gradepoint<%=i%>">
            </div>
        </div>
        <div class="col-12 col-sm-6">
            <label class="control-label">Description</label>
            <div class="controls">
                <input type="text" class=" required form-control" name="gradeletterdescription<%=i%>" id="gradeletterdescription<%=i%>">
            </div>
        </div>
    </div>
    <%        }
    %>
    <input type="hidden" name="numberofgradeletters" id="numberofgradeletters" value="<%=count%>"/>
</div>
