package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.EligibilityDetailsEntity;

public interface IEligibilityDeterminationRepository extends JpaRepository<EligibilityDetailsEntity, Integer> {
	public EligibilityDetailsEntity findByCaseNo(Integer caseNo);

}
