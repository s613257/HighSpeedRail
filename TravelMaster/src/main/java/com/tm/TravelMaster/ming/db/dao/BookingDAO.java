package com.tm.TravelMaster.ming.db.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tm.TravelMaster.ming.model.PriceInfo;
import com.tm.TravelMaster.ming.model.StationInfo;
import com.tm.TravelMaster.ming.model.TicketInfo;


public interface BookingDAO {
	public List<TicketInfo> getAllTranInfo();
	public List<StationInfo> getAllStationInfo();
	public Map<Set<String> , Integer> getAllPriceInfo();
	public List<PriceInfo> getInfoByPrice();
}
