Feature: Login validation in Advantage Online Shopping

  As an online customer
  I want invalid credentials to be rejected
  So that authentication rules can be verified

  Background:
    Given the customer opens Advantage Online Shopping

  @exception @login @regression
  Scenario: Invalid login is rejected
    When the customer tries to login with invalid credentials
    Then the customer should see an invalid authentication message
