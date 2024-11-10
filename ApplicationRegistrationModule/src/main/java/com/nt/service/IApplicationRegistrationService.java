package com.nt.service;

import com.nt.bindings.AppRegistrationInputs;

public interface IApplicationRegistrationService {
	
	
	public Integer applicationRegistrationService(AppRegistrationInputs appInputs) throws IllegalIdValueException;
	

}
