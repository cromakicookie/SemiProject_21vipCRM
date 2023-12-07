package com.web.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "COM_MEMO")
@TableGenerator(name="MEMO_SEQ_GENERATOR",
				table="ALL_SEQUENCE",
				pkColumnValue="MEMO_SEQ",
				initialValue=0,
				allocationSize = 1)
public class CustomerMemo extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMO_SEQ_GENERATOR")
	private Long MemoNum;  //메모번호
	private int memoLevel; //중요도 체크:1  미체크:0
	private String memoContent; //내용
	
    @ManyToOne
    @JoinColumn(name = "CUSTOMER_NUM", referencedColumnName = "customerNum") // 외부 키 지정
    private Customer customer;
    
    public void setCustomer(Customer customer) {
 	   this.customer=customer;
    }
	


}
