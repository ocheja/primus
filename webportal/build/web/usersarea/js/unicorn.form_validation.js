/**
 * Unicorn Admin Template
 * Version 2.0.1
 * Diablo9983 -> diablo9983@gmail.com
 **/

$(document).ready(function() {

//    $('input[type=checkbox],input[type=radio]').iCheck({
//        checkboxClass: 'icheckbox_flat-blue',
//        radioClass: 'iradio_flat-blue'
//    });
//
//    $('select').select2();
    // Form Validation
    $("#create_degree_requirement_form").validate({
        rules: {
            required: {
                required: true
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight: function(element, errorClass, validClass) {
            $(element).parents('.form-group').addClass('has-error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.form-group').removeClass('has-error');
            $(element).parents('.form-group').addClass('has-success');
        },
        submitHandler: function(form) {
            $('#create_degree_requirement_form input[type=submit]').attr("disabled", "disabled");
            var value = submitForm('create_degree_requirement_form', '/Primus/admin/ajax','action=createnewdegreerequirement');
        }
    });
    $("#addnewtimetable").validate({
        rules: {
            required: {
                required: true
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight: function(element, errorClass, validClass) {
            $(element).parents('.form-group').addClass('has-error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.form-group').removeClass('has-error');
            $(element).parents('.form-group').addClass('has-success');
        },
        submitHandler: function(form) {
            $('#add_pre_course_form input[type=submit]').attr("disabled", "disabled");
            var value = submitForm('addnewtimetable', '/Primus/lecturer/ajax','action=addnewtimetable','');
        }
    });
    $("#viewresults").validate({
        rules: {
            required: {
                required: true
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight: function(element, errorClass, validClass) {
            $(element).parents('.form-group').addClass('has-error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.form-group').removeClass('has-error');
            $(element).parents('.form-group').addClass('has-success');
        },
        submitHandler: function(form) {
            $('#viewresults input[type=submit]').attr("disabled", "disabled");
            form.submit();
            //var value = submitForm('viewresults','/Primus/student/download', 'action=viewresults');
        }
    });
    $("#approvecourses").validate({
        rules: {
            required: {
                required: true
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight: function(element, errorClass, validClass) {
            $(element).parents('.form-group').addClass('has-error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.form-group').removeClass('has-error');
            $(element).parents('.form-group').addClass('has-success');
        },
        submitHandler: function(form) {
            $('#approvecourses input[type=submit]').attr("disabled", "disabled");
            var value = doCourseForms('approvecourses', 'action=loadCourseForms', 'courseform-arena');
        }
    });
    $("#viewstudents").validate({
        rules: {
            required: {
                required: true
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight: function(element, errorClass, validClass) {
            $(element).parents('.form-group').addClass('has-error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.form-group').removeClass('has-error');
            $(element).parents('.form-group').addClass('has-success');
        },
        submitHandler: function(form) {
            $('#viewstudents input[type=submit]').attr("disabled", "disabled");
            var value = doCourseForms('viewstudents', 'action=loadListOfStudents', 'courseform-arena');
        }
    });
    $("#manageresults").validate({
        rules: {
            required: {
                required: true
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight: function(element, errorClass, validClass) {
            $(element).parents('.form-group').addClass('has-error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.form-group').removeClass('has-error');
            $(element).parents('.form-group').addClass('has-success');
        },
        submitHandler: function(form) {
            $('#manageresults input[type=submit]').attr("disabled", "disabled");
            var value = doCourseForms('manageresults', 'action=manageresults', 'courseform-arena');
        }
    });
    $("#create_session_form").validate({
        rules: {
            required: {
                required: true
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight: function(element, errorClass, validClass) {
            $(element).parents('.form-group').addClass('has-error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.form-group').removeClass('has-error');
            $(element).parents('.form-group').addClass('has-success');
        },
        submitHandler: function(form) {
            $('#create_session_form input[type=submit]').attr("disabled", "disabled");
            var value = submitForm('create_session_form', '/Primus/admin/ajax','action=createnewsession');
        }
    }); 
    $("#add_lecturer_position_form").validate({
        rules: {
            required: {
                required: true
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight: function(element, errorClass, validClass) {
            $(element).parents('.form-group').addClass('has-error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.form-group').removeClass('has-error');
            $(element).parents('.form-group').addClass('has-success');
        },
        submitHandler: function(form) {
            $('#add_lecturer_position_form input[type=submit]').attr("disabled", "disabled");
            var value = submitForm('add_lecturer_position_form', '/Primus/admin/ajax','action=addlecturerposition', 'viewPositionCloseXtra');
        }
    });
    $("#add_lecturer_course_form").validate({
        rules: {
            required: {
                required: true
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight: function(element, errorClass, validClass) {
            $(element).parents('.form-group').addClass('has-error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.form-group').removeClass('has-error');
            $(element).parents('.form-group').addClass('has-success');
        },
        submitHandler: function(form) {
            $('#add_lecturer_course_form input[type=submit]').attr("disabled", "disabled");
            var value = submitForm('add_lecturer_course_form', '/Primus/admin/ajax','action=addlecturercourse', 'viewCoursesCloseXtra');
        }
    });
    $("#create_course_form").validate({
        rules: {
            required: {
                required: true
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight: function(element, errorClass, validClass) {
            $(element).parents('.form-group').addClass('has-error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.form-group').removeClass('has-error');
            $(element).parents('.form-group').addClass('has-success');
        },
        submitHandler: function(form) {
            $('#create_course_form input[type=submit]').attr("disabled", "disabled");
            var value = submitForm('create_course_form', '/Primus/admin/ajax','action=createcourse');
        }
    });
    $("#add_pre_course_form").validate({
        rules: {
            required: {
                required: true
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight: function(element, errorClass, validClass) {
            $(element).parents('.form-group').addClass('has-error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.form-group').removeClass('has-error');
            $(element).parents('.form-group').addClass('has-success');
        },
        submitHandler: function(form) {
            $('#add_pre_course_form input[type=submit]').attr("disabled", "disabled");
            var value = submitForm('add_pre_course_form', '/Primus/admin/ajax','action=addprecourse', 'viewCoursesCloseXtra');
        }
    });
    $("#create_department_form").validate({
        rules: {
            required: {
                required: true
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight: function(element, errorClass, validClass) {
            $(element).parents('.form-group').addClass('has-error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.form-group').removeClass('has-error');
            $(element).parents('.form-group').addClass('has-success');
        },
        submitHandler: function(form) {
            $('#create_department_form input[type=submit]').attr("disabled", "disabled");
            var value = submitForm('create_department_form', '/Primus/admin/ajax','action=createdepartment');
        }
    });
    $("#create_faculty_form").validate({
        rules: {
            required: {
                required: true
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight: function(element, errorClass, validClass) {
            $(element).parents('.form-group').addClass('has-error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.form-group').removeClass('has-error');
            $(element).parents('.form-group').addClass('has-success');
        },
        submitHandler: function(form) {
            $('#create_faculty_form input[type=submit]').attr("disabled", "disabled");
            var value = submitForm('create_faculty_form', '/Primus/admin/ajax','action=createFaculty');
        }
    });
    $("#create_admin_form").validate({
        rules: {
            required: {
                required: true
            },
            password: {
                required: true,
                minlength: 6,
                maxlength: 20
            },
            confirmPassword: {
                required: true,
                minlength: 6,
                maxlength: 20,
                equalTo: "#password"
            },
            email: {
                required: true,
                email: true
            },
            phoneNumber: {
                required: true,
                phoneNG: true
            },
            image: {
                required: true,
                extension: "png,jpeg,,jpg,gif",
                accept: "image/*",
                filesize: 1048576
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight: function(element, errorClass, validClass) {
            $(element).parents('.form-group').addClass('has-error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.form-group').removeClass('has-error');
            $(element).parents('.form-group').addClass('has-success');
        },
        submitHandler: function(form) {
            $('#create_admin_form input[type=submit]').attr("disabled", "disabled");
            form.submit();
        }
    });
    $("#uploadresults").validate({
        rules: {
            required: {
                required: true
            },
            image: {
                required: true,
                accept: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel",
                filesize: 1048576
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight: function(element, errorClass, validClass) {
            $(element).parents('.form-group').addClass('has-error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.form-group').removeClass('has-error');
            $(element).parents('.form-group').addClass('has-success');
        },
        submitHandler: function(form) {
            $('#uploadresults input[type=submit]').attr("disabled", "disabled");
            form.submit();
        }
    });
    $("#changepasswordform").validate({
        rules: {
            required: {
                required: true
            },
            newpassword: {
                required: true,
                minlength: 6,
                maxlength: 20
            },
            renewpassword: {
                required: true,
                minlength: 6,
                maxlength: 20,
                equalTo: "#newpassword"
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight: function(element, errorClass, validClass) {
            $(element).parents('.form-group').addClass('has-error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.form-group').removeClass('has-error');
            $(element).parents('.form-group').addClass('has-success');
        },
        submitHandler: function(form) {
            $('#changepasswordform input[type=submit]').attr("disabled", "disabled");
            editSettings('changepasswordform', '/Primus/settings', 'settingsResponseModalLink');
        }
    });
    $("#create_student_form").validate({
        rules: {
            required: {
                required: true
            },
            password: {
                required: true,
                minlength: 3,
                maxlength: 20
            },
            confirmPassword: {
                required: true,
                minlength: 3,
                maxlength: 20,
                equalTo: "#password"
            },
            email: {
                required: true,
                email: true
            },
            phoneNumber: {
                required: true,
                phoneNG: true
            },
            image: {
                required: true,
                extension: "png,jpeg,,jpg,gif",
                accept: "image/*",
                filesize: 1048576
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight: function(element, errorClass, validClass) {
            $(element).parents('.form-group').addClass('has-error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.form-group').removeClass('has-error');
            $(element).parents('.form-group').addClass('has-success');
        },
        submitHandler: function(form) {
            $('#create_lecturer_form input[type=submit]').attr("disabled", "disabled");
            form.submit();
        }
    });
    
    $("#create_lecturer_form").validate({
        rules: {
            required: {
                required: true
            },
            password: {
                required: true,
                minlength: 6,
                maxlength: 20
            },
            confirmPassword: {
                required: true,
                minlength: 6,
                maxlength: 20,
                equalTo: "#password"
            },
            email: {
                required: true,
                email: true
            },
            phoneNumber: {
                required: true,
                phoneNG: true
            },
            image: {
                required: true,
                extension: "png,jpeg,,jpg,gif",
                accept: "image/*",
                filesize: 1048576
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight: function(element, errorClass, validClass) {
            $(element).parents('.form-group').addClass('has-error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.form-group').removeClass('has-error');
            $(element).parents('.form-group').addClass('has-success');
        },
        submitHandler: function(form) {
            $('#create_lecturer_form input[type=submit]').attr("disabled", "disabled");
            form.submit();
        }
    });
    $.validator.addMethod('filesize', function(value, element, param) {
        // param = size (en bytes) 
        // element = element to validate (<input>)
        // value = value of the element (file name)
        return this.optional(element) || (element.files[0].size <= param);
    });
    $("#create_batch_admin_form").validate({
        rules: {
            required: {
                required: true
            },
            excelFile: {
                required: true,
                accept: ".csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel"
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight: function(element, errorClass, validClass) {
            $(element).parents('.form-group').addClass('has-error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.form-group').removeClass('has-error');
            $(element).parents('.form-group').addClass('has-success');
        },
        submitHandler: function(form) {
            $('#create_batch_admin_form input[type=submit]').attr("disabled", "disabled");
            form.submit();
        }
    });
    $("#number_validate").validate({
        rules: {
            min: {
                required: true,
                min: 10
            },
            max: {
                required: true,
                max: 24
            },
            number: {
                required: true,
                number: true
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight: function(element, errorClass, validClass) {
            $(element).parents('.form-group').addClass('has-error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.form-group').removeClass('has-error');
            $(element).parents('.form-group').addClass('has-success');
        }
    });
    $("#password_validate").validate({
        rules: {
            pwd: {
                required: true,
                minlength: 6,
                maxlength: 20
            },
            pwd2: {
                required: true,
                minlength: 6,
                maxlength: 20,
                equalTo: "#pwd"
            }
        },
        errorClass: "help-inline",
        errorElement: "span",
        highlight: function(element, errorClass, validClass) {
            $(element).parents('.form-group').addClass('has-error');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents('.form-group').removeClass('has-error');
            $(element).parents('.form-group').addClass('has-success');
        }
    });
});

function editSettings(formId, url, link2Click) {
    $.ajax
            ({
                url: url,
                data: $('#' + formId).serialize(),
                async: false,
                type: 'POST',
                dataType: 'json',
                beforeSend: function() {
                    $('#loading_gif').show();
                    $('#message_on_end').text('Loading...');
                },
                success: function(resp) {
                    $("#settings_message_content").text("Action completed. Status --- " + resp.message);
                    $("#"+link2Click).click();
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
