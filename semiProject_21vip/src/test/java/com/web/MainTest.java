package com.web;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.web.config.SecurityConfig;
import com.web.domain.Member;
import com.web.domain.QMember;
import com.web.domain.Role;
import com.web.persistence.MainRepository;
import com.web.service.EmailSendService;
import com.web.service.MemberService;

@SpringBootTest
public class MainTest {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	MainRepository mainRepo;
	
	@Autowired
	EmailSendService emailSendService;
	
	
	@Test
	public void findBySearchOption() {
		
		emailSendService.updatePassword("qwer1234", "test");
	}
	
	

}
