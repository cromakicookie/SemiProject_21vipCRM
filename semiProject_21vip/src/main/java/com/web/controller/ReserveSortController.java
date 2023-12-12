package com.web.controller;

import org.hibernate.query.criteria.internal.expression.function.AggregationFunction.MAX;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.domain.Luxury;
import com.web.persistence.LuxuryPageRepository;
import com.web.service.LuxuryService;

@Controller
public class ReserveSortController {
	
	@Autowired
	private LuxuryService luxuryService;
	
	@Autowired
	private LuxuryPageRepository luxuryPageRepo;
	
	// 전체목록 luxurySeq순으로 오름차순정렬
	@RequestMapping("/reservation")
	public String reservation(Model model, @PageableDefault(page = 0, size = 4, sort = "luxurySeq", direction = Direction.ASC)
								Pageable pageable, String searchKeyword, String brand) {
		
		Page<Luxury> Pagelist;  
		
		if(searchKeyword != null && brand != null && !searchKeyword.isEmpty() && !brand.isEmpty()) {
			Pagelist = luxuryService.findCustomerNumBrand(searchKeyword, pageable, brand);
			System.out.println(3);
		} else if(searchKeyword != null && !searchKeyword.isEmpty()) {
			Pagelist = luxuryService.findCustomerNum(searchKeyword, pageable);
			System.out.println(1);
		} else if(brand != null && !brand.isEmpty()) {
			Pagelist = luxuryService.findCustomerNumBrand(searchKeyword, pageable, brand);
			System.out.println(2);
		} else {
			Pagelist = luxuryService.PageList(pageable);
			System.out.println(4);
		}
//		if(searchKeyword != null) {  // searchKeyword가 있으면 searchKeyword가 포함되어있는 고객 조회
//			Pagelist = luxuryService.findCustomerNum(searchKeyword, pageable);
//			System.out.println(1);
//		} else {
//			Pagelist = luxuryService.PageList(pageable); // 없으면 전체 조회
//			System.out.println(2);
//		}
		int nowPage = Pagelist.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - 4, 1); 
		int endPage = Math.min(nowPage + 5, Pagelist.getTotalPages());
		
		model.addAttribute("sort", "luxurySeq"); // 정렬하기위한 변수
		model.addAttribute("Pagelist", Pagelist);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		return "reserve/reservation";
	}
	
	// 고객번호(customerNum)로 오름차순
	@RequestMapping("/reservationcustoNum")
	public String reservationcustoNum(Model model, @PageableDefault(page = 0, size = 4, sort = "customerNum", direction = Direction.ASC)
								Pageable pageable, String searchKeyword, String brand) {
		
		Page<Luxury> Pagelist;  
		if(!searchKeyword.isEmpty() && !brand.isEmpty()) {
			Pagelist = luxuryService.findCustomerNumBrand(searchKeyword, pageable, brand);
			System.out.println(3);
		} else if(!searchKeyword.isEmpty()) {
			Pagelist = luxuryService.findCustomerNum(searchKeyword, pageable);
			System.out.println(1);
		} else if(!brand.isEmpty()) {
			Pagelist = luxuryService.findCustomerNumBrand(searchKeyword, pageable, brand);
			System.out.println(2);
		} else {
			Pagelist = luxuryService.PageList(pageable);
			System.out.println(4);
		}
		int nowPage = Pagelist.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - 4, 1); 
		int endPage = Math.min(nowPage + 5, Pagelist.getTotalPages());
		
		model.addAttribute("sort", "customerNum"); // 정렬하기위한 변수
		model.addAttribute("Pagelist", Pagelist);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		return "reserve/reservation";
	}
	
	// 최근예약일자 정렬
	@RequestMapping("/reservationDate")
	public String reservationDate(Model model, @PageableDefault(page = 0, size = 4, sort = "luxuryDate", direction = Direction.ASC)
								Pageable pageable, String searchKeyword, String brand) {
		Page<Luxury> Pagelist;
		if(!searchKeyword.isEmpty() && !brand.isEmpty()) {
			Pagelist = luxuryService.findCustomerNumBrand(searchKeyword, pageable, brand);
			System.out.println(3);
		} else if(!searchKeyword.isEmpty()) {
			Pagelist = luxuryService.findCustomerNum(searchKeyword, pageable);
			System.out.println(1);
		} else if(!brand.isEmpty()) {
			Pagelist = luxuryService.findCustomerNumBrand(searchKeyword, pageable, brand);
			System.out.println(2);
		} else {
			Pagelist = luxuryService.PageList(pageable);
			System.out.println(4);
		}
		int nowPage = Pagelist.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - 4, 1); 
		int endPage = Math.min(nowPage + 5, Pagelist.getTotalPages());
		
		model.addAttribute("sort", "luxuryDate");
		model.addAttribute("Pagelist", Pagelist);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		return "reserve/reservation";
	}
	
	
	// 브랜드명으로 검색
	@RequestMapping("/reservationBrand")
	public String reservationBrand(Model model, @PageableDefault(page = 0, size = 4, sort = "luxurySeq", direction = Direction.ASC)
								Pageable pageable, String searchKeyword, String brand) {
		Page<Luxury> Pagelist;
		if(searchKeyword != null && !searchKeyword.isEmpty()) {
			Pagelist = luxuryService.findCustomerNumBrand(searchKeyword, pageable, brand);
		} else {
			Pagelist = luxuryService.findBrand(brand, pageable);
		}
		int nowPage = Pagelist.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - 4, 1); 
		int endPage = Math.min(nowPage + 5, Pagelist.getTotalPages());
		
		model.addAttribute("sort", "luxuryBrand");
		model.addAttribute("Pagelist", Pagelist);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		return "reserve/reservation";
	}
}
