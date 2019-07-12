jQuery(document).ready(function () {
	$("#backupstatus").attr("checked", true);
	loaddetails();
	loadlist();
	
	if ($('#backupstatus').is(":checked")) {
		$('#showinst').hide();
	};
});

function loaddetails() {
	$.ajax({
		type: 'POST',
		url: "backup/getinstinfo",
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
				$("#totalcalls").text(data.totalcalls);
				$("#remainingcalls").text(data.remainingcalls);
				$("#totalinstanc").text(data.totalinst);
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


function valueChanged() {
	if ($('#backupstatus').is(":checked")) {
		$('#showinst').hide();
	} else {
		$('#showinst').show();
	};
}



$("#backup-btn").click(function () {

	if ($('#backupstatus').is(":checked")) {
		backupall();
	} else {
		var form = {
				"token": $("#my_multi_select").val(),
			};
		 backupselected(form);
	};
	
});

function backupselected(form){
	$.ajax({
		type: 'POST',
		url: "backup/backupselected",
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
				$("#backup-form")[0].reset();
				$(".ms-selectable ul li").removeClass('ms-hover');
				$(".ms-selectable ul li").removeAttr("style");
				$(".ms-selectable ul li").removeClass('ms-selected');
				$(".ms-selection ul li").removeClass('ms-selected');
				$(".ms-selection ul li").attr("style", "display: none;");
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


function backupall(){
	$.ajax({
		type: 'POST',
		url: "backup/backupall",
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

function loadlist(){
	$.ajax({
		type: 'POST',
		url: "/instancemanagement/getlist",
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
			var text = "";
			if (data.status) {
				for (var i = 0; i < data.data.length; i++) {
					text = text + "<option value='" + data.data[i].instToken + "'>" + data.data[i].nameOfInstance + "</option>";
				}
				$('#my_multi_select').append(text);
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