package com.dw.practWeb.service;

import org.springframework.stereotype.Service;

import com.dw.practWeb.model.Registration;

@Service
public interface RegistrationService {
  Registration getCurrentUser();
}
