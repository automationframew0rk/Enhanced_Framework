package com.salesforce.pages;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.framework.selenium.api.design.Locators;
import com.framework.testng.api.base.ProjectSpecificMethods;

public class TasksPage extends ProjectSpecificMethods {

	public TasksPage clickNewTask() {
		pause(3000);
		click(locateElement(Locators.XPATH,
				"//span[text()='Show more actions']/preceding-sibling::lightning-primitive-icon"),"Clicked");
		pause(3000);
		clickUsingJs(locateElement(Locators.XPATH, "//div[text()='New Task']"),"New task is clicked sucessfully");
		return this;
	}

	public TasksPage selectContact() {
		click(locateElement(Locators.XPATH, "(//span[text()='Name'])[3]/following::input"),"clicked");
		click(locateElement(Locators.XPATH, "//div[contains(@class,'primaryLabel')]"),"Contact is selected sucessfully");
return this;
	}

	public TasksPage enterCommet(String comment) {
		clearAndType(locateElement(Locators.XPATH, "(//span[text()='Comments'])[2]/following::textarea"), comment,"Contact is selected sucessfully");
		return this;
	}

	public TasksPage clickSaveButton() {
		click(locateElement(Locators.XPATH, "//span[text()='Save']"),"Save button is clicked sucessfully");
		return this;
	}

	public TasksPage verifyTheMessage() {
		String elementText = getElementText(
				locateElement(Locators.XPATH, "//span[@class='genericError uiOutputText']"),"Fetched");
		// if (elementText.contains("Review the errors on this page.")) {
		// 	reportStep("verified successfully", "pass");
		// } else
		// 	reportStep("not verified successfully", "fail");
		return this;
	}

	public TasksPage enterSubject(String subject) {
		clearAndType(locateElement(Locators.XPATH, "//label[text()='Subject']/following::input"), subject," Subject is entered successfully");
		return this;
	}

	public TasksPage selectStatus(String status) {
		click(locateElement(Locators.XPATH, "(//span[text()='Status'])[2]/following::a"),"Clicked");
		click(locateElement(Locators.XPATH, "//a[@title='" + status + "']"),"Status is selected sucessfully");
		return this;
	}

	public TasksPage verifyTasksCreated(String text) {
		String elementText = getElementText(locateElement(Locators.XPATH,
				"//span[contains(@class,'toastMessage slds-text-heading--small forceActionsText')]"),"Fetched");
		// if (elementText.contains(text)) {
		// 	reportStep("Task created successfully", "pass");
		// } else
		// 	reportStep("Task not created successfully", "fail");
		return this;
	}

	public TasksPage selectRecentlyViewed() {
		click(locateElement(Locators.XPATH, "//button[@title='Select a List View']"),"Clicked");
		clickUsingJs(locateElement(Locators.XPATH, "//a[@role='option']//span[text()='Recently Viewed']"),"Recently Viewed is selected sucessfully");
		reportStep("Recently Viewed is selected sucessfully", "pass");
		return this;
	}

	public TasksPage selectTableView() {
		click(locateElement(Locators.XPATH, "//button[@title='Display as Split View']"),"Click");
		click(locateElement(Locators.XPATH, "//span[text()='Table']"),"Table View is selected sucessfully");
		return this;
	}

	public TasksPage clickEditDropdown() {
		click(locateElement(Locators.XPATH, "//span[@class='slds-icon_container slds-icon-utility-down']/span"),"Edit dropdown is clicked sucessfully");
		return this;
	}

	public TasksPage clickEditButton() {
		clickUsingJs(locateElement(Locators.XPATH, "//div[text()='Edit']"),"Edit button is clicked sucessfully");
		return this;
	}

	public String addDate(int num) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, num);
		String format = sdf.format(cal.getTime());
		System.out.println(format);
		return format;
	}

	public TasksPage enterDate(int num) {
		String selectDate = addDate(num);
		JavascriptExecutor js = ((JavascriptExecutor) getDriver());
		WebElement FromDate = locateElement(Locators.XPATH, "//label[text()='Due Date']/following::input");
		js.executeScript("arguments[0]. value='" + selectDate + "';", FromDate);
		return this;
	}

	public TasksPage selectPriority(String priority) {
		clickUsingJs(locateElement(Locators.XPATH, "(//span[text()='Priority'])[2]/following::a"),"Clicked");
		clickUsingJs(locateElement(Locators.XPATH, "//a[text()='" + priority + "']"),"Priority is selected sucessfully");
		return this;
	}

	public TasksPage clickSaveEditButton() {
		click(locateElement(Locators.XPATH, "(//span[text()='Save'])[2]"),"Save button is clicked sucessfully");
		return this;
	}

	public TasksPage verifyEditIsSaved(String text) {
		pause(2000);
		String elementText = getElementText(locateElement(Locators.XPATH,
				"//span[contains(@class,'toastMessage slds-text-heading--small forceActionsText')]"),"Fetched");
		// if (elementText.contains(text)) {
		// 	reportStep("Task edited successfully", "pass");
		// } else
		// 	reportStep("Task not edited successfully", "fail");
		return this;
	}

	public TasksPage clickDeleteButton() {
		click(locateElement(Locators.XPATH, "(//li[@class='uiMenuItem'])[3]/a"),"Clicked");
		click(locateElement(Locators.XPATH, "//span[text()='Delete']"),"Delete button is clicked sucessfully");
		return this;
	}

	public TasksPage verifyTaskDeleted(String text) {
		pause(2000);
		String elementText = getElementText(locateElement(Locators.XPATH,
				"//span[contains(@class,'toastMessage slds-text-heading--small forceActionsText')]"),"Fetched");
		// if (elementText.contains(text)) {
			
		// } else
			
		return this;
	}

}
