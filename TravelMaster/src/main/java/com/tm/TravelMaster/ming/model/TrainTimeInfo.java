package com.tm.TravelMaster.ming.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "TranInfo")
@Data
public class TrainTimeInfo {
	
	public TrainTimeInfo() {
	}
	
	@Id @Column(name = "ID")	
	private int id;
	
	@Column(name = "TranNo")
	private String tranNo;
	
	@Column(name = "DepartureST")
	private String departureST;
	
	@Column(name = "DestinationST")
	private String destinationST;
	
	@Column(name = "DepartureTime")
	private String departureTime;
	
	@Column(name = "ArrivalTime")
	private String arrivalTime;


}
