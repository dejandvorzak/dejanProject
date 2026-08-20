package com.dejan.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepTwoPage extends BasePage {

    private final By pageTitle = By.className("title");
    private final By cartItems = By.className("cart_item");
    private final By subtotalLabel = By.className("summary_subtotal_label");
    private final By taxLabel = By.className("summary_tax_label");
    private final By totalLabel = By.className("summary_total_label");
    private final By cancelButton = By.id("cancel");
    private final By finishButton = By.id("finish");

    public CheckoutStepTwoPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDisplayed() {
        return isVisible(pageTitle) && getText(pageTitle).equalsIgnoreCase("Checkout: Overview");
    }

    public int getItemCount() {
        return driver.findElements(cartItems).size();
    }

    public double getItemTotal() {
        return parseAmount(getText(subtotalLabel));
    }

    public double getTax() {
        return parseAmount(getText(taxLabel));
    }

    public double getTotal() {
        return parseAmount(getText(totalLabel));
    }

    public ProductsPage cancel() {
        click(cancelButton);
        return new ProductsPage(driver);
    }

    public CheckoutCompletePage finishOrder() {
        click(finishButton);
        return new CheckoutCompletePage(driver);
    }

    private double parseAmount(String labelText) {
        return Double.parseDouble(labelText.replaceAll("[^0-9.]", ""));
    }
}
