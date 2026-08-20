package com.dejan.automation.stepdefinitions;

import com.dejan.automation.core.DriverManager;
import com.dejan.automation.pages.LoginPage;
import com.dejan.automation.pages.ProductsPage;
import com.dejan.automation.pages.components.ErrorBanner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LoginSteps {

    private LoginPage loginPage;
    private ProductsPage productsPage;

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        loginPage = new LoginPage(DriverManager.getDriver());
    }

    @When("the user logs in with username {string} and password {string}")
    public void the_user_logs_in_with_username_and_password(String username, String password) {
        productsPage = loginPage.loginAs(username, password);
    }

    @Then("the user should be redirected to the products page")
    public void the_user_should_be_redirected_to_the_products_page() {
        Assert.assertTrue(productsPage.productsIsDisplayed(), "Products page was not displayed after login");
    }

    @Then("the user should see the error message {string}")
    public void the_user_should_see_the_error_message(String expectedMessage) {
        String actualMessage = new ErrorBanner(DriverManager.getDriver()).getMessage();
        Assert.assertEquals(actualMessage, expectedMessage);
    }
}
