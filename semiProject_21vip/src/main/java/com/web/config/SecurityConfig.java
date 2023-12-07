package com.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer.JwtConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import lombok.RequiredArgsConstructor;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
//@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) //secured 어노테이션 활성화, preAuthorize 어노테이션 활성화
public class SecurityConfig{
	
//	private final CorsFilter corsFilter;
	
//	private final AuthenticationConfiguration authConfig;
//	
//	 @Bean
//	 public AuthenticationManager authenticationManager() throws Exception {
//	        return authConfig.getAuthenticationManager();
//	 }
//	
//	
//	@Bean
//	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//		http
//			.csrf().disable()
//			.cors().configurationSource(corsFilter()) // cors설정
//			.and()
//			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//			.and()
//			//.addFilterBefore(corsFilter, ChannelProcessingFilter.class)
//			.formLogin().disable()
//			.httpBasic().disable()
//			//.addFilter(new JwtAuthenticationFilter(authenticationManager()))
//			//.addFilter(new JwtAuthorizationFilter(authenticationManager(), userService))
//			.authorizeRequests()
//			.antMatchers("/").permitAll()
//			.antMatchers("/api/v1/customer*/**").authenticated()
//			.antMatchers("/api/v1/reservation*/**").authenticated()
//			.antMatchers("/api/v1/calendar*/**").authenticated()
//			.antMatchers("/api/v1/mypage*/**").authenticated()
//			.antMatchers("/api/v1/employee*/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
//			.anyRequest().permitAll()
//			;
//		return http.build();
//	}
//	
//	@Bean
//    public CorsConfigurationSource corsFilter(){
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true); // 자바스크립트 응답을 처리할 수 있게 할지 설정(ajax, axios)
//        config.addAllowedOrigin("*");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/api/*", config);
//        // TODO /api/* 으로 요청이 왔을때, Allowed 된 요청만 받는지 확인하기
//
//        return source;
//    }
	
	
	@Autowired
	private MemberDetailService memberDetailService;
	
//	//해당 메소드에서 리턴되는 오브젝트를 IoC(Inversion of Control)로 등록
	@Bean
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}
	
//	@Bean
//    AuthenticationManager authenticationManager(
//    AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
	
	
	
	@Bean
	//filterChain : HTTP 요청에 대한 웹 기반 보안 구성. 인증/인가 및 로그인, 로그아웃 설정
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
			.csrf().disable()
			.headers().frameOptions().disable()
			.and()
			.authorizeRequests()
			.antMatchers("/").permitAll()
//			.antMatchers("/customer*/**").authenticated()
//			.antMatchers("/reservation*/**").authenticated()
//			.antMatchers("/calendar*/**").authenticated()
//			.antMatchers("/mypage*/**").authenticated()
//			.antMatchers("/employee*/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
			.anyRequest().permitAll()
			.and()
			.formLogin()
			.loginPage("/loginForm")
			.loginProcessingUrl("/login")
			.defaultSuccessUrl("/")
			.and()
			.exceptionHandling().accessDeniedPage("/accessDenied")
			.and()
			.logout()
			.invalidateHttpSession(true).logoutSuccessUrl("/logoutSuccess")
			.and()
			.userDetailsService(memberDetailService)
		;	
		return http.build();
	}
	

}
