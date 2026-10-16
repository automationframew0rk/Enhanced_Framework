package com.leaftaps.pages;

import com.framework.selenium.api.design.Locators;
import com.framework.testng.api.base.ProjectSpecificMethods;

public class ViewContactPage extends ProjectSpecificMethods {
    public ViewContactPage verifyFirstName(String firstName) {
        verifyExactText(locateElement(Locators.ID, "sectionHeaderTitle_contacts"), "View Contact",
            "Saved contact details page is displayed");
        verifyExactText(locateElement(Locators.ID, "viewContact_firstName_sp"), firstName,
            "Created contact displays first name: " + firstName);
        return this;
    }

    public ViewContactPage verifyLastName(String lastName) {
        verifyExactText(locateElement(Locators.ID, "viewContact_lastName_sp"), lastName,
            "Created contact displays last name: " + lastName);
        return this;
    }
}
