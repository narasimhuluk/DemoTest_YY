

Feature: Error Validation

 
 @ErrorValidation
  Scenario Outline: Title of your scenario outline
    Given I Landed on Ecommerce page.
    When Login with Username <name> and <password>
    Then "Incorrect email or password." message is displayed

   Examples: 
| name  | password |
| narasimhulu3k@gmail.com |arasimha@444|
