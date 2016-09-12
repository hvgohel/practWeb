package com.dw.practWeb.service.impl;

import com.dw.practWeb.model.Customer;
import com.dw.practWeb.repository.CustomerRepository;
import com.dw.practWeb.service.CustomerService;
import com.dw.practWeb.utils.BeanMapper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CustomerServiceImpl implements CustomerService {

  @Inject
  private CustomerRepository customerRepository;

  @Inject
  private BeanMapper beanMapper;

  @Override
  public Customer create(Customer customer) {
    // customer.setCreated(new Date());
    Customer cust = customerRepository.save(customer);

    return beanMapper.map(cust, Customer.class, "customer-1");
  }
}
