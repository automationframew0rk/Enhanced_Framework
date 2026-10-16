package com.framework.selenium.api.base;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.framework.selenium.api.design.Browser;
import com.framework.selenium.api.design.Element;
import com.framework.selenium.api.design.Locators;
import com.framework.utils.Reporter;

public class SeleniumBase extends Reporter implements Browser, Element {
	protected Actions act;

	public static String projectId;
	public static String auctionRef;

	protected String getAttribute(WebElement ele, String attributeValue, String description) {
		String val = "";
		try {
			val = ele.getAttribute(attributeValue);
			reportStep(description, "pass");
		} catch (Exception e) {
			handleFailure(e, description);
		}
		return val;
	}

	protected void moveToElement(WebElement ele, String description) {
		try {
			act = new Actions(getDriver());
			act.moveToElement(ele).perform();
			reportStep(description, "pass");
		} catch (Exception e) {
			handleFailure(e, description);
		}
	}

	protected void dragAndDrop(WebElement eleSoutce, WebElement eleTarget, String description) {
		try {
			act = new Actions(getDriver());
			act.dragAndDrop(eleSoutce, eleTarget).perform();
			reportStep(description, "pass");
		} catch (Exception e) {
			handleFailure(e, description);
		}
	}

	protected void contextClick(WebElement ele, String description) {
		try {
			act = new Actions(getDriver());
			act.contextClick(getWait().until(ExpectedConditions.elementToBeClickable(ele))).perform();
			reportStep(description, "pass");
		} catch (Exception e) {
			handleFailure(e, description);
		}
	}

	protected void hoverAndClick(WebElement ele, String description) {
		try {
			act = new Actions(getDriver());
			act.moveToElement(getWait().until(ExpectedConditions.elementToBeClickable(ele))).pause(5000).click().perform();
			reportStep(description, "pass");
		} catch (Exception e) {
			handleFailure(e, description);
		}
	}

	protected void doubleTap(WebElement ele, String description) {
		try {
			act = new Actions(getDriver());
			act.click(getWait().until(ExpectedConditions.elementToBeClickable(ele))).click().perform();
			reportStep(description, "pass");
		} catch (Exception e) {
			handleFailure(e, description);
		}
	}

	protected void doubleClick(WebElement ele, String description) {
		try {
			act = new Actions(getDriver());
			act.doubleClick(getWait().until(ExpectedConditions.elementToBeClickable(ele))).perform();
			reportStep(description, "pass");
		} catch (Exception e) {
			handleFailure(e, description);
		}
	}

