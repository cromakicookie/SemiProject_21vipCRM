package com.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	// 목록 추가
	@PostMapping("/reservationResult")
	public String reservationResult(Luxury luxury) {
		luxuryService.insertLuxury(luxury);
		return "redirect:/reservation";
	}
	
	// 게시글 상세보기
	@GetMapping("/reserveList")
	public String luxuryView(Model model, Long luxurySeq) {
		
		model.addAttribute("list", luxuryService.luxuryView(luxurySeq));
		return "reserve/ReserveList";
	}
	
	// 수정 폼 이동
	@GetMapping("/updateForm/{luxurySeq}")
	public String ReservationUpdate(Model model, @PathVariable("luxurySeq") long luxurySeq) {
		
		model.addAttribute("list",luxuryService.luxuryView(luxurySeq));
		return "reserve/ReservationUpdate";
	}
	
	
	// 수정 결과 전송
	@PostMapping("/updateResult")
	public String updateResult(Luxury luxury) {
			luxuryService.luxuryupdate(luxury);
		return "forward:reservation";
	}
	
	// 글 삭제
	@GetMapping("/luxuryDelete")
	public String luxuryDelete(long luxurySeq) {
		luxuryService.luxuryDelete(luxurySeq);
		return "redirect:reservation";
	}
	
//	@PostMapping(value="/updateResult/{luxurySeq}")
//	public String updateResult(@PathVariable("luxurySeq") long luxurySeq, Luxury luxury) {
//		Luxury findLuxury = luxuryService.luxuryView(luxurySeq);
//		findLuxury.setCustomerNum(luxury.getCustomerNum());
//		findLuxury.setLuxuryBrandName(luxury.getLuxuryBrandName());
//		findLuxury.setLuxuryDate(luxury.getLuxuryDate());
//		findLuxury.setLuxuryName(luxury.getLuxuryName());
//		findLuxury.setLuxuryPhone(luxury.getLuxuryPhone());
//		findLuxury.setLuxuryTime(luxury.getLuxuryTime());
//		findLuxury.setLuxuryHeadCount(luxury.getLuxuryHeadCount());
//		luxuryService.insertLuxury(findLuxury);
//		return "forward:reservation";
//	}
	

	
}
