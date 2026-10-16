package com.leaftaps.pages;

import com.framework.selenium.api.design.Locators;
import com.framework.testng.api.base.ProjectSpecificMethods;

public class CreateLeadPage extends ProjectSpecificMethods {
    public CreateLeadPage enterCompanyName(String companyName) {
        clearAndType(locateElement(Locators.ID, "createLeadForm_companyName"), companyName,
            "Company name entered in the main form");
        return this;
    }

    public CreateLeadPage enterFirstName(String firstName) {
        clearAndType(locateElement(Locators.ID, "createLeadForm_firstName"), firstName,
            "First name entered successfully");
        return this;
    }

    public CreateLeadPage enterLastName(String lastName) {
        clearAndType(locateElement(Locators.ID, "createLeadForm_lastName"), lastName,
            "Last name entered successfully");
        return this;
    }

    public ViewLeadPage clickCreateLead() {
        clickUsingJs(locateElement(Locators.XPATH,
            "//form[@id='createLeadForm']//input[@type='submit' and @value='Create Lead']"),
            "Create Lead submitted from the main form");
        return new ViewLeadPage();
    }
}
