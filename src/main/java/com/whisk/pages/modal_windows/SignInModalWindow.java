package com.whisk.pages.modal_windows;


import com.whisk.base.ui.BasePage;
import com.whisk.base.ui.Locator;
import com.whisk.base.ui.LocatorTypes;

public class SignInModalWindow extends BasePage {

    private final Locator emailInput = new Locator(LocatorTypes.ID, "_input-1");
    private final Locator continueButton = new Locator(LocatorTypes.XPATH, "//button[@data-testid='auth-continue-button']");
    private final Locator passwordInput = new Locator(LocatorTypes.ID, "_input-2");
    private final Locator loginButton = new Locator(LocatorTypes.XPATH, "//button[@data-testid='auth-login-button']");
    private final Locator letsGetCookingButton = new Locator(LocatorTypes.XPATH, "//button[@data-testid='community-onboarding-continue']");

    public SignInModalWindow typeToEmailInput(String text) {
        waitForElementVisibility("Waiting for 'Email or Phone' input visibility ...", emailInput);
        type("Typing to 'Email or Phone' input... ", text, emailInput);
        return this;
    }

    public SignInModalWindow clickContinueButton() {
        click("Clicking on 'Continue' button on 'Sign In' form'", continueButton);
        return this;
    }

    public SignInModalWindow typeToPasswordInput(String text) {
        waitForElementVisibility("Waiting for 'Password' input visibility ...", passwordInput);
        type("Typing to 'Password' input... ", text, passwordInput);
        return this;
    }

    public SignInModalWindow clickLogInButton() {
        click("Clicking on 'Log In' button on 'Sign In' form'", loginButton);
        return this;
    }

    public SignInModalWindow clickLetsGetCookingButton() {
        waitForElementVisibility("Waiting for 'Let's Get Cooking' button visibility ...", letsGetCookingButton);
        click("Clicking on 'Let's Get Cooking' button", letsGetCookingButton);
        return this;
    }
}
