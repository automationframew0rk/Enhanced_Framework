package com.framework.testng.api.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeMethod;
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

	@BeforeMethod
	public void preCondition() throws IOException {

		FileInputStream config = new FileInputStream("./src/main/resources/config.properties");
		prop = new Properties();
		prop.load(config);

		startApp("chrome", false, prop.getProperty("url"));
		setNode();
		username = prop.getProperty("username");
		password = prop.getProperty("password");

	}

	public void postCondition() {
		close("Closed");

	}

}
