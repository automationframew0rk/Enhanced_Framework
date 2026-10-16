package com.leaftaps.pages;

import com.framework.selenium.api.design.Locators;
import com.framework.testng.api.base.ProjectSpecificMethods;

public class CrmPage extends ProjectSpecificMethods {
    public AccountsPage clickAccounts() {
        clickUsingJs(locateElement(Locators.LINK_TEXT, "Accounts"), "Accounts tab clicked successfully");
        return new AccountsPage();
    }

    public CreateContactPage clickCreateContact() {
        clickUsingJs(locateElement(Locators.CSS, "ul.shortcuts a[href*='createContactForm']"),
            "Create Contact shortcut clicked successfully");
        return new CreateContactPage();
    }

    public CreateAccountPage clickCreateAccount() {
        clickUsingJs(locateElement(Locators.CSS, "ul.shortcuts a[href*='createAccountForm']"),
            "Create Account shortcut clicked successfully");
        return new CreateAccountPage();
    }

    public LeadsPage clickLeads() {
        clickUsingJs(locateElement(Locators.LINK_TEXT, "Leads"), "Leads link clicked successfully");
        return new LeadsPage();
    }
}
