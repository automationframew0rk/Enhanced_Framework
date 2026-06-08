package com.salesforce.pages;

import com.framework.selenium.api.design.Locators;
import com.framework.testng.api.base.ProjectSpecificMethods;

public class IndividualsPage extends ProjectSpecificMethods {

	public IndividualsPage clickNewButton() {

		click(locateElement(Locators.XPATH, "//div[text()='New']"),"New Button clicked successfully");
		return this;
	}

	public IndividualsPage selectSalutation() {

		click(locateElement(Locators.XPATH, "//span[text()='Salutation']/following::a[1]"),"Clicked Successfully");
		click(locateElement(Locators.XPATH, "//a[text()='Mr.']")," Mr. selected successfully");
		return this;
	}

	public IndividualsPage enterFirstName(String fName) {
		clearAndType(locateElement(Locators.XPATH, "//span[text()='First Name']/following::input[1]"), fName, "First name entered successfully");
		return this;
	}

	public IndividualsPage enterLastName(String lName) {
		clearAndType(locateElement(Locators.XPATH, "//span[text()='Last Name']/following::input[1]"), lName,"Last Name entered successfully");
		return this;
	}

	public IndividualsPage clickSaveButton() {

		click(locateElement(Locators.XPATH, "//button[@title='Save']"),"Save Button clicked successfully");
		return this;
	}

	public IndividualsPage verifyCreateIndividuals(String text) {
		verifyPartialText(locateElement(Locators.XPATH,
				"//span[@class='toastMessage slds-text-heading--small forceActionsText']"), text,"Name Matches is loaded");
		return this;
	}

	public IndividualsPage enterIndividualSearchField(String search) {
		typeAndEnter(locateElement(Locators.XPATH, "//input[@placeholder='Search this list...']"), search,"Search Field entered successfully");
		return this;
	}

	public IndividualsPage clickShowActionDD() {

		click(locateElement(Locators.XPATH,
				"//div[@class='forceVirtualActionMarker forceVirtualAction']/a[@role='button']"),"Show Actions DD clicked successfully");
		return this;
	}

	public IndividualsPage clickDeleteLink() {

		click(locateElement(Locators.XPATH, "//a[@title='Delete']"),"Delete Link is clicked successfully");
		return this;
	}

	public IndividualsPage clickPoupDeleteButton() {

		click(locateElement(Locators.XPATH, "//span[text()='Delete']"),"Delete Button is clicked successfully");
		return this;
	}
	
	public IndividualsPage verifyDeleteIndividuals(String text) {
		verifyPartialText(locateElement(Locators.XPATH,"//span[contains(@class,'toastMessage')]"), text,"Name Matches is loaded");
		return this;
	}
	
	public IndividualsPage clickEditLink() {

        click(locateElement(Locators.XPATH, "//a[@title='Edit']"),"Edit Link is clicked successfully");
        return this;
    }
	
	public IndividualsPage verifyEditIndividuals(String text) {
        verifyPartialText(locateElement(Locators.XPATH,
                "//span[@class='toastMessage slds-text-heading--small forceActionsText']"), text,"Name Matches is loaded");
        return this;
	}

}
