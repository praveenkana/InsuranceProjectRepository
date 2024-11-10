package com.nt.bindings;

import java.time.LocalDate;

import lombok.Data;

@Data
public class EligibilityDetailsOutput {

	private String holderName;
	private Long holderIdValue;
	private String planName;
	private String planStatus;
	private LocalDate planStartDate;
	private LocalDate planEndDate;
	private Double benefitAmount;
	private String denialReason;
	
}
