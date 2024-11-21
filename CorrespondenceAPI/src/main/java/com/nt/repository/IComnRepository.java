package com.nt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.CmnEntity;

public interface IComnRepository extends JpaRepository<CmnEntity, Integer> {

	public List<CmnEntity> findByComnStatus(String status);

	public CmnEntity findByCaseNo(Integer caseNo);
}
