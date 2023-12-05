package com.web.service;

import com.web.domain.User;

public interface MainService {

	void insertUser(User user);
	User getUser(User user);

}