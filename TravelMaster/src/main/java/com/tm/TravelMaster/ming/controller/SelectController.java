package com.tm.TravelMaster.ming.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tm.TravelMaster.ming.db.service.HighSpeedRailService;
import com.tm.TravelMaster.ming.model.PriceInfo;
import com.tm.TravelMaster.ming.model.StationInfo;

@Controller
@RequestMapping("/select")
public class SelectController {

	@Autowired
	private HighSpeedRailService highSpeedRailService;

	@GetMapping("")
	public String index(Model model) {
		List<StationInfo> stationList = highSpeedRailService.findAllStationInfo();
		List<PriceInfo> priceInfos = highSpeedRailService.findAllPriceInfo();
		model.addAttribute("stationList", stationList);
		model.addAttribute("priceInfos", priceInfos);
		return "SelectPage";
	}

}
