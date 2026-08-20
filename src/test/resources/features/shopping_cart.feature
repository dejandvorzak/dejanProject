@cart
Feature: Shopping cart

  As a logged in user
  I want to review and modify the contents of my cart
  So that I only check out with the items I want

  Background:
    Given the user is on the login page
    And the user logs in with username "standard_user" and password "secret_sauce"

  Scenario: The cart is empty by default
    When the user opens the cart
    Then the cart should contain 0 items

  @smoke
  Scenario: Items added on the listing page appear in the cart
    Given the user has added the following products to the cart from the product listing:
      | Sauce Labs Backpack   |
      | Sauce Labs Bike Light |
    When the user opens the cart
    Then the cart should contain 2 items
    And the cart should list "Sauce Labs Backpack"
    And the cart should list "Sauce Labs Bike Light"

  Scenario: Removing an item from the cart page
    Given the user has added "Sauce Labs Backpack" to the cart from the product listing
    And the user opens the cart
    When the user removes "Sauce Labs Backpack" from the cart page
    Then the cart should contain 0 items
    And the cart badge should not be displayed

  @smoke
  Scenario: Continue shopping returns to the product listing
    Given the user opens the cart
    When the user clicks continue shopping
    Then the products page should list 6 products

  Scenario: Checkout button navigates to the checkout information step
    Given the user has added "Sauce Labs Backpack" to the cart from the product listing
    And the user opens the cart
    When the user proceeds to checkout
    Then the user should be on the checkout information page

  Scenario: An empty cart still allows navigating to checkout
    Given the user opens the cart
    When the user proceeds to checkout
    Then the user should be on the checkout information page
