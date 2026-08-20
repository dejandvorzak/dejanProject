package com.dejan.automation.stepdefinitions;

import com.dejan.automation.core.DriverManager;
import com.dejan.automation.pages.ProductsPage;
import com.dejan.automation.pages.components.Header;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.List;

public class CartSteps {

    @Given("the user has added {string} to the cart from the product listing")
    @When("the user adds {string} to the cart from the product listing")
    public void the_user_adds_product_to_cart_from_listing(String productName) {
        new ProductsPage(DriverManager.getDriver()).addProductToCart(productName);
    }

    @Given("the user has added the following products to the cart from the product listing:")
    @When("the user adds the following products to the cart from the product listing:")
    public void the_user_adds_products_to_cart_from_listing(DataTable dataTable) {
        ProductsPage productsPage = new ProductsPage(DriverManager.getDriver());
        List<String> productNames = dataTable.asList();
        productNames.forEach(productsPage::addProductToCart);
    }

    @When("the user removes {string} from the cart from the product listing")
    public void the_user_removes_product_from_cart_from_listing(String productName) {
        new ProductsPage(DriverManager.getDriver()).removeProductFromCart(productName);
    }

    @Then("the cart badge should show {string}")
    public void the_cart_badge_should_show(String expectedCount) {
        String actualCount = new Header(DriverManager.getDriver()).getCartBadgeCount();
        Assert.assertEquals(actualCount, expectedCount);
    }

    @Then("the cart badge should not be displayed")
    public void the_cart_badge_should_not_be_displayed() {
        boolean displayed = new Header(DriverManager.getDriver()).isCartBadgeDisplayed();
        Assert.assertFalse(displayed, "Expected cart badge to be hidden but it was displayed");
    }
}
