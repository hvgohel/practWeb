package com.dw.practWeb.service.impl;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dw.practWeb.model.Registration;
import com.dw.practWeb.repository.RegistrationRepository;
import com.dw.practWeb.security.SecurityUser;
import com.dw.practWeb.service.SecurityRegisteredUserManager;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class SecurityRegisteredUserManagerImpl implements SecurityRegisteredUserManager {
  @Inject
  private RegistrationRepository registrationRepository;

  @Override
  public Registration get(String userName) {
    return registrationRepository.findByUserName(userName);
  }

  @Override
  public Long getCurrentRegisteredUserId() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication != null) {
      Object principal = authentication.getPrincipal();
      if (principal instanceof SecurityUser) {
        SecurityUser user = (SecurityUser) principal;
        return user.getId();
      }
    }

    return null;
  }
}
