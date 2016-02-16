package com.dw.practWeb.service;

import org.springframework.stereotype.Service;

import com.dw.practWeb.model.Customer;

@Service
public interface CustomerService {
  Customer create(Customer customer);
}
