package com.dw.practWeb.controller;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dw.practWeb.config.WebConfig;
import com.dw.practWeb.model.Customer;
import com.dw.practWeb.service.CustomerService;

@RestController
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class CustomerAPIController {
  @Inject
  private CustomerService customerService;

  @RequestMapping(value = WebConfig.CREATE_CUSTOMER, method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Customer create(@RequestBody Customer customer) {
    return customerService.create(customer);
  }
}
