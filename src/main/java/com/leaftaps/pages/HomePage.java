package com.leaftaps.pages;

import com.framework.selenium.api.design.Locators;
import com.framework.testng.api.base.ProjectSpecificMethods;

public class HomePage extends ProjectSpecificMethods {
    public HomePage verifyHomePage() {
        verifyDisplayed(locateElement(Locators.LINK_TEXT, "CRM/SFA"), "Leaftaps home page is loaded after login");
        return this;
    }

    public CrmPage clickCrmSfa() {
        clickUsingJs(locateElement(Locators.LINK_TEXT, "CRM/SFA"), "CRM/SFA link clicked successfully");
        return new CrmPage();
    }
}
