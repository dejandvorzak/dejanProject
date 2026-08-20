package com.dejan.automation.stepdefinitions;

import com.dejan.automation.core.DriverManager;
import com.dejan.automation.pages.ProductsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProductCatalogSteps {

    @Then("the products page should list {int} products")
    public void the_products_page_should_list_products(int expectedCount) {
        int actualCount = new ProductsPage(DriverManager.getDriver()).getProductCount();
        Assert.assertEquals(actualCount, expectedCount);
    }

    @And("every product should have a name, a price and an image")
    public void every_product_should_have_a_name_a_price_and_an_image() {
        boolean allComplete = new ProductsPage(DriverManager.getDriver()).allProductsHaveNamePriceAndImage();
        Assert.assertTrue(allComplete, "At least one product is missing a name, price or image");
    }

    @When("the user sorts products by {string}")
    public void the_user_sorts_products_by(String sortOption) {
        new ProductsPage(DriverManager.getDriver()).sortBy(sortOption);
    }

    @Then("the products should be ordered by {string}")
    public void the_products_should_be_ordered_by(String sortOption) {
        ProductsPage productsPage = new ProductsPage(DriverManager.getDriver());
        switch (sortOption) {
            case "Name (A to Z)" -> assertSorted(productsPage.getProductNames(), Comparator.naturalOrder());
            case "Name (Z to A)" -> assertSorted(productsPage.getProductNames(), Comparator.reverseOrder());
            case "Price (low to high)" -> assertSorted(productsPage.getProductPrices(), Comparator.naturalOrder());
            case "Price (high to low)" -> assertSorted(productsPage.getProductPrices(), Comparator.reverseOrder());
            default -> throw new IllegalArgumentException("Unknown sort option: " + sortOption);
        }
    }

    @And("the {string} product button should now say {string}")
    public void the_product_button_should_now_say(String productName, String expectedLabel) {
        String actualLabel = new ProductsPage(DriverManager.getDriver()).getProductButtonLabel(productName);
        Assert.assertEquals(actualLabel, expectedLabel);
    }

    private <T> void assertSorted(List<T> actual, Comparator<T> comparator) {
        List<T> expected = actual.stream().sorted(comparator).collect(Collectors.toList());
        Assert.assertEquals(actual, expected, "Products are not ordered as expected");
    }
}
