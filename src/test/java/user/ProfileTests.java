package user;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.*;

import Base.BaseTest;
import Pages.AccountPage;
import Pages.HomePage;
import Pages.LoginPage;
import Utilities.ReportManager;
import Utilities.ScannerUtil;

public class ProfileTests extends BaseTest {

  

    @Test
    public void testEditProfileDetails() throws IOException {
    	try {
		test= ReportManager.createTest("Edit profile", "Editing profile details");
    	HomePage homePage = new HomePage(driver);
    	if(!homePage.checkedloginin())
    	{
    		homePage.clickonlogin();
    		test.info("login  page opened");
			Map<String, String> data = ScannerUtil.readExcelToMap("src/main/resources/Userdetails.xlsx");
			test.info("data loaded");
			LoginPage loginPage= new LoginPage(driver);
			test.info("loginpage object loaded");
			assertTrue(loginPage.Accountlogin(data),"Login Failed");
    	}
    	homePage.clickonMyAccount();
    	AccountPage accountPage = new AccountPage(driver);
    	accountPage.clickchangeaccountdetails();
    	
    	//after check Success: Your account has been successfully updated.
    	
    		

		} catch (Exception e) {
			// TODO: handle exception
			logFailure(e, "edit profile failed");
		}
    }

    @Test
    public void testPasswordChangeFlow() throws IOException {
    	try {
			
    	test= ReportManager.createTest("Edit password", "changing password after login");
    	HomePage homePage = new HomePage(driver);
    	if(!homePage.checkedloginin())
    	{
    		homePage.clickonlogin();
    		test.info("login  page opened");
			Map<String, String> data = ScannerUtil.readExcelToMap("src/main/resources/Userdetails.xlsx");
			test.info("data loaded");
			LoginPage loginPage= new LoginPage(driver);
			test.info("loginpage object loaded");
			assertTrue(loginPage.Accountlogin(data),"Login Failed");
    	}
    	homePage.clickonlogin();
    	AccountPage accountPage = new AccountPage(driver);
    	accountPage.clickchangepassword();
    	
    	//after check Success: Your account has been successfully updated.
    	
    		

		} catch (Exception e) {
			// TODO: handle exception
			logFailure(e, "change password failed");
		}
    }

    @Test
    public void testForgotPasswordResetEmail() throws IOException {  	try {
			
    	test= ReportManager.createTest("forgot password", "changing password before login");
    	HomePage homePage = new HomePage(driver);
    	if(homePage.checkedloginin())
    	{
    		homePage.makelogout();
    	}
    	homePage.clickonlogin();
    	LoginPage loginPage= new LoginPage(driver);
    	assertTrue(loginPage.forgotpassword()," operation forgot password failed");
    	
    	//after check Success: Your account has been successfully updated.
    	
    		

		} catch (Exception e) {
			// TODO: handle exception
			logFailure(e, "forgot password failed");
		}
    }

    
}
