@product_catalog
Feature: Product catalog on the inventory page

  As a logged in user
  I want to browse and sort the product catalog
  So that I can find products and add them to my cart

  Background:
    Given the user is on the login page
    And the user logs in with username "standard_user" and password "secret_sauce"

  @smoke
  Scenario: All six products are displayed with name, price and image
    Then the products page should list 6 products
    And every product should have a name, a price and an image

  Scenario Outline: Sorting the product catalog
    When the user sorts products by "<sortOption>"
    Then the products should be ordered by "<sortOption>"

    Examples:
      | sortOption        |
      | Name (A to Z)      |
      | Name (Z to A)      |
      | Price (low to high) |
      | Price (high to low) |

  @smoke
  Scenario: Adding a single product to the cart from the listing page
    When the user adds "Sauce Labs Backpack" to the cart from the product listing
    Then the cart badge should show "1"
    And the "Sauce Labs Backpack" product button should now say "Remove"

  Scenario: Adding multiple products to the cart from the listing page
    When the user adds the following products to the cart from the product listing:
      | Sauce Labs Backpack     |
      | Sauce Labs Bike Light   |
      | Sauce Labs Fleece Jacket |
    Then the cart badge should show "3"

  Scenario: Removing a product from the cart directly from the listing page
    Given the user has added "Sauce Labs Backpack" to the cart from the product listing
    When the user removes "Sauce Labs Backpack" from the cart from the product listing
    Then the cart badge should not be displayed

  Scenario: Cart badge accurately reflects mixed add and remove actions
    When the user adds the following products to the cart from the product listing:
      | Sauce Labs Backpack   |
      | Sauce Labs Bike Light |
    And the user removes "Sauce Labs Backpack" from the cart from the product listing
    Then the cart badge should show "1"
