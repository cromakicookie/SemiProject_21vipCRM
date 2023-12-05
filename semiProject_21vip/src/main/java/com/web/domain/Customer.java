package com.web.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "COM_CUSTOMER")
public class Customer extends BaseEntity {
	
	@Id
	private Long customerNum;		//고객번호
	private String customerName;	//고객명
	private String customerGrade;	//고객등급
	private Date customerBirth;		//생년월일
	private String customerGender;	//성별
	private Date createDate;		//가입일
	private String favoriteStore;	//주이용점
	
	
}
