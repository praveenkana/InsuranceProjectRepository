package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.nt.entity.CorrespondenceEntity;

public interface IComnRepository extends JpaRepository<CorrespondenceEntity, Integer> {

}
