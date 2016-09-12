package com.dw.practWeb.service;

import com.dw.practWeb.model.Customer;

import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
  Customer create(Customer customer);
}
