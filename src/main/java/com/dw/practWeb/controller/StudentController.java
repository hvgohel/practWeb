package com.dw.practWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.dw.practWeb.model.Registration;
import com.dw.practWeb.service.RegistrationService;

@RestController
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class StudentController {

  @Autowired
  private RegistrationService registrationService;

  @RequestMapping(value = "/home", method = RequestMethod.GET)
  public ModelAndView home() {
    ModelAndView model = new ModelAndView();
    Registration loginUser = registrationService.getCurrentUser();
    if (loginUser != null) {
      model.addObject("userName", loginUser.getUserName());
      model.addObject("msg", "welcome user");
    }
    return model;
  }

  @RequestMapping(value = "/admin")
  public ModelAndView login() {
    ModelAndView model = new ModelAndView();
    Registration loginUser = registrationService.getCurrentUser();
    if (loginUser != null) {
      model.addObject("userName", loginUser.getUserName());
    }
    return model;
  }
}
