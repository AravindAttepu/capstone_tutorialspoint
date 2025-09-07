package user;


import java.io.IOException;

import org.testng.annotations.*;

import Base.BaseTest;
import Pages.HomePage;
import Utilities.ReportManager;

public class UserSessionTests extends BaseTest {

 

    @Test
    public void testLogoutFunctionality() throws IOException {
    	try {
    		test= ReportManager.createTest("Logout", "Tessting logout functionality");
    		test.info("test started");
    		HomePage homePage = new HomePage(driver);
    		test.info("home page object created");
        	if(!homePage.isLoggedIn())
        	{
        		test.info("user logged in");
        		homePage.logout();
        		test.info("user kogged out");
        	}
        	else {
				test.info("user not logged in");
			}
        	test.pass("test passed");
		} catch (Exception e) {
			// TODO: handle exception
			logFailure(e, "logout failed");
		}
    }

   
}
