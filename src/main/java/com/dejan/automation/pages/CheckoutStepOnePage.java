package com.dejan.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepOnePage extends BasePage {

    private final By pageTitle = By.className("title");
    private final By firstNameField = By.id("first-name");
    private final By lastNameField = By.id("last-name");
    private final By postalCodeField = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By cancelButton = By.id("cancel");

    public CheckoutStepOnePage(WebDriver driver) {
        super(driver);
    }

    public boolean checkoutInformationIsDisplayed() {
        return isVisible(pageTitle) && getText(pageTitle).equalsIgnoreCase("Checkout: Your Information");
    }

    public void fillInformation(String firstName, String lastName, String postalCode) {
        type(firstNameField, firstName);
        type(lastNameField, lastName);
        type(postalCodeField, postalCode);
    }

    public CheckoutStepTwoPage continueToOverview() {
        click(continueButton);
        return new CheckoutStepTwoPage(driver);
    }

    public CartPage cancel() {
        click(cancelButton);
        return new CartPage(driver);
    }
}
