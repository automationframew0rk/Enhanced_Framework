package com.salesforce.pages;

import com.framework.selenium.api.design.Locators;
import com.framework.testng.api.base.ProjectSpecificMethods;

public class LoginPage extends ProjectSpecificMethods{
	
	public LoginPage enterUsername() {
		clearAndType(locateElement(Locators.ID, "username"),username,"user name entered successfully");
		
		return this;
	}
	
	public LoginPage enterPassword() {
		clearAndType(locateElement(Locators.ID, "passwordd"), prop.getProperty("password"),"password entered successfully");
		return this;
	}
	
	public HomePage clickLogin() {
		click(locateElement(Locators.ID, "Login"),"Login button clicked successfully");
		return new HomePage();
	}

}
