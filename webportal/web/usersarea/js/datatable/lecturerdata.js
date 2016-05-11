$(document).ready(function() {

    var oTable = $('#lecturerData').dataTable({
        "bProcessing": true,
        "bServerSide": true,
        "bJQueryUI": true,
        "sAjaxSource": "/Primus/admin",
        "fnServerParams": function(aoData) {
            aoData.push({"name": "action", "value": "lecturerData"});
        },
        "aoColumns":
                [
                    {"mDataProp": null},
                    {"mDataProp": null,
                        "fnRender": function(oObj) {
                            var titles = oObj.aData.title;
                            var titleresp = "<strong> ";
                            for (i = 0; i < titles.length; i++) {
                                titleresp += titles[i] + " ";
                            }
                            titleresp += "</strong>";
                            return titleresp;
                        }
                    },
                    {"mDataProp": "lecturerName.firstName"},
                    {"mDataProp": "lecturerName.middleName"},
                    {"mDataProp": "lecturerName.surname"},
                    {"mDataProp": "email"},
                    {"mDataProp": "departmentName.name"},
                    {"mDataProp": null,
                        "bSearchable": false,
                        "bSortable": false,
                        "fnRender": function(oObj) {
                            return '<a class="btn btn-success btn-mini" href="#" onclick="doLecturerModal(\'id=' + oObj.aData.id + '&action=pulllecturercourses\',\'lecturercoursesdiv\',\'LecturerCourseModalLink\');">View</a>';
                        }
                    },
                    {"mDataProp": null,
                        "bSearchable": false,
                        "bSortable": false,
                        "fnRender": function(oObj) {
                            return '<a class="btn btn-success btn-mini" href="#" onclick="doLecturerModal(\'id=' + oObj.aData.id + '&action=pulllecturerpositions\',\'lecturerpositionsdiv\',\'LecturerPositionModalLink\');">View</a>';
                        }
                    },
                    {"mDataProp": null
                    },
                    {"mDataProp": null,
                        "bSearchable": false,
                        "bSortable": false,
                        "fnRender": function(oObj) {
                            return "<a class='table-action-deletelink btn btn-danger btn-mini' href='/Primus/admin/delete?id=" + oObj.aData.id + "&actionDelete=lecturer_delete'>Delete</a>";
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
                    {
                        "sTitle": "Picture",
                        "sWidth": "1%",
                        "sClass": "center",
                        "bSortable": false,
                        "aTargets": [9],
                        "fnRender": function(obj) {
                            var sReturn = obj.aData.id;
                            sReturn = imageResolver(sReturn);
                            return sReturn;
                        }
                        , "bUseRendered": false
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
            "actionPerformed": "lecturerDataEdit"
        },
        "oDeleteParameters": {
            "actionDelete": "lecturer_delete"
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
//        ,
//        fnOnEditing: function(input) {
//            $("#alert_content").text('Please confirm that you want to Update to this: ' + input.val());
//            $("#myAlertLink").click();
//            $("#deleteConfirmed").click(function() {
//                return true;
//            });
//        }
    });
//        $('#adminData tbody').on('click', 'tr', function() {
//            if ($(this).hasClass('selected')) {
//                $(this).removeClass('selected');
//            }
//            else {
//                oTable.$('tr.selected').removeClass('selected');
//                $(this).addClass('selected');
//            }
//        });
//
//        $('#button').click(function() {
//            oTable.row('.selected').remove().draw(false);
//        });
});
function imageResolver(sData) {
    return '<img src="/Primus/imagedisp/imagecontroller?id=' +
            sData + '&who=lecturer" width="60px" height="60px" alt="profile picture" title="Profile picture"/>';
//        return '<img src="/Primus/imagedisp?sz=60&imageByteString=' + encodeURIComponent(sData) + '"  
//        width="60px" height="60px" alt="profile picture" title="Profile picture"/>';
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

function doLecturerModal(data, divUsed, modalBtn) {
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