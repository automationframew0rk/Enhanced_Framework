package com.salesforce.pages;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.framework.selenium.api.design.Locators;
import com.framework.testng.api.base.ProjectSpecificMethods;

public class LoginPage extends ProjectSpecificMethods{
	
	public LoginPage enterUsername() {
		clearAndType(locateElement(Locators.ID, "username"),username,"user name entered successfully");
		
		return this;
	}
	
	// Salesforce shows only the username box first; clicking Log In reveals the password box
	public LoginPage clickContinue() {
		click(locateElement(Locators.ID, "Login"),"Log In clicked to show the password box");
		return this;
	}

	public LoginPage enterPassword() {
		clearAndType(locateElement(Locators.ID, "password"), prop.getProperty("password"),"password entered successfully");
		return this;
	}

	// After login Salesforce may ask for an SMS code; type it in the browser within 60 seconds
	public HomePage clickLogin() {
		click(locateElement(Locators.ID, "Login"),"Login button clicked successfully");
		waitForHomePageUrl(60);
		return new HomePage();
	}

	private void waitForHomePageUrl(int seconds) {
		String description = "Home page opened after login (and SMS code, if asked)";
		try {
			new WebDriverWait(getDriver(), Duration.ofSeconds(seconds)).until(ExpectedConditions.urlContains("/lightning/"));
			reportStep(description, "pass");
		} catch (Exception e) {
			handleFailure(e, description);
		}
	}

}
