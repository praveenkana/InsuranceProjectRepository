package com.nt.bindings;

import java.util.List;

import lombok.Data;

@Data
public class DCSummaryReport {
	
	private List<ChildInputs> childDetails;
	private EducationInputs edDetails;
	private IncomeInputs incomeDetails;
	private PlanSelectionInputs planDetails;
	private String planName;
	private AppRegistrationInputs appinputs;
	

}
