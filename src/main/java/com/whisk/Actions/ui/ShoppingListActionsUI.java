package com.whisk.Actions.ui;

import com.whisk.pages.ShoppingListPage;
import com.whisk.utils.Reporter;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListActionsUI {

    private static ShoppingListActionsUI shoppingListActions;

    public ShoppingListActionsUI createShoppingList(String name) {
        Reporter.log("Creating 'Shopping List'...");

        ShoppingListPage.getShoppingListPage()
                .clickCreateNewListLink()
                .typeToNameListInput(name)
                .clickCreateButton();
        return this;
    }

    public void addFoodsToShoppingList(List<String> foodItems) {
        Reporter.log("Adding items to 'Shopping List'...");
        foodItems.forEach(this::addOneItemToShoppingList);
    }

    public ShoppingListActionsUI addOneItemToShoppingList(String item) {
        Reporter.log(String.format("Adding %s to 'Shopping List'...", item));

        ShoppingListPage.getShoppingListPage()
                .typeToAddItemInput(item)
                .clickAutoCompleteItem();
        return this;
    }

    public List<String> collectFoodFromShoppingList(String listName) {
        Reporter.log(String.format("Collecting food from %s ...",listName));

        return ShoppingListPage.getShoppingListPage()
                .clickShoppingList(listName)
                .getNamesItemsAddedToShoppingList();
    }

    public ShoppingListActionsUI removeShoppingList(String listName){
        Reporter.log(String.format("Removing shopping list %s ...",listName));

        ShoppingListPage.getShoppingListPage()
                .clickMenuForList(listName)
                .clickDeleteItemMenuList()
                .clickConfirmDeleteButton();
        return this;
    }

    public  List<String> getShoppingListsNames(){
        List<String> listNames = new ArrayList<>();
        ShoppingListPage.getShoppingListPage()
                .getShoppingLists()
                .forEach((webElement) -> listNames.add(webElement.getText()));
        return listNames;
    }

    public static ShoppingListActionsUI getShoppingListActions() {
        if (shoppingListActions == null)
            shoppingListActions = new ShoppingListActionsUI();
        return shoppingListActions;
    }
}