# http://cucumber.github.com/cucumber-eclipse/update-site
@example
Feature: Login functionality

  Background: 
    Given I have chosen to login

  Scenario: Login successfully
    When I am login with valid detail
      | Fields   | Value    |
      | username | Login    |
      | password | password |
    Then I check user is logged in

  Scenario: Login Failed
    When I am login with Invalid detail
      | Fields   | Value  |
      | username | hiren  |
      | password | nikunj |
    Then I check user is not logged in
