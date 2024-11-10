package com.nt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.bindings.AppRegistrationInputs;
import com.nt.service.IApplicationRegistrationService;
import com.nt.service.IllegalIdValueException;

@RestController
@RequestMapping("/citizen-ar-api")
public class ApplicationRegistrationController {

	@Autowired
	private IApplicationRegistrationService regService;

	@PostMapping("/save")
	public ResponseEntity<String> saveCitizenApplication(@RequestBody AppRegistrationInputs inputs)
			throws IllegalIdValueException {

		Integer appId = regService.applicationRegistrationService(inputs);
		if (appId > 0)
			return new ResponseEntity<String>("citizen registered with id " + appId, HttpStatus.OK);

		else
			return new ResponseEntity<String>("citizen not from the requeste state or invalid idValue",
					HttpStatus.BAD_REQUEST);

	}
}
