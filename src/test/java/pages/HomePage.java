package pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import utils.Reporter;
import wrapper.GenericWrappers;

public class HomePage extends GenericWrappers {
	
    private final RemoteWebDriver driver;
	
	public HomePage() {
		driver = getDriver();
	}
	
	public HomePage verifyHomepageDisplayed(String name)  
	{
		if(verifyElementIsSelected("//a[@aria-label='Home']" , name))
    		Reporter.reportStep("Home page is displayed  :", "SUCCESS");
    	else
			Reporter.reportStep("Home page is not displayed", "FAILURE");
		
		return this;
	}
	
	public HomePage verifyRememberPasswordpopupisDisplayed()  
	{
		if(isElementPresent("//div[@aria-label='OK']"))
    		Reporter.reportStep("Remember Password popup is Displayed :", "SUCCESS");
    	else
			Reporter.reportStep("Remember Password popup is not Displayed", "FAILURE");
		
		return this;
	}
	
	public FriendsPage clickOnFindFriend()
	{
		if(clickBTN_ByXpath("//span[text()='Find friends']"))
    		Reporter.reportStep("Clicked on Find friends section  :", "SUCCESS");
    	else
			Reporter.reportStep("unable to click on Find friends section", "FAILURE");
		
		return new FriendsPage();
	}
	
	
	public FriendSearchResultPage clickOnSearchIcon(String name)
	{
		if(searchText("//input[@aria-label='Search Facebook']" , name))
    		Reporter.reportStep("Friend is searched  :", "SUCCESS");
    	else
			Reporter.reportStep("Friend is not searched", "FAILURE");
		
		return new FriendSearchResultPage();
	}
	
	public ProfilePage clickOnProfileIcon()
	{
		if(isElementPresent("//span[text()='Amruta Chougule']"))
    		Reporter.reportStep("clicked on profile icon  :", "SUCCESS");
    	else
			Reporter.reportStep("Unable to click on profile icon ", "FAILURE");
		
		
		return new ProfilePage() ;
		
	}

}