	public void waitForApperance(WebElement element) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			reportStep("Element did not appear after 20 seconds", "fail", false);

		}

	}

	@Override
	public void click(WebElement ele, String description) {
		String text = "";
		try {
			ele.isDisplayed(); // @FindBy return the proxy even if it does not exist !!
			try {
				Thread.sleep(500);
				getWait().until(ExpectedConditions.elementToBeClickable(ele));
				text = ele.getText();
				if (ele.isEnabled()) {
					ele.click();
				} else {
					getDriver().executeScript("arguments[0].click()", ele);
				}
			} catch (Exception e) {
				boolean bFound = false;
				int totalTime = 0;
				while (!bFound && totalTime < 10000) {
					try {
						Thread.sleep(500);
						ele.click();
						bFound = true;

					} catch (Exception e1) {
						bFound = false;
					}
					totalTime = totalTime + 500;
				}
				if (!bFound)
					ele.click();
			}
			reportStep(description, "pass");
		} catch (Exception e) {
			handleFailure(e, description);
		}
	}

	public void clickUsingJs(WebElement ele, String description) {
		try {
			ele.isDisplayed(); // @FindBy return the proxy even if it does not exist !!

			try {
				getDriver().executeScript("arguments[0].click()", ele);
			} catch (Exception e) {
				boolean bFound = false;
				int totalTime = 0;
				while (!bFound && totalTime < 10000) {
					try {
						Thread.sleep(500);
						getDriver().executeScript("arguments[0].click()", ele);
						bFound = true;

					} catch (Exception e1) {
						bFound = false;
					}
					totalTime = totalTime + 500;
				}
				if (!bFound)
					getDriver().executeScript("arguments[0].click()", ele);
			}
			reportStep(description, "pass");
		} catch (Exception e) {
			handleFailure(e, description);
		}
	}

	public void click(Locators locatorType, String value, String description) {
		String text = "";
		WebElement ele = locateElement(locatorType, value);
		try {
			try {
				Thread.sleep(500);
				getWait().until(ExpectedConditions.elementToBeClickable(ele));
				text = ele.getText();
				if (ele.isEnabled()) {
					ele.click();
				} else {
					getDriver().executeScript("arguments[0].click()", ele);
				}
			} catch (Exception e) {
				boolean bFound = false;
				int totalTime = 0;
				while (!bFound && totalTime < 10000) {
					try {
						Thread.sleep(500);
						ele = locateElement(locatorType, value);
						ele.click();
						bFound = true;

					} catch (Exception e1) {
						bFound = false;
					}
					totalTime = totalTime + 500;
				}
				if (!bFound)
					ele.click();
			}
		} catch (Exception e) {
			handleFailure(e, description);
		}
		try {
			reportStep(description, "pass");
		} catch (Exception e) {
			handleFailure(e, description);
		}
	}

	public void clickWithNoSnap(WebElement ele, String description) {
		try {
			getWait().until(ExpectedConditions.elementToBeClickable(ele));
			ele.click();
			reportStep(description, "pass");
		} catch (Exception e) {
			handleFailure(e, description);
		}
	}

	@Override
	public void append(WebElement ele, String data, String description) {
		try {
			String attribute = ele.getAttribute("value");
			if (attribute.length() > 1) {
				ele.sendKeys(data);
			} else {
				ele.sendKeys(data);
			}
			reportStep(description, "pass");
		} catch (Exception e) {
			handleFailure(e, description);
		}
	}

	@Override
	public void clear(WebElement ele, String description) {
		try {
			ele.clear();
			reportStep(description, "pass");
		} catch (Exception e) {
			handleFailure(e, description);
		}
	}

	/**
	 * Overloaded method used to clear the existing value and type the data with
	 * keys for tab or enter kind of
	 * 
	 * @param ele  - WebElement from the DOM
	 * @param data - Use to type and pass Keys as many needed
	 */
	public void clearAndType(WebElement ele, String description, CharSequence... data) {
		try {
			getWait().until(ExpectedConditions.visibilityOf(ele));
			ele.clear();
			ele.sendKeys(data);
			reportStep(description, "pass");
		} catch (Exception e) {
			try {
				pause(500);
				ele.sendKeys(data);
				reportStep(description, "pass");
			} catch (Exception e1) {
				handleFailure(e1, description);
			}
		}

	}

	@Override
	public void clearAndType(WebElement ele, String data, String description) {
		try {
			getWait().until(ExpectedConditions.visibilityOf(ele));
			ele.clear();
			ele.sendKeys(data);
			// Fall back when native typing does not update the input value.
			if (!data.equals(ele.getDomProperty("value"))) {
				getDriver().executeScript(
					"arguments[0].value = arguments[1];"
					+ "arguments[0].dispatchEvent(new Event('input', {bubbles: true}));"
					+ "arguments[0].dispatchEvent(new Event('change', {bubbles: true}));", ele, data);
			}
			getWait().until(ExpectedConditions.domPropertyToBe(ele, "value", data));
			reportStep(description, "pass");
		} catch (Exception e) {
			try {
				pause(500);
				ele.sendKeys(data);
				reportStep(description, "pass");
			} catch (Exception e1) {
				handleFailure(e1, description);
			}
		}

	}



    

	public void typeAndTab(WebElement ele, String data, String description) {
		try {
			getWait().until(ExpectedConditions.visibilityOf(ele));
			ele.clear();
			ele.sendKeys("", "", data, Keys.TAB);
			reportStep(description, "pass");
		} catch (Exception e) {
			handleFailure(e, description);
		}

	}

	public void type(WebElement ele, String data, String description) {
		try {
			getWait().until(ExpectedConditions.visibilityOf(ele));
			ele.clear();
			ele.sendKeys("", "", data);
			reportStep(description, "pass");
		} catch (Exception e) {
			handleFailure(e, description);
		}

	}

	public void typeAndEnter(WebElement ele, String data, String description) {
		try {
			getWait().until(ExpectedConditions.visibilityOf(ele));
			ele.clear();
			ele.sendKeys("", "", data, Keys.ENTER);
			reportStep(description, "pass");
		} catch (Exception e) {
			handleFailure(e, description);
		}

	}

	@Override
	public String getElementText(WebElement ele, String description) {
		try {
			String text = ele.getText();
			reportStep(description, "pass");
			return text;
		} catch (Exception e) {
			handleFailure(e, description);
		}
		return null;
	}

	@Override
	public String getBackgroundColor(WebElement ele, String description) {
		String cssValue = null;
		try {
			cssValue = ele.getCssValue("color");
			reportStep(description, "pass");
		} catch (Exception e) {
			handleFailure(e, description);
		}
		return cssValue;
	}

	@Override
	public String getTypedText(WebElement ele, String description) {
		String attributeValue = null;
		try {
			attributeValue = ele.getAttribute("value");
			reportStep(description, "pass");
		} catch (Exception e) {
			handleFailure(e, description);
		}
		return attributeValue;
	}

	@Override
	public void selectDropDownUsingText(WebElement ele, String value, String description) {
		try {
			Select sel = new Select(ele);
			sel.selectByVisibleText(value);
			reportStep(description, "pass");
		} catch (Exception e) {
			handleFailure(e, description);
		}
	}

	@Override
	public void selectDropDownUsingIndex(WebElement ele, int index, String description) {
		try {
			Select sel = new Select(ele);
			sel.selectByIndex(index);
			reportStep(description, "pass");
		} catch (Exception e) {
			handleFailure(e, description);
		}
	}

	@Override
	public void selectDropDownUsingValue(WebElement ele, String value, String description) {
		try {
			Select sel = new Select(ele);
			sel.selectByValue(value);
			reportStep(description, "pass");
		} catch (Exception e) {
			handleFailure(e, description);
		}
	}

	@Override
	public boolean verifyExactText(WebElement ele, String expectedText, String description) {
		try {
			String text = ele.getText();
			if (text.contains(expectedText)) {
				reportStep(description, "pass");
				return true;
			} else {
				reportStep(description, "fail");
				return false;
			}
		} catch (Exception e) {
			handleFailure(e, description);
		}
		return false;
	}

	@Override
	public boolean verifyPartialText(WebElement ele, String expectedText, String description) {
		try {
			if (ele.getText().contains(expectedText)) {
				reportStep(description, "pass");
				return true;
			} else {
				reportStep(description, "fail");
				return false;
			}
		} catch (Exception e) {
			handleFailure(e, description);
		}
		return false;
	}

	@Override
	public boolean verifyExactAttribute(WebElement ele, String attribute, String value, String description) {
		try {
			if (ele.getAttribute(attribute).equals(value)) {
				reportStep(description, "pass");
				return true;
			} else {
				reportStep(description, "fail");
				return false;
			}
		} catch (Exception e) {
			handleFailure(e, description);
		}
		return false;
	}

	@Override
	public void verifyPartialAttribute(WebElement ele, String attribute, String value, String description) {
		try {
			if (ele.getAttribute(attribute).contains(value)) {
				reportStep(description, "pass");
			} else {
				reportStep(description, "fail");
			}
		} catch (Exception e) {
			handleFailure(e, description);
		}

	}

	@Override
	public boolean verifyDisplayed(WebElement ele, String description) {
		try {
			if (ele.isDisplayed()) {
				reportStep(description, "pass");
				return true;
			} else {
				reportStep(description, "fail");
				return false;
			}
		} catch (Exception e) {
			handleFailure(e, description);
		}
		return false;

	}

	@Override
	public boolean verifyDisappeared(WebElement ele, String description) {
		try {
			Boolean until = getWait().until(ExpectedConditions.invisibilityOf(ele));
			reportStep(description, "pass");
			return until;
		} catch (Exception e) {
			handleFailure(e, description);
		}
		return false;

	}

	@Override
	public boolean verifyEnabled(WebElement ele, String description) {
		try {
			if (ele.isEnabled()) {
				reportStep(description, "pass");
				return true;
			} else {
				reportStep(description, "fail");
				return false;
			}
		} catch (Exception e) {
			handleFailure(e, description);
		}
		return false;
	}

	@Override
	public boolean verifySelected(WebElement ele, String description) {
		try {
			if (ele.isSelected()) {
				reportStep(description, "pass");
				return true;
			} else {
				reportStep(description, "fail");
				return false;
			}
		} catch (Exception e) {
			handleFailure(e, description);
		}
		return false;

	}

	@Override
	public void startApp(String url, boolean headless) {
		try {
			setDriver("chrome", headless);
			setWait();
			act = new Actions(getDriver());
			getDriver().manage().window().maximize();
			getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(90));
			getDriver().get(url);
			reportStep("The Browser Launched in chrome browser with URL " + url, "pass");
		} catch (Exception e) {
			reportStep("Something went wrong \n" + e.getMessage(), "fail");
		}

	}

	@Override
	public void startApp(String browser, boolean headless, String url) {
		try {
			if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.silentOutput", "true");
				// System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");

				setDriver("chrome", headless);
			} else if (browser.equalsIgnoreCase("firefox")) {
				// System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");

				setDriver("firefox", headless);
			} else if (browser.equalsIgnoreCase("ie")) {
				// System.setProperty("webdriver.ie.driver", "./drivers/IEDriverServer.exe");

				setDriver("ie", false);
			}
			setWait();
			getDriver().manage().window().maximize();
			getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

			getDriver().get(url);
		} catch (WebDriverException e) {
			e.printStackTrace();
			reportStep("The Browser Could not be Launched. Hence Failed \n" + e.getMessage(), "fail");
		} catch (Exception e) {
			e.printStackTrace();
			reportStep("The Browser Could not be Launched. Hence Failed \n" + e.getMessage(), "fail");
		}
	}

	@Override
	public WebElement locateElement(Locators locatorType, String value) {

		try {

			switch (locatorType) {

			case CLASS_NAME:
				return getDriver().findElement(By.className(value));

			case CSS:
				return getDriver().findElement(By.cssSelector(value));

			case ID:
				return getDriver().findElement(By.id(value));

			case LINK_TEXT:
				return getDriver().findElement(By.linkText(value));

			case NAME:
				return getDriver().findElement(By.name(value));

			case PARTIAL_LINKTEXT:
				return getDriver().findElement(By.partialLinkText(value));

			case TAGNAME:
				return getDriver().findElement(By.tagName(value));

			case XPATH:
				return getDriver().findElement(By.xpath(value));

			default:

				reportStep(
						"Invalid Locator Type : " + locatorType,
						"fail");

				break;
			}

		}

		catch (NoSuchElementException e) {

			handleFailure(
					e,
					"Element not found using locator : "
							+ locatorType
							+ " = "
							+ value);
		}

		catch (Exception e) {

			handleFailure(
					e,
					"Exception occurred while locating element using : "
							+ locatorType
							+ " = "
							+ value);
		}

		return null;
	}

	@Override
	public WebElement locateElement(String value) {
		try {
			WebElement findElementById = getDriver().findElement(By.id(value));
			return findElementById;
		} catch (NoSuchElementException e) {
			reportStep("The Element with locator id Not Found with value: " + value + "\n" + e.getMessage(), "fail");
		} catch (Exception e) {
			reportStep("The Element with locator id Not Found with value: " + value + "\n" + e.getMessage(), "fail");
		}
		return null;
	}

	@Override
	public List<WebElement> locateElements(Locators type, String value) {
		try {
			switch (type) {
			case CLASS_NAME:
				return getDriver().findElements(By.className(value));
			case CSS:
				return getDriver().findElements(By.cssSelector(value));
			case ID:
				return getDriver().findElements(By.id(value));
			case LINK_TEXT:
				return getDriver().findElements(By.linkText(value));
			case NAME:
				return getDriver().findElements(By.name(value));
			case PARTIAL_LINKTEXT:
				return getDriver().findElements(By.partialLinkText(value));
			case TAGNAME:
				return getDriver().findElements(By.tagName(value));
			case XPATH:
				return getDriver().findElements(By.xpath(value));
			default:
				System.err.println("Locator is not Valid");
				break;
			}
		} catch (NoSuchElementException e) {
			reportStep("The Element with locator:" + type + " Not Found with value: " + value + "\n" + e.getMessage(),
					"fail");
		}
		return null;
	}

	@Override
	public void switchToAlert(String description) {
		try {
			getDriver().switchTo().alert();
			reportStep(description, "pass");
		} catch (Exception e) {
			handleFailure(e, description);
		}
	}

	@Override
	public void acceptAlert(String description) {
		try {
			getWait().until(ExpectedConditions.alertIsPresent());
			Alert alert = getDriver().switchTo().alert();
			alert.accept();
			reportStep(description, "pass");
		} catch (Exception e) {
			handleFailure(e, description);
		}

	}

	@Override
	public void dismissAlert(String description) {
		try {
			Alert alert = getDriver().switchTo().alert();
			alert.dismiss();
			reportStep(description, "pass");
		} catch (Exception e) {
			handleFailure(e, description);
		}

	}

	@Override
	public String getAlertText(String description) {
		String text = "";
		try {
			Alert alert = getDriver().switchTo().alert();
			text = alert.getText();
			reportStep(description, "pass");
		} catch (Exception e) {
			handleFailure(e, description);
		}
		return text;
	}

	@Override
	public void typeAlert(String data, String description) {
		try {
			getDriver().switchTo().alert().sendKeys(data);
			reportStep(description, "pass");
		} catch (Exception e) {
			handleFailure(e, description);
		}
	}

	@Override
	public void switchToWindow(int index, String description) {
		try {
			Set<String> allWindows = getDriver().getWindowHandles();
			List<String> allhandles = new ArrayList<String>(allWindows);
			getDriver().switchTo().window(allhandles.get(index));
			reportStep(description, "pass");
		} catch (Exception e) {
			handleFailure(e, description);
		}
	}

	@Override
	public boolean switchToWindow(String title, String description) {
		try {
			Set<String> allWindows = getDriver().getWindowHandles();
			for (String eachWindow : allWindows) {
				getDriver().switchTo().window(eachWindow);
				if (getDriver().getTitle().equals(title)) {
					break;
				}
			}
			reportStep(description, "pass");
			return true;
		} catch (Exception e) {
			handleFailure(e, description);
		}
		return false;
	}

	@Override
	public void switchToFrame(int index, String description) {
		try {
			Thread.sleep(100);
			getDriver().switchTo().frame(index);
			reportStep(description, "pass");
		} catch (Exception e) {
			handleFailure(e, description);
		}

	}

	@Override
	public void switchToFrame(WebElement ele, String description) {
		try {
			getDriver().switchTo().frame(ele);
			reportStep(description, "pass");
		} catch (Exception e) {
			handleFailure(e, description);
		}

	}

	public void switchToFrameUsingXPath(String xpath, String description) {
		try {
			getDriver().switchTo().frame(locateElement(Locators.XPATH, xpath));
			reportStep(description, "pass");
		} catch (Exception e) {
			handleFailure(e, description);
		}

	}

	@Override
	public void switchToFrame(String idOrName, String description) {
		try {
			getDriver().switchTo().frame(idOrName);
			reportStep(description, "pass");
		} catch (Exception e) {
			handleFailure(e, description);
		}
	}

	@Override
	public void defaultContent(String description) {
		try {
			getDriver().switchTo().defaultContent();
			reportStep(description, "pass");
		} catch (Exception e) {
			handleFailure(e, description);
		}
	}

	@Override
	public boolean verifyUrl(String url, String description) {
		try {
			if (getDriver().getCurrentUrl().equals(url)) {
				reportStep(description, "pass");
				return true;
			} else {
				reportStep(description, "fail");
			}
		} catch (Exception e) {
			handleFailure(e, description);
		}
		return false;
	}

	@Override
	public boolean verifyTitle(String title, String description) {
		try {
			if (getDriver().getTitle().equals(title)) {
				reportStep(description, "pass");
				return true;
			} else {
				reportStep(description, "fail");
			}
		} catch (Exception e) {
			handleFailure(e, description);
		}
		return false;
	}

	@Override
	public long takeSnap() {
		long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L;
		try {
			FileUtils.copyFile(getDriver().getScreenshotAs(OutputType.FILE),
					new File("./" + Reporter.folderName + "/images/" + number + ".jpg"));
		} catch (WebDriverException e) {
			reportStep("The browser has been closed." + e.getMessage(), "fail");
		} catch (IOException e) {
			reportStep("The snapshot could not be taken " + e.getMessage(), "warning");
		}
		return number;
	}

	@Override
	public void close(String description) {
		try {
			getDriver().close();
			reportStep(description, "pass");
		} catch (Exception e) {
			handleFailure(e, description);
		}
	}

	@Override
	public void quit(String description) {
		try {
			getDriver().quit();
			reportStep(description, "pass");
		} catch (Exception e) {
			handleFailure(e, description);
		}
	}

	public void waitForDisapperance(WebElement element) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
			wait.until(ExpectedConditions.invisibilityOf(element));
		} catch (Exception e) {
			reportStep("Element did not appear after 10 seconds", "fail", false);

		}

	}

	public void pause(int timeout) {
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void chooseDate(WebElement ele, String data, String description) {
		try {
			getDriver().executeScript("arguments[0].setAttribute('value', '" + data + "')", ele);
			reportStep(description, "pass");
		} catch (Exception e) {
			handleFailure(e, description);
		}

	}

	public void fileUpload(WebElement ele, String data, String description) {
		try {
			hoverAndClick(ele, "Open file upload dialog");
			pause(2000);

			// Store the copied text in the clipboard
			StringSelection stringSelection = new StringSelection(data);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

			// Paste it using Robot class
			Robot robot = new Robot();

			// Enter to confirm it is uploaded
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);

			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);

			Thread.sleep(5000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			reportStep(description, "pass");
		} catch (Exception e) {
			handleFailure(e, description);
		}

	}

	public void fileUploadWithJs(WebElement ele, String data, String description) {
		try {

			clickUsingJs(ele, "Click file input via JS");
			pause(2000);

			// Store the copied text in the clipboard
			StringSelection stringSelection = new StringSelection(data);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

			// Paste it using Robot class
			Robot robot = new Robot();

			// Enter to confirm it is uploaded
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);

			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);

			Thread.sleep(5000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			reportStep(description, "pass");
		} catch (Exception e) {
			handleFailure(e, description);
		}

	}

	@Override
	public void executeTheScript(String js, WebElement ele) {
		getDriver().executeScript(js, ele);
	}

	public static void generateProjectAndAuctionIds() {
		int ranNum1 = (int) (Math.random() * 10000);
		int ranNum2 = ranNum1 + 9999;
		projectId = Integer.toString(ranNum1);
		auctionRef = Integer.toString(ranNum2);
	}

}
