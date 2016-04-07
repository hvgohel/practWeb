package com.dw.practWeb.test;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(format = "pretty", features = "classpath:student", tags = "@example, @example2")
public class StudentTest {
}
