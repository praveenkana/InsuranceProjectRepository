package com.nt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.DC_ChildrenEntity;

public interface IDC_ChildrenRepository extends JpaRepository<DC_ChildrenEntity, Integer> {
	
	public List<DC_ChildrenEntity> findByCaseNo(Integer caseNo);

}
