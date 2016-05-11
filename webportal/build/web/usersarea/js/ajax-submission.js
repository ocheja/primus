/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function submitForm(formId, url, data, extra) {
    $.ajax
            ({
                url: url,
                data: $('#' + formId).serialize() + '&' + data,
                async: false,
                type: 'POST',
                dataType: 'json',
                beforeSend: function() {
                    $('#loading_gif').show();
                    $('#message_on_end').text('Loading...');
                },
                success: function(resp) {
                    $("#message_content").text("Action completed. Status --- " + resp.message);
                    if (extra !== null) {
                        $("#"+extra).click();
                    }
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

