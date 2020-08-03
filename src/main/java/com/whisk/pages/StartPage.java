package com.whisk.pages;

import com.whisk.base.ui.BasePage;
import com.whisk.base.ui.Locator;
import com.whisk.base.ui.LocatorTypes;
import com.whisk.pages.modal_windows.SignInModalWindow;
import com.whisk.utils.Constants;


public class StartPage extends BasePage {

    private static StartPage mainPage;
    private final Locator singInLink = new Locator(LocatorTypes.XPATH, "//a[contains(text(),'Sign In')]");

    public StartPage openMainPage() {
        openPage(Constants.MAIN_PAGE_URL);
        return this;
    }

    public SignInModalWindow clickSignInButton() {
        waitForElementVisibility("Waiting for 'Sign In' link visibility ...", singInLink);
        click("Clicking on 'Sign In' link... ", singInLink);
        switchToNewTab();
        return new SignInModalWindow();
    }

    public static StartPage getMainPage() {
        if (mainPage == null)
            mainPage = new StartPage();
        return mainPage;
    }
}
