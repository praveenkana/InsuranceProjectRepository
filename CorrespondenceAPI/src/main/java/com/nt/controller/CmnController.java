package com.nt.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.bindings.CmnSummary;
import com.nt.service.ICorrespondenceService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/correspondence")
public class CmnController {
	
	@Autowired
	private ICorrespondenceService service;
	
	@GetMapping("/process-communication")
	public ResponseEntity<CmnSummary> processCommunication() throws FileNotFoundException, MessagingException, IOException{
		
		CmnSummary summary = service.processPendingCommunication();
		return new ResponseEntity<CmnSummary>(summary,HttpStatus.OK);
		
	}

}
