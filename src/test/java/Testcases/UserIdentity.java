package Testcases;

import static org.testng.Assert.assertTrue; // ✅ only TestNG

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

    @Test(description = "Register a new user and verify login.")
    public void Registration() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint - Registration", "Register and Login flow");

            Map<String, String> data = ScannerUtil.readExcelToMap("src/main/resources/UserDetails.xlsx");

            test.info("Starting user registration");
            RegisterPage accountPage = new RegisterPage(driver);
            assertTrue(accountPage.reigisteruser(data), "Registration Failed");
            test.pass("User registered successfully");

            test.info("Logging in with registered user");
            LoginPage loginPage = new LoginPage(driver);
            assertTrue(loginPage.Accountlogin(data), "Login Failed after registration");
            test.pass("User login successful");

        } catch (Exception e) {
            test.fail("Test failed due to exception: " + e.getMessage());
            logFailure(e, "user registration and login");
        }
    }

    @Test(description = "Verify valid user login.")
    public void Login() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint - Login", "Verify valid login");

            Map<String, String> data = ScannerUtil.readExcelToMap("src/main/resources/UserDetails.xlsx");

            test.info("Attempting to log in with valid credentials");
            LoginPage loginPage = new LoginPage(driver);
            assertTrue(loginPage.Accountlogin(data), "Valid login failed");
            test.pass("Valid user login successful");

        } catch (Exception e) {
            test.fail("Login test failed due to exception: " + e.getMessage());
            logFailure(e, "valid login");
        }
    }

    @Test(description = "Verify invalid login scenario.")
    public void InvalidLogin() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint - Invalid Login", "Verify login with invalid credentials");

            Map<String, String> data = new HashMap<>();
            data.put("Email", "invalid@email.com");
            data.put("Password", "invalidpassword");

            test.info("Attempting to log in with invalid credentials");
            LoginPage loginPage = new LoginPage(driver);
            assertTrue(loginPage.invalidLogin(data), "Invalid login test failed");
            test.pass("Invalid login handled correctly");

        } catch (Exception e) {
            test.fail("Invalid login test failed due to exception: " + e.getMessage());
            logFailure(e, "invalid login");
        }
    }

    @Test(description = "Verify forgotten password functionality.")
    public void forgottenPassword() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint - Forgotten Password", "Verify reset password flow");

            Map<String, String> data = ScannerUtil.readExcelToMap("src/main/resources/UserDetails.xlsx");

            test.info("Attempting forgotten password request");
            LoginPage loginPage = new LoginPage(driver);
            assertTrue(loginPage.forgottenPassword(data), "Forgotten password test failed");
            test.pass("Forgotten password flow executed successfully");

        } catch (Exception e) {
            test.fail("Forgotten password test failed due to exception: " + e.getMessage());
            logFailure(e, "forgotten password");
        }
    }
}
