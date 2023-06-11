var priceInfos = new Map();
		let tmpS;
		<c:forEach var="pk" items="${priceInfos.entrySet()}">
		tmpS = new Set();
		tmpS.add(${pk.getKey().toArray()[0]});
		tmpS.add(${pk.getKey().toArray()[1]});
		priceInfos.set(tmpS, ${pk.getValue()})
		</c:forEach>
		function showPrice() {
			let departureST = document.querySelector("#DepartureST");
			let destinationST = document.querySelector("#DestinationST");
			let price = document.querySelector("#price");
			if (parseInt(departureST.value) == parseInt(destinationST.value)) {
				price.value = 0;
				return;
			}
			priceInfos.forEach((value, key, map) => {
				if (key.has(parseInt(departureST.value)) && key.has(parseInt(destinationST.value))) {
					price.value = value;
					return;
				}
			});
		}