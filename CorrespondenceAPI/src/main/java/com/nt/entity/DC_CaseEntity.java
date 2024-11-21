package com.nt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DC_CaseEntity {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer caseNo;
	private Integer appId;
	private Integer planId;
	

}
