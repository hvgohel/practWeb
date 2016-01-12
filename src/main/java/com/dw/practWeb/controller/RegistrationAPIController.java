package com.dw.practWeb.controller;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dw.practWeb.model.Registration;

@RestController
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class RegistrationAPIController
{
}
