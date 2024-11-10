package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.DC_EducationEntity;
import com.nt.entity.DC_IncomeEntity;

public interface IDC_EducationRepository extends JpaRepository<DC_EducationEntity, Integer> {
	public DC_EducationEntity findByCaseNo(Integer caseNo);

}
