package com.tm.TravelMaster.ming.db.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tm.TravelMaster.ming.model.PriceInfo;

public interface PriceDAO  extends JpaRepository<PriceInfo, Integer>{

}
