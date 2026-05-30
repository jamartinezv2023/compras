Feature: E2E purchase in Advantage Online Shopping

  Background:
    Given the customer opens Advantage Online Shopping

  @e2e @happy_path @registration @checkout
  Scenario: Register user, select product and complete payment with SafePay
    When the customer registers a new repeatable account
    And the customer selects an available laptop product
    And the customer adds the product with quantity 2 to the cart
    And the customer completes checkout using SafePay
    Then the customer should see the successful payment confirmation

  @exception @login
  Scenario: Invalid login is rejected
    When the customer tries to login with invalid credentials
    Then the customer should see an invalid authentication message

  @coverage @cart
  Scenario: Add product to cart and validate quantity
    When the customer registers a new repeatable account
    And the customer selects an available speaker product
    And the customer adds the product with quantity 1 to the cart
    Then the cart should display added products

  @coverage @search
  Scenario: Search product by name
    When the customer searches for product "HP"
    Then the customer should see search results
