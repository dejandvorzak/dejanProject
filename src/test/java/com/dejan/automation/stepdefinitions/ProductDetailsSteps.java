package com.dejan.automation.stepdefinitions;

import com.dejan.automation.core.DriverManager;
import com.dejan.automation.pages.ProductDetailsPage;
import com.dejan.automation.pages.ProductsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class ProductDetailsSteps {

    @Given("the user opens the details of {string}")
    public void the_user_opens_the_details_of(String productName) {
        new ProductsPage(DriverManager.getDriver()).openProductDetails(productName);
    }

    @Given("the user has added the product to the cart from the details page")
    @When("the user adds the product to the cart from the details page")
    public void the_user_adds_the_product_to_cart_from_details_page() {
        new ProductDetailsPage(DriverManager.getDriver()).addToCart();
    }

    @When("the user removes the product from the cart from the details page")
    public void the_user_removes_the_product_from_cart_from_details_page() {
        new ProductDetailsPage(DriverManager.getDriver()).removeFromCart();
    }

    @When("the user navigates back to the products page")
    public void the_user_navigates_back_to_the_products_page() {
        new ProductDetailsPage(DriverManager.getDriver()).backToProducts();
    }

    @Then("the product details page should show name {string}")
    public void the_product_details_page_should_show_name(String expectedName) {
        String actualName = new ProductDetailsPage(DriverManager.getDriver()).getProductName();
        Assert.assertEquals(actualName, expectedName);
    }

    @And("the product details page should show a description and a price")
    public void the_product_details_page_should_show_description_and_price() {
        ProductDetailsPage page = new ProductDetailsPage(DriverManager.getDriver());
        Assert.assertFalse(page.getProductDescription().isBlank(), "Product description should not be blank");
        Assert.assertFalse(page.getProductPrice().isBlank(), "Product price should not be blank");
    }

    @And("the product button on the details page should now say {string}")
    public void the_product_button_on_details_page_should_now_say(String expectedLabel) {
        String actualLabel = new ProductDetailsPage(DriverManager.getDriver()).getButtonLabel();
        Assert.assertEquals(actualLabel, expectedLabel);
    }
}
