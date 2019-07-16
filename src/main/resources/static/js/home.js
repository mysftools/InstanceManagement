var Login = function () {

	var handleRegister = function () {

		$('.register-form').validate({
			errorElement: 'span', // default input error message container
			errorClass: 'help-block', // default input error message class
			focusInvalid: false, // do not focus the last invalid input
			ignore: "",
			rules: {

				securityCode: {
					required: true
				},
				nameOfInstance: {
					required: true
				},
				clientkey: {
					required: true
				},
				clientSecreat: {
					required: true
				},
				select: {
					required: true
				},
				apex: {
					required: true
				},
				call: {
					required: true
				}

			},

			invalidHandler: function (event, validator) { // display error alert on form submit

			},

			highlight: function (element) { // hightlight error inputs
				$(element).closest('.form-group').addClass('has-error'); // set error class to the control group
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

		$('#cancle').click(function () {
			$('.form-group').removeClass('has-error');
			$(".register-form")[0].reset();
			$(".help-block").html("");
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

	viewcounter();
	Login.init();
	
	$("#new_user").click(function () {

		$("#instence").show();
		$("#inst").show();
		$("#inst-run").hide();
		$("#apex1").hide();
		$("#inst-update").hide();
		$(".register-form")[0].reset();
		$('.modal-title').text("Enter Details");
		$('#new_inst').modal('show');
	});

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
			"url": "/instancemanagement/getall",
			"type": "POST",
			"dataSrc": ''
		},
		//Set column definition initialisation properties.
		
		"columns": [
			{
				data: "id",
				mRender: function (data, type, row) {
					return '<i style="radius= 50%; font-size: 20px; color: royalblue;" onclick="getinst(' + "'" + row.instToken + "'" + ')" class="fa fa-edit"></i>' +
						'&nbsp &nbsp<i style="radius= 30%; font-size: 20px; color: red;" onclick="deleteUser(' + "'" + row.instToken + "'" + ')" class="fa fa-trash"></i>';

				}
			},
			{
				data: "type"
			},
			{
				data: "id",
				mRender: function (data, type, row) {
					return '<a  onclick="viewUser(' + "'" + row.instToken + "'" + ')" >' + "" + row.nameOfInstance + "" + '</a>';
				}
			},

			{
				data: "id",
				mRender: function (data, type, row) {
					var str;
					str = "<button class='btn btn-xs green dropdown-toggle' type='button' data-toggle='dropdown' aria-expanded='false' " +
							"onclick=runapex('" + row.instToken + "','" + row.nameOfInstance + "') > Run Apex" +
						"</button>"+"<button class='btn btn-xs blue dropdown-toggle' type='button' data-toggle='dropdown' aria-expanded='false' onclick=showinstdetails('" + row.instToken + "')> Instance Details" +
						"</button>"

					return str;
				}
			}
		]
	});
	list_refresh = function () {
		table.ajax.reload(null, false);
	};
	postProcess = function () {
		$('#new_inst').modal('hide');
		list_refresh();
		App.unblockUI();
	};

});

function showinstdetails(token) {
	 window.location.replace("/instancedetails?token="+token);
}

$("#inst").click(function () {

	var form = {
		"nameOfInstance": $("#nameOfInstance").val(),
		"type": $("#select").val(),
		"clientkey": $("#clientkey").val(),
		"clientSecreat": $("#clientSecreat").val(),
		"securityCode": $("#securityCode").val()
	};

	if (form.nameOfInstance != "" && form.type != "" && form.securityCode != "") {
		$('#new_inst').modal('hide');
		$.ajax({
			type: 'POST',
			url: "instancemanagement/add",
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
					postProcess();
				} else if (!data.status) {
					error("Problem occures during process");
					App.unblockUI();
				} else {
					error("Problem occures during process");
					list_refresh();
					App.unblockUI();
				}

			},
			error: function () {
				list_refresh();
				App.unblockUI();
			}
		}
		);
	};
});

function runapex(token, name) {
	viewcounter();
	var n =Number($("#sc").text());
	if (n <= 0) {
		warnUser();
	} else {

		$("#show-api-call").hide();
		$("#inst").hide();
		$("#apex1").show();
		$("#instence").hide();
		$("#inst-update").hide();
		$("#inst-run").show();
		$(".register-form")[0].reset();
		$('.modal-title').text('Add Instance Details');
		$('#new_inst').modal('show');
		$('[name=uid]').val(token);
		$('#instname').text(name);

	}

}

$("#inst-run").click(function () {
	$("#show-api-call").show();
	var form = {
		"code": $("#apex").val(),
		"token": $("#uid").val(),
		"num": $("#call").val().toString(),
	};
	viewcounter();
	var n = Number($("#sc").text());
	var d = Number($("#call").val());
	if (n < d) {
		$("#call").rules("add", {
			required: true,
			max: n
		});
	} else {
		var prog=100/d;
		var promises = [];
		var duration = 5000;
		for (var i = 0; i < d; i++) {
			 progress(i,prog);
			 console.log('done');
			var request =runcode(form,i,prog);
			  promises.push( request);
			 
		};
		addinstdetails(form);
		//postProcess();
	};

});

