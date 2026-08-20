package com.dejan.automation.pages.components;

import com.dejan.automation.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ErrorBanner extends BasePage {

    private final By errorMessage = By.cssSelector("[data-test='error']");

    public ErrorBanner(WebDriver driver) {
        super(driver);
    }

    public String getMessage() {
        return getText(errorMessage);
    }
}
