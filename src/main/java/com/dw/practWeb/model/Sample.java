package com.dw.practWeb.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sample")
@JsonInclude(value = Include.NON_NULL)
public class Sample extends Base<String> {
  private static final long serialVersionUID = 1L;
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
