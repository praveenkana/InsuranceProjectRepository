package com.nt.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "ELIGIBILITY_DETAILS")

public class EligibilityDetailsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ElgibilityId;
	private Integer caseNo;
	private String holderName;
	private Long holderIdValue;
	private String planName;
	private String planStatus;
	private LocalDate planStartDate;
	private LocalDate planEndDate;
	private Double benefitAmount;
	private String denialReason;

}
