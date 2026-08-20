@checkout
Feature: Checkout flow

  As a logged in user with items in my cart
  I want to complete the checkout process
  So that I can place an order

  Background:
    Given the user is on the login page
    And the user logs in with username "standard_user" and password "secret_sauce"
    And the user has added the following products to the cart from the product listing:
      | Sauce Labs Backpack   |
      | Sauce Labs Bike Light |
    And the user opens the cart
    And the user proceeds to checkout

  @smoke
  Scenario: Completing checkout with valid information
    When the user fills in checkout information with first name "John", last name "Doe" and postal code "11000"
    And the user continues to the overview step
    Then the overview page should list 2 items
    And the total should equal the item total plus tax
    When the user finishes the order
    Then the user should see the order confirmation "Thank you for your order!"

  Scenario Outline: Checkout information validation errors
    When the user fills in checkout information with first name "<firstName>", last name "<lastName>" and postal code "<postalCode>"
    And the user continues to the overview step
    Then the user should see the error message "<errorMessage>"

    Examples:
      | firstName | lastName | postalCode | errorMessage                     |
      |           | Doe      | 11000      | Error: First Name is required    |
      | John      |          | 11000      | Error: Last Name is required     |
      | John      | Doe      |            | Error: Postal Code is required   |

  Scenario: Cancelling checkout at the information step returns to the cart
    When the user cancels the checkout
    Then the user should be on the cart page

  Scenario: Cancelling checkout at the overview step returns to the products page
    Given the user fills in checkout information with first name "John", last name "Doe" and postal code "11000"
    And the user continues to the overview step
    When the user cancels the checkout
    Then the products page should list 6 products

  @smoke
  Scenario: Cart is emptied after completing an order
    Given the user fills in checkout information with first name "John", last name "Doe" and postal code "11000"
    And the user continues to the overview step
    When the user finishes the order
    Then the cart badge should not be displayed

  Scenario: Returning home after order completion shows the product listing
    Given the user fills in checkout information with first name "John", last name "Doe" and postal code "11000"
    And the user continues to the overview step
    And the user finishes the order
    When the user goes back home
    Then the products page should list 6 products
