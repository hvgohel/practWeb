package com.dw.practWeb.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.dw.practWeb.listener.CustomerListener;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

@Entity
@Table(name = "customer")
@JsonSerialize(include = Inclusion.NON_NULL)
@EntityListeners(CustomerListener.class)
public class Customer extends BaseModel
{
    @Column(name = "`firstname`")
    private String firstName;
    
    @Column(name = "`lastname`")
    private String lastName;
    
    @Column(name = "`mobilenumber`")
    private String mobileNumber;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "`registrationid`")
    private Registration registration;
    
    @OneToMany
    private List<Contact> contacts;

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getMobileNumber()
    {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber)
    {
        this.mobileNumber = mobileNumber;
    }

    public Registration getRegistration()
    {
        return registration;
    }

    public void setRegistration(Registration registration)
    {
        this.registration = registration;
    }

    public List<Contact> getContacts()
    {
        return contacts;
    }

    public void setContacts(List<Contact> contacts)
    {
        this.contacts = contacts;
    }
}
