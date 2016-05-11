/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function submitFormCF(formId, data, extra) {
    $.ajax
            ({
                url: '/Primus/lecturer/ajax',
                data: $('#' + formId).serialize() + '&' + data,
                async: false,
                type: 'GET',
                dataType: 'json',
                beforeSend: function() {
                    $('#loading_gif').show();
                    $('#myAlertHClose').click();
                    $('#message_on_end').text('Loading...');
                },
                success: function(resp) {
                    $("#message_content").text("Action completed. Status --- " + resp.message);
                    if (extra !== null) {
                        $("#" + extra).click();
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
function doCourseForms(formId, data, divUsed, extra) {
    $.ajax
            ({
                url: '/Primus/lecturer/ajax',
                data: $('#' + formId).serialize() + "&" + data,
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
                            if (formId === 'approvecourses')
                                $.getScript('/Primus/usersarea/js/datatable/courseformdata.js');
                            else if (formId === 'viewstudents')
                                $.getScript('/Primus/usersarea/js/datatable/viewstudentcoursesdata.js');
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

function confirmReg() {
    //$('#preViewClose').click();
    $('#myAlertHModalLink').click();
}