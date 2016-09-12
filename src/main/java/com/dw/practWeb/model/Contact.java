package com.dw.practWeb.model;

import com.dw.practWeb.listener.ContactListener;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "contact")
@JsonInclude(value = Include.NON_NULL)
@EntityListeners(ContactListener.class)
public class Contact extends BaseModel {
  @Column(name = "`firstname`")
  private String firstName;

  @Column(name = "`lastname`")
  private String lastName;

  @Column(name = "`mobilenumber`")
  private String mobileNumber;

  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "`customerid`")
  private Customer customer;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getMobileNumber() {
    return mobileNumber;
  }

  public void setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }
}
