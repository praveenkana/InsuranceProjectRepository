package com.nt.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.nt.bindings.CmnSummary;

import jakarta.mail.MessagingException;

public interface ICorrespondenceService {

	public CmnSummary processPendingCommunication() throws FileNotFoundException, MessagingException, IOException;
	
}
