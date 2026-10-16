package com.leaftaps.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.framework.testng.api.base.ProjectSpecificMethods;
import com.leaftaps.pages.LoginPage;

public class TC002_CreateLead extends ProjectSpecificMethods {
    @BeforeTest(alwaysRun = true)
    public void setValues() {
        testcaseName = "TC002_CreateLead";
        testDescription = "Create a Leaftaps lead and verify the first name and company";
        authors = "Ram";
        category = "Leads";
        excelFileName = "CreateLead";
    }

    @Test(dataProvider = "fetchData", groups = {"smoke", "leads"})
    public void runCreateLead(String companyName, String firstName, String lastName) {
        new LoginPage()
            .enterUsername()
            .enterPassword()
            .clickLogin()
            .clickCrmSfa()
            .clickLeads()
            .clickCreateLead()
            .enterCompanyName(companyName)
            .enterFirstName(firstName)
            .enterLastName(lastName)
            .clickCreateLead()
            .verifyFirstName(firstName)
            .verifyCompanyName(companyName);
    }
}
