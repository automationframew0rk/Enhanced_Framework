package com.leaftaps.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.framework.testng.api.base.ProjectSpecificMethods;
import com.leaftaps.pages.LoginPage;

public class TC005_FindAccounts extends ProjectSpecificMethods {
    @BeforeTest(alwaysRun = true)
    public void setValues() {
        testcaseName = "TC005_FindAccounts";
        testDescription = "Find an existing Leaftaps account by name and verify its details";
        authors = "Ram";
        category = "Accounts";
        excelFileName = "FindAccounts";
    }

    @Test(dataProvider = "fetchData", groups = {"find-accounts"})
    public void runFindAccounts(String accountName) {
        new LoginPage()
            .enterUsername()
            .enterPassword()
            .clickLogin()
            .clickCrmSfa()
            .clickAccounts()
            .clickFindAccounts()
            .enterAccountName(accountName)
            .clickFindAccounts()
            .openMatchingAccount(accountName)
            .verifyAccountName(accountName);
    }
}
