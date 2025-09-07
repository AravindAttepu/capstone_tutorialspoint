package login;

import Base.BaseTest;
import Pages.LoginPage;
import Pages.RegisterPage;
import Utilities.ReportManager;
import Utilities.ScannerUtil;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class LoginTests extends BaseTest {

    @Test
    public void testValidLogin() throws IOException {
        try {
            test = ReportManager.createTest("Login Test", "Valid Login");

            test.info("Registering a new user");
            Map<String, String> data = ScannerUtil.readExcelToMap("src/main/resources/UserDetails.xlsx");
            RegisterPage accountPage = new RegisterPage(driver);
            assertTrue(accountPage.reigisteruser(data), "Registration Failed");

            test.info("Logging in with the new user");
            LoginPage loginPage = new LoginPage(driver);
            assertTrue(loginPage.Accountlogin(data), "Login Failed");
            test.pass("Successfully logged in");

        } catch (Exception e) {
            logFailure(e, "testValidLogin");
        }
    }

    @Test
    public void testInvalidLogin() throws IOException {
        try {
            test = ReportManager.createTest("Login Test", "Invalid Login");

            test.info("Attempting to log in with invalid credentials");
            Map<String, String> data = ScannerUtil.readExcelToMap("src/main/resources/UserDetails.xlsx");
            LoginPage loginPage = new LoginPage(driver);
            assertFalse(loginPage.invalidLogin(data), "Invalid login was successful");
            test.pass("Successfully failed to log in with invalid credentials");

        } catch (Exception e) {
            logFailure(e, "testInvalidLogin");
        }
    }
}
