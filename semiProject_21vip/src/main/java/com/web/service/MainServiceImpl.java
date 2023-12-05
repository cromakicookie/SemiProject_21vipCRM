package com.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.domain.User;
import com.web.persistence.MainRepository;

@Service
public class MainServiceImpl implements MainService {

	@Autowired
	private MainRepository mainRepo;
	
	@Override
	public void insertUser(User user) {
		mainRepo.save(user);
	}
	
	@Override
	public User getUser(User user) {
		return mainRepo.findById(user.getUserId()).get();
	}
}
