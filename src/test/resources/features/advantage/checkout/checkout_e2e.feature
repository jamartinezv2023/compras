Feature: Checkout E2E purchase in Advantage Online Shopping

  As an online customer
  I want to complete a full purchase
  So that I can validate registration, cart and payment confirmation

  Background:
    Given the customer opens Advantage Online Shopping

  @e2e @happy_path @registration @checkout @smoke @regression
  Scenario: Register user, select product and complete payment with SafePay
    When the customer registers a new repeatable account
    And the customer selects an available laptop product
    And the customer adds the product with quantity 2 to the cart
    And the customer completes checkout using SafePay
    Then the customer should see the successful payment confirmation
