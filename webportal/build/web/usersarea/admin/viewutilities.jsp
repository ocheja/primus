<%-- 
    Document   : adminhomepage
    Created on : 14-May-2014, 06:03:50
    Author     : Ocheja Patrick Ileanwa
--%>
<%@page import="com.primus.appstates.AdministratorState"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<link rel="stylesheet" href="<%=request.getContextPath()%>/usersarea/css/jquery.dataTables.css" />
<script src="<%=request.getContextPath()%>/usersarea/js/unicorn.interface.js"></script>
<script src="<%=request.getContextPath()%>/usersarea/js/jquery.jeditable.js"></script>
<script src="<%=request.getContextPath()%>/usersarea/js/jquery.dataTables.editable.js"></script>
<script src="<%=request.getContextPath()%>/usersarea/js/jquery.dataTables.min.js"></script>
<input type="hidden" id="nodeView" name="nodeView" value="view">
<div class="row">
    <div class="col-12">

        <div class="widget-box">
            <div class="widget-title">
                <span class="icon">
                    <i class="glyphicon glyphicon-th"></i>
                </span>
                <h5>Sessions</h5>
            </div>
            <div class="widget-content">
                <table id="sessionData" class="display" cellspacing="0" width="100%">
                    <thead>
                        <tr>
                            <th>Serial No.</th>
                            <th>Start Year</th>
                            <th>End Year</th>
                            <th>Current Session</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>

                    </tbody>
                    <tfoot>
                        <tr>
                            <th>Serial No.</th>
                            <th>Start Year</th>
                            <th>End Year</th>
                            <th>Current Session</th>
                            <th>Action</th>
                        </tr>
                    </tfoot>
                </table>
            </div>
        </div>  		
    </div>
</div>

<div class="row">
    <div class="col-12">

        <div class="widget-box">
            <div class="widget-title">
                <span class="icon">
                    <i class="glyphicon glyphicon-th"></i>
                </span>
                <h5>Degree Requirements</h5>
            </div>
            <div class="widget-content">
                <table id="DegreeReqData" class="display" cellspacing="0" width="100%">
                    <thead>
                        <tr>
                            <th>Serial No.</th>
                            <th>Title of Degree</th>
                            <th>Department</th>
                            <th>Minimum Year(s)</th>
                            <th>Maximum Year(s)</th>
                            <th>Courses for each Level</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>

                    </tbody>
                    <tfoot>
                        <tr>
                            <th>Serial No.</th>
                            <th>Title of Degree</th>
                            <th>Department</th>
                            <th>Minimum Year(s)</th>
                            <th>Maximum Year(s)</th>
                            <th>Courses for each Level</th>
                            <th>Action</th>
                        </tr>
                    </tfoot>
                </table>
            </div>
        </div>  		
    </div>
</div>
<script src="<%=request.getContextPath()%>/usersarea/js/datatable/utilitydata.js"></script>
<a href="#DegreeCourseModal" id="DegreeCourseModalLink" data-toggle="modal" style="display:none" class="btn btn-primary"></a>
<a href="#InformerModal" id="informerModalLink" data-toggle="modal" style="display:none" class="btn btn-primary"></a>
<a href="#myAlert" id="myAlertLink" data-toggle="modal" style="display:none" class="btn btn-danger">Alert</a>
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
<div id="degreecoursesdiv">

</div>

<script>
    $(document).ready(function() {
        $('select').select2();
    });
    function prepareDegreeCourseForm(modalID, degreeID, degreeTitle, preViewBoxToClose) {
        $('#' + preViewBoxToClose).click();
        var owningDegreeSpaceElem = document.getElementsByClassName('owning_degree_space');
        var owningDegreeElem = document.getElementsByName('owningDegree');
        for (i = 0; i < owningDegreeElem.length; i++) {
            var nodey = owningDegreeElem[i];
            nodey.value = degreeID;
        }
        for (i = 0; i < owningDegreeSpaceElem.length; i++) {
            var nodex = owningDegreeSpaceElem[i];
            $(nodex).text(degreeTitle);
        }
        $("#" + modalID).click();
        $("#deleteConfirmed").click(function() {
            $.ajax({
                url: "/Primus/admin/delete",
                data: "&id=" + courseID,
                async: false,
                dataType: 'json',
                success: function(resp) {
                    $("#message_content").text("Delete action finished. Status - " + resp.message);
                    $("#informerModalLink").click();
                },
                error: function(request, status, errorThrown) {
                    $("#message_content").text("Delete action finished. Status - " + status);
                    $("#informerModalLink").click();

                }
            });
        });
        return false;
    }

    function deleteDegreeCourseRow(data, extra) {
        $.ajax
                ({
                    url: '/Primus/admin/ajax',
                    data: data,
                    async: false,
                    type: 'POST',
                    dataType: 'json',
                    beforeSend: function() {
                    },
                    success: function(resp) {
                        $("#message_content").text("Action completed. Status --- " + resp.message);
                        if (extra !== null) {
                            $("#" + extra).click();
                        }
                        $("#informerModalLink").click();
                        return false;
                    },
                    error: function(request, status, errorThrown) {
                        console.log(errorThrown);
                        var d = $(status);
                    }
                });
        return false;
    }
</script>