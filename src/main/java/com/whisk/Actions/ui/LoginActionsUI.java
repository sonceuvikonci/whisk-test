package com.whisk.Actions.ui;

import com.whisk.pages.StartPage;

public class LoginActionsUI {

    private static LoginActionsUI loginActions;

    public void loginWithEmailPassword(String email, String password) {
        StartPage.getMainPage()
                .openMainPage()
                .clickSignInButton()
                .typeToEmailInput(email)
                .clickContinueButton()
                .typeToPasswordInput(password)
                .clickLogInButton()
                .clickLetsGetCookingButton();
    }

    public static LoginActionsUI getLoginActionsUI() {
        if (loginActions == null)
            loginActions = new LoginActionsUI();
        return loginActions;
    }
}
