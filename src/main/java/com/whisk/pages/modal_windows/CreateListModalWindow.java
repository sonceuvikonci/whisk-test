package com.whisk.pages.modal_windows;

import com.whisk.base.ui.BasePage;
import com.whisk.base.ui.Locator;
import com.whisk.base.ui.LocatorTypes;

public class CreateListModalWindow extends BasePage {

    private static CreateListModalWindow createListModalWindow;

    private final Locator nameListInput = new Locator(LocatorTypes.ID, "_input-3");
    private final Locator createButton = new Locator(LocatorTypes.XPATH, "//button[@data-testid='create-new-shopping-list-create-button']");

    public CreateListModalWindow typeToNameListInput(String text) {
        waitForElementVisibility("Waiting for name list input visibility on 'Create Shopping List' window ...", nameListInput);
        typeWithWipe("Typing name list to input", text, nameListInput);
        return this;
    }

    public CreateListModalWindow clickCreateButton() {
        click("Clicking on 'Create' button on 'Create Shopping List' window ...", createButton);
        return this;
    }

    public static CreateListModalWindow getCreateListModalWindow() {
        if (createListModalWindow == null)
            createListModalWindow = new CreateListModalWindow();
        return createListModalWindow;
    }
}
