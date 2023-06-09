package com.tm.TravelMaster.ming.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tm.TravelMaster.ming.db.dao.BookingDAO;
import com.tm.TravelMaster.ming.db.service.HighSpeedRailService;
import com.tm.TravelMaster.ming.db.service.TicketService;
import com.tm.TravelMaster.ming.model.TicketInfo;


@Controller
@RequestMapping("/highSpeedRail")
public class HighSpeedRailController {

	@Autowired
	@Qualifier("bookingDAOImpl")
	private BookingDAO bookingDAO;

	@Autowired
	private TicketService ticketDAO;

	@Autowired
	@Qualifier("highSpeedRailServiceImpl")
	private HighSpeedRailService ticketService;

	@GetMapping("")
	public String index(Model model) {
		return "BookingAdmin";
	}

	@GetMapping("/insert")
	public String insert(Model model) {
		model.addAttribute("stationList", bookingDAO.getAllStationInfo());
		model.addAttribute("priceInfos", bookingDAO.getAllPriceInfo());
//		model.addAttribute("ticketDto", new TicketInfo());
		return "BookingAdminDataPage";
	}

    @GetMapping("/update")
    public String update(Model model, @RequestParam("id") String id) {
        TicketInfo ticketDto = ticketDAO.findTicketInfoById(Integer.parseInt(id));
        model.addAttribute("stationList", bookingDAO.getAllStationInfo());
        model.addAttribute("priceInfos", bookingDAO.getAllPriceInfo());
        model.addAttribute("ticketDto", ticketDto);
        return "BookingAdminDataPage";
    }

    @PostMapping("/doAction")
    public String doAction(@ModelAttribute("ticketDto") TicketInfo ticketDto, @RequestParam("action")String action) {
    	if(action.equals("doInsert")) {
    		ticketDAO.insertTicketInfo(ticketDto);
    	}else if(action.equals("doUpdate")) {
    		ticketDAO.updateTicketInfo(ticketDto);
    	}
        return "redirect:/highSpeedRail";
    }

}
