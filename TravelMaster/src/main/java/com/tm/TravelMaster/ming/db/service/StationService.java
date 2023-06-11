package com.tm.TravelMaster.ming.db.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tm.TravelMaster.ming.db.repos.StationInfoRepository;
import com.tm.TravelMaster.ming.model.StationInfo;

@Service
public class StationService {
	
	@Autowired
	private StationInfoRepository stationDao;
	
	public List<StationInfo> findAllStationInfo(){
		return stationDao.findAll();
	}

}
