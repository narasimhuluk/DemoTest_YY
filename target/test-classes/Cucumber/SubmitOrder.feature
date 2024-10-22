

Feature: Pruchase the order from Ecommerce website
  I want to use this template for my feature file

Background:
Given I Landed on Ecommerce page.


 @Regression
Scenario Outline: Positive Test of Submitting order
Given Login with Username <name> and <password>
When I add Product <productName> to cart 
And Checkout <productName> and submit order
Then "THANKYOU FOR THE ORDER." message is displayed on the ConformationPage.

Examples: 
| name  | password |productName|
| narasimhulu3k@gmail.com |Narasimha@444|ZARA COAT 3|
      
