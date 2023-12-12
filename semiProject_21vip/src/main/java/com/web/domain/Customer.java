package com.web.domain;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
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
	
	// 다대일(N:1) 관계 설정
    @OneToMany(mappedBy = "customer", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @OrderBy("MemoNum ASC")
    private List<CustomerMemo> customerMemoList = new ArrayList<>();
	
    //고객번호로 메모테이블의 일치하는 행 리스트를 뽑을 수 있음. 
    //내림차순 시 MemoNum DESC
	
}
