package com.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.domain.Luxury;
import com.web.domain.Member;
import com.web.domain.Role;
import com.web.persistence.MainRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MainRepository mainRepo;
	
	@Override
	public List<Member> listAll(){
		return mainRepo.findAll(Sort.by("memberName").ascending());
	}
	
	@Override
	public Member findMember(String username) {
		return mainRepo.findByUsername(username);
	}

	//사원 수정
	@Override
	public int updateMember(Member member) {
		return mainRepo.updateMemberModal(member.getUsername(), member.getMemberEmail(),
				member.getMemberBirth(), member.getMemberDept(), member.getMemberRole());
	}
	
	//사원 삭제
	@Override
	public void deleteMember(String username) {
		mainRepo.deleteById(username);
	}

	//사원검색
//	@Override
//	public List<Member> searchMember(String memberName) {
//		return mainRepo.findByMemberNameContaining(memberName);
//	}
	
	//사원 조건 검색
	@Override
	public Page<Member> findMember(Pageable pageable, String memberDept, Role memberRole, String memberName){
		return mainRepo.findBySearchOption(pageable, memberDept, memberRole, memberName);
	}
	
	//페이지처리
	@Override
	public Page<Member> PageList(Pageable pageable) {
		return mainRepo.findAll(pageable);
	}
	
	
}
