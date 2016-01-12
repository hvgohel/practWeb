package com.dw.practWeb.listener;

import java.util.Date;

import javax.persistence.PrePersist;

import com.dw.practWeb.model.Registration;

public class RegistrationListener
{
    @PrePersist
    private void prePersist(Registration registration)
    {
        registration.setCreated(new Date());
    }
}
