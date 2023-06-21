package com.tm.TravelMaster.ming.db.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tm.TravelMaster.ming.model.TranInfo;

public interface TrainInfoRepository extends JpaRepository<TranInfo, Integer>{
	
	@Modifying
	@Query(value="delete from TranInfo where tranNo=:tranNo", nativeQuery = true)
	public void deleteByTranNo(@Param("tranNo") String tranNo);

	@Modifying
	@Query(value="update TranInfo set trainArrvialTime=:trainArrvialTime"
			+ "where tranNo=:tranNo AND stationID=:stationID", nativeQuery = true)
	public void updateByTranNo(
			@Param("tranNo") String tranNo ,
			@Param("stationID") String stationID, 
			@Param("trainArrvialTime") String trainArrvialTime);
}
