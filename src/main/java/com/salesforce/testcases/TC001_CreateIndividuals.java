package com.salesforce.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.framework.testng.api.base.ProjectSpecificMethods;
import com.salesforce.pages.LoginPage;

public class TC001_CreateIndividuals extends ProjectSpecificMethods{
	@BeforeTest
	public void setValues() {
		testcaseName = "TC_001_Create Individuals";
		testDescription ="Verify Craete Individuals are created";
		authors="Ram";
		category ="Individuals";
		excelFileName="CreateIndividuals";
		
	}
	
	@Test( dataProvider = "fetchData")
	public void runLogin(String search,String fName,String lName) {
		new LoginPage()
		.enterUsername()
		.enterPassword()
		.clickLogin()
		.clickAppLauncher()
		.clickViewAll()
		.enterSearchField(search)
		.clickIndividualsLink()
		.clickNewButton()
		.selectSalutation()
		.enterFirstName(fName)
		.enterLastName(lName)
		.clickSaveButton()
		.verifyCreateIndividuals(lName);
	

	}

}
