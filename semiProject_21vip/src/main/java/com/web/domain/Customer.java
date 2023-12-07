package com.web.domain;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	private String customerNum;		//고객번호 양식 c0000 (c+네자리숫자) 
	private String customerName;	//고객명
	private String customerGrade;	//고객등급
    private String customerBirth;	//생년월일
	private String customerGender;	//성별
	private String favoriteStore;	//주이용점
	private String customerHP;	//핸드폰번호
	
	
}
