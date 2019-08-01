var Login = function () {

    var handleLogin = function () {

        $('.login-form').validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            rules: {
                username: {
                    required: true
                },
                password: {
                    required: true
                }
            },

            messages: {
                username: {
                    required: "Username is required."
                },
                password: {
                    required: "Password is required."
                }
            },

            invalidHandler: function (event, validator) { //display error alert on form submit   
                $('.alert-danger', $('.login-form')).show();
            },

            highlight: function (element) { // hightlight error inputs
                $(element)
                    .closest('.form-group').addClass('has-error'); // set error class to the control group
            },

            success: function (label) {
                label.closest('.form-group').removeClass('has-error');
                label.remove();
            },

            errorPlacement: function (error, element) {
                error.insertAfter(element.closest('.input-icon'));
            },

            submitHandler: function (form) {
                form.submit(); // form validation success, call ajax form submit
            }
        });

        $('.login-form input').keypress(function (e) {
            if (e.which == 13) {
                if ($('.login-form').validate().form()) {
                    $('.login-form').submit(); //form validation success, call ajax form submit
                }
                return false;
            }
        });
    }
    var handleRegister = function () {

        $('#reg').validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            ignore: "",
            rules: {

                username: {
                    required: true
                },
                password: {
                    required: true
                },

                rpassword: {
                    required: true
                },
                role_list: {
                    required: true
                },
                company_list: {
                    required: true
                },
                userid: {
                    required: true,
                    email: true
                }
            },


            invalidHandler: function (event, validator) { //display error alert on form submit   

            },

            highlight: function (element) { // hightlight error inputs
                $(element)
                    .closest('.form-group').addClass('has-error'); // set error class to the control group
            },

            success: function (label) {
                label.closest('.form-group').removeClass('has-error');
                label.remove();
            },

            errorPlacement: function (error, element) {
                if (element.closest('.input-icon').size() === 1) {
                    error.insertAfter(element.closest('.input-icon'));
                } else {
                    error.insertAfter(element);
                }
            },

            submitHandler: function (form) {
                form.submit();
            }
        });

        $('#reg input').keypress(function (e) {
            if (e.which == 13) {
                if ($('#reg').validate().form()) {
                    $('#reg').submit();
                }
                return false;
            }
        });

        jQuery('#register-btn').click(function () {

            $('title').text("Register");
            jQuery('.login-form').hide();
            jQuery('#reg').show();
        });
        jQuery('#register-back-btn').click(function () {
            $('title').text("Login");
            jQuery('.login-form').show();
            $('.form-group').removeClass('has-error');
            $('.form-group span').html('');
            $("#reg")[0].reset();
            jQuery('#reg').hide();
        });
    }

    var handleForgetPassword = function () {
        $('#forget-form-email').validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            ignore: "",
            rules: {
                email: {
                    required: true,
                    email: true
                }
            },

            messages: {
                email: {
                    required: "Email is required."
                }
            },

            invalidHandler: function (event, validator) { //display error alert on form submit   

            },

            highlight: function (element) { // hightlight error inputs
                $(element)
                    .closest('.form-group').addClass('has-error'); // set error class to the control group
            },

            success: function (label) {
                label.closest('.form-group').removeClass('has-error');
                label.remove();
            },

            errorPlacement: function (error, element) {
                error.insertAfter(element.closest('.input-icon'));
            },

            submitHandler: function (form) {
                form.submit();
            }
        });

        $('#forget-form-email input').keypress(function (e) {
            if (e.which == 13) {
                if ($('#forget-form-email').validate().form()) {
                    $('#forget-form-email').submit();
                }
                return false;
            }
        });

        jQuery('#forget-password').click(function () {
            $('title').text("Forget Password");
            jQuery('.login-form').hide();
            jQuery('#forget-form-email').show();
        });


        jQuery('#back-btn').click(function () {
            $('title').text("Login");
            jQuery('.login-form').show();
            $("#forget-form-email")[0].reset();
            $('.form-group').removeClass('has-error');
            $(".help-block").html("");
            jQuery('#forget-form-email').hide();
        });
    }

    return {
        //main function to initiate the module
        init: function () {
            handleLogin();
            handleForgetPassword();
            handleRegister();

        }

    };

}();

function postprocess() {
    jQuery('.login-form').show();
    $("#forget-form-email")[0].reset();
    $('.form-group').removeClass('has-error');
    $(".help-block").html("");
    jQuery('#forget-form-email').hide();
}

jQuery(document).ready(function () {
    $('title').text("Login");
    jQuery('.otp-form').hide();
    Login.init();
});


$("#forget-pass-btn").click(function () {

    var form = {
        "email": $("#email-r").val()
    };
    if (form.email != "") {
        $.ajax({
            type: 'POST',
            url: "usermanagement/forgetpassword",
            dataType: "JSON",
            async: true,
            data: JSON.stringify(form),
            processData: false,
            cache: false,
            contentType: "application/json",
            beforeSend: function () {

                App.blockUI({
                    boxed: true,
                    message: "Please Wait..."
                });
            },
            success: function (data) {

                if (data.status) {
                    success(data.message);
                    App.unblockUI();
                    postprocess();
                } else if (!data.status) {
                    error("Problem occures during process");
                    App.unblockUI();
                } else {
                    error("Problem occures during process");
                    App.unblockUI();
                }

            },
            error: function () {
                error("Problem occures during process");
                App.unblockUI();
            }
        }
        );
    };
});




$("#register-submit-btn").click(function () {
    var form = {
        "username": $("#username").val(),
        "userid": $("#userid").val(),
        "password": $("#password").val(),
        "rpassword": $("#rpassword").val(),
        "companyId": $("#company_list").val(),
        "role": $("#role_list").val()
    };
    if (form.username != "" && form.password != "" && form.rpassword != "" && form.role != "" && form.companyId != "") {
        $.ajax({

            type: 'POST',
            url: "usermanagement/saveuser",
            dataType: "JSON",
            async: false,
            data: JSON.stringify(form),
            processData: false,
            cache: false,
            contentType: "application/json",
            beforeSend: function () {
                App.blockUI({
                    boxed: true,
                    message: "Please Wait..."
                });
            },
            success: function (data) {
                if (data.status) {
                    success(data.message);
                    App.unblockUI();
                } else if (!data.status) {
                    error("Problem occures during process");
                    App.unblockUI();
                } else {
                    error("Problem occures during process");
                    App.unblockUI();
                }

            },
            error: function () {
                error("Problem occures during process");
                App.unblockUI();
            }
        });
    } else {

        error("Problem occures during process");
        App.unblockUI();
    };

});
