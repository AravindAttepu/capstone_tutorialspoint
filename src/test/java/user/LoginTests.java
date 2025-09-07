package user;

import static org.junit.Assert.assertFalse;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.*;

import Base.BaseTest;
import Pages.HomePage;
import Pages.LoginPage;
import Utilities.ReportManager;
import Utilities.ScannerUtil;

public class LoginTests extends BaseTest {

  

    @Test(priority = 1)
    public void testLoginValidCredentials() throws IOException {
    	try {
    		
    		test= ReportManager.createTest("Login user", "entering details of user");
    		test.info("test started");
			HomePage page= new HomePage(driver);
			test.info("homepage object loaded");
			page.clickOnLogin();
			test.info("login  page opened");
			Map<String, String> data = ScannerUtil.readExcelToMap("src/main/resources/Userdetails.xlsx");
			test.info("data loaded");
			LoginPage loginPage= new LoginPage(driver);
			test.info("loginpage object loaded");
			assertTrue(loginPage.Accountlogin(data),"Login Failed");
			test.pass("Login successfull");
		} catch (Exception e) {
			// TODO: handle exception
			logFailure(e, "Login Failed");
		}
    }

    @Test(priority = 2)
    public void testLoginInvalidCredentials() throws IOException {
    	try {
    		test= ReportManager.createTest("Login user", "entering invalid details of user");
    		test.info("test started");
			HomePage page= new HomePage(driver);
			test.info("homepage object loaded");
			page.clickOnLogin();
			test.info("login page opened");
			Map<String, String> data = ScannerUtil.readExcelToMap("src/main/resources/Userdetails.xlsx");
			test.info("data loaded");
			LoginPage loginPage= new LoginPage(driver);
			test.info("loginpage object loaded");
			assertFalse(loginPage.Accountlogin(data),"Registration Failed");
			test.pass("invalid credentials test passed");
		} catch (Exception e) {
			// TODO: handle exception
			logFailure(e, "Invalid Login Failed");
		}
    }



   
}
