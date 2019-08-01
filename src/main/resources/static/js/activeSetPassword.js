var Login = function () {

	var handleRegister = function () {

		$('.login-form').validate({
			errorElement: 'span', // default input error message container
			errorClass: 'help-block', // default input error message class
			focusInvalid: false, // do not focus the last invalid input
			ignore: "",
			rules: {
				password: {
					required: true
				},
				cpassword: {
					required: true
				}
			},

			invalidHandler: function (event, validator) { // display error
				// alert on form
				// submit

			},

			highlight: function (element) { // hightlight error inputs
				$(element).closest('.form-group').addClass('has-error');
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

		
	}

	return {
		// main function to initiate the module
		init: function () {
			handleRegister();
		}

	};
}();


jQuery(document).ready(function () {
	Login.init();
});

