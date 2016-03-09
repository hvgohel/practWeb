package com.dw.practWeb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dw.practWeb.model.Registration;
import com.dw.practWeb.repository.RegistrationRepository;
import com.dw.practWeb.service.RegistrationService;
import com.dw.practWeb.service.SecurityRegisteredUserManager;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class RegistrationServiceImpl implements RegistrationService {

  private ThreadLocal<Registration> currentUser = new ThreadLocal<Registration>();

  @Autowired
  private RegistrationRepository registrationRepository;

  @Autowired
  private SecurityRegisteredUserManager securityRegisteredUserManager;

  @Override
  public Registration getCurrentUser() {
    if (currentUser.get() != null) {
      return currentUser.get();
    }

    Long regId = securityRegisteredUserManager.getCurrentRegisteredUserId();

    if (regId != null) {
      return registrationRepository.findOne(regId);
    }

    return null;
  }
}
