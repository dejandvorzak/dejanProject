package com.dejan.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage extends BasePage {

    private final By confirmationHeader = By.className("complete-header");
    private final By backHomeButton = By.id("back-to-products");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public String getConfirmationMessage() {
        return getText(confirmationHeader);
    }

    public ProductsPage goBackHome() {
        click(backHomeButton);
        return new ProductsPage(driver);
    }
}
