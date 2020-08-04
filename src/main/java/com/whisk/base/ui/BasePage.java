package com.whisk.base.ui;

import com.whisk.utils.Constants;
import com.whisk.utils.Reporter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage extends DriverContainer{

    public WebElement getElement(Locator locator, Object... args) {
        By by = locator.getLocator(args);
        return driver().findElement(by);
    }

    public List<WebElement> getElements(Locator locator, Object... args) {
        By by = locator.getLocator(args);
        return driver().findElements(by);
    }

    public String getElementText(String message, Locator locator, Object... args) {
        Reporter.log(message);
        WebElement element = getElement(locator, args);
        return element.getText();
    }

    public void type(String message, String value, Locator locator, Object... args) {
        Reporter.log(message);
        WebElement inputElement = getElement(locator, args);
        inputElement.clear();
        inputElement.sendKeys(value);
    }

    public void typeWithWipe(String message, String value, Locator locator, Object... args) {
        Reporter.log(message);
        WebElement inputElement = getElement(locator, args);
        String oldValue = inputElement.getAttribute("value");
        for (int i = 1; i <= oldValue.length(); i++) {
            inputElement.sendKeys(Keys.ARROW_RIGHT);
        }
        for (int i = 1; i <= oldValue.length(); i++) {
            inputElement.sendKeys(Keys.BACK_SPACE);
        }
        inputElement.sendKeys(value);
    }

    public void click(String message, Locator locator, Object... args) {
        Reporter.log(message);
        WebElement element = getElement(locator, args);
        element.click();
    }

    public void switchToNewTab() {
        for (String winHandle : driver().getWindowHandles()) {
            driver().switchTo().window(winHandle);
        }
    }

    public void waitForElementVisibility(String message, Locator locator, Object... args) {
        Reporter.log(message);
        By by = locator.getLocator(args);
        WebDriverWait wait = new WebDriverWait(driver(), Constants.ELEMENT_TIMEOUT_SIXTY_SECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void openPage(String url) {
        Reporter.log("Opening " + url + " url address...");
        driver().get(url);
    }

    public void waitInSeconds(int seconds) {
        try {
            Reporter.log("Waiting '" + seconds + "' seconds timeout...");
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
