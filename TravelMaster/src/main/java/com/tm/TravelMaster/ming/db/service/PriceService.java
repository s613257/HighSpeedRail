package com.tm.TravelMaster.ming.db.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tm.TravelMaster.ming.db.repos.PriceInfoRepository;
import com.tm.TravelMaster.ming.model.PriceInfo;

@Service
public class PriceService {
	
	@Autowired
	private PriceInfoRepository priceDao;
	
	public List<PriceInfo> findAllPriceInfo(){
		return priceDao.findAll();
	}

}
