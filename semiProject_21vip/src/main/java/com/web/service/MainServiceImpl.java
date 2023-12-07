package com.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.domain.Member;
import com.web.persistence.MainRepository;

@Service
public class MainServiceImpl implements MainService {

	@Autowired
	private MainRepository mainRepo;
	
	@Override
	public void insertMember(Member member) {
		mainRepo.save(member);
	}
	
	@Override
	public Member getMember(Member member) {
		return mainRepo.findById(member.getUsername()).get();
	}
}
