package com.dejan.automation.pages;

import com.dejan.automation.pages.components.Header;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class CartPage extends BasePage {

    private final By pageTitle = By.className("title");
    private final By cartItems = By.className("cart_item");
    private final By cartItemName = By.className("inventory_item_name");
    private final By continueShoppingButton = By.id("continue-shopping");
    private final By checkoutButton = By.id("checkout");

    public final Header header;

    public CartPage(WebDriver driver) {
        super(driver);
        this.header = new Header(driver);
    }

    public boolean isDisplayed() {
        return isVisible(pageTitle) && getText(pageTitle).equalsIgnoreCase("Your Cart");
    }

    public int getItemCount() {
        return driver.findElements(cartItems).size();
    }

    public List<String> getItemNames() {
        return driver.findElements(cartItemName).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void removeItem(String productName) {
        By locator = By.xpath(
                "//div[@class='cart_item'][.//div[@class='inventory_item_name' and text()='"
                        + productName + "']]//button");
        waitForClickable(locator).click();
    }

    public ProductsPage continueShopping() {
        click(continueShoppingButton);
        return new ProductsPage(driver);
    }

    public CheckoutStepOnePage proceedToCheckout() {
        click(checkoutButton);
        return new CheckoutStepOnePage(driver);
    }
}
