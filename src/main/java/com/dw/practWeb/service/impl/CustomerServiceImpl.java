package com.dw.practWeb.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dw.practWeb.exception.IllegalArgumentException;
import com.dw.practWeb.model.Customer;
import com.dw.practWeb.model.Registration;
import com.dw.practWeb.repository.CustomerRepository;
import com.dw.practWeb.repository.RegistrationRepository;
import com.dw.practWeb.service.CustomerService;
import com.dw.practWeb.utils.BeanMapper;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CustomerServiceImpl implements CustomerService {

  private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private RegistrationRepository registrationRepository;

  @Autowired
  private BeanMapper beanMapper;

  @Autowired
  private BCryptPasswordEncoder encoder;

  @Override
  public Customer create(Customer customer) {
    LOGGER.debug("validateCustomer() :: customer creation start");
    validateCustomer(customer);

    Customer cust = customerRepository.save(customer);

    LOGGER.debug("validateCustomer() :: customer {} created successfully", cust.getRegistration().getUserName());
    return beanMapper.map(cust, Customer.class, "customer-1");
  }

  private void validateCustomer(Customer customer) {
    if (customer == null) {
      throw new IllegalArgumentException("customer object should not be null");
    }

    Registration registration = customer.getRegistration();

    if (registration == null) {
      throw new IllegalArgumentException("registration object should not be null");
    }

    String userName = registration.getUserName();
    if (StringUtils.isBlank(userName)) {
      throw new IllegalArgumentException("userName should not be null");
    }

    String password = registration.getPassword();
    if (StringUtils.isBlank(password)) {
      throw new IllegalArgumentException("password should not be null");
    }

    registration.setPassword(encoder.encode(password));
    if (userName.length() < 5) {
      throw new IllegalArgumentException("userName contain minimum 6 character");
    }

    if (password.length() < 6) {
      throw new IllegalArgumentException("password contain minimum 6 character");
    }

    Registration reg = registrationRepository.findByUserName(userName);
    if (reg != null) {
      throw new IllegalArgumentException(
          "given userName : " + userName + " is already exist, so choose different user name");
    }
  }
}
