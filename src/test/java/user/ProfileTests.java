package user;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.*;

import Base.BaseTest;
import Pages.AccountPage;
import Pages.HomePage;
import Pages.LoginPage;
import Utilities.ReportManager;
import Utilities.ScannerUtil;

public class ProfileTests extends BaseTest {

  

    @Test(priority = 1)
    public void testEditProfileDetails() throws IOException {
    	try {
    	    test= ReportManager.createTest("Edit profile", "Editing profile details");
            test.info("Checking if user is logged in");
    	    HomePage homePage = new HomePage(driver);
    	if(!homePage.isLoggedIn())
    	{
    		homePage.clickOnLogin();
    		test.info("login  page opened");
			Map<String, String> data = ScannerUtil.readExcelToMap("src/main/resources/UserDetails.xlsx");
			test.info("data loaded");
			LoginPage loginPage= new LoginPage(driver);
			test.info("loginpage object loaded");
			assertTrue(loginPage.Accountlogin(data),"Login Failed");
    	}
    	homePage.clickOnMyAccount();
    	test.info("Clicked on MYAccount");
    	AccountPage accountPage = new AccountPage(driver);
    	accountPage.clickchangeaccountdetails();
        test.pass("Profile details edited successfully");
    	
    	//after check Success: Your account has been successfully updated.
    	
    		

		} catch (Exception e) {
			// TODO: handle exception
			logFailure(e, "edit profile failed");
		}
    }

    @Test(priority = 2)
    public void testPasswordChangeFlow() throws IOException {
    	try {
			
    	test= ReportManager.createTest("Edit password", "changing password after login");
        test.info("Checking if user is logged in");
    	HomePage homePage = new HomePage(driver);
    	if(!homePage.isLoggedIn())
    	{
    		homePage.clickOnLogin();
    		test.info("login  page opened");
			Map<String, String> data = ScannerUtil.readExcelToMap("src/main/resources/Userdetails.xlsx");
			test.info("data loaded");
			LoginPage loginPage= new LoginPage(driver);
			test.info("loginpage object loaded");
			assertTrue(loginPage.Accountlogin(data),"Login Failed");
    	}
    	homePage.clickOnLogin();
    	AccountPage accountPage = new AccountPage(driver);
    	accountPage.clickchangepassword();
        test.pass("Password changed successfully");
    	
    	//after check Success: Your account has been successfully updated.
    	
    		

		} catch (Exception e) {
			// TODO: handle exception
			logFailure(e, "change password failed");
		}
    }

    @Test(priority = 3)
    public void testForgotPasswordResetEmail() throws IOException {  	try {
			
    	test= ReportManager.createTest("forgot password", "changing password before login");
        test.info("Checking if user is logged in");
    	HomePage homePage = new HomePage(driver);
    	if(homePage.isLoggedIn())
    	{
    		homePage.logout();
    	}
    	homePage.clickOnLogin();
    	LoginPage loginPage= new LoginPage(driver);
    	Map<String, String> userMap=  new HashMap<>();
    	userMap.put("email", "fhfg@gdfg.com");
    	assertTrue(loginPage.forgottenPassword(userMap)," operation forgot password failed");
        test.pass("Forgot password reset email sent successfully");
    	
    	//after check Success: Your account has been successfully updated.
    	
    		

		} catch (Exception e) {
			// TODO: handle exception
			logFailure(e, "forgot password failed");
		}
    }

    
}
