package com.tm.TravelMaster.ming.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.tm.TravelMaster.ming.model.dto.HighSpeedRailTicket;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity @Table(name = "TicketInfo")
@Data 
public class TicketInfo {
	
	@Id @Column(name = "TicketID")	
	private int ticketID;
	
	@Column(name = "TranNo")
	private String tranNo;
	
	@Column(name = "Seat")
	private String seat;
	
	@Column(name = "DepartureST")
	private String departureST;
	
	@Column(name = "DestinationST")
	private String destinationST;
	
	@Column(name = "DepartureDate")
	private String departuredate;
	
	@Column(name = "DepartureTime")
	private String departuretime;
	
	@Column(name = "ArrivalTime")
	private String arrivaltime;
	
	@Column(name = "Price")
	private int price;
	
	@Column(name = "BookingDate")
	private String bookingdate;
	
	public TicketInfo() {
	}

	public TicketInfo(HighSpeedRailTicket bkdto) {
		setTicketID(bkdto.getTicketID());
		setTranNo(bkdto.getTranNo());
		setSeat(bkdto.getSeat());
		setDepartureST(bkdto.getDepartureST());
		setDestinationST(bkdto.getDestinationST());
		setDeparturedate(bkdto.getDeparturedate());
		setDeparturetime(bkdto.getDeparturetime());
		setArrivaltime(bkdto.getArrivaltime());
		setPrice(bkdto.getPrice());
		setBookingdate(bkdto.getBookingdate());
	}

	@Override
	public String toString() {
		return "TicketDTO [ticketID=" + ticketID + ", tranNo=" + tranNo + ", seat=" + seat + ", DepartureST="
				+ departureST + ", DestinationST=" + destinationST + ",departuredate="+ departuredate +", Departuretime=" + departuretime
				+ ", Arrivaltime=" + arrivaltime + ", price=" + price + ", bookingdate=" + bookingdate + "]";
	}

	public String getBookingdate() {
		return bookingdate;
	}
	
	public String getDeparturedate() {
		return departuredate;
	}

	public void setBookingdate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		bookingdate = sdf.format(date);
	}
	public void setBookingdate(String date) {
		bookingdate = date;
	}
	public void setDeparturedate(Date depdate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		departuredate = sdf.format(depdate);
	}
	public void setDeparturedate(String depdate) {
		departuredate = depdate;
	}

}
