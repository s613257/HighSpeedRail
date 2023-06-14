package com.tm.TravelMaster.ming.db.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tm.TravelMaster.ming.db.repos.PriceInfoRepository;
import com.tm.TravelMaster.ming.db.repos.StationInfoRepository;
import com.tm.TravelMaster.ming.db.repos.TrainInfoRepository;
import com.tm.TravelMaster.ming.db.repos.TrainTimeInfoRepostory;
import com.tm.TravelMaster.ming.model.HighSpeedRailTicket;
import com.tm.TravelMaster.ming.model.PriceInfo;
import com.tm.TravelMaster.ming.model.StationInfo;
import com.tm.TravelMaster.ming.model.TicketInfo;
import com.tm.TravelMaster.ming.model.TrainTimeInfo;
import com.tm.TravelMaster.ming.model.TranInfo;

@Service
public class HighSpeedRailService {
	
	@Autowired
	private TicketInfoService ticketService;
	
	@Autowired
	private StationInfoRepository stationInfoRepos;
	
	@Autowired
	private TrainInfoRepository tranInfoRepos;
	
	@Autowired
	private PriceInfoRepository priceInfoRepos;
	
	@Autowired
	private TrainTimeInfoRepostory trainTimeInfoRepostory;
	
	public List<StationInfo> findAllStationInfo(){
		return stationInfoRepos.findAll();
	}
	
	public List<TranInfo> findAllTranInfo(){
		return tranInfoRepos.findAll();
	}
	
	public List<PriceInfo> findAllPriceInfo(){
		return priceInfoRepos.findAll();
	}
	
	//查詢時刻表
	public List<TrainTimeInfo> getAllTrainTimeInfo() {
		
		return trainTimeInfoRepostory.getTrainTimeInfo();
	}
	
	//查詢單筆訂票資訊
	public HighSpeedRailTicket getBookingTkById(int ticketID) {
		List<StationInfo> stationInfos = findAllStationInfo();
		Map<Integer, String> stationMap = new HashMap<Integer, String>();
		for (StationInfo stationInfo : stationInfos) {
			stationMap.put(stationInfo.getStationID(), stationInfo.getStationName());
		}

		TicketInfo ticketInfo = ticketService.findTicketInfoById(ticketID);
		HighSpeedRailTicket result = new HighSpeedRailTicket(ticketInfo);
		result.setDepartureST(stationMap.get(Integer.parseInt(ticketInfo.getDepartureST())));
		result.setDestinationST(stationMap.get(Integer.parseInt(ticketInfo.getDestinationST())));
		return result;
	}

	//查詢所有訂票資訊
	public List<HighSpeedRailTicket> getAllBookingTk() {

		List<TicketInfo> ticketInfoLst = ticketService.findAllTicketInfo();

		List<StationInfo> stationInfos = findAllStationInfo();
		Map<Integer, String> stationMap = new HashMap<Integer, String>();
		for (StationInfo stationInfo : stationInfos) {
			stationMap.put(stationInfo.getStationID(), stationInfo.getStationName());
		}

		List<HighSpeedRailTicket> result = new ArrayList<>();
		for (TicketInfo ticketInfo : ticketInfoLst) {
			HighSpeedRailTicket bookingTk = new HighSpeedRailTicket(ticketInfo);
			bookingTk.setDepartureST(stationMap.get(Integer.parseInt(ticketInfo.getDepartureST())));
			bookingTk.setDestinationST(stationMap.get(Integer.parseInt(ticketInfo.getDestinationST())));
			result.add(bookingTk);
		}
		return result;

	}
	
	//依據出發站抵達站show出相對應票價
	public Map<Set<String> , Integer> getAllPriceInfo() {
		List<PriceInfo> priceList = findAllPriceInfo();
		Map<Set<String> , Integer> result = new HashMap<Set<String> , Integer>();
		for(PriceInfo price: priceList) {
			Set<String> tmpS = new HashSet<String>();
			tmpS.add(price.getDepartureST());
			tmpS.add(price.getDestinationST());
			result.put(tmpS, price.getPrice());
		}		
		return result;
	}

}
