
$(document).ready(function() {
	let dtDataList = JSON.parse(document.querySelector("#dataTableContent").value);
	$('#queryResult').DataTable({data:dtDataList});
});	

function updateTarget(trainNO){
	$('#EditField').show();
}
// func cancel => $('#EditField').hide();

function deleteTarget(trainNO){
	// ajax delete
}