package com.web.domain;

import java.util.Date;

import javax.persistence.Column;
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
@Table(name="COM_USER")
public class User extends BaseEntity{

	@Id
	private String userId;
	private String userPw;
	private String userName;
	private String userBirth;
	private String userDept;
	
	@Enumerated(EnumType.STRING)
	private Role userRole;
	
	private String userFile;

	
}
