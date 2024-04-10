package wrapper;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import utils.Reporter;

public abstract class GenericWrappers extends reports.HtmlReporter {

	protected String browserName = "chrome";

	public boolean launchApp(String browser, boolean remote) {

		if (browser.equals("ie"))
			browser = "internet explorer";

		sUrl = "https://www.facebook.com/";

		boolean bReturn = false;

		try {
			if (remote) {
				System.out.println("Driver " + "http://" + sHubUrl + ":" + sHubPort + "/wd/hub");
				System.out.println("Driver passed" + "http://" + sHubUrl + ":" + sHubPort + "/wd/hub");
				setDriver(browser, true);
				System.out.println("Driver passed" + "http://" + sHubUrl + ":" + sHubPort + "/wd/hub");
			} else {
				if (browser.equals("firefox")) {
					setDriver("firefox");

				} else if (browser.equals("chrome")) {

					if (sPlatform.startsWith("win")) {
						System.setProperty("webdriver.chrome.driver",
								"E:\\social_media\\TestingExtentReportFW\\drivers\\chromedriver.exe");
					} else if (sPlatform.startsWith("linux")) {
						System.setProperty("webdriver.chrome.driver",
								getAbsolutePath() + "drivers/chromedriver_linux/chromedriver");
					} else if (sPlatform.startsWith("mac")) {
						System.setProperty("webdriver.chrome.driver",
								getAbsolutePath() + "drivers/chromedriver_mac/chromedriver");
					}
					setDriver("chrome");

				}

			}

			getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			Dimension d = new Dimension(1400, 900);
			getDriver().manage().window().setSize(d);
			getDriver().manage().window().maximize();
			System.out.println(sUrl);
			getDriver().get(sUrl);
			setWait();
			primaryWindowHandle.set(getDriver().getWindowHandle());
			bReturn = true;
			Reporter.reportStep("The browser:" + browser + " launched successfully", "SUCCESS");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Reporter.reportStep("The browser:" + browser + " could not be launched", "FAILURE");
		}
		return bReturn;

	}

	public static String getAbsolutePath() {
		return GenericWrappers.class.getProtectionDomain().getCodeSource().getLocation().getFile().replace("%20", " ")
				+ "../../";
	}

	public void quitBrowser() {

		String VAR = "";
		try {

			if (getDriver() != null) {
				if (isAlive()) {
					getDriver().quit();
				} else
					System.out.println("Driver is not present.");

			} else {
				System.out.println("----Alert is NOT present-----");
				if (isAlive()) {
					System.out.println("Driver is present in else Box");
					getDriver().quit();
				} else
					System.out.println("Driver is not present for quit.");
			}

		} catch (NoSuchWindowException e) {
			System.out.println("---In No Such Window Exception Catch Block---");

			System.out.println("Session is Expired");

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("---In Exception Catch Block---");
			System.out.println("Session is Expired 2nd block");

		}

	}

	public Boolean isAlive() {
		try {
			System.out.println("Current URL:" + getDriver().getCurrentUrl());// or driver.getTitle();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean editById(String id, String data) {

		boolean bReturn = false;
		WebElement ele = null;
		try {
			ele = getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
			ele.clear();
			ele.sendKeys(data);
			bReturn = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return bReturn;

	}

	public boolean clickOnBTN_ByName(String name) {

		boolean bReturn = false;
		WebElement ele = null;
		try {
			ele = getWait().until(ExpectedConditions.visibilityOfElementLocated(By.name(name)));
			ele.click();
			getWait();
			bReturn = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return bReturn;

	}

	public boolean clickBTN_ByXpath(String xpath) { // CLICK METHOD

		boolean bReturn = false;
		WebElement ele = null;
		try {
			ele = getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			ele.click();
			getWait();
			

			bReturn = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return bReturn;
	}

	public boolean searchText(String xpath, String name) { // * search for frined

		boolean bReturn = false;
		WebElement ele = null;
		try {
			ele = getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			ele.click();
			ele.sendKeys(name, Keys.ENTER);
			getWait();
			bReturn = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return bReturn;
	}

	public boolean verifyElementIsSelected(String xpath, String expected_result) {
		boolean bReturn = false;
		WebElement ele = null;
		try {
			ele = getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			String color = ele.getCssValue("color");
			// String exp_color="rgba(56, 88, 152, 1)";

			if (expected_result.equals(color)) {
				System.out.println("element is by default available / selected");
			}
			bReturn = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return bReturn;

	}

	public boolean isElementPresent(String xpath) {
		boolean bReturn = false;
		WebElement ele = null;
		try {
			ele = getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			ele.click();
			getWait();

			bReturn = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return bReturn;

	}

	public boolean verifyProfileName(String xpath, String expected_result) {
		boolean bReturn = false;
		WebElement ele = null;
		try {
			ele = getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			String profile_name = ele.getText();
			if (expected_result.equals(profile_name)) {
				System.out.println("element is by default available / selected");
			}
			
			getWait();
			pageScrollUp();
			getWait();
			bReturn = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return bReturn;
	}

	public boolean uploadFileUsingRobotClass(String xpath) {

		boolean bReturn = false;
		WebElement ele = null;
		try {
			ele = getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			ele.click();
			UploadFileUsingRobotClass();
			getWait();
			pageScrollDown();

			
			bReturn = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return bReturn;
	}
	
	public void UploadFileUsingRobotClass()
	{
		Robot rb = null;
		try {
			rb = new Robot();

		} catch (AWTException e) {

		}
         
		rb.delay(2000);
		StringSelection filepath = new StringSelection("C:\\photo\\aayush.jpg");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filepath, null);;
		

		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);
		rb.delay(2000);

		rb.keyRelease(KeyEvent.VK_V);
		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.delay(2000);

		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);
		rb.delay(2000);
	}
	public void DownloadFile()
	{
		
	}

	public void pageScrollUp() {
		JavascriptExecutor jse = (JavascriptExecutor) getDriver();
		jse.executeScript("window.scrollBy(0,-250)");
	}
	
	public void pageScrollDown() {
		JavascriptExecutor jse = (JavascriptExecutor) getDriver();
		jse.executeScript("window.scrollBy(0,250)");
	}

}
