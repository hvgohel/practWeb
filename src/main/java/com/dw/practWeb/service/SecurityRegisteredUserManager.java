package com.dw.practWeb.service;

import com.dw.practWeb.model.Registration;

public interface SecurityRegisteredUserManager
{
    Long getCurrentRegisteredUserId();

    Registration get(String email);
}
