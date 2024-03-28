package wrapper;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchWindowException;

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
						System.setProperty("webdriver.chrome.driver", getAbsolutePath() + "drivers/chromedriver.exe");
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

			getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			Dimension d = new Dimension(1400, 900);
			getDriver().manage().window().setSize(d);
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

}
