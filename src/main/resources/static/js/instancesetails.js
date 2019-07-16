jQuery(document).ready(function () {
	$("#token").hide();
	console.log($("#token").text());
	var t = $("#token").text();

	table = $('#listinstdetails').DataTable({
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
			"url": "/instancedetails/get?token=" + t,
			"type": "POST",
			"dataSrc": ''
		},
		//Set column definition initialisation properties.
		"columns": [
			{
				data: "instname"
			},
			{
				data: "id",
				mRender: function (data, type, row) {
					return '<div id="scripttr"><span style=" height:50px " onclick="getscript(' + "'" + row.detailToken + "'" + ')" >' + row.stript.replace(/\n/g, "<br />");   + '</span></div>';
				}
			},
			{
				data: "id",
				mRender: function (data, type, row) {
					return row.date.split(".")[0];
				}
			},
			{
				data: "noOfCalls"
			}
		]
	});
	list_refresh = function () {
		table.ajax.reload(null, false);
	};
});

function getscript(token){
	
	$.ajax({
		type: 'POST',
		url: "instancedetails/getbyid?token="+token,
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
				//success(data.message);
				App.unblockUI();
				//console.log(data.respons['stript']);
				$('.title').text(data.respons['instname']);
				$('.script').empty()
				$('.script').append(data.respons['stript'].replace(/\n/g, "<br />"));
				$('#responsive').modal('show');
				
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
	});
	
}
