$(document).ready(function() {

    var oTable = $('#sessionData').dataTable({
        "bProcessing": true,
        "bServerSide": true,
        "bJQueryUI": true,
        "sAjaxSource": "/Primus/admin",
        "fnServerParams": function(aoData) {
            aoData.push({"name": "action", "value": "sessionData"});
        },
        "aoColumns":
                [
                    {"mDataProp": null},
                    {"mDataProp": "startYear"},
                    {"mDataProp": "endYear"},
                    {"mDataProp": "currentSession"},
                    {"mDataProp": null,
                        "bSearchable": false,
                        "bSortable": false,
                        "fnRender": function(oObj) {
                            return "<a class='table-action-deletelink btn btn-danger btn-mini' href='/Primus/admin/delete?id=" + oObj.aData.id + "&actionDelete=student_delete'>Delete</a>";
                        }
                    }
                ],
        "fnDrawCallback": function(oSettings) {
            /* Need to redo the counters if filtered or sorted */
            if (oSettings.bSorted || oSettings.bFiltered || oSettings.bDrawing)
            {
                for (var i = 0, iLen = oSettings.aiDisplay.length; i < iLen; i++)
                {
                    $('td:eq(0)', oSettings.aoData[ oSettings.aiDisplay[i] ].nTr).html(i + 1);
                }
            }
        },
        "aoColumnDefs":
                [
                    {
                        "bSortable": true,
                        "aTargets": [1],
                        "orderData": [1, 0]
                    },
                    {"bSortable": true,
                        "aTargets": [2],
                        "orderData": [2, 0]
                    }
                ],
        "aaSorting": [[1, 'asc']],
        "fnCreatedRow": function(row, data, index) {
            $(row).attr("id", data.id);
        }
    }).makeEditable({
        "sDeleteHttpMethod": "POST",
        "sDeleteURL": "/Primus/admin/delete",
//            "sDeleteRowButtonId": "deleteAdminBtn",
        "sUpdateURL": "/Primus/admin/update",
        "sUpdateType": "POST",
        oUpdateParameters: {
            "actionPerformed": "sessionDataEdit"
        },
        "oDeleteParameters": {
            "actionDelete": "session_delete"
        },
        "aoColumns": [
            null,
            null,
            null,
            null,
            null
        ],
        "fnOnDeleting": function(tr, id, fnDeleteRow) {
            $("#alert_content").text('Please confirm that you want to delete row? Can not be undone');
            $("#myAlertLink").click();
            $("#deleteConfirmed").click(function() {
                fnDeleteRow(id);
            });
            return false;
        },
        "sSuccessResponse": "Successful",
        "fnOnDeleted": function(status)
        {
            $("#message_content").text("Deleted action finished. Status - " + status);
            $("#informerModalLink").click();
        }
    });
    
    //Degree requiremnt Datatable codes
    var oTable1 = $('#DegreeReqData').dataTable({
        "bProcessing": true,
        "bServerSide": true,
        "bJQueryUI": true,
        "sAjaxSource": "/Primus/admin",
        "fnServerParams": function(aoData) {
            aoData.push({"name": "action", "value": "DegreeReqData"});
        },
        "aoColumns":
                [
                    {"mDataProp": null},
                    {"mDataProp": "titleOfDegree"},
                    {"mDataProp": "departmentName.name"},
                    {"mDataProp": "minAllowableYears"},
                    {"mDataProp": "maxAllowableYears"},
                    {"mDataProp": null,
                        "bSearchable": false,
                        "bSortable": false,
                        "fnRender": function(oObj) {
                            return '<a class="btn btn-success btn-mini" href="#" onclick="doCoursesForDegreeModal(\'id=' + oObj.aData.id + '&action=pullcoursesfordegreereq\',\'degreecoursesdiv\',\'DegreeCourseModalLink\');">View</a>';
                        }
                    },
                    {"mDataProp": null,
                        "bSearchable": false,
                        "bSortable": false,
                        "fnRender": function(oObj) {
                            return "<a class='table-action-deletelink btn btn-danger btn-mini' href='/Primus/admin/delete?id=" + oObj.aData.id + "&actionDelete=student_delete'>Delete</a>";
                        }
                    }
                ],
        "fnDrawCallback": function(oSettings) {
            /* Need to redo the counters if filtered or sorted */
            if (oSettings.bSorted || oSettings.bFiltered || oSettings.bDrawing)
            {
                for (var i = 0, iLen = oSettings.aiDisplay.length; i < iLen; i++)
                {
                    $('td:eq(0)', oSettings.aoData[ oSettings.aiDisplay[i] ].nTr).html(i + 1);
                }
            }
        },
        "aoColumnDefs":
                [
                    {
                        "bSortable": true,
                        "aTargets": [1],
                        "orderData": [1, 0]
                    },
                    {"bSortable": true,
                        "aTargets": [2],
                        "orderData": [2, 0]
                    }
                ],
        "aaSorting": [[1, 'asc']],
        "fnCreatedRow": function(row, data, index) {
            $(row).attr("id", data.id);
        }
    }).makeEditable({
        "sDeleteHttpMethod": "POST",
        "sDeleteURL": "/Primus/admin/delete",
//            "sDeleteRowButtonId": "deleteAdminBtn",
        "sUpdateURL": "/Primus/admin/update",
        "sUpdateType": "POST",
        oUpdateParameters: {
            "actionPerformed": "studentDataEdit"
        },
        "oDeleteParameters": {
            "actionDelete": "student_delete"
        },
        "aoColumns": [
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        ],
        "fnOnDeleting": function(tr, id, fnDeleteRow) {
            $("#alert_content").text('Please confirm that you want to delete row? Can not be undone');
            $("#myAlertLink").click();
            $("#deleteConfirmed").click(function() {
                fnDeleteRow(id);
            });
            return false;
        },
        "sSuccessResponse": "Successful",
        "fnOnDeleted": function(status)
        {
            $("#message_content").text("Deleted action finished. Status - " + status);
            $("#informerModalLink").click();
        }
    });
});
function imageResolver(sData) {
    return '<img src="/Primus/imagedisp/imagecontroller?id=' +
            sData + '&who=student" width="60px" height="60px" alt="profile picture" title="Profile picture"/>';
}
function actionLinkResolver(sData) {
    return  '<a href="/Primus/admin/delete?id="' + sData + '" class="table-action-deletelink">Delete</a>';
//        '<img src="data:image/png;base64,' +
//                sData + '" width="60px" height="60px" alt="profile picture" title="Profile picture"/>';
//        return '<img src="/Primus/imagedisp?sz=60&imageByteString=' + encodeURIComponent(sData) + '"  
//        width="60px" height="60px" alt="profile picture" title="Profile picture"/>';
}
function deleteAdmin(id) {
    $("#alert_content").text('Please confirm that you want to delete this record');
    $("#myAlertLink").click();
    $("#deleteConfirmed").click(function() {
        $.ajax({
            url: "/Primus/admin/delete",
            data: "&id=" + id,
            async: false,
            dataType: 'json',
            success: function(resp) {
                $("#message_content").text("Delete action finished. Status - " + resp.message);
                $("#informerModalLink").click();
                drawTable();
            },
            error: function(request, status, errorThrown) {
                $("#message_content").text("Delete action finished. Status - " + status);
                $("#informerModalLink").click();

            }
        });
    });
    return false;
}

function doCoursesForDegreeModal(data, divUsed, modalBtn) {
    $.ajax
            ({
                url: '/Primus/admin/pull',
                data: data,
                async: false,
                type: 'GET',
                success: function(resp, status, xhr) {
                    var ct = xhr.getResponseHeader("content-type") || "";
                    if (ct.indexOf('html') > -1) {
                        var d = $(resp);
                        var htmlStr = "";
                        for (i = 0; i < d.length; i++) {
                            var node = d[i];
                            if (node.id === divUsed) {
                                htmlStr = $(node).html();
                                break;
                            }
                        }
                        if (htmlStr.length > 0) {
                            $('#'+divUsed).html(htmlStr);
                            $("#"+modalBtn).click();
                        } else {

                        }
                    }
                    if (ct.indexOf('json') > -1) {
                        console.log(resp);
                    }
                    return false;
                },
                error: function(request, status, errorThrown) {
                    console.log(errorThrown);
                    var d = $(status);
                }
            });
    return false;
}