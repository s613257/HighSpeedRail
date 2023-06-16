package com.tm.TravelMaster.ming.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.tm.TravelMaster.ming.db.service.HighSpeedRailService;

@Controller
@RequestMapping("/select")
public class SelectController {

	@Autowired
	private HighSpeedRailService highSpeedRailService;

	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("stationList", highSpeedRailService.findAllStationInfo());
		model.addAttribute("priceInfos", GetAllPriceInfo());
		return "ming/SelectPage";
	}

	private String GetAllPriceInfo() {
		String json = new Gson().toJson(highSpeedRailService.findAllPriceInfo());
		return json;
	}

}
