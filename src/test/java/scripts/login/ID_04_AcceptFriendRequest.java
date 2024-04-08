package scripts.login;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.FriendsPage;
import pages.HomePage;
import pages.LoginPage;
import utils.DataInputProvider;
import wrapper.GenericWrappers;

public class ID_04_AcceptFriendRequest extends GenericWrappers {
	
	@BeforeTest
	public void setReportDetails() {
		
		System.out.println(" ------  in before test ------");
		testcaseName = this.getClass().getName();
		testcaseDec = this.getClass().getSimpleName();
		author= "Amruta Chougule";
		category = "Login";
	}
	
	@Test(dataProvider = "LoginTestID_01")
	public void acceptRequestTest(String username,String password, String expected_result)
	{
		try {
			launchApp(browserName, false);
			new LoginPage()
			.enterUserName(username)
			.enterPassword(password)
			.clickonLoginBTN()
			.verifyHomepageDisplayed(expected_result)
			.clickOnFindFriend()
			.verifyFriendspageDisplayed()
			.clickOnConfirmBTN();
			
			Status= "PASS";
		}
		
		finally {
			quitBrowser();
		}
		
	}
	
	@DataProvider(name= "LoginTestID_01")
	public Object[][] fetchData() throws IOException{
		Object[][] arrayObj = DataInputProvider.getSheet("LoginTest_ID_01");
		return arrayObj;
	}

}
