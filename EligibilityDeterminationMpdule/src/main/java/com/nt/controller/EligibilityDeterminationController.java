package com.nt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.bindings.EligibilityDetailsOutput;
import com.nt.service.IEligibilityDeterminationService;

@RestController
@RequestMapping("/ed-module")
public class EligibilityDeterminationController {
	@Autowired
	private IEligibilityDeterminationService edservice;

	@PostMapping("/save-eddetails")
	public ResponseEntity<EligibilityDetailsOutput> getEligibilityDetails(@PathVariable Integer caseNo){
		
		EligibilityDetailsOutput elgiout = edservice.determineEligibility(caseNo);
		return new ResponseEntity<EligibilityDetailsOutput> (elgiout,HttpStatus.OK);
	
	}
}
