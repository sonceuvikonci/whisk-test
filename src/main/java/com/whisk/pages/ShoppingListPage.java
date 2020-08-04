package com.whisk.pages;

import com.whisk.base.ui.BasePage;
import com.whisk.base.ui.Locator;
import com.whisk.base.ui.LocatorTypes;
import com.whisk.pages.modal_windows.CreateListModalWindow;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListPage extends BasePage {

    private static ShoppingListPage shoppingListPage;

    private final Locator createNewListLink = new Locator(LocatorTypes.XPATH, "//a/div[text()='Create new list']");
    private final Locator addItemInput = new Locator(LocatorTypes.XPATH, "//input[@data-testid='desktop-add-item-autocomplete']");
    private final Locator autoCompleteItem = new Locator(LocatorTypes.XPATH, "//div[@data-testid='desktop-add-item-autocomplete']//b/parent::div");
    private final Locator itemAddedToShoppingList = new Locator(LocatorTypes.XPATH, "//span[@data-testid='shopping-list-item-name']");
    private final Locator deleteItemMenuList = new Locator(LocatorTypes.XPATH, "//button[@data-testid='shopping-list-delete-menu-button']");
    private final Locator confirmDeleteButton = new Locator(LocatorTypes.XPATH, "//button[@data-testid='confirm-delete-button']");
    private final Locator shoppingListLink = new Locator(LocatorTypes.XPATH, "//div[@data-testid='shopping-lists-list-name']");

    public CreateListModalWindow clickCreateNewListLink() {
        waitForElementVisibility("Waiting for 'Create New List' link visibility ...", createNewListLink);
        click("Clicking on 'Create New List' link... ", createNewListLink);
        return CreateListModalWindow.getCreateListModalWindow();
    }

    public ShoppingListPage typeToAddItemInput(String text) {
        waitForElementVisibility("Waiting for 'Add item' input link visibility ...", addItemInput);
        type("Typing to 'Add item' input ...", text, addItemInput);
        return this;
    }

    public ShoppingListPage clickAutoCompleteItem() {
        waitForElementVisibility("Waiting for 'AutoComplete Item' visibility ...", autoCompleteItem);
        click("Clicking on 'AutoComplete Item' link... ", autoCompleteItem);
        return this;
    }

    public List<WebElement> getItemsAddedToShoppingList() {
        return getElements(itemAddedToShoppingList);
    }

    public List<String> getNamesItemsAddedToShoppingList() {
        List<String> _nameFoodFromShoppingList = new ArrayList<>();
        getItemsAddedToShoppingList().forEach((item) -> _nameFoodFromShoppingList.add(item.getText()));
        return _nameFoodFromShoppingList;
    }

    public ShoppingListPage clickShoppingList(String listName) {
        Locator _shoppingList = new Locator(LocatorTypes.XPATH, String.format("//div[@data-testid='shopping-lists-list-name'][contains(text(),'%s')]", listName));
        click(String.format("Clicking on %s ...", listName),
                _shoppingList);
        return this;
    }

    public ShoppingListPage clickMenuForList(String listName)  {
        Locator _shoppingListMenu = new Locator(LocatorTypes.XPATH, String.format("//div[@data-testid='shopping-lists-list-name'][contains(text(),'%s')]//parent::div/following-sibling::div[1]//button", listName));
        waitForElementVisibility("Waiting for menu visibility...",_shoppingListMenu);
        waitInSeconds(1);
        click(String.format("Clicking on menu for %s ...", listName), _shoppingListMenu);
        return this;
    }

    public ShoppingListPage clickDeleteItemMenuList() {
        waitForElementVisibility("Waiting for 'Delete list' menu item visibility... ", deleteItemMenuList);
        click("Clicking on 'Delete list' menu item ...", deleteItemMenuList);
        return this;
    }

    public ShoppingListPage clickConfirmDeleteButton() {
        waitForElementVisibility("Waiting for 'Confirm Delete' button visibility ", confirmDeleteButton);
        click("Clicking on 'Confirm Delete' button ...", confirmDeleteButton);
        return this;
    }

    public List<WebElement> getShoppingLists() {
        return getElements(shoppingListLink);
    }

    public static ShoppingListPage getShoppingListPage() {
        if (shoppingListPage == null)
            shoppingListPage = new ShoppingListPage();
        return shoppingListPage;
    }
}
