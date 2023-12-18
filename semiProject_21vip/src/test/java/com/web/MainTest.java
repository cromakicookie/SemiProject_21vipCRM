package com.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.web.config.SecurityConfig;
import com.web.persistence.MainRepository;

@SpringBootTest
public class MainTest {

	@Autowired
	MainRepository mainRepository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Test
	public void test() {
		System.out.println(bCryptPasswordEncoder.encode("0EFRNMBWUJ"));
		
	}

}
