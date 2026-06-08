package com.salesforce.pages;

import com.framework.selenium.api.design.Locators;
import com.framework.testng.api.base.ProjectSpecificMethods;

public class VerifyNewContact extends ProjectSpecificMethods {

	public VerifyNewContact verifyToastMeassage() {
		
		 contactName = getElementText(locateElement(Locators.XPATH, 
				"//span[contains(@class,'toastMessage slds-text-heading--small forceActionsText')]"),"Fetched");
		System.out.println(contactName);
		return this;
	}
}
