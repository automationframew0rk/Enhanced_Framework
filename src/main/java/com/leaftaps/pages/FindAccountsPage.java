package com.leaftaps.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import com.framework.selenium.api.design.Locators;
import com.framework.testng.api.base.ProjectSpecificMethods;

public class FindAccountsPage extends ProjectSpecificMethods {
    public FindAccountsPage enterAccountName(String accountName) {
        clearAndType(locateElement(Locators.CSS, "#findAccounts input[name='accountName']"), accountName,
            "Account name entered in the search form");
        return this;
    }

    public FindAccountsPage clickFindAccounts() {
        clickUsingJs(locateElement(Locators.XPATH,
            "//div[@id='findAccounts']//button[normalize-space()='Find Accounts']"),
            "Find Accounts search submitted");
        return this;
    }

    public ViewAccountPage openMatchingAccount(String accountName) {
        try {
            // The AJAX grid can replace its rows while search results arrive.
            getWait().ignoring(StaleElementReferenceException.class).until(driver -> {
                WebElement account = driver.findElements(
                    By.cssSelector("#findAccounts .x-grid3-col-groupName a"))
                    .stream()
                    .filter(link -> link.isDisplayed() && accountName.equals(link.getText().trim()))
                    .findFirst().orElse(null);
                if (account == null) {
                    return false;
                }
                getDriver().executeScript("arguments[0].click()", account);
                return true;
            });
            reportStep("Found and opened matching account: " + accountName, "pass");
        } catch (Exception e) {
            handleFailure(e, "Find and open account: " + accountName);
        }
        return new ViewAccountPage();
    }
}
