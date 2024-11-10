package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.CommunicationEntity;

public interface IComnRepository extends JpaRepository<CommunicationEntity, Integer> {

}
