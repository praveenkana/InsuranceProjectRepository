package com.nt.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.bindings.PlanDataInputs;
import com.nt.service.IplanMgmtService;

@RestController
@RequestMapping("/plan-api")
public class PlanAdminController {

	@Autowired
	private IplanMgmtService service;

	@PostMapping("/save-plan")
	public ResponseEntity<String> savePlan(@RequestBody PlanDataInputs plan) {

		String msg = service.registerPlan(plan);

		return new ResponseEntity<String>(msg, HttpStatus.OK);

	}

	@GetMapping("/categories")
	public ResponseEntity<Map<Integer, String>> fetchPlanCategories() {

		Map<Integer, String> categories = service.getPlanCategories();

		return new ResponseEntity<Map<Integer, String>>(categories, HttpStatus.OK);

	}

	@GetMapping("/plans")
	public ResponseEntity<List<PlanDataInputs>> fetchAllPlans() {
		List<PlanDataInputs> list = service.showAllPlans();
		return new ResponseEntity<List<PlanDataInputs>>(list, HttpStatus.OK);

	}

	@GetMapping("/find/{planId}")
	public ResponseEntity<PlanDataInputs> getPlansById(@PathVariable Integer planId) {

		PlanDataInputs plan = service.showPlansById(planId);
		return new ResponseEntity<PlanDataInputs>(plan, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<String> updatePlanDetails(@RequestBody PlanDataInputs plan) {
		String msg = service.updatePlan(plan);

		return new ResponseEntity<String>(msg, HttpStatus.OK);

	}

	@DeleteMapping("/delete/{planId}")
	public ResponseEntity<String> removePlanById(@PathVariable Integer planId) {

		String msg = service.deletePlan(planId);

		return new ResponseEntity<String>(msg, HttpStatus.OK);

	}
	@PutMapping("/status/{planId}/{status}")
	
	public ResponseEntity<String> changePlanStatus(Integer planId,String status){
		
		String msg = service.changePlanStatus(planId, status);
		
		return new ResponseEntity<String>(msg,HttpStatus.OK);
		
	}

}
