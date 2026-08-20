@product_details
Feature: Product details page

  As a logged in user
  I want to open a single product's detail page
  So that I can review it before deciding to buy it

  Background:
    Given the user is on the login page
    And the user logs in with username "standard_user" and password "secret_sauce"

  @smoke
  Scenario: Opening a product from the listing shows its details
    When the user opens the details of "Sauce Labs Backpack"
    Then the product details page should show name "Sauce Labs Backpack"
    And the product details page should show a description and a price

  Scenario: Adding a product to the cart from the details page
    Given the user opens the details of "Sauce Labs Backpack"
    When the user adds the product to the cart from the details page
    Then the cart badge should show "1"
    And the product button on the details page should now say "Remove"

  Scenario: Removing a product from the cart from the details page
    Given the user opens the details of "Sauce Labs Backpack"
    And the user has added the product to the cart from the details page
    When the user removes the product from the cart from the details page
    Then the cart badge should not be displayed

  @smoke
  Scenario: Navigating back to the product listing from the details page
    Given the user opens the details of "Sauce Labs Backpack"
    When the user navigates back to the products page
    Then the products page should list 6 products

  Scenario: Cart state is preserved when navigating back from the details page
    Given the user opens the details of "Sauce Labs Backpack"
    And the user adds the product to the cart from the details page
    When the user navigates back to the products page
    Then the cart badge should show "1"
