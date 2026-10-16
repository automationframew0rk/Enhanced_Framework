package com.leaftaps.testcases;

import java.util.UUID;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.framework.testng.api.base.ProjectSpecificMethods;
import com.leaftaps.pages.LoginPage;

public class TC003_CreateAccount extends ProjectSpecificMethods {
    @BeforeTest(alwaysRun = true)
    public void setValues() {
        testcaseName = "TC003_CreateAccount";
        testDescription = "Create a Leaftaps account using only the mandatory account name";
        authors = "Ram";
        category = "Accounts";
        excelFileName = "CreateAccount";
    }

    @Test(dataProvider = "fetchData", groups = {"accounts"})
    public void runCreateAccount(String accountNamePrefix) {
        // Leaftaps rejects duplicate account names; each run needs a new name.
        String accountName = accountNamePrefix + "_" + UUID.randomUUID();
        new LoginPage()
            .enterUsername()
            .enterPassword()
            .clickLogin()
            .clickCrmSfa()
            .clickCreateAccount()
            .enterAccountName(accountName)
            .clickCreateAccount()
            .verifyAccountName(accountName);
    }
}
