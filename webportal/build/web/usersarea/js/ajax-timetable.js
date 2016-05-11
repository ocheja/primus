/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function submitTimetable(formId, data, extra) {
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
function timetableNavigator(action, data, divUsed, extra) {
    var currentDis = document.getElementById('currentDisplay');
    var department = document.getElementById('department');
    var nextDis = '';
    if (currentDis !== null && department !== null) {
        currentDis = currentDis.value;
        department = department.value;
        if (currentDis === 'Monday') {
            if (action === 'nextDay')
                nextDis = 'Tuesday';
            else if (action === 'previousDay')
                nextDis = 'Sunday';

        } else if (currentDis === 'Tuesday') {
            if (action === 'nextDay')
                nextDis = 'Wednesday';
            else if (action === 'previousDay')
                nextDis = 'Monday';

        } else if (currentDis === 'Wednesday') {
            if (action === 'nextDay')
                nextDis = 'Thursday';
            else if (action === 'previousDay')
                nextDis = 'Tuesday';

        } else if (currentDis === 'Thursday') {
    console.log('fffffffffffffffffffffuccccccccccccccck');
            if (action === 'nextDay')
                nextDis = 'Friday';
            else if (action === 'previousDay')
                nextDis = 'Wednesday';

        } else if (currentDis === 'Friday') {
            if (action === 'nextDay')
                nextDis = 'Saturday';
            else if (action === 'previousDay')
                nextDis = 'Thursday';

        } else if (currentDis === 'Saturday') {
            if (action === 'nextDay')
                nextDis = 'Sunday';
            else if (action === 'previousDay')
                nextDis = 'Friday';

        } else if (currentDis === 'Sunday') {
            if (action === 'nextDay')
                nextDis = 'Monday';
            else if (action === 'previousDay')
                nextDis = 'Saturday';

        }else 
    console.log('hhhhhhhhhhhhhhhhhhh'+currentDis);
        data = data + '&nextDisplay=' + nextDis + '&department=' + department;
    }
    $.ajax
            ({
                url: extra,
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
                            console.log($(node).html());
                            if (node.id === divUsed) {
                                htmlStr = $(node).html();
                                break;
                            }
                        }
                        if (htmlStr.length > 0) {
                            $('#' + divUsed).html(htmlStr);

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