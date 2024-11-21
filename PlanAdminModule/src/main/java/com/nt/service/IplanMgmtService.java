package com.nt.service;

import java.util.List;
import java.util.Map;

import com.nt.bindings.PlanDataInputs;

public interface IplanMgmtService {

	public String registerPlan(PlanDataInputs plan);
	public Map<Integer, String> getPlanCategories();
	public List< PlanDataInputs > showAllPlans();
	public PlanDataInputs showPlansById(Integer planId);
	public String updatePlan(PlanDataInputs plan);
	public String deletePlan(Integer planId);
	public String changePlanStatus(Integer planId,String planStatus);
	
}
