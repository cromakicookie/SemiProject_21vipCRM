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
	
	@GetMapping("/reservation")
	public String reservation(Model model, @PageableDefault(page = 0, size = 5, sort = "luxurySeq", direction = Direction.ASC)
								Pageable pageable, String searchKeyword) {
		Page<Luxury> list;
		if(searchKeyword != null) {
			list = luxuryService.PageSearchList(searchKeyword, pageable);
		} else {
			list = luxuryService.PageList(pageable);
		}
		int nowPage = list.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - 4, 1); 
		int endPage = Math.min(nowPage + 5, list.getTotalPages());
		
		model.addAttribute("list", list);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		return "reserve/reservation";
	}
	
	
}
