package scripts.login;

import org.testng.annotations.BeforeTest;

import wrapper.GenericWrappers;

public class LoginTest_ID11 extends GenericWrappers{
	
	@BeforeTest
	public void setReportDetails() {
		
		System.out.println(" ------  in before test ------");
		testcaseName = this.getClass().getName();
		testcaseDec = this.getClass().getSimpleName();
		author= "Amruta Chougule";
		category = "Login";
	}

}
