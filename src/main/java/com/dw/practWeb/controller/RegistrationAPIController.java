package com.dw.practWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dw.practWeb.service.RegistrationService;

@RestController
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class RegistrationAPIController {

  @Autowired
  private RegistrationService registrationService;

  @RequestMapping(value = "/data/current/user", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  void getCurrentuser() {
    registrationService.getCurrentUser();
  }
}
