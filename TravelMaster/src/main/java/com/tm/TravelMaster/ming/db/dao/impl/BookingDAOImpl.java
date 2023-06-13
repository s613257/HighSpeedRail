package com.tm.TravelMaster.ming.db.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tm.TravelMaster.ming.db.dao.BookingDAO;
import com.tm.TravelMaster.ming.model.PriceInfo;
import com.tm.TravelMaster.ming.model.TicketInfo;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public class BookingDAOImpl {
	

	@Autowired
	private SessionFactory factory;

	@Override
	public List<TicketInfo> getAllTranInfo() {
		return getAllTranInfoBySql("select "
				+ "TicketID = 0, "
				+ "BookingDate = getdate(), "
				+ "price = 0, "
				+ "Seat = 0, "
				+ "DepartureDate =  getdate(), "
				+ "dep_st.TranNo TranNo, "
				+ "dep_st.StationID DepartureST,"
				+ "dep_st.TrainArrivalTime Departuretime, "
				+ "des_st.StationID DestinationST,"
				+ "des_st.TrainArrivalTime Arrivaltime "
				+ "from TranInfo dep_st " 
				+ "left join TranInfo des_st on dep_st.TranNo = des_st.TranNo and dep_st.TrainArrivalTime <> des_st.TrainArrivalTime "
				+ "and des_st.TrainArrivalTime > dep_st.TrainArrivalTime "
				+ "where des_st.StationID is not null;");
	}
	
	private List<TicketInfo> getAllTranInfoBySql(String sql) {
		Session session = factory.openSession();
		List<TicketInfo> resultTranList = new ArrayList<TicketInfo>();
		Query<TicketInfo> query = session.createQuery(sql,TicketInfo.class);
		resultTranList= query.list();
		return resultTranList;
	}
	


}
