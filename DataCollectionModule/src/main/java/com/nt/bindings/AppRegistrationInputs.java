package com.nt.bindings;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class AppRegistrationInputs {
	
	private String fullName;
	public Long idNo;
	private String emal;
	private String gender;
	private String stateName;
}
