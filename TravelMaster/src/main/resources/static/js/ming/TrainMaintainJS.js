
$(document).ready(function() {
	let dtDataList = JSON.parse(document.querySelector("#dataTableContent").value);
	$('#queryResult').DataTable({ data: dtDataList });
});

function updateTarget(tranNo) {
	let myModal = document.getElementById("myModal");
	let EditField = new bootstrap.Modal(document.getElementById('EditField'));

	myModal.addEventListener("click", function(e) {
		EditField.show();
	});


	/*$('#EditField').show();*/
}
// func cancel => $('#EditField').hide();


function deleteTarget(tranNo) {
	Swal.fire({
		title: '是否刪除?',
		icon: 'warning',
		showCancelButton: true,
		cancelButtonText: '取消',
		confirmButtonText: '確定',
		preConfirm: async () => {
			console.log("tranNo=" + deleteTarget);
			try {
				const response = await fetch(`DeleteTranInfo?tranNo=${tranNo}`);
				if (!response.ok) {
					throw new Error(response.statusText);
				}
				return await response.json();
			} catch (error) {
				Swal.showValidationMessage(
					`Request failed: ${error}`
				);
			}
		},
		allowOutsideClick: () => !Swal.isLoading()
	}).then((result) => {
		if (result.isConfirmed) {
			let resultObj = result.value;
			console.log(resultObj);
			if (resultObj.result) {
				Swal.fire(
					'刪除成功',
					`紀錄刪除成功(班次="${resultObj.id}")`,
					'success'
				);
			} else {
				Swal.fire(
					'刪除失敗',
					`紀錄刪除失敗(班次="${resultObj.id}")`,
					'error'
				);
			}
		}
	});
}


