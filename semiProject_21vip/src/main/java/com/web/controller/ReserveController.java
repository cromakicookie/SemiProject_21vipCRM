package com.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	
	// 예약등록 폼 이동
	@GetMapping("/ReservationInsert")
	public String ReservationInsert() {
		return "reserve/ReservationInsert";
	}
	
	// 게시글 상세보기
	@GetMapping("/reserveList")
	public String luxuryView(Model model, Long luxurySeq) {
		
		model.addAttribute("list", luxuryService.luxuryView(luxurySeq));
		return "reserve/ReserveList";
	}
	
	// 수정 폼 이동
	@PostMapping("/updateForm/{luxurySeq}")
	public String ReservationUpdate(Model model, @PathVariable("luxurySeq") long luxurySeq) {
		
		model.addAttribute("list",luxuryService.luxuryView(luxurySeq));
		return "reserve/ReservationUpdate";
	}
	
	
	@PostMapping("/reservationResult")
	public String reservationResult(Luxury luxury) {
		luxuryService.insertLuxury(luxury);
		return "redirect:/reservation";
	}
	
	// 글 삭제
	@GetMapping("/luxuryDelete")
	public String luxuryDelete(long luxurySeq) {
		luxuryService.luxuryDelete(luxurySeq);
		return "redirect:reservation";
	}
	
	
}
