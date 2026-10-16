package com.framework.testng.api.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

import com.framework.selenium.api.base.SeleniumBase;
import com.framework.utils.DataLibrary;

public class ProjectSpecificMethods extends SeleniumBase {

	public static Properties prop;

	public static String username, password;
	public static String contactName;

	@DataProvider(name = "fetchData", indices = 0)
	public Object[][] fetchData() throws IOException {
		return DataLibrary.readExcelData(excelFileName);
	}

	@BeforeMethod(alwaysRun = true)
	public void preCondition() throws IOException {

		prop = new Properties();
		try (FileInputStream config = new FileInputStream("./src/main/resources/config.properties")) {
			prop.load(config);
		}

		setNode();
		startApp("chrome", Boolean.getBoolean("headless"), prop.getProperty("url"));
		username = prop.getProperty("username");
		password = prop.getProperty("password");

	}

	@AfterMethod(alwaysRun = true)
	public void postCondition() {
		if (getDriver() != null) {
			getDriver().quit();
		}

	}

}
