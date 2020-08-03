package com.whisk.pages.pages_parts;

import com.whisk.base.ui.BasePage;
import com.whisk.base.ui.Locator;
import com.whisk.base.ui.LocatorTypes;
import com.whisk.pages.ShoppingListPage;

public class NavigationBar extends BasePage {

    private static NavigationBar navigationBar;

    private final Locator shoppingTab = new Locator(LocatorTypes.XPATH, "//div[@data-testid='shopping-list-nav-link']");

    public ShoppingListPage clickShoppingTab() {
        waitForElementVisibility("Waiting for 'Shopping tab' link visibility ...", shoppingTab);
        click("Clicking on 'Shopping tab'' link... ", shoppingTab);
        return ShoppingListPage.getShoppingListPage();
    }

    public static NavigationBar getNavigationBar() {
        if (navigationBar == null)
            navigationBar = new NavigationBar();
        return navigationBar;
    }
}
