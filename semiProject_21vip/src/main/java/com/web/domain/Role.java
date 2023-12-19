package com.web.domain;

public enum Role {
	
	ROLE_MEMBER("평사원"), 
	ROLE_MANAGER("매니저"), 
	ROLE_ADMIN("관리자");
	
	final private String role;
	public String getRole() { //한글명 가져오기
		return role;
	}

	private Role(String role) {
		this.role = role;
	}
	
	

}
