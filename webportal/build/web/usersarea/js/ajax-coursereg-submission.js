/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function submitFormCR(formId, data, extra) {
    var session = document.getElementById('academicSession').value;
    var semester = document.getElementById('semester').value;
    $.ajax
            ({
                url: '/Primus/student/ajax',
                data: $('#' + formId).serialize() + '&' + data+'&academicSession='+session+'&semester='+semester,
                async: false,
                type: 'POST',
                dataType: 'json',
                beforeSend: function() {
                    $('#loading_gif').show();
                    $('#myAlertHClose').click();
                    $('#message_on_end').text('Loading...');
                },
                success: function(resp) {
                    $("#message_content").text("Action completed. Status --- " + resp.message);
                    if (extra !== null) {
                        $("#"+extra).click();
                    }
                    $('#preViewClose').click();
                    $("#informerModalLink").click();
                    $('#' + formId + ' input[type=submit]').removeAttr("disabled");
                    $('#loading_gif').hide();
                    return false;
                },
                error: function(request, status, errorThrown) {
                    console.log(errorThrown);
                    $('#loading_gif').hide();
                    var d = $(status);
                }
            });
    return false;
}

function deleteCourse( data, extra) {
    $.ajax
            ({
                url: '/Primus/admin/delete',
                data: data,
                async: false,
                type: 'POST',
                dataType: 'json',
                beforeSend: function() {
                    $('#loading_gif').show();
                    $('#myAlertHClose').click();
                    $('#message_on_end').text('Loading...');
                },
                success: function(resp) {
                    $("#message_content").text("Action completed. Status --- " + resp.message);
                    if (extra !== null) {
                        $("#"+extra).click();
                    }
                    $('#preViewClose').click();
                    $("#informerModalLink").click();
                    $('#' + formId + ' input[type=submit]').removeAttr("disabled");
                    $('#loading_gif').hide();
                    return false;
                },
                error: function(request, status, errorThrown) {
                    console.log(errorThrown);
                    $('#loading_gif').hide();
                    var d = $(status);
                }
            });
    return false;
}
function doCourseReg(data, divUsed, extra) {
    $.ajax
            ({
                url: '/Primus/student/ajax',
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
                            $('#' + divUsed).html(htmlStr);
                            if (extra !== null && extra === 'addRequiredCoursesModalLink') {
                                $("#" + extra).click();
                                console.log("Hrerererereerer");
                            }

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

function confirmReg(){
    //$('#preViewClose').click();
    $('#myAlertHModalLink').click();
}