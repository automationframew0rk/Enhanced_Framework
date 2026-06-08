package com.framework.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.remote.UnreachableBrowserException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.framework.selenium.api.base.DriverInstance;

public abstract class Reporter extends DriverInstance {

	private static ExtentReports extent;

	private static final ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();

	private static final ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	private static final ThreadLocal<String> testName = new ThreadLocal<String>();

	private String fileName = "result.html";

	private String pattern = "dd-MMM-yyyy HH-mm-ss";

	public String testcaseName, testDescription, authors, category, dataFileName, dataFileType, excelFileName,
			sheetName;

	public static String folderName = "";

	@BeforeSuite(alwaysRun = true)
	public synchronized void startReport() {

		String date = new SimpleDateFormat(pattern).format(new Date());

		folderName = "reports/" + date;

		File folder = new File("./" + folderName);

		if (!folder.exists()) {
			folder.mkdir();
		}

		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("./" + folderName + "/" + fileName);

		htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);

		htmlReporter.config().setChartVisibilityOnOpen(false);

		htmlReporter.config().setTheme(Theme.STANDARD);

		htmlReporter.config().setDocumentTitle("Salesforce");

		htmlReporter.config().setEncoding("utf-8");

		htmlReporter.config().setReportName("Salesforce");

		htmlReporter.setAppendExisting(true);

		extent = new ExtentReports();

		extent.attachReporter(htmlReporter);
	}

	@BeforeClass(alwaysRun = true)
	public synchronized void startTestCase() {

		ExtentTest parent = extent.createTest(testcaseName, testDescription);

		parent.assignCategory(category);

		parent.assignAuthor(authors);

		parentTest.set(parent);

		testName.set(testcaseName);
	}

	public synchronized void setNode() {

		ExtentTest child = parentTest.get().createNode(getTestName());

		test.set(child);
	}

	public abstract long takeSnap();

	// ==========================================================
	// CENTRALIZED FAILURE HANDLER
	// ==========================================================

	public void handleFailure(Exception e, String description) {

		StackTraceElement caller = Thread.currentThread().getStackTrace()[3];

		String fullClassName = caller.getClassName();

		String pageClassName = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);

		String failedMethodName = caller.getMethodName();

		int lineNumber = caller.getLineNumber();

		String exceptionName = e.getClass().getSimpleName();

		String exceptionMessage = e.getMessage();

		// clean los
		if (exceptionMessage != null) {

			if (exceptionMessage.contains("For documentation")) {

				exceptionMessage = exceptionMessage.substring(0, exceptionMessage.indexOf("For documentation"));
			}

			if (exceptionMessage.contains("Session info")) {

				exceptionMessage = exceptionMessage.substring(0, exceptionMessage.indexOf("Session info"));
			}

			if (exceptionMessage.contains("Build info")) {

				exceptionMessage = exceptionMessage.substring(0, exceptionMessage.indexOf("Build info"));
			}

			exceptionMessage = exceptionMessage.replace("\n", " ").replace("\r", " ").trim();
		}

		String finalMessage = "<b>Step :</b> " + description +

				"<br><b>Page :</b> " + pageClassName +

				"<br><b>Method :</b> " + failedMethodName +

				"<br><b>Line :</b> " + lineNumber +

				"<br><b>Error :</b> " + exceptionName +

				"<br><b>Message :</b> " + exceptionMessage;

		reportStep(finalMessage, "fail");
	}

	// ==========================================================
	// REPORT STEP
	// ==========================================================

	public void reportStep(String desc, String status, boolean bSnap) {

		synchronized (test) {

			MediaEntityModelProvider img = null;

			if (bSnap && !(status.equalsIgnoreCase("INFO") || status.equalsIgnoreCase("skipped"))) {

				long snapNumber = takeSnap();

				try {

					img = MediaEntityBuilder
							.createScreenCaptureFromPath("./../../" + folderName + "/images/" + snapNumber + ".jpg")
							.build();

				} catch (IOException e) {

				}
			}

			if (status.equalsIgnoreCase("pass")) {

				test.get().pass(desc, img);

			}

			else if (status.equalsIgnoreCase("fail")) {

				test.get().fail(desc, img);

				throw new RuntimeException("See Extent Report for more details.");

			}

			else if (status.equalsIgnoreCase("warning")) {

				test.get().warning(desc, img);

			}

			else if (status.equalsIgnoreCase("skipped")) {

				test.get().skip("The test is skipped due to dependency failure");

			}

			else if (status.equalsIgnoreCase("INFO")) {

				test.get().info(desc);
			}
		}
	}

	public void reportStep(String desc, String status) {

		reportStep(desc, status, true);
	}

	@AfterSuite(alwaysRun = true)
	public synchronized void endResult() {

		try {

			if (getDriver() != null) {

				getDriver().quit();
			}

		} catch (UnreachableBrowserException e) {

		}

		extent.flush();
	}

	public String getTestName() {

		return testName.get();
	}

	public Status getTestStatus() {

		return parentTest.get().getModel().getStatus();
	}
}