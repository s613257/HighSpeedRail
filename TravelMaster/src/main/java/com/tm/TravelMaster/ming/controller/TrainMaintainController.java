package com.tm.TravelMaster.ming.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.google.gson.Gson;
import com.tm.TravelMaster.ming.db.service.HighSpeedRailService;
import com.tm.TravelMaster.ming.model.StationInfo;
import com.tm.TravelMaster.ming.model.TranInfo;

@Controller
public class TrainMaintainController {

	@Autowired
	private HighSpeedRailService highSpeedRailService;

	@GetMapping("trainMaintain")
	public String index(Model model) {
		List<TranInfo> list = highSpeedRailService.findAllTranInfo();

		Map<String, String> trainInfoMap = new HashMap<>(); // [102,1 -> 12:00],[102,2 -> 12:15]
		for (TranInfo info : list) {
			String key = info.getTranNo() + "," + info.getStationID();
			String value = info.getTrainArrvialTime();
			trainInfoMap.put(key, value);
		}

		Set<String> trainNoSet = new HashSet<>();
		for (TranInfo info : list) {
			trainNoSet.add(info.getTranNo());
		}

		List<StationInfo> stationList = highSpeedRailService.findAllStationInfo();

		// --- ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
		List<List<String>> dataTableContentList = new ArrayList<List<String>>();

		for (Object trainNo : trainNoSet.toArray()) {
			List<String> dataLst = new ArrayList<String>();
			dataLst.add((String) trainNo);
			for (StationInfo stInfo : stationList) {
				String key = (String) trainNo + "," + stInfo.getStationID();
				dataLst.add(trainInfoMap.get(key));
			}
			String update = "<button class=\"btn btn-light\" onclick=\"updateTarget(" + trainNo
					+ ")\"><i class=\"fa-solid fa-pen-to-square\"></i> </button>";
			dataLst.add(update);
			String delete = "<button class=\"btn btn-light\" onclick=\"deleteTarget(" + trainNo
					+ ")\"><i class=\"fa-solid fa-trash-can\"></i> </button>";
			dataLst.add(delete);
			dataTableContentList.add(dataLst);
		}

		String dataTableContent = new Gson().toJson(dataTableContentList);
		model.addAttribute("dataTableContent", dataTableContent);
		// --- ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
		model.addAttribute("trainNoSet", trainNoSet);
		model.addAttribute("stationList", stationList);
		return "ming/TrainMaintain";
	}

}