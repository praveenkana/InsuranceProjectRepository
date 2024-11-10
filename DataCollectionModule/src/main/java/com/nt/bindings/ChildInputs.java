package com.nt.bindings;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ChildInputs {
	
	private Integer childId;
	private Integer caseNo;
	private Long childIdVal;
	private LocalDate childDob;

}
