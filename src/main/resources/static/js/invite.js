var Login = function () {


    var handleRegister = function () {

        $('.register-form').validate({
            errorElement: 'span', // default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            ignore: "",
            rules: {

                username: {
                    required: true
                },
                userid: {
                    required: true,
                    email: true
                },
                password: {
                    required: true
                },
                cpassword: {
                    required: true
                },
                companyname: {
                    required: true
                }
            },


            invalidHandler: function (event, validator) { // display error
                // alert on form
                // submit

            },

            highlight: function (element) { // hightlight error inputs
                $(element)
                    .closest('.form-group').addClass('has-error');
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

        $('.register-form input').keypress(function (e) {
            if (e.which == 13) {
                if ($('.register-form').validate().form()) {
                    $('.register-form').submit();
                }
                return false;
            }
        });

    }

    return {
        init: function () {
            handleRegister();
        }
    };
}();

jQuery(document).ready(function () {
    Login.init();

    table = $('#listMember').DataTable({
        "processing": true, // Feature control the processing indicator.
        "serverSide": false, // Feature control DataTables' server-side
        // processing mode.
        "order": [], // Initial no order.
        "autoWidth": false,
        "language": {

            "zeroRecords": "Sorry, no results were found",
            "infoEmpty": "No records available",
        },
        // Load data for the table's content from an Ajax source
        "ajax": {
            "url": "usermanagement/adminuser",
            "type": "POST",
            "dataSrc": ''
        },
        // Set column definition initialisation properties.
        "columns": [
            {
                data: "id",
                mRender: function (data, type, row) {
                    return '<i style="radius= 50%; font-size: 20px; color: royalblue;" onclick="getuser(' + "'" + row.token + "'" + ')" class="fa fa-eye"></i>';

                }
            },
            {
                data: "username"
            },
            {
                data: "userid"
            },
            {
                data: "companyName"
            },
            {
                data: "role"
            },
            {
                data: "status",
                mRender: function (data, type, row) {
                    if (data) {
                        return '<span style="cursor: pointer;" onclick="updatestatus(' + "'" + row.token + "'" + ')" class="label label-sm label-success"> Active </span>';
                    }
                    return '<span style="cursor: pointer;" onclick="updatestatus(' + "'" + row.token + "'" + ')" class="label label-sm label-warning"> Blocked </span>';
                }
            },
            {
                data: "id",
                mRender: function (data, type, row) {

                    return '<i style="radius= 50%; font-size: 20px; color: royalblue;" onclick="updateuser(' + "'" + row.token + "'" + ')" class="fa fa-edit"></i>' +
                        '&nbsp &nbsp<i style="radius= 30%; font-size: 20px; color: red;" onclick="deleteUser(' + "'" + row.token + "'" + ')" class="fa fa-trash"></i>';

                }
            }
        ]
    });

    list_refresh = function () {
        table.ajax.reload(null, false);
    };
});
$("#add_new").click(function () {
    $("#pass").show();
    $('.modal-title').text("Enter User Details");
    $('#user-update').hide();
    $('#new_user').modal('show');
});

$("#cancle").click(function () {
    $('.form-group').removeClass('has-error');
    $(".register-form")[0].reset();
    $(".help-block").html("");
});


$("#user-save").click(function () {
    var form = {
        "username": $("#username").val(),
        "userid": $("#userid").val(),
        "password": $("#password").val(),
        "rpassword": $("#cpassword").val(),
        "companyId": $("#companyname").val(),
        "role": $("#role_list").val()
    };
    if (form.username != "" && form.password != "" && form.rpassword != "" && form.role != "" && form.companyId != "") {
        $.ajax({

            type: 'POST',
            url: "invite/saveuser?url=" + (window.document.location.protocol + "//" + window.document.location.host),
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

function getuser(token) {
    $('#viewUser').modal('show');
    $('.modal-title').text("User Details");
    $.ajax({
        type: 'POST',
        url: "usermanagement/getbyid?token=" + token,
        dataType: "JSON",
        async: true,
        processData: false,
        cache: false,
        contentType: false,
        beforeSend: function () { },
        success: function (data) {

            if (data.status) {
                $('#username1').text(data.response['username']);
                $('#userid1').text(data.response['userid']);
                $('#companyname1').text(data.response['companyName']);
                $('#role1').text(data.response['role']);
            }
            else {
                error("Problem occures during process");
            }
        },
        error: function () {
            error("Problem occures during process");
            App.unblockUI();
        }

    });

}

function updatestatus(token) {
    swal({
        title: "Are you sure?",
        text: "You want to change Status?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    }).then((willDelete) => {
        if (willDelete) {
            swal("User Status has been changed!", { icon: "success", });
            return upst(token);
        }
        else {
            swal("Operation is canceled!", { icon: "error", });
        }
    });
}
var upst = function (token) {
    var form = new FormData();
    form.append("token", token);

    $.ajax({
        type: 'POST',
        url: "usermanagement/upstatus",
        dataType: "JSON",
        async: true,
        data: form,
        processData: false,
        cache: false,
        contentType: false,
        beforeSend: function () { },
        success: function (data) {
            success("Status updated successfully!!");
            list_refresh();
            App.unblockUI();
        },
        error: function () {
            error("Problem occures during process");
            App.unblockUI();
        }
    });
};

function updateuser(token) {

    $("#pass").hide();
    $("#user-save").hide();
    $("#user-update").show();
    $('.modal-title').text('Update User Details');
    $('#new_user').modal('show');
    $.ajax({
        type: 'POST',
        url: "usermanagement/getbyid?token=" + token,
        dataType: "JSON",
        async: true,
        processData: false,
        cache: false,
        contentType: false,
        beforeSend: function () { },
        success: function (data) {
            // console.log(data);
            if (data.status) {
                $('[name=memberToken]').val(data.response['token']);
                $('#username').val(data.response['username']);
                $('#companyname').val(data.response['companyName']);
                $('#userid').val(data.response['userid']);

            }
            else {
                error("Problem occures during process");
            }
        },
        error: function () {
            error("Problem occures during process");
            App.unblockUI();
        }

    });

}
$("#user-update").click(function () {
    var form = {
        "token": $("#memberToken").val(),
        "username": $("#username").val(),
        "companyname": $("#companyname").val(),
        "userid": $("#userid").val(),
    };

    $.ajax({
        type: 'POST',
        url: "usermanagement/update",
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
                list_refresh();
                $('#new_user').modal('hide');
                $(".register-form")[0].reset();
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
    }
    );
});

function deleteUser(token) {
    swal({
        title: "Are you sure?",
        text: "You want to delete this user?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    }).then((willDelete) => {
        if (willDelete) {
            swal("User has been deleted!", { icon: "success", });
            return deleteuser(token);
        }
        else {
            swal("Operation is canceled!", { icon: "error", });
        }
    });
}

var deleteuser = function (token) {
    var form = new FormData();
    form.append("token", token);

    $.ajax({
        type: 'POST',
        url: "usermanagement/deletebyid",
        dataType: "JSON",
        async: true,
        data: form,
        processData: false,
        cache: false,
        contentType: false,
        beforeSend: function () {
            App.blockUI({
                boxed: true,
                message: "Please Wait..."
            });
        },
        success: function (data) {
            if (data.status) {
                success(data.message);
                list_refresh();
                App.unblockUI();
            }
            else {
                error("Problem occures during process");
            }
        },
        error: function () {
            error("Problem occures during process");
            App.unblockUI();
        }

    });
}

