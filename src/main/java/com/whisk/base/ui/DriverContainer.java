package com.whisk.base.ui;

import org.openqa.selenium.WebDriver;

public abstract class DriverContainer {

    protected static WebDriver driver() {
        return BaseTest.getDriver();
    }
}
