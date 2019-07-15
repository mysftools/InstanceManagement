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
				data: "id",
				mRender: function (data, type, row) {
					return '<i style="radius= 50%; font-size: 20px; color: royalblue;" onclick="getinst(' + "'" + row.instToken + "'" + ')" class="fa fa-eye"></i>';

				}
			},
			{
				data: "instname"
			},
			{
				data: "id",
				mRender: function (data, type, row) {
					return '<span style=" height:50px " onclick="getinst(' + "'" + row.instToken + "'" + ')" >' + row.stript + '</span>';
				}
			},
			{
				data: "date"
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

