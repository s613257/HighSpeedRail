package com.tm.TravelMaster.ming.db.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tm.TravelMaster.ming.db.dao.BookingDAO;
import com.tm.TravelMaster.ming.db.service.HighSpeedRailService;
import com.tm.TravelMaster.ming.db.service.TicketService;
import com.tm.TravelMaster.ming.model.HighSpeedRailTicket;
import com.tm.TravelMaster.ming.model.StationInfo;
import com.tm.TravelMaster.ming.model.TicketInfo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class HighSpeedRailServiceImpl  implements HighSpeedRailService {

	@Autowired
	@Qualifier("bookingDAOImpl")
	private BookingDAO bookingDao;

	@Autowired
	private TicketService ticketDao;

	public HighSpeedRailServiceImpl() {

	}

	@Override
	public HighSpeedRailTicket getBookingTkById(int ticketID) {
		List<StationInfo> stationInfos = bookingDao.getAllStationInfo();
		Map<Integer, String> stationMap = new HashMap<Integer, String>();
		for (StationInfo stationInfo : stationInfos) {
			stationMap.put(stationInfo.getStationID(), stationInfo.getStationName());
		}

		TicketInfo ticketInfo = ticketDao.findTicketInfoById(ticketID);
		HighSpeedRailTicket result = new HighSpeedRailTicket(ticketInfo);
		result.setDepartureST(stationMap.get(Integer.parseInt(ticketInfo.getDepartureST())));
		result.setDestinationST(stationMap.get(Integer.parseInt(ticketInfo.getDestinationST())));
		return result;
	}

	@Override
	public List<HighSpeedRailTicket> getAllBookingTk() {

		List<TicketInfo> ticketInfoLst = ticketDao.findAllTicketInfo();

		List<StationInfo> stationInfos = bookingDao.getAllStationInfo();
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

}
