package com.web.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="COM_LUXURY")
@TableGenerator(name="LUXURY_SEQ_GENERATOR",
table="ALL_SEQUENCE",
pkColumnValue="LUXURY_SEQ",
initialValue= 0,
allocationSize = 1)
public class Luxury extends BaseEntity {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LUXURY_SEQ_GENERATOR")
	@Column(updatable = false)
	private long luxurySeq;              // 글순번
	private String customerNum;            // 고객 번호       
	private String luxuryBrandName;     // 브랜드명
	
	private String luxuryDate;             // 예약일자
	private String luxuryName;           // 고객명
	private String luxuryPhone;          // 고객전화번호
	private String luxuryTime;           // 예약시간
	private long luxuryHeadCount;        // 참여인원

	
	
	
	
}
