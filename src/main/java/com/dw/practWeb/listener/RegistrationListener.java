package com.dw.practWeb.listener;

import java.util.Date;

import javax.persistence.PrePersist;

import com.dw.practWeb.model.Registration;
import com.dw.practWeb.model.Registration.Role;

public class RegistrationListener {
  @PrePersist
  private void prePersist(Registration registration) {
    registration.setCreated(new Date());
    registration.setRole(Role.USER);
  }
}
