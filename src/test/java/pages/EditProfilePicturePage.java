package pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import utils.Reporter;
import wrapper.GenericWrappers;

public class EditProfilePicturePage extends GenericWrappers  {
	
	private final RemoteWebDriver driver;

	public EditProfilePicturePage() { //initialize pages 
		driver = getDriver();
	}
	
	public EditProfilePicturePage verifyEditProfilePicturePageDisplayed() {
		if (isElementPresent("//a[@aria-label='Facebook']"))
			Reporter.reportStep("profile page is displayed  :", "SUCCESS");
		else
			Reporter.reportStep("profile page is not displayed", "FAILURE");

		return this;
	}
	
	
	public EditProfilePicturePage clickOnEditMenu() {
		if (isElementPresent("(//div[@aria-label='Actions for this post'])[last()]"))
			Reporter.reportStep("Edit menu is clicked ", "SUCCESS");
		else
			Reporter.reportStep("Unable to click on Edit menu", "FAILURE");

		return this;
	}
	
	
	public EditProfilePicturePage clickOnDownload() {
		if (isElementPresent("//span[text()='Download']"))
			Reporter.reportStep("Download option is selected ", "SUCCESS");
		else
			Reporter.reportStep("Unable to click on download option", "FAILURE");

		return this;
	}
	
	
	
	

}
