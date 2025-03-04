
@tag
Feature: Error validation
  I want to use this template for my feature file

    

  @ErrorValidation
  Scenario Outline: Title of your scenario outline
    Given I landed on Ecommerece page
    When Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
       | username              | password     | 
       | vipin1@gmail.com      | Vipin@1024   |
