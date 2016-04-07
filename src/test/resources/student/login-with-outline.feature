@example2
Feature: Login functionality using Scenario Outline

  Background: 
    Given I have chosen to login

  Scenario Outline: 
    When I am login for <username>
    And with <password>
    Then Check user <status>

    Examples: 
      | username | password | status |
      | hiren    | hiren    | pass   |
      | hiren    | nik      | failed |
