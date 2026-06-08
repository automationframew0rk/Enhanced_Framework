package com.salesforce.pages;

import com.framework.selenium.api.design.Locators;
import com.framework.testng.api.base.ProjectSpecificMethods;

public class HomePage extends ProjectSpecificMethods {

	public HomePage verifyHomePage() {
		verifyDisplayed(locateElement(Locators.XPATH, "//span[text()='Home']"),"Homepage is loaded");
		return this;
	}

	public HomePage clickAppLauncher() {

		click(locateElement(Locators.XPATH, "//div[@class='slds-icon-waffle']"),"Toggle Menu button clicked successfully");
		return this;
	}

	public HomePage clickViewAll() {

		click(locateElement(Locators.XPATH, "//button[text()='View All']"),"View All link clicked successfully");
		return this;
	}

	public HomePage enterSearchField(String search) {
		clearAndType(locateElement(Locators.XPATH, "//input[@placeholder='Search apps or items...']"), search,"Search Field entered successfully");
		return this;
	}

	public IndividualsPage clickIndividualsLink() {

		click(locateElement(Locators.XPATH, "//mark[text()='Individuals']"),"Individuals link clicked successfully");
		return new IndividualsPage();
	}
	
	public WorkTypeGroupPage clickWorkTypeGroupLink() {

		click(locateElement(Locators.XPATH, "//mark[text()='Work Type']"),"Work Type Groups link clicked successfully");
		return new WorkTypeGroupPage();
	}

	public HomePage clickSales() {
		click(locateElement(Locators.XPATH, "(//p[@class='slds-truncate']/mark[text()='Sales'])[3]"),"Sales is clicked sucessfully");
		return this;
	}
	
	public TasksPage clickTasksTab() {
		clickUsingJs(locateElement(Locators.XPATH, "//span[text()='Tasks']"),"Tasks Tab is clicked sucessfully");
		return new TasksPage();
	}
	
	public HomePage typeSales() {
		typeAndEnter(locateElement(Locators.XPATH, "//div[contains(@class,'slds-form-element__control slds-grow')]//input[1]") ,"Sales","Search for sales is done successfully");
		return this;
		
	}
	
	public ContactPage clickContact() {
		
		clickUsingJs(locateElement(Locators.XPATH, "//span[text()='Contacts']"),"Clicked Contacts Successfully");
		return new ContactPage();
	}
		
	
}
