package com.web.controller;

import java.net.http.HttpHeaders;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.mail.Session;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.client.RestTemplate;

import com.web.domain.Customer;
import com.web.domain.Luxury;
import com.web.service.CoolSmsService;
import com.web.service.CustomerService;
import com.web.service.LuxuryService;

@Controller
public class ReserveController {
	
	@Autowired
	private CoolSmsService css;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private LuxuryService luxuryService;
	
	
	@GetMapping("/oauth/kakao")
	public @ResponseBody String kakaoCallback(String code) {
		RestTemplate rt = new RestTemplate();
//		HttpHeaders headers = new HttpHeaders();
		
		return "카카오 서버로부터 받은 CODE 정보: " + code;
	}
	@GetMapping("kakao")
	public String kakaoCall(String code) {
		return "reserve/kakao";
	}
	
	@PostMapping("/Duplicate")
	@ResponseBody
	public int getCustomer(@RequestParam("customerNum") String customerNum) {
		Customer customer = new Customer();
		customer.setCustomerNum(customerNum);
		customer = customerService.getCustomer(customer);
		if(customer == null) {
			return 0;
		} else {
			return 1;
		}
	}
	
		
	@GetMapping("/ReservationInsert")
	public String ReservationInsert() {
		return "reserve/ReservationInsert";
	}
	
	@GetMapping("/findCustomerNum")
	@ResponseBody
	public ResponseEntity <Map<String, Object>> findCustomerNum(HttpSession session,@RequestParam("customerNum") String customerNum) {
//		List<String> list = new ArrayList<>();
		Map<String, Object> list = new HashMap<>();  // Map을 이용하여 JSON형식으로 받기
		list = luxuryService.searchCustomer(customerNum);
		session.setAttribute("customerNumL", customerNum);
		if(list == null) {
			return null;
		} else {
			return ResponseEntity.ok(list);
		}
	}
	
	
	// 예약정보 전송
	@PostMapping("/reservationResult")
	public String reservationResult(HttpSession session,Luxury luxury) {
		String cN = (String)session.getAttribute("customerNumL");
		System.out.println(cN);
		
		Customer ct = new Customer();
		ct.setCustomerNum(cN);
		luxury.setCustomer(ct);
		
		luxuryService.insertLuxury(luxury);
		return "redirect:/reservation";
	}
	
	// 게시글 상세보기
	@GetMapping("/reserveList")
	public String luxuryView(HttpSession session, Model model, Long luxurySeq, String customerNum) {
		String customerNumL = (String)session.getAttribute("customerNumL");
		
		Luxury luxury = new Luxury();
		Customer customer = new Customer();
		customer.setCustomerNum(customerNumL);
		luxury.setCustomer(customer);
		model.addAttribute("customer", luxuryService.getCustomer(customerNum));
		model.addAttribute("list", luxuryService.luxuryView(luxurySeq));
		return "reserve/ReserveList";
	}
	
	// 수정 폼 이동
	@GetMapping("/updateForm/{luxurySeq}")
	public String ReservationUpdate(HttpSession session, Model model, @PathVariable("luxurySeq") long luxurySeq, String customerNum) {
		String customerNumL = (String)session.getAttribute("customerNumL");
		
		Luxury luxury = new Luxury();
		Customer customer = new Customer();
		customer.setCustomerNum(customerNumL);
		luxury.setCustomer(customer);
		
		model.addAttribute("customer", luxuryService.getCustomer(customerNum));
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
	public String luxuryDelete(@RequestParam("luxurySeq") long luxurySeq) {
		luxuryService.luxuryDelete(luxurySeq);
		return "redirect:reservation";
	}
	

	
}
