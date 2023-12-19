package com.web.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name="COM_VOUCHER")  //바우처 기본정보 테이블
@TableGenerator(name="VOUCHER_SEQ_GENERATOR",
				table="ALL_SEQUENCE",
				pkColumnValue="VOUCHER_SEQ",
				initialValue=0,
				allocationSize = 1)
public class Voucher {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VOUCHER_SEQ_GENERATOR")
	private Long voucherSeq;			//바우처 시퀀스 	
	
	@Column(name = "VOUCHER_CODE")
	private String voucherCode;			//바우처 일련번호 
	
	private String voucherType;			//바우처 타입
	private String voucherService;		//테마명
	private String voucherServiceName;	//서비스이름
	
	@Temporal(TemporalType.DATE)
	private Date voucherStartDate;		//금년 바우처 서비스 사용 가능 시작일
	
	@Temporal(TemporalType.DATE)
	private Date voucherEndDate;		//금년 서비스 종료일

	private int status;					//사용여부 0:미사용, 1:사용 

	public Voucher() {
		this.status = 0;  //초기값 0, 등록버튼을 누르면 setter 사용하여 1로 변환
		
	}
	
//	 @ManyToOne
//	 @JoinColumn(name = "CHECKED_VOUCHER_CODE", referencedColumnName = "CheckedVoucherCode")
//     private VoucherC voucherC;


	
}
