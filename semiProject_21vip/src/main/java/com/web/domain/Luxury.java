package com.web.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="COM_LUXURY")
public class Luxury extends BaseEntity {
	
	
	@Id
	@GeneratedValue
	private long luxurySeq;              // 글순번
	private long customerNum;            // 고객 번호       
	private String luxuryBrandName;     // 브랜드명
	
	private String  luxuryDate;             // 예약일자
	private String luxuryName;           // 고객명
	private String luxuryPhone;          // 고객전화번호
	private String luxuryTime;           // 예약시간
	private long luxuryHeadCount;        // 참여인원

	
	
	
	
}
