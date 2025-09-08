package user;


import java.io.IOException;
import java.util.Map;

import org.testng.annotations.*;

import Base.BaseTest;
import Pages.HomePage;
import Pages.LoginPage;
import Utilities.ReportManager;
import Utilities.ScannerUtil;

public class UserSessionTests extends BaseTest {

 

    @Test(priority = 1)
    public void testLogoutFunctionality() throws IOException {
    	try {
    		test= ReportManager.createTest("Logout", "Tessting logout functionality");
    		test.info("test started");
    		HomePage homePage = new HomePage(driver);
    		test.info("home page object created");
        	if(!homePage.isLoggedIn())
        	{
        		driver.get("https://tutorialsninja.com/demo/index.php?route=account/login");
        		LoginPage page = new LoginPage(driver);
        		Map<String, String> data= ScannerUtil.readExcelToMap("src/main/resources/UserDetails.xlsx");
        		page.Accountlogin(data);
        		test.info("user logged in");
        		homePage.logout();
        		test.info("user logged out");
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
