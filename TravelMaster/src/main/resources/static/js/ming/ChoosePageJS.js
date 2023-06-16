function createBtn(content) {
	let td = document.createElement("td");
	let input_btn = document.createElement("input");
	td.appendChild(input_btn);
	input_btn.id = content;
	input_btn.name = "seat";
	input_btn.type = "checkbox";
	input_btn.classList.add("btn-check");
	input_btn.autocomplete = "off";
	input_btn.onchange = show;
	

	let input_btn_lbl = document.createElement("label");
	td.appendChild(input_btn_lbl);
	input_btn_lbl.classList.add("btn");
	input_btn_lbl.classList.add("btn-secondary");
	
	input_btn.onclick = ()=>{
		if(input_btn.checked){
			input_btn_lbl.classList.remove("btn-secondary");
			input_btn_lbl.classList.add("btn-dark");
			
		}else{
			input_btn_lbl.classList.remove("btn-dark");
			input_btn_lbl.classList.add("btn-secondary");
		}
	};
	
	input_btn_lbl.setAttribute("for", content);
	input_btn_lbl.innerHTML = content;
	return td;
}

function initSeats() {
	const rowNum = 10;
	let seatChar = ['A', 'B', 'C', 'D', 'E'];
	let seats_container = document.getElementById("seats_container");
	let tr = document.createElement("tr");
	let cnt = 0;
	for (let i = 0; i < seatChar.length + 1; i++) {
		if (i == 3) {
			let sideWay = document.createElement("td");
			sideWay.innerHTML = "走道";
			sideWay.classList.add("align-middle");
			sideWay.rowSpan = rowNum;
			sideWay.style = "text-align:center; width:55px";
			tr.appendChild(sideWay);
			continue;
		}
		tr.appendChild(createBtn("01" + seatChar[cnt]));
		cnt++;
	}
	seats_container.appendChild(tr);
	for (let i = 2; i <= rowNum; i++) {
		let trTmp = document.createElement("tr");
		for (let j = 0; j < seatChar.length; j++) {
			trTmp.appendChild(createBtn(i.toString().padStart(2, '0')
				+ seatChar[j]));
		}
		seats_container.appendChild(trTmp);
	}
}
function show() {
	let selectedSeats = document.getElementsByName("seat");
	let p = document.getElementById("selectedSeats");
	p.innerHTML = "&nbsp;";
	let p_innerHTML = "";
	let selectedCnt = 0;
	for (let i = 0; i < selectedSeats.length; i++) {
		if (selectedSeats[i].checked == true) {
			selectedCnt++;
			p_innerHTML = p_innerHTML + " [ " + selectedSeats[i].id
				+ " ] ";
		}
	}
	if (selectedCnt > 0) {
		p.innerHTML = p_innerHTML; // &nbsp;
	}
	let priceTotal = document.querySelector("#singlePrice").value * selectedCnt;
	document.querySelector("#priceTotal").innerHTML = priceTotal + "&nbsp;元";
}

$(function() {
	$('#submit').on("click", function() {
		Swal.fire('您的訂票已完成!', '請至會員專區查閱電子票券', 'success'); 
	})
})