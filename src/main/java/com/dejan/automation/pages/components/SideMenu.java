package com.dejan.automation.pages.components;

import com.dejan.automation.pages.BasePage;
import com.dejan.automation.pages.LoginPage;
import com.dejan.automation.pages.ProductsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SideMenu extends BasePage {

    private final By menuButton = By.id("react-burger-menu-btn");
    private final By allItemsLink = By.id("inventory_sidebar_link");
    private final By logoutLink = By.id("logout_sidebar_link");
    private final By resetAppStateLink = By.id("reset_sidebar_link");

    public SideMenu(WebDriver driver) {
        super(driver);
    }

    public LoginPage logout() {
        open();
        click(logoutLink);
        return new LoginPage(driver);
    }

    public void resetAppState() {
        open();
        click(resetAppStateLink);
    }

    public ProductsPage goToAllItems() {
        open();
        click(allItemsLink);
        return new ProductsPage(driver);
    }

    private void open() {
        click(menuButton);
        waitForVisible(allItemsLink);
    }
}
