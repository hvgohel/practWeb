package com.dw.practWeb.config;

public class WebConfig
{
    // Student API
    public static final String CREATE_STUDENT = "/test/student"; // POST
    public static final String CREATE_STUDENTS = "/test/students"; // POST
    public static final String GET_STUDENTS = "/test/students"; // GET
    public static final String GET_STUDENTS_BY_CITY = "/test/{city}/students"; // GET
    public static final String GET_STUDENTS_BY_CITY_AND_NAME = "/test/studs"; // GET
}
