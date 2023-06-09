package com.tm.TravelMaster.ming.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

public class TranInfo {
	
	private String tranNo;
	private int stationID;
	private String trainArrvialTime;

	public TranInfo() {
	}

	public TranInfo(ResultSet rs) throws SQLException {
		this.tranNo = rs.getString("tranNo");
		this.stationID = rs.getInt("StationID");
		this.trainArrvialTime = rs.getString("tranArrvialTime");
	}

	@Override
	public String toString() {
		return "TranInfoDTO [TranNo=" + tranNo + ", StationID=" + stationID + ", TrainArrivalTime=" + trainArrvialTime + "]";
	}

	public String getTranNo() {
		return tranNo;
	}

	public void setTranNo(String tranNo) {
		this.tranNo = tranNo;
	}

	public int getStationID() {
		return stationID;
	}

	public void setStationID(int stationID) {
		this.stationID = stationID;
	}

	public String getTrainArrvialTime() {
		return trainArrvialTime;
	}

	public void setTrainArrvialTime(String trainArrvialTime) {
		this.trainArrvialTime = trainArrvialTime;
	}

}
