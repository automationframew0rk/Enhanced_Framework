package com.salesforce.pages;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.framework.selenium.api.design.Locators;
import com.framework.testng.api.base.ProjectSpecificMethods;

public class ContactPage extends ProjectSpecificMethods {

	public CreateContactPage clickNewContact() {
		click(locateElement(Locators.XPATH, "//div[text()='New']"),"Cliked New button Successfully");
		return new CreateContactPage();
	}
	
	
	public ContactPage searchContact(String contactName) {
		
		typeAndEnter(locateElement(Locators.XPATH, "(//input[@type='search'])[2]"), contactName,"Search for Existing Contact is Successful");
			return this;
	}
     public EditContactPage selectEdit() {
		pause(3000);
		click(locateElement(Locators.XPATH, "//span[@class='slds-icon_container slds-icon-utility-down']/span"),"Edit Contact is selelcted Successfully");
		
		return new EditContactPage();
	}

	
}
