package com.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.web.domain.Member;
import com.web.persistence.MainRepository;

import org.springframework.data.domain.Sort;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MainRepository mainRepo;
	
	@Override
	public List<Member> getMemberList(){
		return mainRepo.findAll(Sort.by(Sort.Direction.ASC, "memberName"));
	}
}
