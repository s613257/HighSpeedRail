package com.tm.TravelMaster.ming.db.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tm.TravelMaster.ming.db.dao.TranDAO;
import com.tm.TravelMaster.ming.model.TranInfo;

@Service
public class TranService {
	
	@Autowired
	private TranDAO tranDao;
	
	public List<TranInfo> findAllTranInfo(){
		return tranDao.findAll();
	}
}
