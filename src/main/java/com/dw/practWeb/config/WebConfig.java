package com.dw.practWeb.config;

public class WebConfig {
  // Student API
  public static final String CREATE_STUDENT = "/test/student"; // POST
  public static final String CREATE_STUDENTS = "/test/students"; // POST
  public static final String UPDATE_STUDENT = "/test/student/{id}"; // PATCH
  public static final String DELETE_STUDENT = "/test/student/{id}"; // DELETE
  public static final String GET_STUDENT = "/test/student"; // GET
  public static final String GET_STUDENT_BY_IDS = "/test/students"; // GET
  public static final String GET_STUDENT_BY_ID = "/test/student/{id}"; // GET
  public static final String GET_STUDENTS_BY_CITY = "/test/{city}/students"; // GET
  public static final String GET_STUDENTS_BY_CITY_AND_NAME = "/test/studs"; // GET

  // Customer API
  public static final String CREATE_CUSTOMER = "/data/customer"; // POST
}
