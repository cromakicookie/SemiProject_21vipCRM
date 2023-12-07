package com.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.domain.Customer;
import com.web.service.CustomerService;

@Controller
public class CustomerController {
	
	@Autowired
	private CustomerService customerService; 
	
	//고객관리 메인 
	@GetMapping("customerMain")
	public String CustomerMain() {
		return "customer/customerMain";
	}
	
	//메인 고객정보 상세페이지 이동
	@GetMapping("customerPage")
	public String CustomerPage() {
		return "customer/customerPage";
	}
	
	//바우처 등록 페이지
	@GetMapping("voucherForm")
	public String VoucherForm() {
		return "customer/voucherForm";
	}
	
	//바우처 수정 페이지
	@GetMapping("voucherUpdateForm")
	public String VoucherUpdateForm() {
		return "customer/voucherUpdateForm";
	}
	
	//고객 등록 페이지
	@GetMapping("insertCustomer")
	public String insertCustomer() {
		return "customer/insertCustomer";
	}
	
	
	//고객 등록
	@PostMapping("customerInsert")
	public String customerInsert(Customer customer) {
		customerService.insertCustomer(customer);
		return "redirect:customerMain";
	}
	
	
	//고객 조회 -> 해당 고객 정보 리스트 출력
	@PostMapping("searchCustomer")
	public String searchCustomer(@RequestParam(name="customerNum") String customerNum, Model model) {
		//서비스에서 고객번호로 고객정보 가져옴
		Customer customer = new Customer();
		customer.setCustomerNum(customerNum);
		customer = customerService.getCustomer(customer);
		model.addAttribute("customer", customer);
//		System.out.println(customerService.getCustomer(customer));
		
//		 if (customer == null) {
//		        // 고객이 없을 경우
//		        model.addAttribute("check", 0);
//		    } else {
//		        // 고객이 있을 경우
//		        model.addAttribute("check", 1);
//		        model.addAttribute("customer", customer);
//		    }
//		 
		return "customer/customerPage";
	}
	
	//고객 조회 -> 데이터가 있으면 1 없으면 0 반환
	

	
	


}
