package com.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.web.service.CustomerService;

@Controller
public class VoucherController {

	@Autowired
	private CustomerService customerService;

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
	
	//생일 바우처 표시
	
}
