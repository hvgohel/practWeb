package com.dw.practWeb.listener;

import com.dw.practWeb.model.Customer;

import java.util.Date;

import javax.persistence.PrePersist;

public class CustomerListener {
  @PrePersist
  private void prePersist(Customer customer) {
    customer.setCreated(new Date());
  }
}
