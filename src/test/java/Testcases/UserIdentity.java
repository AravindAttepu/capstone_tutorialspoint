package Testcases;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import Base.BaseTest;
import Pages.LoginPage;
import Pages.RegisterPage;
import Utilities.ScannerUtil;
import Utilities.ReportManager;

public class UserIdentity extends BaseTest {

    // Test to verify successful user registration and subsequent login.
    @Test(description = "Register a new user and verify login.")
    public void Registration() throws IOException {
        try {
            test = ReportManager.createTest("TutorialsPoint - Registration", "End-to-end user registration and login validation.");

            Map<String, String> data = ScannerUtil.readExcelToMap("src/main/resources/UserDetails.xlsx");

            test.info("Starting user registration process.");
            RegisterPage accountPage = new RegisterPage(driver);
            assertTrue(accountPage.reigisteruser(data), "User registration failed.");
            test.pass("User registration was successful.");

            test.info("Proceeding to login with the newly registered user.");
            LoginPage loginPage = new LoginPage(driver);
            assertTrue(loginPage.Accountlogin(data), "Login failed after successful registration.");
            test.pass("User login was successful.");

        } catch (Exception e) {
            logFailure(e, "User Registration and Login");
        }
    }

    // Test to verify that a registered user can log in successfully.
    @Test(description = "Verify valid user login.")
    public void Login() throws IOException {
        try {
            test = ReportManager.createTest("TutorialsPoint - Login", "Verify login functionality with valid credentials.");

            Map<String, String> data = ScannerUtil.readExcelToMap("src/main/resources/UserDetails.xlsx");

            test.info("Attempting to log in with valid user credentials.");
            LoginPage loginPage = new LoginPage(driver);
            assertTrue(loginPage.Accountlogin(data), "Login with valid credentials failed.");
            test.pass("Valid user login was successful.");

        } catch (Exception e) {
            logFailure(e, "Valid Login");
        }
    }

    // Test to verify that login fails with invalid credentials.
    @Test(description = "Verify invalid login scenario.")
    public void InvalidLogin() throws IOException {
        try {
            test = ReportManager.createTest("TutorialsPoint - Invalid Login", "Verify that login fails with incorrect credentials.");

            Map<String, String> data = new HashMap<>();
            // Corrected key to 'email' and added the 'password' key
            data.put("email", "invalid@example.com");
            data.put("password", "wrongpassword");

            test.info("Attempting to log in with invalid credentials.");
            LoginPage loginPage = new LoginPage(driver);
            assertFalse(loginPage.invalidLogin(data), "Login with invalid credentials unexpectedly succeeded.");
            test.pass("Invalid login attempt was correctly blocked.");

        } catch (Exception e) {
            logFailure(e, "Invalid Login");
        }
    }

    // Test to verify the forgotten password functionality.
    @Test(description = "Verify forgotten password functionality.")
    public void forgottenPassword() throws IOException {
        try {
            test = ReportManager.createTest("TutorialsPoint - Forgotten Password", "Verify the password reset email functionality.");

            Map<String, String> data = ScannerUtil.readExcelToMap("src/main/resources/UserDetails.xlsx");

            test.info("Attempting to trigger the forgotten password flow.");
            LoginPage loginPage = new LoginPage(driver);
            assertTrue(loginPage.forgottenPassword(data), "The forgotten password process failed.");
            test.pass("The forgotten password email was sent successfully.");

        } catch (Exception e) {
            logFailure(e, "Forgotten Password");
        }
    }
}
