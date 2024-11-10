package com.nt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.bindings.ChildInputs;
import com.nt.bindings.DCSummaryReport;
import com.nt.bindings.EducationInputs;
import com.nt.bindings.IncomeInputs;
import com.nt.bindings.PlanSelectionInputs;
import com.nt.service.IDCManagmentService;

@RestController
@RequestMapping("/dc-api")
public class DataCollectionController {

	@Autowired
	private IDCManagmentService dcservice;

	@PostMapping("/generateCaseNo/{appId}")
	public ResponseEntity<Integer> generateCaseNumber(@PathVariable Integer appId) {
		Integer caseNo = dcservice.generateCaseNo(appId);
		return new ResponseEntity<Integer>(caseNo, HttpStatus.OK);

	}

	@GetMapping("/planNames")
	public ResponseEntity<List<String>> displayPlanNames() {

		List<String> allPlans = dcservice.showAllPlans();

		return new ResponseEntity<List<String>>(allPlans, HttpStatus.OK);

	}

	@PutMapping("/updatePlanSelection")
	public ResponseEntity<Integer> updatePlanSelection(@RequestBody PlanSelectionInputs inputs) {

		Integer caseNo = dcservice.savePlanSelection(inputs);
		return new ResponseEntity<Integer>(caseNo, HttpStatus.OK);

	}
	@PostMapping("/saveIncome")
	public ResponseEntity<Integer> saveIncomeDetails(@RequestBody IncomeInputs income){
		
		Integer caseNo = dcservice.saveIncomeDetails(income);
	
		return new ResponseEntity<Integer>(caseNo,HttpStatus.OK);
	}
	@PostMapping("/saveEducation")
	public ResponseEntity<Integer> saveEducationDetails(@RequestBody EducationInputs education){
		
		Integer caseNo = dcservice.saveEducationDetails(education);
	
		return new ResponseEntity<Integer>(caseNo,HttpStatus.OK);

	}
	@PostMapping("/save-children")
	public ResponseEntity<Integer> saveChildrenDetails(@RequestBody List<ChildInputs>children){
		Integer caseNo = dcservice.saveChildrenDetails(children);
		return new ResponseEntity<Integer>(caseNo,HttpStatus.OK);
		
	}
	@GetMapping("/summary")
	public ResponseEntity<DCSummaryReport> showDCSummary(@PathVariable Integer caseNo){
		DCSummaryReport report = dcservice.showDataCollectionReport(caseNo);
		
		return new ResponseEntity<DCSummaryReport>(report,HttpStatus.OK);
	}
}



























