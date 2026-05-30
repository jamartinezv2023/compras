Feature: Product search in Advantage Online Shopping

  As an online customer
  I want to search products by name
  So that I can validate product discovery using data driven testing

  Background:
    Given the customer opens Advantage Online Shopping

  @coverage @search @data_driven @regression
  Scenario Outline: Search products by name
    When the customer searches for product "<product>"
    Then the customer should see search results

    Examples:
      | product |
      | HP      |
      | MOUSE   |
      | HEADSET |
