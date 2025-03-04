 @tag
  Feature: Purchase the oreder from Ecommerce website
  I want to use this template for my feature file
  
  Background:
  Given I landed on Ecommerece page

  @tag2
  Scenario Outline: Positive Test of Submitting the order
  
    Given Logged in with username <name> and password <password>
    When I add prdouct <productName> to cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | username            | password       | productName  |
      | vipin1@gmail.com    | Vipin@1024     | ZARA COAT 3  |
      
