package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.DC_IncomeEntity;

public interface IDCIncomeRepository extends JpaRepository<DC_IncomeEntity, Integer> {
	
	public DC_IncomeEntity findByCaseNo(Integer caseNo);

}
