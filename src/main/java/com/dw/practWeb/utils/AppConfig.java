package com.dw.practWeb.utils;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Value;

@Named
public class AppConfig {

  @Value("${test.sample}")
  private String test;

  public String getTest() {
    return test;
  }

  @Value("${baseURI:http://localhost:8080}")
  private String baseUri;

  public String getBaseUri() {
    return baseUri;
  }

  public void setBaseUri(String baseUri) {
    this.baseUri = baseUri;
  }
}
