package com.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //Auditing 기능 활성화
//@SpringBootApplication
//public class SemiProjectApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(SemiProjectApplication.class, args);
//		
////		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//	}
//
//}


@SpringBootApplication
public class SemiProjectApplication extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SemiProjectApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SemiProjectApplication.class, args);
	}

}
