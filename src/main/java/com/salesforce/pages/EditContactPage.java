package com.salesforce.pages;

import com.framework.selenium.api.design.Locators;
import com.framework.testng.api.base.ProjectSpecificMethods;

public class EditContactPage extends ProjectSpecificMethods {

	public EditContactPage clickDropdown() {

		clickUsingJs(locateElement(Locators.XPATH, "//span[@class='slds-icon_container slds-icon-utility-down']/span"),"Clicked dropdown Successfully");
		return this;

	}

	public EditContactPage clickEdit() {        
		clickUsingJs(locateElement(Locators.XPATH, "//a[@title='Edit']//div"),"Clicked Edit Successfully");
		return this;

	}

	public EditContactPage enterPhoneNumber(String Phno) {
		pause(3000);
		typeAndEnter(locateElement(Locators.XPATH, "//input[@name='Phone']"), Phno,"Enter");
		return this;

	}

	public VerifyEditContactPage clickSaveOnEdit() {
		click(locateElement(Locators.XPATH, "(//button[text()='Save'])[2]"),"Clicked Save button Successfully");
		return new VerifyEditContactPage();

	}

}
