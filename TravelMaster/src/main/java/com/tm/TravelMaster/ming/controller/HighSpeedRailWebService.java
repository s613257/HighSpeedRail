package com.tm.TravelMaster.ming.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.tm.TravelMaster.ming.db.dao.BookingDAO;
import com.tm.TravelMaster.ming.db.service.HighSpeedRailService;
import com.tm.TravelMaster.ming.db.service.TicketInfoService;
import com.tm.TravelMaster.ming.model.HighSpeedRailTicket;
import com.tm.TravelMaster.ming.model.TicketInfo;


@Controller
@RequestMapping("/services")
public class HighSpeedRailWebService {
	
	@Autowired
	@Qualifier("bookingDAOImpl")
	private BookingDAO bookingDAO;

	@Autowired
	private TicketInfoService ticketDAO;
	
	@Autowired
	@Qualifier("highSpeedRailServiceImpl")
	private HighSpeedRailService ticketService;
	
    @GetMapping("/GetTranInfo")
    @ResponseBody
    public String GetTranInfo(
            @RequestParam("departure_ST") String departureST,
            @RequestParam("destination_ST") String destinationST,
            @RequestParam("departure_time") String departureTime) {
        try {
            List<TicketInfo> tranInfos = bookingDAO.getAllTranInfo();
            List<TicketInfo> tranTimeLst = new ArrayList<TicketInfo>();
            
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
            
            Date departureTimeObj = sdf.parse(departureTime);
            for (TicketInfo tranInfo : tranInfos) {
                if (tranInfo.getDepartureST().equals(departureST) && 
                        tranInfo.getDestinationST().equals(destinationST)) {
                    Date departureStTime = sdf.parse(tranInfo.getDeparturetime());
                    if (departureTimeObj.compareTo(departureStTime) < 0) {
                        tranTimeLst.add(tranInfo);
                    }
                }
            }
            
            String json = new Gson().toJson(tranTimeLst);
            return json;
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    @GetMapping("/GetAllTicketInfo")
    @ResponseBody
    public String GetAllTicketInfo() {
        List<HighSpeedRailTicket> tks = ticketService.getAllBookingTk();
        Map<String, List<List<String>>> inputMap = new HashMap<String, List<List<String>>>();
        List<List<String>> dataList = new ArrayList<List<String>>();
        
        for (HighSpeedRailTicket tk : tks) {
            List<String> tkDataLst = new ArrayList<String>();
            tkDataLst.add(Integer.toString(tk.getTicketID()));
            tkDataLst.add(tk.getTranNo());
            tkDataLst.add(tk.getSeat());
            tkDataLst.add(tk.getDepartureST());
            tkDataLst.add(tk.getDestinationST());
            tkDataLst.add(tk.getDeparturedate());
            tkDataLst.add(tk.getDeparturetime());
            tkDataLst.add(tk.getArrivaltime());
            tkDataLst.add(Integer.toString(tk.getPrice()));
            tkDataLst.add(tk.getBookingdate());
            tkDataLst.add("<button class=\"btn btn-light\" onclick=\"updateTarget(" + tk.getTicketID() + ")\"><i class=\"fa-solid fa-pen-to-square\"></i> </button>");
            tkDataLst.add("<button class=\"btn btn-light\" onclick=\"deleteTarget(" + tk.getTicketID() + ")\"><i class=\"fa-solid fa-trash-can\"></i> </button>");
            dataList.add(tkDataLst);
        }
        inputMap.put("data", dataList);
        
        String json = new Gson().toJson(inputMap);
        return json;
    }

    @DeleteMapping(value="/DeleteTicketInfo", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String DeleteTicketInfo(@RequestParam("id") String id) {
        boolean isSucceed = ticketDAO.deleteTickerInfoById(Integer.parseInt(id));
        String msg = isSucceed ? "刪除成功" : "刪除失敗";
        String json = String.format("{\"msg\":\"%s(id=%s)\"}", msg, id);
        return json;
    }
    

}
