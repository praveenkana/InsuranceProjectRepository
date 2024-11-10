package com.nt.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CITIZEN_APPLICATION")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppRegistrationEntity {

	@Id
	@SequenceGenerator(name = "gen1", sequenceName = "App_Id_Sequence", initialValue = 1000, allocationSize = 1)
	@GeneratedValue(generator = "gen1", strategy = GenerationType.SEQUENCE)
	private Integer appId;
	@Column(length = 40)
	private String fullName;
	public Long idNo;
	@Column(length = 40)
	private String emal;
	@Column(length = 1)
	private String gender;
	private Long phoneNo;
	@Column(length = 30)
	private String bankName;
	@Column(length = 30)
	private String branchName;
	@Column(length = 30)
	private String IFSC;
	private Long AccountNo;
	@Column(length = 30)
	private String stateName;
	private LocalDateTime dob;
	@Column(length = 40)
	private String createdBy;
	@Column(length = 40)
	private String updatedBy;
	@Column(updatable = false)
	@CreationTimestamp
	private LocalDateTime createdOn;
	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDateTime updatedOn;

}
