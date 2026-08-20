package com.dejan.automation.stepdefinitions;

import com.dejan.automation.core.DriverManager;
import com.dejan.automation.pages.CartPage;
import com.dejan.automation.pages.CheckoutCompletePage;
import com.dejan.automation.pages.CheckoutStepOnePage;
import com.dejan.automation.pages.CheckoutStepTwoPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CheckoutSteps {

    @Given("the user fills in checkout information with first name {string}, last name {string} and postal code {string}")
    public void the_user_fills_in_checkout_information(String firstName, String lastName, String postalCode) {
        new CheckoutStepOnePage(DriverManager.getDriver()).fillInformation(firstName, lastName, postalCode);
    }

    @And("the user continues to the overview step")
    public void the_user_continues_to_the_overview_step() {
        new CheckoutStepOnePage(DriverManager.getDriver()).continueToOverview();
    }

    @Then("the overview page should list {int} items")
    public void the_overview_page_should_list_items(int expectedCount) {
        int actualCount = new CheckoutStepTwoPage(DriverManager.getDriver()).getItemCount();
        Assert.assertEquals(actualCount, expectedCount);
    }

    @And("the total should equal the item total plus tax")
    public void the_total_should_equal_the_item_total_plus_tax() {
        CheckoutStepTwoPage overview = new CheckoutStepTwoPage(DriverManager.getDriver());
        double expectedTotal = overview.getItemTotal() + overview.getTax();
        Assert.assertEquals(overview.getTotal(), expectedTotal, 0.01);
    }

    @When("the user finishes the order")
    public void the_user_finishes_the_order() {
        new CheckoutStepTwoPage(DriverManager.getDriver()).finishOrder();
    }

    @Then("the user should see the order confirmation {string}")
    public void the_user_should_see_the_order_confirmation(String expectedMessage) {
        String actualMessage = new CheckoutCompletePage(DriverManager.getDriver()).getConfirmationMessage();
        Assert.assertEquals(actualMessage, expectedMessage);
    }

    @When("the user cancels the checkout")
    public void the_user_cancels_the_checkout() {
        WebDriver driver = DriverManager.getDriver();
        if (driver.getCurrentUrl().contains("step-two")) {
            new CheckoutStepTwoPage(driver).cancel();
        } else {
            new CheckoutStepOnePage(driver).cancel();
        }
    }

    @Then("the user should be on the cart page")
    public void the_user_should_be_on_the_cart_page() {
        boolean displayed = new CartPage(DriverManager.getDriver()).isDisplayed();
        Assert.assertTrue(displayed, "Expected to be on the cart page");
    }

    @When("the user goes back home")
    public void the_user_goes_back_home() {
        new CheckoutCompletePage(DriverManager.getDriver()).goBackHome();
    }
}
