package scripts.login;

import java.io.IOException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.FriendSearchResultPage;
import pages.FriendsPage;
import pages.HomePage;
import pages.LoginPage;
import utils.DataInputProvider;
import wrapper.GenericWrappers;

public class ID_05_SeachFriendandSendFriendRequest extends GenericWrappers {
	
	@BeforeTest
	public void setReportDetails() {
		
		System.out.println(" ------  in before test ------");
		testcaseName = this.getClass().getName();
		testcaseDec = this.getClass().getSimpleName();
		author= "Amruta Chougule";
		category = "Login";
	}
	
	@Test(dataProvider = "SendFriend_Request")
	public void acceptRequestTest(String username,String password,String expected_result , String name)
	{
		try {
			launchApp(browserName, false);
			new LoginPage()
			.enterUserName(username)
			.enterPassword(password)
			.clickonLoginBTN()
			//.verifyInvalidLoginError()
			.verifyHomepageDisplayed(expected_result)
			.clickOnSearchIcon(name);
			
			new FriendSearchResultPage()
			.addFriends();
			//verify frnd is added
			Status= "PASS";
		}
		
		finally {
			quitBrowser();
		}
		
	}
	
	@DataProvider(name= "SendFriend_Request")
	public Object[][] fetchData() throws IOException{
		Object[][] arrayObj = DataInputProvider.getSheet("send_friendRequest_Test");
		return arrayObj;
	}

	

}
