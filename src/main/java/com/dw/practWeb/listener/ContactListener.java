package com.dw.practWeb.listener;

import com.dw.practWeb.model.Contact;

import java.util.Date;

import javax.persistence.PrePersist;

public class ContactListener {
  @PrePersist
  private void prePersist(Contact contact) {
    contact.setCreated(new Date());
  }
}
