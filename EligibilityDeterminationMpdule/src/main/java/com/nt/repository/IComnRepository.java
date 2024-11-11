package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.CmnEntity;

public interface IComnRepository extends JpaRepository<CmnEntity, Integer> {

}
