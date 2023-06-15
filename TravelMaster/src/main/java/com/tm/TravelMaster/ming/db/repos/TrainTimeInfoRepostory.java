package com.tm.TravelMaster.ming.db.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tm.TravelMaster.ming.model.TrainTimeInfo;

public interface TrainTimeInfoRepostory extends JpaRepository<TrainTimeInfo, Integer> {

	@Query(value = "select ROW_NUMBER() OVER(ORDER BY CONVERT( TIME, dep_st.TrainArrivalTime ) ASC) AS ID, "
			+ "dep_st.TranNo TranNo, "
			+ "dep_st.StationID DepartureST, "
			+ "dep_st.TrainArrivalTime Departuretime, "
			+ "des_st.StationID DestinationST, "
			+ "des_st.TrainArrivalTime Arrivaltime "
			+ "from TranInfo dep_st "
			+ "left join TranInfo des_st "
				+ "on dep_st.TranNo = des_st.TranNo "
				+ "and dep_st.TrainArrivalTime <> des_st.TrainArrivalTime "
				+ "and des_st.TrainArrivalTime > dep_st.TrainArrivalTime "
			+ "where des_st.StationID is not null;", nativeQuery = true)
	public List<TrainTimeInfo> getTrainTimeInfo();
}