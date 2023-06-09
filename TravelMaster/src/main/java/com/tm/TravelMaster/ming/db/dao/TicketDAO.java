package com.tm.TravelMaster.ming.db.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tm.TravelMaster.ming.model.TicketInfo;


public interface TicketDAO extends JpaRepository<TicketInfo, Integer>{

//	public TicketInfo getTicketInfoById(int ticketID);
//	public List<TicketInfo> getAllTicketInfo();
//	public boolean insertTicketInfo(TicketInfo ticket);
//	public boolean insertTicketInfo(Session session, TicketInfo ticket);
//	public boolean updateTicketInfo(TicketInfo ticket);
//	public boolean deleteTicketInfo(int ticketID);
	
}
