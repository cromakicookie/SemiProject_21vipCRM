package com.web.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="COM_MEMBER")
public class Member{

	@Id
	private String username;
	private String password;
	private String memberName;
	private String memberBirth;
	private String memberDept;
	
	@Enumerated(EnumType.STRING)
	private Role memberRole;
	
	private String memberFile;

	
}
