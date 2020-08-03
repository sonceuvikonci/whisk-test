package com.whisk.api;

import com.whisk.Actions.api.ShoppingListActionsAPI;
import com.whisk.models.ShoppingList;
import com.whisk.utils.Constants;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ShoppingListTestAPI {

    @Test(description = "1. Verify Shopping List creation with API methods successfully", priority = 1)
    public void checkShoppingListCreation() {

        ShoppingList expectedList = ShoppingListActionsAPI.getShoppingListActionsAPI()
                .createShoppingListAPI(Constants.SHOPPING_LIST_NAME)
                .getShoppingListFromResponse();

        ShoppingList actualList = ShoppingListActionsAPI.getShoppingListActionsAPI()
                .getShoppingListAPI(expectedList.getId())
                .getShoppingListFromResponse();

        Assert.assertEquals(expectedList.getId(), actualList.getId(),
                String.format("Actual shopping list id is %s but should be %s", expectedList.getId(), actualList.getId()));

        Assert.assertTrue(actualList.getContent().isEmpty(),
                String.format("Shopping list obtained by id %s is not empty", actualList.getId()));
    }

    @Test(description = "2. Verify Shopping List deleting with API methods successfully", priority = 2)
    public void checkShoppingListDeleting() {

        ShoppingList list = ShoppingListActionsAPI.getShoppingListActionsAPI()
                .createShoppingListAPI(Constants.SHOPPING_LIST_NAME)
                .check200OKResponse()
                .getShoppingListFromResponse();

        ShoppingListActionsAPI.getShoppingListActionsAPI()
                .deleteShoppingListAPI(list.getId())
                .check200OKResponse();

        String actualMessage = ShoppingListActionsAPI.getShoppingListActionsAPI()
                .getShoppingListAPI(list.getId())
                .check400OKResponse()
                .getCodeFromErrorResponse();

        String expectedMessage = "shoppingList.notFound";

        Assert.assertEquals(actualMessage, expectedMessage,
                String.format("Message in response after request to get deleted shopping list is %s, but should be %s", actualMessage, expectedMessage));
    }
}
