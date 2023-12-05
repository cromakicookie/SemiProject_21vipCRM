package com.web.domain;



import java.util.Date;

import javax.persistence.Column;
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
@Table(name="COM_CALENDAR")
@TableGenerator(name="CALENDAR_SEQ_GENERATOR",
				table="ALL_SEQUENCE",
				pkColumnValue="CALENDAR_SEQ",
				initialValue=0,
				allocationSize = 1)
public class Calendar extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CALENDAR_SEQ_GENERATOR")
	private Long eventNumber;
	
	@Column(name="EVENT_TITLE")
	private String title;
	@Column(name="EVENT_START_DATE")
    private Date start;
	@Column(name="EVENT_END_DATE")
    private Date end;

	private String eventContent;
	private String eventType;
	
	@ManyToOne
	@JoinColumn(name="FILE_NUMBER")
	private File file;
    
}
