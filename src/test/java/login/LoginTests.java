package login;

import Base.BaseTest;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.RegisterPage;
import Utilities.ReportManager;
import Utilities.ScannerUtil;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class LoginTests extends BaseTest {

    @Test(description = "Test successful user registration and login.")
    public void testValidLogin() throws IOException {
        try {
            test = ReportManager.createTest("Valid Login Test", "Verify that a newly registered user can log in.");

            test.info("Reading user data from Excel.");
            Map<String, String> data = ScannerUtil.readExcelToMap("src/main/resources/UserDetails.xlsx");

            test.info("Registering a new user.");
            RegisterPage accountPage = new RegisterPage(driver);
            assertTrue(accountPage.reigisteruser(data), "User registration failed.");
            test.pass("User registration was successful.");
            HomePage homePage=new HomePage(driver);
            homePage.clickOnMyAccount();
            homePage.logout();
            test.info("Logging in with the newly registered user credentials.");
            LoginPage loginPage = new LoginPage(driver);
            assertTrue(loginPage.Accountlogin(data), "Login failed with valid credentials.");
            test.pass("User login was successful.");

        } catch (Exception e) {
            logFailure(e, "testValidLogin");
        }
    }

    @Test(description = "Test login with invalid credentials.")
    public void testLoginInvalidCredentials() throws IOException {
        try {
            test = ReportManager.createTest("Invalid Login Test", "Verify that login fails with incorrect credentials.");

            Map<String, String> invalidData = new HashMap<>();
            invalidData.put("mail", "invalid@example.com");
            invalidData.put("password", "wrongpassword");

            test.info("Attempting to log in with invalid credentials.");
            LoginPage loginPage = new LoginPage(driver);
            
            // Expecting the login to fail, so the result of invalidLogin should be true
            assertTrue(loginPage.invalidLogin(invalidData), "Login with invalid credentials did not show the expected failure message.");
            test.pass("Login correctly failed with invalid credentials.");

        } catch (Exception e) {
            logFailure(e, "testLoginInvalidCredentials");
        }
    }
}
