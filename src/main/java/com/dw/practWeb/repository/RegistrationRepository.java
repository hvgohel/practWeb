package com.dw.practWeb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dw.practWeb.model.Registration;

public interface RegistrationRepository
    extends JpaRepository<Registration, Long>, JpaSpecificationExecutor<Registration> {
  Registration findByUserName(String userName);
}
