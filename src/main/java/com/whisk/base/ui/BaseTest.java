package com.whisk.base.ui;

import com.whisk.utils.Constants;
import com.whisk.utils.Reporter;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class BaseTest implements IHookable {
    private static WebDriver driver;
    private static final String SOFT_ASSERT = "softAssert";

    public static WebDriver getDriver() {
        return driver;
    }

    public static SoftAssert getSoftAssert() {
        return getSoftAssert(org.testng.Reporter.getCurrentTestResult());
    }

    @BeforeMethod(alwaysRun = true)
    public void showMessageForTestMethod(Method testMethod) {
        System.out.println("\n========== Running " + testMethod.getName());
    }

    @BeforeMethod(alwaysRun = true)
    public void startBrowser() {
        String message = "========== Starting Whisk test " + this.getClass().toString();
        System.out.println("\n" + message);
        startChrome();
        driver.manage().window().setSize(new Dimension(1440, 900));
        Reporter.log("Current window size is: " + driver.manage().window().getSize());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        Reporter.log("Stopping WebDriver");
        closeBrowser();
    }

    protected void startChrome() {
        String platform = System.getProperty("os.name").toLowerCase();
        String pathToDriver;

        if (System.getProperty("path.to.driver") != null && !System.getProperty("path.to.driver").isEmpty()) {
            pathToDriver = System.getProperty("path.to.driver");
        } else {
            pathToDriver = (platform.contains("mac")) ? "./lib/driver/mac/chromedriver-84" : "./lib/driver/linux/chromedriver";
        }

        System.setProperty("webdriver.chrome.driver", pathToDriver);

        driver = new ChromeDriver() {
            @Override
            public WebElement findElement(By by) {
                try {
                    return by.findElement(this);
                } catch (NoSuchElementException nse) {
                    Field f;
                    try {
                        f = Throwable.class.getDeclaredField("detailMessage");
                    } catch (NoSuchFieldException e) {
                        throw nse;
                    }
                    f.setAccessible(true);
                    try {
                        String error = "\n" + by.toString() + "\n" + f.get(nse);
                        f.set(nse, error);
                    } catch (IllegalAccessException ia) {
                    }
                    throw nse;
                }
            }
        };
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Constants.ELEMENT_TIMEOUT_SIXTY_SECONDS, TimeUnit.SECONDS);
    }

    protected void closeBrowser() {
        Reporter.log("Closing browser...");
        if (driver != null)
            driver.quit();
    }

    private static SoftAssert getSoftAssert(ITestResult result) {
        Object object = result.getAttribute(SOFT_ASSERT);
        if (object instanceof SoftAssert) {
            return (SoftAssert) object;
        }
        throw new IllegalStateException("Could not find a soft assertion object");
    }

    @Override
    public void run(IHookCallBack callBack, ITestResult testResult) {
        SoftAssert softAssert = new SoftAssert();
        testResult.setAttribute(SOFT_ASSERT, softAssert);
        callBack.runTestMethod(testResult);
        softAssert = getSoftAssert(testResult);
        if (!testResult.isSuccess()) {
            Throwable throwable = testResult.getThrowable();
            if (null != throwable) {
                if (null != throwable.getCause()) {
                    throwable = throwable.getCause();
                }
                softAssert.assertNull(throwable, ExceptionUtils.getStackTrace(throwable));
            }
        }
        softAssert.assertAll();
    }
}
