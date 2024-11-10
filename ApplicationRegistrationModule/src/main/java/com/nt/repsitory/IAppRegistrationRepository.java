package com.nt.repsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.AppRegistrationEntity;

public interface IAppRegistrationRepository extends JpaRepository<AppRegistrationEntity, Integer> {

}
