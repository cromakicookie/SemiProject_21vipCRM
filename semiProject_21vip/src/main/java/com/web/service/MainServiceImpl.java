package com.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.web.domain.Member;
import com.web.persistence.MainRepository;

@Service
public class MainServiceImpl implements MainService {

	@Autowired
	private MainRepository mainRepo;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private FileService fileRepo;
	
	@Override
	public void insertMember(Member member) {
		String encPassword = bCryptPasswordEncoder.encode(member.getPassword());
		member.setPassword(encPassword);
		mainRepo.save(member);
	}
	
	@Override
	public Member getMember(Member member) {
		return mainRepo.findById(member.getUsername()).get();
	}
	
	@Override
	public boolean idValid(String username) {
		return mainRepo.existsById(username);
	}

	@Override
	public List<String> findId(Member member) {
		List<Member> findMemberList = mainRepo.findUsernameByMemberNameAndMemberBirth(member.getMemberName(), member.getMemberBirth());
		List<String> findIdList = new ArrayList<>();
		for (Member memberList : findMemberList) {
			findIdList.add(memberList.getUsername());
		}
		return findIdList;
	}
	
	@Override
	public boolean findPw(Member member) {
		String username = member.getUsername();
		if(mainRepo.findByUsername(username) ==null) {
			return false;
		}
		return true;
	}

	@Override
	public void updatePw(Member member) {
		Member updateMember = mainRepo.findByUsername(member.getUsername());
		insertMember(updateMember);
	}
	
	
	@Override
	public boolean memberEmailCheck(String memberEmail, String username) {
        Member member = mainRepo.findByUsername(username);
        if(member!=null && member.getMemberEmail().equals(memberEmail)) {
            return true;
        }
        else {
            return false;
        }
    }
	
	@Override
	public void updateFile(String username, Long fileNumber) {
		Member updateMember = mainRepo.findByUsername(username);
		updateMember.setMemberFile(fileRepo.getFile(fileNumber));
		
		mainRepo.save(updateMember);
	}
	
	@Override
	public void updateMember(Member member) {
		Member updateMember = mainRepo.findByUsername(member.getUsername());
		String encPassword = bCryptPasswordEncoder.encode(member.getPassword());
		updateMember.setPassword(encPassword);
		updateMember.setMemberEmail(member.getMemberEmail());
		
		mainRepo.save(updateMember);
	}
}

