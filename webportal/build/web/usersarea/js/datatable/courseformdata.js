$(document).ready(function() {

    var oTable = $('#courseFormData').dataTable({
        "bProcessing": true,
        "bServerSide": true,
        "bJQueryUI": true,
        "sAjaxSource": "/Primus/lecturer/ajax",
        "fnServerParams": function(aoData) {
            aoData.push({"name": "action", "value": "courseFormData"});
        },
        "aoColumns":
                [
                    {"mDataProp": null},
                    {"mDataProp": "studentName"},
                    {"mDataProp": "studentRegNum"},
                    {"mDataProp": "academicLevel"},
                    {"mDataProp": "academicSession"},
                    {"mDataProp": "semester"},
                    {"mDataProp": null},
                    {"mDataProp": null,
                        "bSearchable": false,
                        "bSortable": false,
                        "fnRender": function(oObj) {
                            return '<a class="btn btn-success btn-mini" href="#" onclick="doLecturerModal(\'id=' + oObj.aData.id + '&action=pullstudentcourses\',\'studentcoursesdiv\',\'studentCoursesModalLink\');">View</a>';
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
                        "sTitle": "Passport",
                        "sWidth": "1%",
                        "sClass": "center",
                        "bSortable": false,
                        "aTargets": [5],
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
    });
});
function imageResolver(sData) {
    return '<img src="/Primus/imagedisp/imagecontroller?id=' +
            sData + '&who=student" width="60px" height="60px" alt="profile picture" title="Profile picture"/>';
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
                                console.log('resp div equal ooo');
                                htmlStr = $(node).html();
                                break;
                            }
                        }
                        if (htmlStr.length > 0) {
                            $('#' + divUsed).html(htmlStr);
                            console.log('resp div equal ooo clicked!');
                            $("#" + modalBtn).click();
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