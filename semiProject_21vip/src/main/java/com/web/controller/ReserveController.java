package com.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.web.domain.Customer;
import com.web.domain.Luxury;
import com.web.service.CoolSmsService;
import com.web.service.CustomerService;
import com.web.service.LuxuryService;

import okhttp3.Response;

@Controller
public class ReserveController {
	
	@Autowired
	private CoolSmsService css;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private LuxuryService luxuryService;
	
	
//	@GetMapping("/oauth/kakao")
//	public @ResponseBody String kakaoCallback(String code) {
//		RestTemplate rt = new RestTemplate();
//		
//		// HTTP 헤더 오브젝트 생성
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
//		
//		// HTTP 바디 오브젝트 생성
//		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//		params.add("grant_type", "authorization_code");
//		params.add("client_id", "4340646219326247fb0a9428d5ba413d");
//		params.add("redirect_uri","http://localhost:8080/oauth/kakao");
//		params.add("code", code);
//		
//		// Http헤더, 바디를 하나의 오브젝트에 담기
//		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = 
//				new HttpEntity<>(params, headers);
//		
//		// HTTP 요청하기 - Post방식으로 - 그리고 response 변수의 응답 받음
//		ResponseEntity<String> response = rt.exchange(
//				"https://kauth.kakao.com/oauth/token",
//				HttpMethod.POST,
//				kakaoTokenRequest,
//				String.class
//		);
//		
//		return "카카오 서버로부터 받은 CODE 정보: " + response;
//	}
//	@GetMapping("kakao")
//	public String kakaoCall(String code) {
//		return "reserve/kakao";
//	}
	
	@GetMapping("/findphone")
	@ResponseBody
	public Luxury luxuryMessage(@RequestParam("Seq") Long Seq,
								@RequestParam("customerNum") String customerNum,						
								@RequestParam("Name") String name
														) {
		Luxury luxury = luxuryService.luxuryView(Seq);
		System.out.println(name);
		
//		text +=  name + "님 안녕하세요\n";
//		text += "예약내역 안내드립니다.\n";
//		text += "고객번호 : " + customerNum + "\n";
//		text += "명품관 : " + luxury.getLuxuryBrandName() + "\n";
//		text += "이용날짜 : " + luxury.getLuxuryDate() + "\n";
//		text += "인원 : " + luxury.getLuxuryHeadCount() + "명";	
		
//		String head = name + "님 안녕하세요\n";
//		String body = "예약내역 안내드립니다.\n" +
//				  	  "고객번호 : " + customerNum + "\n" +
//				      "명품관 : " + luxury.getLuxuryBrandName() + "\n" +
//			          "예약날짜 : " + luxury.getLuxuryDate() + "\n" +
//			          "예약시간 : " + luxury.getLuxuryTime() + "\n" +
//			          "인원 : " + luxury.getLuxuryHeadCount() + "명";
//		
//		String text = head + body;
	String text = name + "님 안녕하세요\n" +
				  "예약내역 안내드립니다.\n" +
				  "고객번호 : " + customerNum + "\n" +
			      "명품관 : " + luxury.getLuxuryBrandName() + "\n" +
		          "예약날짜 : " + luxury.getLuxuryDate() + "\n" +
		          "예약시간 : " + luxury.getLuxuryTime() + "\n" +
		          "인원 : " + luxury.getLuxuryHeadCount() + "명";
		String phone = luxury.getLuxuryPhone();
		css.sendSms("01036882027", phone, text);
		
		return null;
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
	
		
	@GetMapping("reservationInsert")
	public String ReservationInsert() {
		return "reserve/reservationInsert";
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
		return "reserve/reserveList";
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
		return "reserve/reservationUpdate";
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
