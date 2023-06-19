package com.tm.TravelMaster.ming.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tm.TravelMaster.ming.db.service.HighSpeedRailService;
import com.tm.TravelMaster.ming.model.TranInfo;

@Controller
public class TrainMaintainController {
	
	@Autowired
	private HighSpeedRailService highSpeedRailService;
	
	@GetMapping("trainMaintain")
	public String index(Model model) {
		List<TranInfo> list  = highSpeedRailService.findAllTranInfo();
		
		Map<String, String> trainInfoMap = new HashMap<>();  // [102,1 -> 12:00],[102,2 -> 12:15]
		for(TranInfo info : list) {
			String key = info.getTranNo() + "," + info.getStationID();
			String value = info.getTrainArrvialTime();
			trainInfoMap.put(key, value);
		}
		
		Set<String> trainNoSet = new HashSet<>();
		for(TranInfo info : list) {
			trainNoSet.add(info.getTranNo());
		}
		model.addAttribute("trainInfoMap", trainInfoMap);
		model.addAttribute("trainNoSet", trainNoSet);
		model.addAttribute("list", highSpeedRailService.findAllTranInfo());
		model.addAttribute("stationList", highSpeedRailService.findAllStationInfo());
		return "ming/TrainMaintain";
	}

}
