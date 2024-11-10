package com.nt.bindings;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class AppRegistrationInputs {
	
	private String fullName;
	private String emal;
	private String gender;
	public Long idNo;
	private Long phoneNo;
	private String bankName;
	private String branchName;
	private String IFSC;
	private Long AccountNo;
	private LocalDateTime dob;

}
