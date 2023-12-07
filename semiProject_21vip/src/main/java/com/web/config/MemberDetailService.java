package com.web.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.web.domain.Member;
import com.web.persistence.MainRepository;

@Service
public class MemberDetailService implements UserDetailsService{

	@Autowired
	private MainRepository mainRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Member> optional = mainRepo.findById(username);
		if(!optional.isPresent()) {
			throw new UsernameNotFoundException(username+" 이/가 존재하지 않습니다.");
		}else {
			Member member = optional.get();
			return new SecurityUser(member);
		}
	}

}
