package pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import utils.Reporter;
import wrapper.GenericWrappers;

public class LoginPage extends GenericWrappers {
	
	private final RemoteWebDriver driver;
	
	public LoginPage() {
		driver = getDriver();
	}
	
	
	public LoginPage enterUserName(String userName) {
		
		if(enterById("email", userName))
			Reporter.reportStep("The User Name field entered successfully with value :"+userName, "SUCCESS");
		else
			Reporter.reportStep("The User Name field value could not be entered ", "FAILURE");
		
		return this;
		
	}
	
    public LoginPage enterPassword(String password) {
		
		if(enterById("pass34", password))
			Reporter.reportStep("The password field entered successfully with value :"+password, "SUCCESS");
		else
			Reporter.reportStep("The password field value could not be entered ", "FAILURE");
		
		return this;
		
	}
	

}
