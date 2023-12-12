package com.web.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="COM_FILE")
@TableGenerator(name="FILE_SEQ_GENERATOR",
						table="ALL_SEQUENCE",
						pkColumnValue="FILE_SEQ",
						initialValue=1,
						allocationSize = 1)
public class dataFile extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FILE_SEQ_GENERATOR")
	private Long fileNumber;
	private String fileName;
	private String fileRoot;
	
	@OneToOne(mappedBy = "file")
	private Calendar calendar;
	
}
