<%-- 
    Document   : courseform-session-options
    Created on : 08-July-2014, 15:36:11
    Author     : Ocheja Patrick Ileanwa
--%>

<%@page import="com.primus.data.JqueryDataTableParamModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="row" id="courseform-arena">
    <jsp:include page="/usersarea/lecturer/classmembers.jsp" flush="false"/>    
</div>
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
                <h3>Select Department</h3>
            </div>
            <div class="modal-body">
                <form class="form-horizontal"  method="get" action="/Primus/student/download" name="downloadresultsheet" id="downloadresultsheet" novalidate="novalidate">
                    <div class="form-group">
                        <label class="control-label">Name of Department</label>
                        <div class="controls">
                            <input type="hidden" required="required" id="departmentName" name="departmentName"/>

                        </div>
                    </div>
                    <input type="hidden" value="resultsheetformat" id="action" name="action"/>
                    <div class="form-actions">
                        <input type="submit" value="Submit" id="create_btn" class="btn btn-success btn-max">
                        <div id="loading_gif" style="display: none">
                            <img src="<%=request.getContextPath()%>/usersarea/img/notice_spinner.gif"/>
                            <label id="message_on_end">Loading...</label>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function() {
        var lastResults = [];
        $('select').select2();
        $("#departmentName").select2({
            placeholder: "Search for a department name",
            minimumInputLength: 3,
            multiple: false,
                ajax: {
                url: "/Primus/admin/ajax",
                dataType: 'json',
                type: "GET",
                    data: function(term, page) {
                        return {
                        q: term, // search term
                    action: "departmentNames"
                };
                },
                    results: function(data, page) {
                    lastResults = data;
                return {results: data};
                }
                },
                    createSearchChoice: function(term) {
                        var text = term + (lastResults.some(function(r) {
                    return r.text === term;
                    }) ? "" : " (new)");
            return {id: term, text: text};
        }
    });
    });
</script>
