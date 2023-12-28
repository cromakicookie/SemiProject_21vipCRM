package com.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.domain.Calendar;
import com.web.domain.Customer;
import com.web.domain.CustomerMemo;
import com.web.domain.Luxury;
import com.web.domain.Voucher;
import com.web.domain.VoucherC;
import com.web.service.CoolSmsService;
import com.web.service.CustomerService;
import com.web.service.VoucherService;

@Controller
public class VoucherController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private VoucherService voucherService;

	@Autowired
	private CoolSmsService smsService;

	// 바우처 관리 페이지 (바우처 목록)
	@GetMapping("voucherBoard")
	public String VoucherBoard(@PageableDefault(size = 10, sort = "voucherSeq", direction = Direction.DESC) Pageable pageable, Voucher voucher, Model model,
			String searchKeyword,String searchSelect) {
		
		System.out.println(searchKeyword);
		System.out.println(searchSelect);

		Page<Voucher> Pagelist;
		//Pagelist = voucherService.findByVoucherCode(searchKeyword, pageable);
		
		if(searchKeyword != null && !searchKeyword.isEmpty()) {
			if(searchSelect.equals("S")) {
				Pagelist = voucherService.findByVoucherServiceName(searchKeyword, pageable);
			}else{
				Pagelist = voucherService.findByVoucherCode(searchKeyword, pageable);
			}	
		}else {
			Pagelist = voucherService.PageList(pageable);
		}	

		System.out.println(Pagelist);
		model.addAttribute("voucherListAll", Pagelist);
		model.addAttribute("paging", Pagelist);
		return "customer/voucherBoard";
	}

	
	

	// 바우처 등록 페이지
	@GetMapping("customerVoucherForm")
	public String VoucherForm(HttpSession session, Model model) {
		String customerNum = (String) session.getAttribute("sessionCustomerNum");
		model.addAttribute("customerNum", customerNum);
		return "customer/customerVoucherForm";
	}

	// 바우처 수정 페이지
	@GetMapping("voucherUpdateForm")
	public String VoucherUpdateForm(HttpSession session, Model model) {
		String customerNum = (String) session.getAttribute("sessionCustomerNum");
		model.addAttribute("customerNum", customerNum);
		return "customer/voucherUpdateForm";
	}

	

	// 바우처 기본정보 검색
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

	// 발급된 바우처 검색
	@PostMapping("/getIssuedVoucher/{num}")
	@ResponseBody
	public VoucherC getIssuedVoucher(@PathVariable("num") String voucherCode) {
		System.out.println(voucherCode);
		System.out.println(voucherService.getIssuedVoucher(voucherCode));
		VoucherC voucherc = voucherService.getIssuedVoucher(voucherCode);

		return voucherc;
	}

	// 바우처발급
	@PostMapping("/issueVoucher")
	@ResponseBody
	public Boolean issueVoucher(@RequestParam("voucherCode") String voucherCode,
			@RequestParam("voucherType") String voucherType, @RequestParam("voucherService") String voucherTheme,
			@RequestParam("voucherServiceName") String voucherServiceName, HttpSession session) {

		try {

			String customerNum = (String) session.getAttribute("sessionCustomerNum");
			System.out.println(customerNum);
			System.out.println(voucherCode);

			VoucherC voucherc = new VoucherC();
			Customer customer = new Customer();

			// LocalDate.now() 년, 월, 일까지만 포함된 현재날짜 반환
			LocalDate today = LocalDate.now();
			LocalDate afterOneMonth = today.plus(1, ChronoUnit.MONTHS);

			Voucher voucher = voucherService.getVoucher(voucherCode);

			customer.setCustomerNum(customerNum);
			voucherc.setCustomer(customer);
			voucherc.setCheckedVoucherCode(voucherCode);
			voucherc.setVoucherType2(voucherType);
			voucherc.setVoucherService2(voucherTheme);
			voucherc.setVoucherServiceName2(voucherServiceName);

			// LocalDate->Date 타입 변환 java.sql.Date.valueOf();
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

	// 바우처 삭제
	@GetMapping("/deleteVoucher/{num}")
	@ResponseBody
	public void deleteVoucher(@PathVariable("num") String voucherCode) {
		System.out.println(voucherCode + "삭제 확인");
		voucherService.deleteVoucher(voucherCode);
		
		Voucher voucher = voucherService.getVoucher(voucherCode);
		voucher.setStatus(0);
		voucherService.createVoucher(voucher);
		
		System.out.println(voucher.getVoucherCode() + "바우처의 상태 : "+voucher.getStatus());
		
	}

	// 바우처 문자 전송
	@PostMapping("/sendVoucherSMS")
	@ResponseBody
	public Boolean sendVoucherSMS(@RequestParam("voucherService2") String voucherService2,
			@RequestParam("voucherServiceName2") String voucherServiceName2,
			@RequestParam("checkedVoucherCode") String checkedVoucherCode,
			@RequestParam("voucherExdate") String voucherExdate,
			@RequestParam("phoneNumber") String phoneNumber
			) {

		System.out.println("테마명 : " + voucherService2);
		System.out.println("서비스명 : " + voucherServiceName2);
		System.out.println("바우처코드 : " + checkedVoucherCode);
		System.out.println("사용기한 : " + voucherExdate);
		System.out.println("휴대폰번호 : " + phoneNumber);

		try {
			String smstext = "";
			smstext += "VIP Voucher Present";
			smstext += "\n테마명 : " + voucherService2;
			smstext += "\n서비스명 : " + voucherServiceName2;
			smstext += "\n일련번호 : " + checkedVoucherCode;
			smstext += "\n사용기한 : " + voucherExdate;

			smsService.sendSms("01036882027", "01036882027", smstext);

			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}

	}
	
	// 바우처 생성----------------------------------------------------
	
	// 바우처 UUID 11자리
		@PostMapping("/generateUUIDCode")
		@ResponseBody
		public String generateUUIDCode_11(@RequestParam("word") String word) {
			// 특정문자열을 코드로 변환
			// 생일-다이닝 : "dining"
			// 생일-코스메틱 : "cosmetic"
			// 생일-전시회 : "exhibition"
			// 블랙S-요트 : "yacht"
			// 블랙S-골프 : "golf"
			// 블랙S-호텔 : "hotel"

			UUID nameUUID = UUID.nameUUIDFromBytes(word.getBytes());
			System.out.println("Name-based UUID: " + nameUUID);

			// UUID를 문자열로 변환하고 '-' 제거
			String uuidString = nameUUID.toString().replaceAll("-", "");

			// 앞에서부터 11자리 추출
			String shortenedUUID = uuidString.substring(0, 11);

			return shortenedUUID;
		}
		
		//바우처 15자리 일련번호 생성기
		public String generateVoucherCode(Long voucherSeq, String name) {
			
		     UUID nameUUID = UUID.nameUUIDFromBytes(name.getBytes());
	         System.out.println("Name-based UUID: " + nameUUID);
	         
	         // UUID를 문자열로 변환하고 '-' 제거
	         String uuidString = nameUUID.toString().replaceAll("-", "");

	         // 앞에서부터 11자리 추출
	         String shortenedUUID = uuidString.substring(0, 11);
	         //바우처시퀀스를 00XX 형태로 네자리로 변환
	         String formattedVoucherSeq = String.format("%04d", voucherSeq);
	         
	         //스트링변환메서드String.valueOf();
	         return shortenedUUID+formattedVoucherSeq;
	    }
		
		//Date 데이터 변환 메서드 (String -> Date)
		public Date dateTryCatch(String dateString) {		
			 try {
		            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		            Date stringToDate = dateFormat.parse(dateString);
		            return stringToDate;

		        } catch (ParseException e) {
		            e.printStackTrace();
		            return null;
		        }
		}

		// 바우처 생성
		@PostMapping("createVoucher")
		@ResponseBody
		public void createVoucher(@RequestParam("voucherType") String VoucherType, 
								@RequestParam("voucherCount") int VoucherAmount,
								@RequestParam("voucherService") String voucherTheme,
								@RequestParam("voucherServiceCode") String voucherServiceCode,
								@RequestParam("voucherServiceName") String voucherServiceName) {
			
			//startDate:연간VIP서비스시작기간 endDate:연간서비스종료일자
			Date startDate = dateTryCatch("2023-02-01");
			Date endDate = dateTryCatch("2024-01-31");

			// 다이닝 5장 
			for (int i = 0; i < VoucherAmount; i++) { 
				Voucher voucher = new Voucher();
				voucher.setVoucherType(VoucherType);
				voucher.setVoucherService(voucherTheme);
				voucher.setVoucherServiceName(voucherServiceName);
				voucher.setVoucherStartDate(startDate);
				voucher.setVoucherEndDate(endDate);

				// 엔터티를 먼저 저장한 후에 voucherSeq를 얻을 수 있음 
				Voucher savedVoucher = voucherService.createVoucher(voucher);

				// 저장된 엔터티의 voucherSeq를 이용하여 voucherCode를 생성
				savedVoucher.setVoucherCode(VoucherType + generateVoucherCode(savedVoucher.getVoucherSeq(), voucherServiceCode));

				// 다시 저장 
				voucherService.createVoucher(savedVoucher); 
			}
			
			
		}

}
