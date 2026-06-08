package com.salesforce.pages;

import com.framework.selenium.api.design.Locators;
import com.framework.testng.api.base.ProjectSpecificMethods;

public class WorkTypeGroupPage extends ProjectSpecificMethods {

	public WorkTypeGroupPage clickWorkTypeGroupTab() {

		click(locateElement(Locators.XPATH, "//span[contains(text(),'Work Type Groups')]/following::a[1]"),"Work Type Tab DD is clicked successfully");
		return this;
	}
	
	public WorkTypeGroupPage clickNewWorkTypeGroup() {

		clickUsingJs(locateElement(Locators.XPATH, "//span[text()='New Work Type Group']"),"New Work Type Group is clicked successfully");
		return this;
	}
	
	public WorkTypeGroupPage enterWorkTypeGroupName(String gName) {
		clearAndType(locateElement(Locators.XPATH, "(//span[text()='Work Type Group Name']/following::input[1])[2]"), gName,"Work Type Group Name entered successfully");
		return this;
	}
	
	public WorkTypeGroupPage enterWorkTypeDesc(String desc) {
		clearAndType(locateElement(Locators.XPATH, "//span[text()='Description']/following::textarea[1]"), desc,"Description entered successfully");
		return this;
	}
	
	public WorkTypeGroupPage selectGroupType() {

		click(locateElement(Locators.XPATH, "//span[text()='Group Type']/following::a[1]"),"Clicked");
		click(locateElement(Locators.XPATH, "//a[text()='Capacity']"),"Capacity selected successfully");
	
		return this;
	}
	
	public WorkTypeGroupPage clickWorkTypeSaveButton() {

		click(locateElement(Locators.XPATH, "//button[@name='SaveEdit']"),"Clicked");
		return this;
	}
	
	public WorkTypeGroupPage verifyWorkTypeGroups(String text) {
		verifyPartialText(locateElement(Locators.XPATH,
				"//div[@class='slds-align-middle slds-hyphenate']//span"), text,"Work type created successfully");
		return this;
	}
	
	public WorkTypeGroupPage enterWorkTypeSearchField(String search) {
		typeAndEnter(locateElement(Locators.XPATH, "//input[@placeholder='Search this list...']"), search,"Search Field entered successfully");
		return this;
	}
	
	public WorkTypeGroupPage clickShowActionDD() {

		click(locateElement(Locators.XPATH, "//div[@class='forceVirtualActionMarker forceVirtualAction']/a[@role='button']"),"Show Actions DD clicked successfully");
		return this;
	}
	
	public WorkTypeGroupPage clickEditLink() {

        click(locateElement(Locators.XPATH, "//a[@title='Edit']"),"Edit Link is clicked successfully");
        return this;
    }
	

}
