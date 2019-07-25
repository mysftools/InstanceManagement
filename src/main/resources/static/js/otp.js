var Login = function () {  
    var handleotp= function () {
        $('#otp-form').validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            ignore: "",
            rules: {
            	otp: {
                    required: true,
                }
            },

            messages: {
            	otp: {
                    required: "Otp is required."
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

        $('#otp-form input').keypress(function (e) {
            if (e.which == 13) {
                if ($('#otp-form').validate().form()) {
                    $('#otp-form').submit();
                }
                return false;
            }
        });
    }
    
     return {
        //main function to initiate the module
        init: function () {
        	handleotp();
        }

    };
	
}();



$("#otp-btn").click(function () {

	var form = {
		"otp": $("#otp").val(),
		"username": $("#username").val()
	};
	if (form.otp != "" && form.username != "") {
		$.ajax({
			type: 'POST',
			url: "otp/verify",
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
				} else if (!data.status) {
					error("Problem occures during process");
					App.unblockUI();
				} else {
					error("Problem occures during process");
					App.unblockUI();
				}
			},
			error: function () {
				
				App.unblockUI();
			}
		}
		);
	};
});


$("#back-btn-otp").click(function () {

	var form = {
		"username": $("#back-btn-otp").val()
	};
	if (form.otp != "" && form.username != "") {
		$.ajax({
			type: 'POST',
			url: "otp/resend",
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
				} else if (!data.status) {
					error("Problem occures during process");
					App.unblockUI();
				} else {
					error("Problem occures during process");
					App.unblockUI();
				}
			},
			error: function () {
				
				App.unblockUI();
			}
		}
		);
	};
});