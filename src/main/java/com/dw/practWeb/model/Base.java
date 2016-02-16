package com.dw.practWeb.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

@MappedSuperclass
public class Base {
  @Id
  private String id;

  @CreatedDate
  @Temporal(TemporalType.TIMESTAMP)
  private Date created;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  @PrePersist
  public void setEntityId() {
    this.created = new Date();
    this.id = UUID.randomUUID().toString();
  }
}
