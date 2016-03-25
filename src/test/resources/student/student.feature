Feature: Add new student
    
    Background:
        Given I am logged in and I am add new student with name CucumberUser
    
    Scenario: Successfully add new student 
        When I am add new student
        Then Student added successfully
        
    Scenario Outline: Withdraw fixed amount
        Given I have <Balance> in my account
        When I check my <Balance>
        Then I should receive <Received> cash
    
        Examples:
            | Balance | Received |
            | $500    | $500     |
            | $500    | $500     |
            | $500    | $500     |