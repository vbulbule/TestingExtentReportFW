package pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import utils.Reporter;
import wrapper.GenericWrappers;

public class FriendsPage extends GenericWrappers {

	private final RemoteWebDriver driver;

	public FriendsPage() {
		driver = getDriver();
	}

	public FriendsPage verifyFriendspageDisplayed() {
		if (isElementPresent("//a[@aria-label='Friends']"))
			Reporter.reportStep("Friends page is displayed  :", "SUCCESS");
		else
			Reporter.reportStep("Friends page is not displayed", "FAILURE");

		return this;
	}

	public FriendsPage clickOnConfirmBTN() {
		if (clickBTN_ByXpath("//a[@aria-label='Friends']"))
			Reporter.reportStep("Friend request is accepted  :", "SUCCESS");
		else
			Reporter.reportStep("Friend request is not accepted", "FAILURE");

		return this;

	}
}
