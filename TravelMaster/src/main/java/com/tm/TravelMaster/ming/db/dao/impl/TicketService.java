package com.tm.TravelMaster.ming.db.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tm.TravelMaster.ming.db.dao.TicketDAO;
import com.tm.TravelMaster.ming.model.TicketInfo;


@Service
public class TicketService {

	@Autowired
	private TicketDAO ticketDao;

	public void insertTicketInfo(TicketInfo ticket) {
		ticketDao.save(ticket);
	}

	public TicketInfo findTicketInfoById(int ticketID) {
		Optional<TicketInfo> optional = ticketDao.findById(ticketID);

		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public List<TicketInfo> findAllTicketInfo(){
		return ticketDao.findAll();
	}

	@Transactional
	public TicketInfo updateTicketInfo(TicketInfo tickets) {
		Optional<TicketInfo> optional = ticketDao.findById(tickets.getTicketID());
		
		if(optional.isPresent()) {
			TicketInfo ticket = optional.get();
			ticket.setTranNo(tickets.getTranNo());
			ticket.setSeat(tickets.getSeat());
			ticket.setDepartureST(tickets.getDepartureST());
			ticket.setDestinationST(tickets.getDestinationST());
			ticket.setDeparturedate(tickets.getDeparturedate());
			ticket.setDeparturetime(tickets.getDeparturetime());
			ticket.setArrivaltime(tickets.getArrivaltime());
			ticket.setPrice(tickets.getPrice());
			ticket.setBookingdate(tickets.getBookingdate());
			System.out.println("update data");
			return tickets;
			
		}
		System.out.println("no update data");
		return null;
	}

	public boolean deleteTickerInfoById(int ticketID) {
	    if (ticketID > 0) {
	        ticketDao.deleteById(ticketID);
	        System.out.println("delete data");
	        return true;
	    }
	    return false;
	}

}
