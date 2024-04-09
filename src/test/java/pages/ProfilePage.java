package pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import utils.Reporter;
import wrapper.GenericWrappers;

public class ProfilePage extends GenericWrappers {

	//// div[@aria-label='Add Cover Photo']

	private final RemoteWebDriver driver;

	public ProfilePage() {
		driver = getDriver();
	}

	public ProfilePage verifyProfileNameDisplayed(String name) {
		if (verifyProfileName("//a[@aria-label='Home']", name))
			Reporter.reportStep("profile page is displayed  :", "SUCCESS");
		else
			Reporter.reportStep("profile page is not displayed", "FAILURE");

		return this;
	}

	public ProfilePage clickOnUpdateProfile() {
		if (clickBTN_ByXpath("//div[@aria-label='Update profile picture']"))
			Reporter.reportStep("Clicked on Update profile picture :", "SUCCESS");
		else
			Reporter.reportStep("Unable to click on Update profile picture", "FAILURE");

		return this;
	}

	public ProfilePage clickOnUploadPhoto() {
		if (uploadFileUsingRobotClass("//span[text()='Upload Photo']"))
			Reporter.reportStep("Clicked on upload photo :", "SUCCESS");
		else
			Reporter.reportStep("Unable to click on upload photo", "FAILURE");

		return this;
	}

	public ProfilePage clickOnSaveBTN() {
		if (clickBTN_ByXpath("//span[text()='Save']"))
			Reporter.reportStep("Clicked on save changes :", "SUCCESS");
		else
			Reporter.reportStep("Unable to click on save changes", "FAILURE");

		return this;
	}

	public ProfilePage clickOnAddCoverPhoto() {
		if (clickBTN_ByXpath("//span[text()='Add Cover Photo']"))
			Reporter.reportStep("Clicked on Add cover photo :", "SUCCESS");
		else
			Reporter.reportStep("Unable to click on Add cover photo", "FAILURE");

		return this;
	}

	public ProfilePage clickOnChooseCoverPhoto() {
		if (clickBTN_ByXpath("//span[text()='Choose cover photo']"))
			Reporter.reportStep("Clicked on choose cover photo :", "SUCCESS");
		else
			Reporter.reportStep("Unable to click on choose cover photo", "FAILURE");

		return this;
	}

	public ProfilePage clickOnSelectPhoto() {
		if (clickBTN_ByXpath("(//img[contains(@alt,'child and text')])[last()]"))
			Reporter.reportStep("photo selected:", "SUCCESS");
		else
			Reporter.reportStep("Unable to select photo", "FAILURE");

		return this;
	}

	public ProfilePage clickOnSaveChanges() {
		if (isElementPresent("(//span[text()='Save changes'])[last()]"))
			Reporter.reportStep("clicked on save changes :", "SUCCESS");
		else
			Reporter.reportStep("Unable to save the changes", "FAILURE");

		return this;
	}
	
	public ProfilePage clickOnEditCoverPhoto() {
		if(clickBTN_ByXpath("//span[text()='Edit cover photo']"))
			Reporter.reportStep("Clicked on Edit cover photo :", "SUCCESS");
		else
			Reporter.reportStep("Unable to click on Edit cover photo", "FAILURE");

		return this;
	}
	
	public ProfilePage removeCoverPhoto() {
		if (clickBTN_ByXpath("//span[text()='Remove']"))
			Reporter.reportStep("Clicked on Remove cover photo :", "SUCCESS");
		else
			Reporter.reportStep("Unable to click on Remove cover photo", "FAILURE");

		return this;
		
		
	}
	
	public ProfilePage clickOnConfirmBTN() {
		if (clickBTN_ByXpath("//span[text()='Confirm']"))
			Reporter.reportStep("Clicked on confim  :", "SUCCESS");
		else
			Reporter.reportStep("Unable to click on confirm BTN", "FAILURE");

		return this;
	
	}
	
}
