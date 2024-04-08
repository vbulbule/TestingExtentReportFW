package pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import utils.Reporter;
import wrapper.GenericWrappers;

public class ProfilePage extends GenericWrappers {
	
	////div[@aria-label='Add Cover Photo']

     private final RemoteWebDriver driver;
	
	public ProfilePage() {
		driver = getDriver();
	}
	
	public ProfilePage verifyProfileNameDisplayed(String name)  
	{
		if(verifyProfileName("//a[@aria-label='Home']" , name))
    		Reporter.reportStep("profile page is displayed  :", "SUCCESS");
    	else
			Reporter.reportStep("profile page is not displayed", "FAILURE");
		
		return this;
	}
}
