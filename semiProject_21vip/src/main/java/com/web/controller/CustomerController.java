package com.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.domain.Calendar;
import com.web.domain.Customer;
import com.web.domain.CustomerMemo;
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
	
	//고객정보 상세페이지 이동
	@GetMapping("customerPage")
	public String CustomerPage() {
		return "customer/customerPage";
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
	
	
	//고객 조회 
	@GetMapping("searchCustomer")
	public String searchCustomer(@RequestParam(name="customerNum") String customerNum, Model model) {
		//서비스에서 고객번호로 고객정보 가져옴
		Customer customer = new Customer();
		customer.setCustomerNum(customerNum);
		customer = customerService.getCustomer(customer);
		
//		model.addAttribute("customer", customer);
//		System.out.println(customerService.getCustomer(customer));
			
		 if (customer == null) {
			 	//check 데이터가 있으면 1 없으면 0 반환
		        // 고객이 없을 경우
		        model.addAttribute("check", 0);
		        System.out.println(customer);
		    } else {
		        // 고객이 있을 경우
		        model.addAttribute("check", 1);
		        model.addAttribute("customer", customer);
		        model.addAttribute("memoList", customer.getCustomerMemoList());
		    }
		 
		return "customer/customerPage";
	}
	
	
	//메모 등록
	@PostMapping("memoInsert")
	public String memoInsert(@RequestParam(name = "mcustomerNum") String customerNum,
							@RequestParam(name = "memoContent") String memoContent) {
		
		System.out.println("고번" + customerNum +"내용" + memoContent);

		CustomerMemo cm = new CustomerMemo();
		Customer customer = new Customer();
		
		cm.setMemoContent(memoContent);
		customer.setCustomerNum(customerNum);
		cm.setCustomer(customer);
		
		System.out.println(cm);
		customerService.insertMemo(cm);
		
		return "redirect:/searchCustomer?customerNum="+customerNum;
	}
	
	
	//메모삭제
	@GetMapping("deleteMemo/{num}")
	@ResponseBody
	public void deleteMemo(@PathVariable("num") Long memoNum) {
		System.out.println("삭제 확인");
		CustomerMemo cm = new CustomerMemo();
		cm.setMemoNum(memoNum);
		customerService.deleteMemo(memoNum);
	}
	
	
	//메모페이징
	//전체 메모 리스트
	@GetMapping("/getMemoPage")
	@ResponseBody
	public List<CustomerMemo> getMemoPage(@RequestParam("customerNum") String customerNum,
										@RequestParam("page") int page,
										@RequestParam("pageSize") int pageSize){
		
		Customer customer = new Customer();
		customer.setCustomerNum(customerNum);
		customer = customerService.getCustomer(customer);
		
		//특정 고객의 메모리스트
		List<CustomerMemo> memoPage = customer.getCustomerMemoList();
		return memoPage;
	}
	
	
	






}
