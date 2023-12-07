package com.web.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="COM_VOUCHERC")  //바우처 발급정보 테이블
public class VoucherC extends BaseEntity  {
	
	@Id
	private String voucherCode;			//바우처 일련번호 양식 vip + 순번
	private Date voucherIssueDate;		//바우처 발급일
	private Date voucherExdate;			//바우처 유효기간 (서비스 종료 일)
	
	//foreign 키
	//private Long voucherSeq;
	//private String customerNum;	
	
	
//	@PrePersist
//	public void onCreate() {
//        this.voucherCode = "vip" + String.format("%03d", voucherSeq);
//	}


}
