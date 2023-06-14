var priceInfos = new Map();
var basePath = window.location.protocol + "//" + window.location.hostname + ":" + location.port + '/TM';
let tmpS;

$(document).ready(function() {
	if (priceInfos.size == 0) {
		let priceInfosObj = JSON.parse(document.querySelector("#priceInfos").value);
		priceInfosObj.forEach((element) => {
			tmpS = new Set();
			tmpS.add(element.pk.departureST); // departureST
			tmpS.add(element.pk.destinationST); // destinationST
			priceInfos.set(tmpS, element.price);
		});
	}

});

function showPrice() {
	let departureST = document.querySelector("#departureST");
	let destinationST = document.querySelector("#destinationST");
	let price = document.querySelector("#price");

	if (parseInt(departureST.value) == parseInt(destinationST.value)) {
		price.value = 0;
		return;
	}

	priceInfos.forEach((value, key) => {
		if (key.has(departureST.value) && key.has(destinationST.value)) {
			price.value = value;
			return;
		}
	});
}


function search() {
	let departureST = document.querySelector("#departureST");
	let destinationST = document.querySelector("#destinationST");
	let typeOfTicket = document.querySelector("#typeOfTicket");
	let departuredate = document.querySelector("#departuredate");
	let departureTime = document.querySelector("#departureTime");
	if (parseInt(departureST.value) == parseInt(destinationST.value)) {
		alert("起程站 與 到達站 相同")
		return;
	}
	if (departuredate.value == "") {
		alert("請選擇出發日期")
		return;
	}
	if (departureTime.value == "") {
		alert("請選擇出發時刻")
		return;
	}

	let queryResult = document.querySelector("#queryResult");
	let from_st = document.querySelector("#from_st");
	let to_st = document.querySelector("#to_st");
	let dep_date = document.querySelector("#dep_date");
	from_st.innerHTML = departureST.options[departureST.selectedIndex].text;
	to_st.innerHTML = destinationST.options[destinationST.selectedIndex].text;
	dep_date.innerHTML = departuredate.value;

	let body = {
	"departureST":"${departureST.value}",
	"destinationST":"${destinationST.value}",
	"departureTime":"${departureTime.value}"
	}

	fetch("services/GetTranInfo", {
		method: "POST",
		headers: {
			"Content-type": "application/json;charset=UTF-8"
		},
		body: JSON.stringify(body)
	})
		.then(response => {
			if (response.ok) {
				return response.json();
			} else {
				throw new Error("Error: " + response.status);
			}
		})
		.then(data => {
			placeQueryContent(data);
			queryResult.style.display = "";
		})
		.catch(error => {
			console.error(error);
		});
}

function placeQueryContent(tranInfos) {
	let queryContent = document.querySelector("#queryContent");
	queryContent.innerHTML = '';
	tranInfos.forEach(function(tranInfo) {
		let infoRow = document.createElement("tr");
		let tranNo = document.createElement("td");
		tranNo.innerHTML = tranInfo.tranNo;
		let dep_time = document.createElement("td");
		dep_time.innerHTML = tranInfo.departureTime;
		let arr_time = document.createElement("td");
		arr_time.innerHTML = tranInfo.arrivaltime;
		let diff_time = document.createElement("td");
		let arrTime = arr_time.innerHTML.split(":");
		arrTime = parseInt(arrTime[0]) * 60 + parseInt(arrTime[1]);
		console.log(arrTime);
		let depTime = dep_time.innerHTML.split(":");
		depTime = parseInt(depTime[0]) * 60 + parseInt(depTime[1]);
		let timeDiff = Math.abs(depTime - arrTime);
		diff_time.innerHTML = parseInt(timeDiff / 60) + ":" + timeDiff % 60;
		//diff_time.innerHTML = arr_time.value - dep_time.value
		//diff_time.innerHTML = Math.abs(arrTime.toDateString() - depTime.toDateString());

		let booking = document.createElement("td");
		let booking_btn = document.createElement("button");
		booking_btn.innerHTML = "訂票";


		booking.appendChild(booking_btn);
		booking_btn.classList.add("btn");
		booking_btn.classList.add("btn-secondary");
		booking_btn.onclick = function() { };

		infoRow.appendChild(tranNo);
		infoRow.appendChild(dep_time);
		infoRow.appendChild(arr_time);
		infoRow.appendChild(diff_time);
		infoRow.appendChild(booking);

		queryContent.appendChild(infoRow);
	})
}
