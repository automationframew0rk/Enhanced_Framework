package com.leaftaps.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.framework.testng.api.base.ProjectSpecificMethods;
import com.leaftaps.pages.LoginPage;

public class TC004_CreateContact extends ProjectSpecificMethods {
    @BeforeTest(alwaysRun = true)
    public void setValues() {
        testcaseName = "TC004_CreateContact";
        testDescription = "Create a Leaftaps contact using only the mandatory first and last names";
        authors = "Ram";
        category = "Contacts";
        excelFileName = "CreateLeaftapsContact";
    }

    @Test(dataProvider = "fetchData", groups = {"contacts"})
    public void runCreateContact(String firstName, String lastName) {
        new LoginPage()
            .enterUsername()
            .enterPassword()
            .clickLogin()
            .clickCrmSfa()
            .clickCreateContact()
            .enterFirstName(firstName)
            .enterLastName(lastName)
            .clickCreateContact()
            .verifyFirstName(firstName)
            .verifyLastName(lastName);
    }
}
