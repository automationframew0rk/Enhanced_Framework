package com.leaftaps.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.framework.testng.api.base.ProjectSpecificMethods;
import com.leaftaps.pages.LoginPage;

public class TC001_VerifyLogin extends ProjectSpecificMethods {
    @BeforeTest(alwaysRun = true)
    public void setValues() {
        testcaseName = "TC001_VerifyLogin";
        testDescription = "Verify successful login to Leaftaps";
        authors = "Hari";
        category = "Smoke";
    }

    @Test(groups = {"smoke"})
    public void runLogin() {
        new LoginPage()
            .enterUsername()
            .enterPassword()
            .clickLogin()
            .verifyHomePage();
    }
}
