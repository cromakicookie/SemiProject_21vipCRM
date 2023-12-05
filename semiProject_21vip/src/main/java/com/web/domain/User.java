package com.web.domain;

import java.util.Date;

import javax.persistence.Entity;
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
public class User {

	@Id
	private String userId;
	private String userPw;
	private String userName;
	private Date userBirth;
	private String userDept;
	private String userRole;
	private String userFile;
	private String userRegisterId;
	private Date userRegisterDate;
	private String userUpdateId;
	private Date userUpdateDate;

	
}
