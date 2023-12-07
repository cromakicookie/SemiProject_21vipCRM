package com.web.service;

import com.web.domain.Member;

public interface MainService {

	void insertMember(Member member);
	Member getMember(Member member);

}