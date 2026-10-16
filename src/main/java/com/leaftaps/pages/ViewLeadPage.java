package com.leaftaps.pages;

import com.framework.selenium.api.design.Locators;
import com.framework.testng.api.base.ProjectSpecificMethods;

public class ViewLeadPage extends ProjectSpecificMethods {
    public ViewLeadPage verifyFirstName(String firstName) {
        verifyExactText(locateElement(Locators.ID, "viewLead_firstName_sp"), firstName,
            "Created lead displays first name: " + firstName);
        return this;
    }

    public ViewLeadPage verifyCompanyName(String companyName) {
        // Leaftaps displays the company name followed by its generated identifier.
        verifyPartialText(locateElement(Locators.ID, "viewLead_companyName_sp"), companyName,
            "Created lead displays company name: " + companyName);
        return this;
    }
}
