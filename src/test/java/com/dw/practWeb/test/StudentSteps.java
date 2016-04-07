package com.dw.practWeb.test;

import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class StudentSteps {

  WebDriver driver = null;

  private static final Logger LOGGER = LoggerFactory.getLogger(StudentSteps.class);

  @Before
  public void setup() {
    driver = new FirefoxDriver();
  }

  @After
  public void shutdown() {
    driver.close();
  }

  @Given("^I have chosen to login")
  public void chooseLogin() throws MalformedURLException {
    driver.navigate().to("http://localhost:8080/login");
  }

  @When("^I am login with ([A-z]+) detail$")
  public void loginWithValidDetail(String str, DataTable dataTable) {
    List<List<String>> rec = dataTable.raw();

    driver.findElement(By.name(rec.get(1).get(0))).sendKeys(rec.get(1).get(1));
    driver.findElement(By.name(rec.get(2).get(0))).sendKeys(rec.get(2).get(1));
    driver.findElement(By.name("login")).click();
  }

  @Then("^I check user is logged in")
  public void loginSuccess() {
    Assert.assertTrue(driver.getTitle().equals("home"));
  }

  @Then("^I check user is not logged in")
  public void loginFailed() {
    Assert.assertTrue(driver.getTitle().equals("login"));
  }

  @When("I am login for ([^\"]*)$")
  public void outlineWhen(String userName) {
    driver.findElement(By.name("username")).sendKeys(userName);
  }

  @And("with ([^\"]*)$")
  public void outlineAnd(String password) {
    driver.findElement(By.name("password")).sendKeys(password);
    driver.findElement(By.name("login")).click();
  }

  @Then("Check user ([^\"]*)$")
  public void outlineThen(String status) {
    Assert.assertTrue(driver.getTitle().equals("login") || driver.getTitle().equals("home"));
  }
}
