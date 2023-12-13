package com.web.service;

import java.util.List;

import com.web.domain.Member;

public interface MainService {

	public void insertMember(Member member);
	public Member getMember(Member member);
	public boolean idValid(String username);
	public List<String> findId(Member member);
	public boolean findPw(Member member);
	public void updatePw(Member member);
	public boolean memberEmailCheck(String memberEmail, String username);
	void updateMember(Member member);
	void updateFile(String username, Long fileNumber);

}