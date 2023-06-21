var EditField;
var dtDataList;
var ACTION_STATE = 0;
$(document).ready(function() {
	dtDataList = JSON.parse(document.querySelector("#dataTableContent").value);
	$('#queryResult').DataTable({ data: dtDataList });
	EditField = new bootstrap.Modal(document.getElementById('EditField'));
});

function insertRecord() {
	EditField.show();
	document.querySelector("#EditFieldSubmit").innerHTML = "新增";
	document.querySelector("#EditFieldTitle").innerHTML = `<i class="fa-solid fa-pen-to-square"></i>&nbsp;新增`;
	ACTION_STATE = 1;
}
function updateTarget(tranNo) {
	document.querySelector("#EditFieldSubmit").innerHTML = "修改";
	document.querySelector("#EditFieldTitle").innerHTML = `<i class="fa-solid fa-pen-to-square"></i>&nbsp;修改`;
	document.querySelector("#EditField_tranNo").readOnly = true;
	document.querySelector("#EditField_tranNo").value = tranNo;
	let timeLst;
	dtDataList.forEach((dl) => { // dtDataList: [[tranNo1,t1a,t1b,...],[tranNo2,t2a,t2b,...],[tranNo2,t2a,t2b,...]]
		// dl: [tranNo,t1,t1,...]
		if (dl[0] == tranNo) { // dl[0] => tranNo
			timeLst = dl;
		}
	});
	let StLst = [[${ stationList }]];
	let current_st = 1;
	StLst.forEach((st) => {
		document.querySelector("#EditField_ST_" + st.stationID).value = timeLst[current_st];
		current_st++;
	});
	EditField.show();
	ACTION_STATE = 2;
}

function cancel() {
	EditField.hide();
	document.querySelector("#EditField_tranNo").readOnly = false;
	ACTION_STATE = 0;
}

function formSubmit() {

	let StLst = [[${ stationList }]];
	let parmLst = "ACTION_STATE,tranNo,StLst".split(",");
	parmLst.push(ACTION_STATE); // parmLst[0] : ACTION => "1:新增 ; 2:修改"
	parmLst.push(document.querySelector("#EditField_tranNo").value); // parmLst[1] : tranNo
	StLst.forEach((st) => {
		parmLst.push(document.querySelector("#EditField_ST_" + st.stationID).value);
	});

	let submitForm = document.createElement("form");
	submitForm.method = "POST";
	submitForm.action = ``;
	var tmpInput = document.createElement("input");
	tmpInput.setAttribute("name", "parmLst");
	tmpInput.setAttribute("value", JSON.stringify(parmLst));  // parmLst: [ACTION, tranNo, t1, t1,...]
	submitForm.appendChild(tmpInput);
	document.body.appendChild(submitForm);
	submitForm.submit();
}

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
				).then(() => {
					// 成功刪除後重新導向
					location.href = 'trainMaintain';
				});
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