package com.nt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="DC_EDUCATION")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DC_EducationEntity {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer educationId;
	private String highestQualification;
	private Integer caseNo;
	private Integer passOutYear;
	
	

}
