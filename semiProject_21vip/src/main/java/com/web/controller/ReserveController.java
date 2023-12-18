package com.web.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.domain.Customer;
import com.web.domain.Luxury;
import com.web.service.LuxuryService;

@Controller
public class ReserveController {
	
	@Autowired
	private LuxuryService luxuryService;
	
	@GetMapping("kakao")
	public String kakao() {
		return "reserve/kakao";
	}
		
	@GetMapping("/ReservationInsert")
	public String ReservationInsert() {
		return "reserve/ReservationInsert";
	}
	
//	@GetMapping("/findCustomerNum")
//	@ResponseBody
//	public Customer findCustomerNum(@RequestParam("customerNum") String customerNum) {
//		Customer customer = luxuryService.searchCustomer(customerNum);
//		 
//		return customer;
//	}
	
	
	// 예약정보 전송
	@PostMapping("/reservationResult")
	public String reservationResult(Luxury luxury) {
		luxuryService.insertLuxury(luxury);
		return "redirect:/reservation";
	}
	
	// 게시글 상세보기
//	@GetMapping("/reserveList")
//	public String luxuryView(Model model, Long luxurySeq, String customerNum) {
//		model.addAttribute("customer", luxuryService.searchCustomer(customerNum));
//		model.addAttribute("list", luxuryService.luxuryView(luxurySeq));
//		return "reserve/ReserveList";
//	}
	
	// 수정 폼 이동
//	@GetMapping("/updateForm/{luxurySeq}")
//	public String ReservationUpdate(Model model, @PathVariable("luxurySeq") long luxurySeq, String customerNum) {
//		model.addAttribute("customer", luxuryService.searchCustomer(customerNum));
//		model.addAttribute("list",luxuryService.luxuryView(luxurySeq));
//		return "reserve/ReservationUpdate";
//	}
	
	
	// 수정 결과 전송
	@PostMapping("/updateResult")
	public String updateResult(Luxury luxury) {
			luxuryService.luxuryupdate(luxury);
		return "forward:reservation";
	}
	
	// 글 삭제
	@GetMapping("/luxuryDelete")
	public String luxuryDelete(@RequestParam("luxurySeq") long luxurySeq) {
		luxuryService.luxuryDelete(luxurySeq);
		return "redirect:reservation";
	}
	

	
}
