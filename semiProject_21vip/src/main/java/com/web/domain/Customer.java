package com.web.domain;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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
	private String customerNum;		//고객번호 양식 C0000 
	private String customerName;	//고객명
	private String customerGrade;	//고객등급
    private String customerBirth;	//생년월일
	private String customerGender;	//성별
	private String favoriteStore;	//주이용점
	private String customerHP;	//핸드폰번호
	
	
	// 다대일(N:1) 관계 설정 -----------
	
	//고객번호로 메모 리스트 조회
    @OneToMany(mappedBy = "customer", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @OrderBy("MemoNum DESC") 											//내림차순 시 MemoNum DESC
    private List<CustomerMemo> customerMemoList = new ArrayList<>();
	
    //고객번호로 바우처 리스트 조회
    @OneToMany(mappedBy = "customer", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @OrderBy("createDate ASC") 											//내림차순 시 MemoNum DESC
    private List<VoucherC> issuedVoucherList = new ArrayList<>();    
    

    //고객번호로 명품관 예약 리스트 조회
    @OneToMany(mappedBy = "customer", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @OrderBy("createDate DESC") 											//내림차순 시 MemoNum DESC
    private List<Luxury> luxuryList = new ArrayList<>();
    
    
	
}
