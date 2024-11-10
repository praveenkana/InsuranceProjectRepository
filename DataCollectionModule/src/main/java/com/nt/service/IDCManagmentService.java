package com.nt.service;

import java.util.List;

import com.nt.bindings.ChildInputs;
import com.nt.bindings.DCSummaryReport;
import com.nt.bindings.EducationInputs;
import com.nt.bindings.IncomeInputs;
import com.nt.bindings.PlanSelectionInputs;

public interface IDCManagmentService {
	
	public Integer generateCaseNo(Integer appId);
	public List<String> showAllPlans();
	public Integer savePlanSelection(PlanSelectionInputs inputs);
	public Integer saveIncomeDetails(IncomeInputs inputs);
	public Integer saveEducationDetails(EducationInputs inputs);
	public Integer saveChildrenDetails(List<ChildInputs> inputs);
	public DCSummaryReport showDataCollectionReport(Integer caseNo);
	
}
