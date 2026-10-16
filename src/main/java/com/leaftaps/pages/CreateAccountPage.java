package com.leaftaps.pages;

import com.framework.selenium.api.design.Locators;
import com.framework.testng.api.base.ProjectSpecificMethods;

public class CreateAccountPage extends ProjectSpecificMethods {
    public CreateAccountPage enterAccountName(String accountName) {
        clearAndType(locateElement(Locators.CSS, "#createAccountForm #accountName"), accountName,
            "Account name entered in the main form");
        return this;
    }

    public ViewAccountPage clickCreateAccount() {
        clickUsingJs(locateElement(Locators.CSS, "#createAccountForm input[type='submit']"),
            "Create Account submitted from the main form");
        return new ViewAccountPage();
    }
}
