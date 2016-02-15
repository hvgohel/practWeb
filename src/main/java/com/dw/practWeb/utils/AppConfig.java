package com.dw.practWeb.utils;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Value;

@Named
public class AppConfig
{
    @Value("${test.sample}")
    private String test;

    public String getTest()
    {
        return test;
    }
}
