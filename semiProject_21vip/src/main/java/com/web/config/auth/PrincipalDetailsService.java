package com.web.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.web.domain.Member;
import com.web.persistence.MainRepository;

import lombok.RequiredArgsConstructor;

//http://localhost:8080/login
//@Service
//@RequiredArgsConstructor
//public class PrincipalDetailsService implements UserDetailsService{
//
//	@Autowired
//	private MainRepository mainRepo;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		System.out.println("PrincipalDetailsService의 loadUserByUsername()");
//		Member memberEntity = mainRepo.findByUsername(username);
//		return new PrincipalDetails(memberEntity);
//	}
//
//}
