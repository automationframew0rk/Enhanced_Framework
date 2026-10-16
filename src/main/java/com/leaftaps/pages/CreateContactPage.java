package com.leaftaps.pages;

import com.framework.selenium.api.design.Locators;
import com.framework.testng.api.base.ProjectSpecificMethods;

public class CreateContactPage extends ProjectSpecificMethods {
    public CreateContactPage enterFirstName(String firstName) {
        clearAndType(locateElement(Locators.CSS, "#createContactForm #firstNameField"), firstName,
            "Contact first name entered in the main form");
        return this;
    }

    public CreateContactPage enterLastName(String lastName) {
        clearAndType(locateElement(Locators.CSS, "#createContactForm #lastNameField"), lastName,
            "Contact last name entered in the main form");
        return this;
    }

    public ViewContactPage clickCreateContact() {
        clickUsingJs(locateElement(Locators.CSS, "#createContactForm input[type='submit']"),
            "Create Contact submitted from the main form");
        return new ViewContactPage();
    }
}
