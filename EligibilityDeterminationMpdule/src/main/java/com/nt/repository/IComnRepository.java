package com.nt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.CmnEntity;

public interface IComnRepository extends JpaRepository<CmnEntity, Integer> {

	
}
