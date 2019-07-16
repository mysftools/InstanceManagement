jQuery(document).ready(function () {
    getinst();
});

function getinst() {
    $.ajax({
        type: 'POST',
        url: "usermanagement/getuserbyid",
        dataType: "JSON",
        async: true,

        processData: false,
        cache: false,
        contentType: false,
        beforeSend: function () { },
        success: function (data) {

            if (data.status) {
                $('[name=memberToken]').val(data.response['token']);
                $('#username').val(data.response['username']);

                $('#calls').val(data.response['calls']);

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
        "token": $("#token").val(),
        "username": $("#username").val(),
        "calls": $("calls").val,
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
            console.log(data);
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
    }
    );
});

$("#changepass").click(function () {

    var form = {
        "password": $("#Password").val(),
        "newpassword": $("#npassword").val(),
        "confirmpassword": $("#cpassword").val(),

    };

    $.ajax({
        type: 'POST',
        url: "usermanagement/changepassword",
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
                success("Password changed successsfully");
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

$("#cancelpass").click(function () {
    $("#tab_1_1").show();
    $("#tab_1_3").hide();
});