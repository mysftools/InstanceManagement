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
		$("#inst-stop").hide();
		$("#apex1").hide();
		$("#inst-update").hide();
		$(".register-form")[0].reset();
		$('.modal-title').text("Enter Details");
		$('#new_inst').modal('show');
	});
	$("#inst-stop").click(function () {
		$.ajax({
			type: 'POST',
			url: "apex/stop?instid="+ ($("#uid").val()),
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
		});
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

var id= 1;

function progress(token,name) {
	
	$.ajax({
		type: 'POST',
		url: "apex/getprogress?instancetoken="+token,
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
				var elem = document.getElementById("myBar");
				elem.style.width = data.response['percentage'] + '%';
				$("#show-api-call").show();
				$("#inst").hide();
				$("#apex1").show();
				$("#inst-stop").show();
				$("#instence").hide();
				$("#inst-update").hide();
				$("#inst-run").hide();
				$(".register-form")[0].reset();
				$('.modal-title').text('Add Instance Details');
				$('#new_inst').modal('show');
				$('[name=uid]').val(token);
				$('#instname').text(name);
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
}

function runapex(token, name) {
	viewcounter();
	var n = Number($("#sc").text());
	if (n <= 0) {
		warnUser();
	} else {
		$("#apex").attr('disabled', false);
		$("#call").attr('disabled', false);
		$("#show-api-call").hide();
		$("#inst").hide();
		$("#apex1").show();
		$("#instence").hide();
		$("#inst-stop").hide();
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
					var elem = document.getElementById("myBar");
					elem.style.width = data.response['percentage'] + '%';
					$('#pvalue').text(data.response['percentage']+"%");
					App.unblockUI();
				} else{ 
					if (data.code==102) {
						clearInterval(id);
						runapex($('[name=uid]').val(), $('#instname').text());
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
		
		// progress($('[name=uid]').val(),$('#instname').text(name));
	
	
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
		console.log();
		$("#inst-stop").show();
		$("#inst-run").hide();
		id=	setInterval("settime()", 1000);
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
				
			},
			success: function (data) {
				viewcounter();
				if (data.status) {
					success("DONE");
					App.unblockUI();
					
					// postprocess();
				} else if (!data.status) {
					error("Problem occures during process c1");
					App.unblockUI();
				} else { 
					error("Problem occures during process c2");
					App.unblockUI();
				}
			},
			error: function () {
				$("#apex").attr('disabled','disabled');
				$("#call").attr('disabled','disabled');
				viewcounter();
				
				// error("Problem occures during process c3");
				App.unblockUI();
				
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
				viewcounter();
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

function purchaseruns(p,run) {
	
	$.ajax({
		type: 'POST',
		url: "/payment/ordercreat?amount="+p,
		dataType: "JSON",
		async: false,
		processData: false,
		cache: false,
		contentType: "application/json",
		beforeSend: function () {
			/*
			 * App.blockUI({ boxed: true, message: "Please Wait..." });
			 */
			
		},
		success: function (data) {
			if (data.orders_status=='created') {
				
				var options = {
					    "key": data.key, 
					    "amount": data.amount, 
					    "currency": data.currency,
					    "name": data.company_name,
					    "description": "A Wild Sheep Chase is the third novel by Japanese author  Haruki Murakami",
					    "image": "data:image/jpeg;base64,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",
					    "order_id": data.order_id,
					    "handler": function (response){
					    	var form = {
					    			"razorpay_payment_id":response.razorpay_payment_id,
					    			"razorpay_payment_id": response.razorpay_payment_id,
					    			"razorpay_signature": response.razorpay_signature,
					    			"runs": run
					    		};
					    	updateruns(form);
					    	
					    },
					    "prefill": {
					        "name": data.username,
					        "email": data.email
					    },
					    "notes": {
					        "address": "note value"
					    },
					    "theme": {
					        "color": "#F37254"
					    }
					};
				App.unblockUI();
					var rzp1 = new Razorpay(options);
					    rzp1.open();
					    // e.preventDefault();
				success(data.message);
				// postprocess();
			} else if (!data.orders_status) {
				error("Problem occures during process");
				App.unblockUI();
			} else {
				error("Problem occures during process");
				App.unblockUI();
			}

		},
		error: function () {

			// App.unblockUI();
		}
	});
}

function updateruns(response) {
	
	
	$.ajax({
		type: 'POST',
		url: "payment/updaterun",
		dataType: "JSON",
		data: JSON.stringify(response),
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
				viewcounter();
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