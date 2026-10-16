package com.leaftaps.pages;

import com.framework.selenium.api.design.Locators;
import com.framework.testng.api.base.ProjectSpecificMethods;

public class LeadsPage extends ProjectSpecificMethods {
    public CreateLeadPage clickCreateLead() {
        clickUsingJs(locateElement(Locators.CSS,
            "ul.shortcuts a[href*='createLeadForm']"),
            "Create Lead shortcut clicked successfully");
        return new CreateLeadPage();
    }
}
