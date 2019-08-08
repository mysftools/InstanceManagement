var Login = function () {

	var handleRegister = function () {

		$('.register-form').validate({
			errorElement: 'span', // default input error message container
			errorClass: 'help-block', // default input error message class
			focusInvalid: false, // do not focus the last invalid input
			ignore: "",
			rules: {
				nameOfCoustomer: {
					required: true
				},
				apiversion: {
					required: true
				},
				username: {
					required: true
				},
				password: {
					required: true
				},
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
	loadtable();
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
	

});


function loadtable() {
	$.ajax({
		type: 'POST',
		url: "instancemanagement/getall",
		dataType: "JSON",
		async: false,
		processData: false,
		cache: false,
		contentType: "application/json",
		beforeSend: function () {

			App.blockUI({
				boxed: true,
				message: "Please Wait..."
			});
		},
		success: function (obj) {
			if (obj.status) {
				var role = obj.role;
				$('#listMember').DataTable({
					data: obj.response,
					columns: [
						{
							data: "id",
							mRender: function (data, type, row) {
								var str;
								if (role == 'admin') {
									str = '<i style="radius= 50%; font-size: 20px; color: royalblue;" onclick="getinst(' + "'" + row.instToken + "'" + ')" class="fa fa-edit"></i>' +
										'&nbsp &nbsp<i style="radius= 30%; font-size: 20px; color: red;" onclick="deleteUser(' + "'" + row.instToken + "'" + ')" class="fa fa-trash"></i>';
								} else {
									str = '<i style="radius= 50%; font-size: 20px; color: royalblue;" onclick="alertadmin()" class="fa fa-edit"></i>' +
										'&nbsp &nbsp<i style="radius= 30%; font-size: 20px; color: red;" onclick="alertadmin()" class="fa fa-trash"></i>';
								}

								return str;

							}
						},
						{
							data: "id",
							mRender: function (data, type, row) {
								var type = row.sandbox;
								if (type) {
									return 'Sand Box';
								} else {
									return 'Production';
								}
							}
						},
						{
							data: "coustomerName"
						},
						{
							data: "apiversion"
						},
						{
							data: "id",
							mRender: function (data, type, row) {
								var str;
								if (role == 'admin') {
									str = '<a  onclick="viewUser(' + "'" + row.instToken + "'" + ')" >' + "" + row.nameOfInstance + "" + '</a>';
								} else {
									str = '<a  onclick="alertadmin()" >' + "" + row.nameOfInstance + "" + '</a>';
								}
								return str;
							}
						},

						{
							data: "id",
							mRender: function (data, type, row) {
								var str;
								if (role == 'admin') {
									str = "<button class='btn btn-xs green dropdown-toggle' type='button' data-toggle='dropdown' aria-expanded='false' " +
										"onclick=progress('" + row.instToken + "','" + row.nameOfInstance + "') > Run Apex" +
										"</button>" + "<button class='btn btn-xs blue dropdown-toggle' type='button' data-toggle='dropdown' aria-expanded='false' onclick=backup('" + row.instToken + "')> BackUp" +
										"</button>" + "<button class='btn btn-xs blue dropdown-toggle' type='button' data-toggle='dropdown' aria-expanded='false' onclick=showinstdetails('" + row.instToken + "')> <i class='fa fa-history'></i>" +
										"</button>";
								} else {
									str = "<button class='btn btn-xs green dropdown-toggle' type='button' data-toggle='dropdown' aria-expanded='false' " +
										"onclick=progress('" + row.instToken + "','" + row.nameOfInstance + "') > Run Apex" +
										"</button>" + "<button class='btn btn-xs blue dropdown-toggle' type='button' data-toggle='dropdown' aria-expanded='false' onclick=alertadmin()> BackUp" +
										"</button>" + "<button  class='btn btn-xs blue dropdown-toggle' type='button' data-toggle='dropdown' aria-expanded='false' onclick=showinstdetails('" + row.instToken + "')> <i class='fa fa-history'></i>" +
										"</button>";
								}


								return str;
							}
						}
					]
				});
				App.unblockUI();
			} else if (!obj.status) {
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
	});

}

function alertadmin() {
	error("You are not allowed");
}

function showinstdetails(token) {
	window.location = "/instancedetails?token=" + token;
}

function backup(token) {
	$('[name=uid]').val(token);
	$('.modal-title').text('Add Package.xml File');
	$("#back-up").modal('show');
}

$("#inst").click(function () {

	var form = {
		"coustomerName": $("#nameOfCoustomer").val(),
		"nameOfInstance": $("#nameOfInstance").val(),
		"type": $("#select").val(),
		"clientkey": $("#clientkey").val(),
		"clientSecreat": $("#clientSecreat").val(),
		"username": $("#username").val(),
		"password": $("#password").val(),
		"securityCode": $("#securityCode").val(),
		"apiversion": $("apiversion").val()
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

				App.unblockUI();
			}
		}
		);
	};
});



function progress(token,name) {
	var form=new FormData();
    form.append("instancetoken",token);
	
	$.ajax({
		type: 'POST',
		url: "apex/getprogress?instancetoken="+token,
		dataType: "JSON",
		async: false,
		data:form,
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
				$("#p").attr('style', 'width: ' +data.response['percentage'] + '%; color : red;');
				$("#pvalue").text(data.response['percentage'] + "%");
				$("#show-api-call").show();
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
				$("#apex").attr('disabled','disabled');
				$("#call").attr('disabled','disabled');
				setInterval("settime()", 1000);
				App.unblockUI();
			} else{ 
				if (data.code==102) {
					runapex(token, name);
					App.unblockUI();
				}else {
					error("Problem occures during process pr1");
					App.unblockUI();
				}
			} 
		},
		error: function () {
			viewcounter();
			error("Problem occures during process pr2");
			App.unblockUI();
		}
	});	
}

