package com.web.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.domain.Customer;
import com.web.domain.CustomerMemo;
import com.web.domain.Voucher;
import com.web.domain.VoucherC;
import com.web.service.CustomerService;
import com.web.service.VoucherService;

@Controller
public class VoucherController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private VoucherService voucherService;

	//바우처 등록 페이지
	@GetMapping("voucherForm")
	public String VoucherForm(HttpSession session, Model model) {
		String customerNum = (String)session.getAttribute("sessionCustomerNum");
		model.addAttribute("customerNum", customerNum);
		return "customer/voucherForm";
	}
	
	//바우처 수정 페이지
	@GetMapping("voucherUpdateForm")
	public String VoucherUpdateForm() {
		return "customer/voucherUpdateForm";
	}
	
	
	//바우처 UUID 11자리
	@PostMapping("/generateUUIDCode")
	@ResponseBody
	public String generateUUIDCode_11(@RequestParam("word") String word) {
		//특정문자열을 코드로 변환
		//생일-다이닝 : "dining"
		//생일-코스메틱 : "cosmetic"
		//생일-전시회 : "exhibition"
		//블랙S-요트 : "yacht"
		//블랙S-골프 : "golf"
		//블랙S-호텔 : "hotel"
		
	     UUID nameUUID = UUID.nameUUIDFromBytes(word.getBytes());
         System.out.println("Name-based UUID: " + nameUUID);
         
         // UUID를 문자열로 변환하고 '-' 제거
         String uuidString = nameUUID.toString().replaceAll("-", "");

         // 앞에서부터 11자리 추출
         String shortenedUUID = uuidString.substring(0, 11);
       
         return shortenedUUID;
    }

	
	//바우처 기본정보 검색
	@PostMapping("/getVoucher")
	@ResponseBody
	public Voucher getVoucher(@RequestParam("voucherCode") String voucherCode) {
		System.out.println(voucherCode);
		System.out.println(voucherService.getVoucher(voucherCode));
		Voucher voucher = voucherService.getVoucher(voucherCode);
		int check = voucher.getStatus();
		System.out.println(check);
	
		return voucher;
	}
	
	//발급된 바우처 검색
		@PostMapping("/getIssuedVoucher/{num}")
		@ResponseBody
		public VoucherC getIssuedVoucher(@PathVariable("num") String voucherCode) {
			System.out.println(voucherCode);
			System.out.println(voucherService.getIssuedVoucher(voucherCode));
			VoucherC voucherc = voucherService.getIssuedVoucher(voucherCode);

			return voucherc;
		}
	
	//바우처발급
	@PostMapping("/issueVoucher")
	@ResponseBody
	public Boolean issueVoucher(@RequestParam("voucherCode") String voucherCode,
								@RequestParam("voucherType") String voucherType,
								@RequestParam("voucherService") String voucherTheme, 
								@RequestParam("voucherServiceName") String voucherServiceName, 
								HttpSession session) {
		
		try {

			String customerNum = (String)session.getAttribute("sessionCustomerNum");
			System.out.println(customerNum);
			System.out.println(voucherCode);
			
			VoucherC voucherc = new VoucherC();
			Customer customer = new Customer();
			
			//LocalDate.now()  년, 월, 일까지만 포함된 현재날짜 반환
			LocalDate today = LocalDate.now();
			LocalDate afterOneMonth = today.plus(1, ChronoUnit.MONTHS);
						
			Voucher voucher = voucherService.getVoucher(voucherCode);
			
			customer.setCustomerNum(customerNum);			
			voucherc.setCustomer(customer);
			voucherc.setCheckedVoucherCode(voucherCode);
			voucherc.setVoucherTypeC(voucherType);
			voucherc.setVoucherServiceC(voucherTheme);
			voucherc.setVoucherServiceNameC(voucherServiceName);
			
			//LocalDate->Date 타입 변환 java.sql.Date.valueOf();
			voucherc.setVoucherIssuedDate(java.sql.Date.valueOf(today)); 
			voucherc.setVoucherExdate(java.sql.Date.valueOf(afterOneMonth));
			
			voucher.setStatus(1);
			
			voucherService.issueVoucher(voucherc);
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	
	}
	
	//바우처 삭제
	@GetMapping("/deleteVoucher/{num}")
	@ResponseBody
	public void deleteVoucher(@PathVariable("num") String voucherCode) {
		System.out.println(voucherCode+"삭제 확인");
		voucherService.deleteVoucher(voucherCode);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
