package com.web.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="COM_VOUCHER")  //바우처 기본정보 테이블
public class Voucher {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VOUCHER_SEQ_GENERATOR")
	private Long voucherSeq;			//바우처 순번 세자리숫자
	
	private String voucherType;			//바우처 타입
	private String voucherService;		//테마명
	private String voucherServiceName;	//서비스이름
	private Date voucherStartDate;		//바우처 서비스 사용 가능 시작일
	private Date voucherExdate;			//바우처 유효기간 (서비스 종료 일)
	private int status;					//사용여부 0:미사용, 1:사용
	

	
	
	
}
