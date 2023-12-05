package com.web.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="COM_VOUCHER")
public class Voucher {
	
	@Id
	private Long voucherCode;			//바우처 일련번호
	private String voucherType;			//바우처 타입
	private String voucherService;		//테마명
	private String voucherServiceName;	//서비스이름
	private Date voucherIssueDate;		//바우처 서비스 사용 가능 시작일
	private Date voucherExdate;			//바우처 유효기간 (서비스 종료 일)
	
	

}
