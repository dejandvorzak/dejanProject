package com.dejan.automation.stepdefinitions;

import com.dejan.automation.core.DriverManager;
import com.dejan.automation.pages.LoginPage;
import com.dejan.automation.pages.components.SideMenu;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class NavigationSteps {

    @Given("the user logs out via the side menu")
    public void the_user_logs_out_via_the_side_menu() {
        new SideMenu(DriverManager.getDriver()).logout();
    }

    @Then("the user should be on the login page")
    public void the_user_should_be_on_the_login_page() {
        boolean displayed = new LoginPage(DriverManager.getDriver()).isDisplayed();
        Assert.assertTrue(displayed, "Expected to be on the login page");
    }

    @When("the user resets the app state via the side menu")
    public void the_user_resets_the_app_state_via_the_side_menu() {
        new SideMenu(DriverManager.getDriver()).resetAppState();
    }

    @When("the user selects {string} from the side menu")
    public void the_user_selects_from_the_side_menu(String menuItem) {
        SideMenu sideMenu = new SideMenu(DriverManager.getDriver());
        switch (menuItem) {
            case "All Items" -> sideMenu.goToAllItems();
            default -> throw new IllegalArgumentException("Unsupported menu item: " + menuItem);
        }
    }
}