function runapex(token, name) {
	viewcounter();
	var n = Number($("#sc").text());
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
function settime(){
	while ($('#new_inst').is(':visible') && $("#inst-run").is(':visible')&& $("#show-api-call").is(':visible')) {
		console.log('ddd');
		$.ajax({
			type: 'POST',
			url: "apex/getprogress?instancetoken="+($("#uid").val()),
			dataType: "JSON",
			async: false,
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
					$("#p").attr('style', 'width: ' +data.response['percentage'] + '%; color : red;');
					$("#pvalue").text(data.response['percentage'] + "%");
					$("#apex").attr('disabled','disabled');
					$("#call").attr('disabled','disabled');
					App.unblockUI();
				} else{ 
					if (data.code==102) {
						runapex(token, name);
						App.unblockUI();
					}else {
						error("Problem occures during process pr1");
						App.unblockUI();
					}
				} 
			},
			error: function () {
				viewcounter();
				error("Problem occures during process pr2");
				App.unblockUI();
			}
		});	
		
		//progress($('[name=uid]').val(),$('#instname').text(name));
	};
	
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
		runcode(form);
	};

});

function runcode(form) {
	if (form.code != '' && form.token != '' && form.num != '') {
		$.ajax({
			type: 'POST',
			url: "apex/runcode",
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
					success("DONE");
					App.unblockUI();
					//postprocess();
				} else if (!data.status) {
					error("Problem occures during process c1");
					App.unblockUI();
				} else { 
					error("Problem occures during process c2");
					App.unblockUI();
				}
			},
			error: function () {
				viewcounter();
				error("Problem occures during process c3");
				App.unblockUI();
				postprocess();
			}
		});
	}
}


function getinst(token) {
	$("#instence").show();
	$("#call1").hide();
	$("#inst").hide();
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
				var t = data.response['sandbox'];
				if (t) {
					$('#select').val('true');
				} else {
					$('#select').val('false');
				}
				$('#nameOfCoustomer').val(data.response['coustomerName']);
				$('#clientkey').val(data.response['clientkey']);
				$('#clientSecreat').val(data.response['clientSecreat']);
				$('#nameOfInstance').val(data.response['nameOfInstance']);
				$('#username').val(data.response['username']);
				$('#password').val(data.response['password']);
				$('#apiversion').val(data.response['apiversion']);

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
				success(data.message);
				App.unblockUI();
				postprocess();
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
				console.log(data);
				$('#viewtoken').text(data.response['coustomerName']);
				$('#securityCode1').text(data.response['securityCode']);
				$('#type').text(data.response['sandbox']);
				$('#instToken').text(data.response['instToken']);
				$('#username1').text(data.response['username']);
				$('#nameOfInstanced').text(data.response['nameOfInstance']);
				$('#apiversion1').text(data.response['apiversion']);
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
		"coustomerName": $("#nameOfCoustomer").val(),
		"securityCode": $("#securityCode").val(),
		"type": $("#select").val(),
		"clientkey": $("#clientkey").val(),
		"clientSecreat": $("#clientSecreat").val(),
		"username": $("#username").val(),
		"password": $("#password").val(),
		"nameOfInstance": $("#nameOfInstance").val(),
		"apiversion": $("#apiversion").val()

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
				App.unblockUI();
				postprocess();
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
$('#backup-btn').on("click", function () {

	var tdata = $('[name=uid]').val();
	var file = $('#filepath').val();
	if (file != "") {
		var form = $('.backup-form')[0];
		// Create an FormData object
		var data = new FormData(form);
		console.log(form)
		$.ajax({
			type: "POST",
			enctype: 'multipart/form-data',
			url: "backup/takesingilebackup",
			data: data,
			processData: false,
			contentType: false,
			cache: false,
			beforeSend: function () {
				App.blockUI({
					boxed: true,
					message: "Please Wait..."
				});
			},
			success: function (data) {
				if (data.status) {
					success(data.message);
					$('.backup-form')[0].reset();
					App.unblockUI();
				} else {
					error(data.message);
					$('.backup-form')[0].reset();
					App.unblockUI();
				}
			},
			error: function () {
				$('.backup-form')[0].reset();
			}
		});
	} else {
		if (file == "") {
			$('#filepath-required').removeClass("hide");
			$('#filepath-required').addClass("show");
		}
	}
});
function postprocess() {
	viewcounter();
	sleep(1000);
	location.reload();
}

function sleep(milliseconds) {
	var start = new Date().getTime();
	for (var i = 0; i < 1e7; i++) {
		if ((new Date().getTime() - start) > milliseconds) {
			break;
		}
	}
}


function warnUser() {
	swal({
		title: "Error",
		text: "You have no run's remaining ,Please purchase it",
		icon: "warning",
		buttons: true,
		dangerMode: true,
	}).then((willDelete) => {

		if (willDelete) {
			
			price();
		}
		else {
			swal("Operation is canceled!", { icon: "error", });
		}
	});
}

function price(){
	$('#price').modal('show');
}

function purchaseruns(count){
	console.log(count);
	$.ajax({
		type: 'POST',
		url: "company/purchase?calls=" + count,
		dataType: "JSON",
		async: true,
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
				$('#price').modal('hide');
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
	
}