package com.dw.practWeb.listener;

import java.util.Date;

import javax.persistence.PrePersist;

import com.dw.practWeb.model.Customer;

public class CustomerListener {
  @PrePersist
  private void prePersist(Customer customer) {
    customer.setCreated(new Date());
  }
}
