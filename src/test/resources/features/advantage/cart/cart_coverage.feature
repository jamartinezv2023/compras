Feature: Shopping cart coverage in Advantage Online Shopping

  As an online customer
  I want to add products to the cart
  So that I can validate cart behavior and product persistence

  Background:
    Given the customer opens Advantage Online Shopping

  @coverage @cart @regression
  Scenario: Add product to cart and validate quantity
    When the customer registers a new repeatable account
    And the customer selects an available speaker product
    And the customer adds the product with quantity 1 to the cart
    Then the cart should display added products
