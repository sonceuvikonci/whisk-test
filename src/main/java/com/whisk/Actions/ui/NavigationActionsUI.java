package com.whisk.Actions.ui;

import com.whisk.pages.pages_parts.NavigationBar;

public class NavigationActionsUI {

    private static NavigationActionsUI navigationActions;

    public ShoppingListActionsUI openShoppingPage() {
        NavigationBar.getNavigationBar()
                .clickShoppingTab();
        return ShoppingListActionsUI.getShoppingListActions();
    }

    public static NavigationActionsUI getNavigationActionsUI() {
        if (navigationActions == null)
            navigationActions = new NavigationActionsUI();
        return navigationActions;
    }
}
