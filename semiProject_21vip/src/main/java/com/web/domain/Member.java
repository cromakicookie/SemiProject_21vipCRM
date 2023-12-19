package com.web.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name="COM_MEMBER")
public class Member{

	@Id
	private String username; //Id
	private String password; //비밀번호
	private String memberName; //사원이름
	private String memberBirth; //사원 생년월일
	private String memberDept; //사원 부서
	private String memberEmail; //사원 이메일
	
	@Enumerated(EnumType.STRING)
	private Role memberRole; // 사원 직급
	
	@OneToOne
    @JoinColumn(name="FILE_NUMBER")
    private dataFile memberFile; //사원 프로필 사진

	
}
