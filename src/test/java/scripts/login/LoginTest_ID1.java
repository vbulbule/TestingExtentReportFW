package scripts.login;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import wrapper.GenericWrappers;

public class LoginTest_ID1 extends GenericWrappers {
	
	@BeforeTest
	public void setReportDetails() {
		
		System.out.println(" ---  in before test ------");
		testcaseName = this.getClass().getName();
		testcaseDec = this.getClass().getSimpleName();
		author= "Amruta Chougule";
		category = "Login";
	}
	
	@Test
	public void loginTest() {
		
		try {
			
		    launchApp(browserName, false);
			
			Status= "PASS";
			
		}
		finally {
			quitBrowser();
		}
		
	}

}
