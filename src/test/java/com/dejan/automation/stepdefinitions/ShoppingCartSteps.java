package com.dejan.automation.stepdefinitions;

import com.dejan.automation.core.DriverManager;
import com.dejan.automation.pages.CartPage;
import com.dejan.automation.pages.CheckoutStepOnePage;
import com.dejan.automation.pages.components.Header;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class ShoppingCartSteps {

    @Given("the user opens the cart")
    public void the_user_opens_the_cart() {
        new Header(DriverManager.getDriver()).openCart();
    }

    @Then("the cart should contain {int} items")
    public void the_cart_should_contain_items(int expectedCount) {
        int actualCount = new CartPage(DriverManager.getDriver()).getItemCount();
        Assert.assertEquals(actualCount, expectedCount);
    }

    @And("the cart should list {string}")
    public void the_cart_should_list(String productName) {
        boolean present = new CartPage(DriverManager.getDriver()).getItemNames().contains(productName);
        Assert.assertTrue(present, "Expected cart to contain \"" + productName + "\"");
    }

    @When("the user removes {string} from the cart page")
    public void the_user_removes_from_the_cart_page(String productName) {
        new CartPage(DriverManager.getDriver()).removeItem(productName);
    }

    @When("the user clicks continue shopping")
    public void the_user_clicks_continue_shopping() {
        new CartPage(DriverManager.getDriver()).continueShopping();
    }

    @When("the user proceeds to checkout")
    public void the_user_proceeds_to_checkout() {
        new CartPage(DriverManager.getDriver()).proceedToCheckout();
    }

    @Then("the user should be on the checkout information page")
    public void the_user_should_be_on_the_checkout_information_page() {
        boolean displayed = new CheckoutStepOnePage(DriverManager.getDriver()).checkoutInformationIsDisplayed();
        Assert.assertTrue(displayed, "Expected to be on the checkout information page");
    }
}
