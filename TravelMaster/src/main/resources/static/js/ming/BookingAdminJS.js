$(document).ready(function() {
	$('#queryResult').hide();
});

var basePath = window.location.protocol + "//" + window.location.hostname + ":" + location.port + '/TM';

function show() {
	let table;
	let allTicketInfoDataSource = basePath + "/services/GetAllTicketInfo";
	console.log(allTicketInfoDataSource);
	if ($.fn.dataTable.isDataTable('#queryResult')) {
		table = $('#queryResult').DataTable();
	} else {
		table = $('#queryResult').DataTable({
			ajax: allTicketInfoDataSource
		});
	}
	table.ajax.reload();
	$('#queryResult').show();
}

function insertRecord() {
	location.href = basePath + "/highSpeedRail/insert";
}

function updateTarget(id) {
	location.href = basePath + "/highSpeedRail/update?id=" + id;
}

function deleteTarget(id) {
	const xhttp = new XMLHttpRequest();
	xhttp.onload = function() {
		if (this.status === 200) {
			deleteResult = JSON.parse(this.response);
			Swal.fire({
				title: '是否刪除?',
				icon: 'warning',
				showCancelButton: true,
				cancelButtonColor: '#d33',
				confirmButtonText: '取消',
				confirmButtonColor: '#3085d6',
				confirmButtonText: '確定'
			}).then((result) => {
				if (result.isConfirmed) {
					Swal.fire(
						'Deleted!',
						'刪除成功',
						'success'
					)
				}
				$("#search").click();
			})
		}
	}
	xhttp.open("Delete", basePath + "/services/DeleteTicketInfo");
	xhttp.setRequestHeader("Content-type",
		"application/x-www-form-urlencoded");
	xhttp.send("services=DeleteTicketInfo&id=" + id);
}