package pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import utils.Reporter;
import wrapper.GenericWrappers;

public class FriendSearchResultPage extends GenericWrappers {

	private final RemoteWebDriver driver;

	public FriendSearchResultPage() {
		driver = getDriver();
	}
	
	public FriendSearchResultPage addFriends()
	{
		if(clickBTN_ByXpath("(//div[@aria-label='Add Friend']//span[text()='Add Friend'])[1]"))
    		Reporter.reportStep("Friend Added  :", "SUCCESS");
    	else
			Reporter.reportStep("Friend is not Added ", "FAILURE");
		
		return this;
	}

}
