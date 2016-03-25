package com.dw.practWeb.test;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dw.practWeb.model.Student;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StudentSteps {

  private List<Student> students = new ArrayList<Student>();

  private List<List<String>> lst = new ArrayList<List<String>>();

  private Student student;

  private static final Logger LOGGER = LoggerFactory.getLogger(StudentSteps.class);

  @Given("^I am logged in and I am add new student with name ([a-zA-Z]+)$")
  public void given(String name) {
    student = new Student();
    student.setName(name);
  }

  @When("^I am add new student")
  public void when() {
    students.add(student);
    LOGGER.debug("student created successfully");
  }

  @Then("^Student added successfully")
  public void then() {
    LOGGER.info(student.getName());
  }

  @Given("^I have:$ in my account ")
  public void given_2(DataTable dataTable) {
    for (List<String> r : dataTable.raw()) {
      lst.add(r);
    }
  }

  @When("^I check my:$")
  public void when_2(DataTable dataTable) {
    LOGGER.debug("when_2() :: {} ", dataTable.raw());
  }

  @Then("^I should receive:$ cash")
  public void then_2(DataTable dataTable) {
    LOGGER.debug("then_2() :: ", dataTable.raw());
  }
}