function progress(i,prog) {
	$("#p").attr('style', 'width: ' + (i+1)*prog + '%; color : red;');
	
	$("#pvalue").text((i+1)*prog+"%");
}

function addinstdetails(form) {
	$.ajax({
		type: 'POST',
		url: "instancedetails/add",
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
			viewcounter();
			if (data.status) {
				//success("DONE");
				App.unblockUI();
				list_refresh();
			} else if (!data.status) {
				error("Problem occures during process");
				App.unblockUI();
			} else {
				error("Problem occures during process");
				App.unblockUI();
			}

		},
		error: function () {
			viewcounter();
			error("Problem occures during process");
			App.unblockUI();
		}
	});
}

function runcode(form,i,prog) {
	if (form.code != '' && form.token != '' && form.num != '') {
		
		return $.ajax({
			type: 'POST',
			url: "apex/runcode",
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
				viewcounter();
				if (data.status) {
					success("DONE");
					App.unblockUI();
					list_refresh();
				} else if (!data.status) {
					error("Problem occures during process");
					App.unblockUI();
				} else {
					error("Problem occures during process");
					App.unblockUI();
				}

			},
			error: function () {
				viewcounter();
				error("Problem occures during process");
				App.unblockUI();
			}
		});
	}
	
}

function warnUser() {
	swal({
		title: "Ohhhhoo",
		text: "You have executed the number of calls",
		icon: "warning",
		buttons: true,
		dangerMode: true,
	}).then((willDelete) => {

		if (willDelete) {
			swal("Please contact admin!", { icon: "success", });

		}
		else {
			swal("Operation is canceled!", { icon: "error", });
		}
	});
}
function getinst(token) {
	$("#inst").hide();
	$("#call1").hide();
	$("#inst-run").hide();
	$("#apex1").hide();
	$("#inst-update").show();
	$(".register-form")[0].reset();
	$('.modal-title').text('Update Instance Details');
	$('#new_inst').modal('show');


	var form = new FormData();
	form.append("token", token);

	$.ajax({
		type: 'POST',
		url: "instancemanagement/findbyinsttoken",
		dataType: "JSON",
		async: true,
		data: form,
		processData: false,
		cache: false,
		contentType: false,
		beforeSend: function () { },
		success: function (data) {

			if (data.status) {
				$('[name=uid]').val(data.response['instToken']);
				$('#securityCode').val(data.response['securityCode']);
				$('#select').val(data.response['type']);
				$('#clientkey').val(data.response['clientkey']);
				$('#clientSecreat').val(data.response['clientSecreat']);
				$('#nameOfInstance').val(data.response['nameOfInstance']);

			}
			else {
				error("Problem occures during process");
				postProcess();
			}
		},
		error: function () {
			error("Problem occures during process");
			App.unblockUI();
			postProcess();
		}

	});
}



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
		url: "instancemanagement/deletebyid",
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
				console.log(data);
				success(data.message);
				postProcess();

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

function viewUser(token) {
	$('#viewinst').modal('show');
	var form = new FormData();
	form.append("token", token);

	$.ajax({
		type: 'POST',
		url: "instancemanagement/findbyinsttoken",
		dataType: "JSON",
		async: true,
		data: form,
		processData: false,
		cache: false,
		contentType: false,
		beforeSend: function () { },
		success: function (data) {

			if (data.status) {
				$('#viewtoken').text(data.response['token']);
				$('#securityCode1').text(data.response['securityCode']);
				$('#type').text(data.response['type']);
				$('#instToken').text(data.response['instToken']);

				$('#nameOfInstanced').text(data.response['nameOfInstance']);
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

$("#inst-update").click(function () {

	var form = {
		"instToken": $("[name=uid]").val(),
		"securityCode": $("#securityCode").val(),
		"type": $("#select").val(),
		"clientkey": $("#clientkey").val(),
		"clientSecreat": $("#clientSecreat").val(),
		"nameOfInstance": $("#nameOfInstance").val(),

	};
	
	$.ajax({
		type: 'POST',
		url: "instancemanagement/updatebyid",
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
				success("Instance updated successfully");
				postProcess();
			} else if (!data.status) {
				$.each(data.errors, function (k, v) {
					$("#" + k + "-e").addClass("has-error");
					$("#" + k + "-required").html(v);
				});
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

function viewcounter() {

	$.ajax({
		type: 'POST',
		url: "backup/getinstinfo",
		dataType: "JSON",
		async: true,

		processData: false,
		cache: false,
		contentType: false,
		beforeSend: function () { },
		success: function (data) {

			if (data.status) {
				$("#fc").text(data.totalcalls);
				$("#sc").text(data.remainingcalls);
				$("#tc").text(data.totalinst);
				
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