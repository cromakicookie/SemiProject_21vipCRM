package com.web.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.web.domain.Member;



//public class PrincipalDetails implements UserDetails{
//	
//	private Member member;
//	
//	public PrincipalDetails(Member member) {
//		this.member = member;
//	}
//
//	//유저 권한 리턴
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		Collection<GrantedAuthority> authorities = new ArrayList<>();
//		authorities.add(new GrantedAuthority(){
//			@Override
//			public String getAuthority() {
//				return member.getMemberRole().toString();
//			}
//		});
//		return authorities;
//	}
//
//	@Override
//	public String getPassword() {
//		return member.getPassword();
//	}
//
//	@Override
//	public String getUsername() {
//		return member.getUsername();
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		return true;
//	}
//
//}
