package com.nt.bindings;

import java.time.LocalDate;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class PlanDataInputs {

	private Integer planId;
	private String planName;
	private String planDescription;
	private String activeSw;
	private LocalDate endDate;
	private LocalDate startDate;
}
