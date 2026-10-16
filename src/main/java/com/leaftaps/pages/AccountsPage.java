package com.leaftaps.pages;

import com.framework.selenium.api.design.Locators;
import com.framework.testng.api.base.ProjectSpecificMethods;

public class AccountsPage extends ProjectSpecificMethods {
    public FindAccountsPage clickFindAccounts() {
        clickUsingJs(locateElement(Locators.LINK_TEXT, "Find Accounts"),
            "Find Accounts shortcut clicked successfully");
        return new FindAccountsPage();
    }
}
