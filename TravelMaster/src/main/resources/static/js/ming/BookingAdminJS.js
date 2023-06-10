$(document).ready(function() {
	document.querySelector("#queryResult").hidden = true;
});
var basePath = window.location.protocol + "//" + window.location.hostname + ":" + location.port + '/TM';
console.log(basePath);

function show() {
	let table;
	let allTicketInfoDataSource = basePath
		+ "services/GetAllTicketInfo";
	if ($.fn.dataTable.isDataTable('#queryResult')) {
		table = $('#queryResult').DataTable();
	} else {
		table = $('#queryResult').DataTable({
			ajax: allTicketInfoDataSource
		});
	}
	table.ajax.reload();
	document.querySelector("#queryResult").hidden = false;
}

function insertRecord() {
	location.href = basePath + "highSpeedRail/insert";
}

function updateTarget(id) {
	location.href = basePath + "highSpeedRail/update?id=" + id;
}

function deleteTarget(id) {
	const xhttp = new XMLHttpRequest();
	xhttp.onload = function() {
		if (this.status === 200) {
			deleteResult = JSON.parse(this.response);
			alert(deleteResult.msg);
			document.querySelector("#search").click();
		}
	}
	xhttp.open("POST", basePath + "services/DeleteTicketInfo");
	xhttp.setRequestHeader("Content-type",
		"application/x-www-form-urlencoded");
	xhttp.send("services=DeleteTicketInfo&id=" + id);
}