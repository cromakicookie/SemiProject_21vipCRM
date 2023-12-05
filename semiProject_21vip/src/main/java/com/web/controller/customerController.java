package com.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class customerController {
	
	@GetMapping("/customerPage")
	public String CustomerPage() {
		return "customer/customerPage";
	}

}
