package com.whisk.ui;

import com.whisk.Actions.ui.LoginActionsUI;
import com.whisk.Actions.ui.NavigationActionsUI;
import com.whisk.Actions.ui.ShoppingListActionsUI;
import com.whisk.base.ui.BaseTest;
import com.whisk.utils.Constants;
import static com.whisk.utils.Constants.*;
import com.whisk.utils.DataGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ShoppingListTestsUI extends BaseTest {

    @Test(description = "1.Verify if user can add items to shopping list successfully", priority = 1)
    public void checkAddingToShoppingList() {

        LoginActionsUI.getLoginActionsUI()
                .loginWithEmailPassword(EMAIL, PASSWORD);

        List<String> expectedFoodList = DataGenerator.generateFoodList(5);

        NavigationActionsUI.getNavigationActionsUI()
                .openShoppingPage()
                .createShoppingList(SHOPPING_LIST_NAME)
                .addFoodsToShoppingList(expectedFoodList);

        List<String> actualFoodList = ShoppingListActionsUI.getShoppingListActions()
                .collectFoodFromShoppingList(SHOPPING_LIST_NAME);

        actualFoodList.forEach((itemName) -> {
            boolean result = expectedFoodList.contains(itemName);
            getSoftAssert().assertTrue(result, itemName + " is not added to " + SHOPPING_LIST_NAME);
        });
    }

    @Test(description = "2. Verify if user can remove created shopping list successfully", priority = 2)
    public void checkRemovingShoppingList() {

        LoginActionsUI.getLoginActionsUI()
                .loginWithEmailPassword(EMAIL, PASSWORD);

        NavigationActionsUI.getNavigationActionsUI()
                .openShoppingPage()
                .createShoppingList(SHOPPING_LIST_NAME)
                .removeShoppingList(SHOPPING_LIST_NAME);

        List<String> shoppingListsNames = ShoppingListActionsUI.getShoppingListActions()
                .getShoppingListsNames();

        Assert.assertFalse(shoppingListsNames.contains(SHOPPING_LIST_NAME), Constants.SHOPPING_LIST_NAME + " is not deleted");
    }
}
