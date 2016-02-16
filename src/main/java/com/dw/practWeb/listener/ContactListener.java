package com.dw.practWeb.listener;

import java.util.Date;

import javax.persistence.PrePersist;

import com.dw.practWeb.model.Contact;

public class ContactListener {
  @PrePersist
  private void prePersist(Contact contact) {
    contact.setCreated(new Date());
  }
}
