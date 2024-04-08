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
		
		if(editById("email", userName))
			Reporter.reportStep("The User Name field entered successfully with value :"+userName, "SUCCESS");
		else
			Reporter.reportStep("The User Name field value could not be entered ", "FAILURE");
		
		return this;
		
	}
	
    public LoginPage enterPassword(String password) {
		
		if(editById("pass", password))
			Reporter.reportStep("The password field entered successfully with value :"+password, "SUCCESS");
		else
			Reporter.reportStep("The password field value could not be entered ", "FAILURE");
		
		return this;
		
	}
    
    public HomePage clickonLoginBTN()
    {
    	if(clickOnBTN_ByName("login"))
    		Reporter.reportStep("The Login button clicked  :", "SUCCESS");
    	else
			Reporter.reportStep("The Login button is not clicked ", "FAILURE");
    	
    	return new HomePage();
    	
    }
    
  /*  public HomePage verifyInvalidLoginError()
    {
    	
    	if(isElementPresent("(//div[@id='email_container']//div)[2]"))
    	{
    		Reporter.reportStep("Invalid Login error is found : ", "SUCCESS");
    		getWait();
    	}
    	else
    		Reporter.reportStep("Invalid Login error is not found ", "FAILURE");
    	
    	return new HomePage();
    	
    }
	*/

}
