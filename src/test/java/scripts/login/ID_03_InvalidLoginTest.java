package scripts.login;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DataInputProvider;
import wrapper.GenericWrappers;

public class ID_03_InvalidLoginTest extends GenericWrappers {
	
	@BeforeTest
	public void setReportDetails() {
		
		System.out.println(" ------  in before test ------");
		testcaseName = this.getClass().getName();
		testcaseDec = this.getClass().getSimpleName();
		author= "Amruta Chougule";
		category = "Login";
	}
	
	@Test(dataProvider = "InvalidLoginTest")
	public void invalidLoginTest(String username,String password)
	{
		try {
			launchApp(browserName, false);
			new LoginPage()
			.enterUserName(username)
			.enterPassword(password)
			.clickonLoginBTN();
			
			Status= "PASS";
		}
		
		finally {
			quitBrowser();
		}
		
	}
	
	@DataProvider(name= "InvalidLoginTest")
	public Object[][] fetchData() throws IOException{
		Object[][] arrayObj = DataInputProvider.getSheet("InvalidLoginTest");
		return arrayObj;
	}

}
