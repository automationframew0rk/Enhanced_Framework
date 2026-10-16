package com.leaftaps.pages;

import com.framework.selenium.api.design.Locators;
import com.framework.testng.api.base.ProjectSpecificMethods;

public class ViewAccountPage extends ProjectSpecificMethods {
    public ViewAccountPage verifyAccountName(String accountName) {
        verifyExactText(locateElement(Locators.ID, "sectionHeaderTitle_accounts"), "Account Details",
            "Saved account details page is displayed");
        // Leaftaps appends the generated account ID to the displayed name.
        verifyPartialText(locateElement(Locators.XPATH,
            "//td[span[normalize-space()='Account Name']]/following-sibling::td[1]/span"), accountName,
            "Created account displays name: " + accountName);
        return this;
    }
}
