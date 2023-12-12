package com.web.controller;

import java.util.Random;

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
	
	
	// 예약등록 폼 이동
	@GetMapping("/ReservationInsert")
	public String ReservationInsert(Model model) {
		String RandomNum = generateRandomNum();
		model.addAttribute("generateRandomNum", generateRandomNum());
		return "reserve/ReservationInsert";
	}
	
	private String generateRandomNum() {
		Random random = new Random();
		int randomNumber = random.nextInt(9999) + 1000;
		return "L" + randomNumber;
	}
	
	// 예약정보 전송
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
	

	
}
