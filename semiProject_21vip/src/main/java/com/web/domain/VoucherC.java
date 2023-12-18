package com.web.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name="COM_VOUCHERC")  //바우처 발급정보 테이블
public class VoucherC extends BaseEntity  {
	
	@Id
	@Column(updatable = false)
	private String CheckedVoucherCode;	//발행완료된 바우처 번호 
		
	private Date voucherIssuedDate;		//바우처 발행일
	private Date voucherExdate;			//바우처 유효기간 	
	
	@ManyToOne
    @JoinColumn(name = "CUSTOMER_NUM", referencedColumnName = "customerNum") // 외부 키 지정
    private Customer customer; //고객번호
    
    public void setCustomer(Customer customer) {
 	   this.customer=customer;
    }
	
    //바우처코드로 기본바우처 정보 조회
    
    


}
