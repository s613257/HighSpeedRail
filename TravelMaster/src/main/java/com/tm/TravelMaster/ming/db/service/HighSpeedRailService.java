package com.tm.TravelMaster.ming.db.service;

import java.util.List;

import com.tm.TravelMaster.ming.model.HighSpeedRailTicket;

public interface HighSpeedRailService {
	public HighSpeedRailTicket getBookingTkById(int ticketID);
	public List<HighSpeedRailTicket> getAllBookingTk();
}
