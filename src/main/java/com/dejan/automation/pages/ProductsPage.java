package com.dejan.automation.pages;

import com.dejan.automation.pages.components.Header;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ProductsPage extends BasePage {

    private final By pageTitle = By.className("title");
    private final By sortDropdown = By.className("product_sort_container");
    private final By inventoryItems = By.className("inventory_item");
    private final By itemName = By.className("inventory_item_name");
    private final By itemPrice = By.className("inventory_item_price");
    private final By itemImage = By.cssSelector(".inventory_item_img img");

    public final Header header;

    public ProductsPage(WebDriver driver) {
        super(driver);
        this.header = new Header(driver);
    }

    public boolean productsIsDisplayed() {
        return isVisible(pageTitle) && getText(pageTitle).equalsIgnoreCase("Products");
    }

    public int getProductCount() {
        return driver.findElements(inventoryItems).size();
    }

    public List<String> getProductNames() {
        List<WebElement> nameElements = driver.findElements(itemName);
        List<String> names = new ArrayList<>();

        for (WebElement element : nameElements) {
            names.add(element.getText());
        }

        return names;
    }

    public List<Double> getProductPrices() {
        List<WebElement> priceElements = driver.findElements(itemPrice);
        List<Double> prices = new ArrayList<>();

        for (WebElement element : priceElements) {
            String priceText = element.getText().replace("$", "");
            double price = Double.parseDouble(priceText);
            prices.add(price);
        }

        return prices;
    }

    public boolean allProductsHaveNamePriceAndImage() {
        List<WebElement> items = driver.findElements(inventoryItems);
        for (WebElement item : items) {
            boolean hasName = !item.findElement(itemName).getText().isBlank();
            boolean hasPrice = !item.findElement(itemPrice).getText().isBlank();
            boolean hasImage = item.findElement(itemImage).isDisplayed();

            if (!hasName || !hasPrice || !hasImage) {
                return false;
            }
        }
        return true;
    }

    public void sortBy(String visibleOptionText) {
        new Select(waitForVisible(sortDropdown)).selectByVisibleText(visibleOptionText);
    }

    public void addProductToCart(String productName) {
        productButton(productName).click();
    }

    public void removeProductFromCart(String productName) {
        productButton(productName).click();
    }

    public String getProductButtonLabel(String productName) {
        return productButton(productName).getText();
    }

    public void openProductDetails(String productName) {
        click(By.xpath("//div[contains(@class, 'inventory_item_name') and text()='" + productName + "']"));
    }

    private WebElement productButton(String productName) {
        By locator = By.xpath("//div[contains(@class, 'inventory_item_name') and text()='" + productName
                + "']/ancestor::div[@class='inventory_item']//button");
        return waitForClickable(locator);
    }
}
