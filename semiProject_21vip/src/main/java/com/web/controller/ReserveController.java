package com.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.web.domain.Luxury;
import com.web.service.LuxuryService;

@Controller
public class ReserveController {
	
	@Autowired
	private LuxuryService luxuryService;
	
//	@GetMapping("/reservation")
//	public String Reservation() {
//	return "reserve/Reservation";
//	}
	
	@GetMapping("/ReservationInsert")
	public String ReservationInsert() {
		return "reserve/ReservationInsert";
	}
	
	@PostMapping("/ReservationUpdate")
	public String ReservationUpdate() {
		return "reserve/ReservationUpdate";
	}
	
	@PostMapping("/reservationResult")
	public String reservationResult(Luxury luxury) {
		luxuryService.insertLuxury(luxury);
		return "redirect:/reservation";
	}
	
	
}
