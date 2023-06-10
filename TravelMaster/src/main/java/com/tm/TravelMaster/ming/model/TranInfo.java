package com.tm.TravelMaster.ming.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity @Table(name = "TranInfo")
@Data
public class TranInfo {
	
	@Id @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "TranNo")
	private String tranNo;
	
	@Column(name = "StationID")
	private int stationID;
	
	@Column(name = "TrainArrivalTime")
	private String trainArrvialTime;


	public TranInfo(ResultSet rs) throws SQLException {
		this.tranNo = rs.getString("tranNo");
		this.stationID = rs.getInt("StationID");
		this.trainArrvialTime = rs.getString("tranArrvialTime");
	}

	@Override
	public String toString() {
		return "TranInfoDTO [TranNo=" + tranNo + ", StationID=" + stationID + ", TrainArrivalTime=" + trainArrvialTime + "]";
	}

}
