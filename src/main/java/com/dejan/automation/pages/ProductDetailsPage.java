package com.dejan.automation.pages;

import com.dejan.automation.pages.components.Header;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailsPage extends BasePage {

    private final By productName = By.className("inventory_details_name");
    private final By productDescription = By.className("inventory_details_desc");
    private final By productPrice = By.className("inventory_details_price");
    private final By addOrRemoveButton = By.className("btn_inventory");
    private final By backButton = By.id("back-to-products");

    public final Header header;

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
        this.header = new Header(driver);
    }

    public String getProductName() {
        return getText(productName);
    }

    public String getProductDescription() {
        return getText(productDescription);
    }

    public String getProductPrice() {
        return getText(productPrice);
    }

    public void addToCart() {
        click(addOrRemoveButton);
    }

    public void removeFromCart() {
        click(addOrRemoveButton);
    }

    public String getButtonLabel() {
        return getText(addOrRemoveButton);
    }

    public ProductsPage backToProducts() {
        click(backButton);
        return new ProductsPage(driver);
    }
}
