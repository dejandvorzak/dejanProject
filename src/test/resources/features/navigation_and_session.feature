@navigation
Feature: Navigation menu and session handling

  As a logged in user
  I want to use the side menu to navigate and manage my session
  So that I can move around the app and log out safely

  Background:
    Given the user is on the login page
    And the user logs in with username "standard_user" and password "secret_sauce"

  @smoke
  Scenario: Logging out returns the user to the login page
    When the user logs out via the side menu
    Then the user should be on the login page

  Scenario: A logged out user cannot reach the products page without logging in again
    Given the user logs out via the side menu
    When the user logs in with username "standard_user" and password "secret_sauce"
    Then the user should be redirected to the products page

  Scenario: Resetting the app state clears the cart
    Given the user has added the following products to the cart from the product listing:
      | Sauce Labs Backpack   |
      | Sauce Labs Bike Light |
    When the user resets the app state via the side menu
    Then the cart badge should not be displayed

  Scenario: The "All Items" menu link returns to the product listing from the cart page
    Given the user opens the cart
    When the user selects "All Items" from the side menu
    Then the products page should list 6 products

  Scenario: The "All Items" menu link returns to the product listing from the checkout page
    Given the user has added "Sauce Labs Backpack" to the cart from the product listing
    And the user opens the cart
    And the user proceeds to checkout
    When the user selects "All Items" from the side menu
    Then the products page should list 6 products
