package com.web.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.web.domain.Luxury;
import com.web.domain.Member;
import com.web.domain.Role;

public interface MemberService {

	public List<Member> listAll();
	
	public Member findMember(String username);
	int updateMember(Member member);
	public void deleteMember(String username);
//	List<Member> searchMember(String memberName);
	public Page<Member> findMember(Pageable pageable, String memberDept, Role memberRole, String memberName);
	public Page<Member> PageList(Pageable pageable);
	public List<Member> excelDownloadMember(String memberName, String memberDept, Role memberRole);

}