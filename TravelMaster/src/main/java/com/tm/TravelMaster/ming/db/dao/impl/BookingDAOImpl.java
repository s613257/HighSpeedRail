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
import com.tm.TravelMaster.ming.model.StationInfo;
import com.tm.TravelMaster.ming.model.TicketInfo;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public class BookingDAOImpl  implements BookingDAO {
	

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
	
	@Override
	public List<StationInfo> getAllStationInfo() {
		Session session = factory.openSession();
		Query<StationInfo> query = session.createQuery("from StationInfo", StationInfo.class);
		return query.list();
	}
	
	
	@Override
	public Map<Set<String> , Integer> getAllPriceInfo() {
		List<PriceInfo> priceList = getInfoByPrice();
		Map<Set<String> , Integer> result = new HashMap<Set<String> , Integer>();
		for(PriceInfo price: priceList) {
			Set<String> tmpS = new HashSet<String>();
			tmpS.add(price.getDepartureST());
			tmpS.add(price.getDestinationST());
			result.put(tmpS, price.getPrice());
		}		
		return result;
	}

	private List<TicketInfo> getAllTranInfoBySql(String sql) {
		Session session = factory.openSession();
		Query<TicketInfo> query = session.createQuery("from TicketInfo",TicketInfo.class);
		return  query.list();
	}

	
	@Override
	public List<PriceInfo> getInfoByPrice() {
		Session session = factory.openSession();
		Query<PriceInfo> query = session.createQuery("from PriceInfo",PriceInfo.class);
		return query.list();
	}

}
