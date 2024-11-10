package com.nt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;

@Entity
@Data
public class CorrespondenceEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer comnId;
	private Integer caseNo;
	@Lob
	private byte[] comnNoticepdf;
	private String comnStatus="pending";
	
}