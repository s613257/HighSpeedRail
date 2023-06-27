package com.tm.TravelMaster.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping("/layout/indexNavbar")
	public String dashbord() {
		return "layout/index";
	}

	// 會員中心
	@GetMapping("/layout/memberCenter")
	public String getMemberCenter() {
		return "layout/memberCenter";
	}
}
