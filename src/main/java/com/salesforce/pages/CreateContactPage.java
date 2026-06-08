package com.salesforce.pages;

import com.framework.selenium.api.design.Locators;
import com.framework.testng.api.base.ProjectSpecificMethods;

public class CreateContactPage extends ProjectSpecificMethods {

	public CreateContactPage selectSalutation() {
		pause(3000);
		click(locateElement(Locators.XPATH, "(//div[contains(@class,'slds-combobox__form-element slds-input-has-icon')]//button)[2]"),"Clicked Successfully");
		clickUsingJs(locateElement(Locators.XPATH, "//span[text()='Mr.']"),"Clicked Successfully");
		return this;
	}
	public CreateContactPage enterFirstName(String fname) {
		clearAndType(locateElement(Locators.XPATH, "//label[text()='First Name']/following::input"),fname);
		return this;
	}

	
	public CreateContactPage enterLastName(String lname) {
		clearAndType(locateElement(Locators.XPATH, "//label[text()='Last Name']/following::input"),lname);
		return this;

	}
	public VerifyNewContact clickSave() {
		click(locateElement(Locators.XPATH, "(//button[text()='Save'])[2]"),"Clicled Successfully");
		return new VerifyNewContact();

	}
	

}
