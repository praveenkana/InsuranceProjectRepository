package com.nt.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="DC_CHILDREN")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DC_ChildrenEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer childId;
	private Integer caseNo;
	private Long childIdVal;
	private LocalDate childDob;
	
	
	
	

}
