package com.tm.TravelMaster.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashbordController {

	@GetMapping("/Choose")
	public String dashbord() {
		return "ming/ChoosePage";
	}
}
