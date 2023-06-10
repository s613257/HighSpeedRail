package com.tm.TravelMaster.ming.db.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tm.TravelMaster.ming.model.TranInfo;

public interface TranDAO extends JpaRepository<TranInfo, Integer>{

}
