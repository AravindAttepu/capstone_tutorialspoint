package user;

import Base.BaseTest;
import Pages.LoginPage;
import Utilities.ReportManager;
import Utilities.ScannerUtil;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class LoginTests extends BaseTest {

    @Test
    public void testLoginValidCredentials() throws IOException {
        try {
            test = ReportManager.createTest("User Login", "Test with valid credentials");

            test.info("Reading user data from Excel");
            Map<String, String> data = ScannerUtil.readExcelToMap("src/main/resources/UserDetails.xlsx");

            test.info("Attempting to log in with valid credentials");
            LoginPage loginPage = new LoginPage(driver);
            assertTrue(loginPage.Accountlogin(data), "Login with valid credentials failed");
            test.pass("Successfully logged in with valid credentials");

        } catch (Exception e) {
            logFailure(e, "testLoginValidCredentials");
        }
    }

    @Test
    public void testLoginInvalidCredentials() throws IOException {
        try {
            test = ReportManager.createTest("User Login", "Test with invalid credentials");

            test.info("Reading user data from Excel");
            Map<String, String> data = ScannerUtil.readExcelToMap("src/main/resources/UserDetails.xlsx");

            test.info("Attempting to log in with invalid credentials");
            LoginPage loginPage = new LoginPage(driver);
            assertFalse(loginPage.invalidLogin(data), "Login with invalid credentials was successful");
            test.pass("Successfully failed to log in with invalid credentials");

        } catch (Exception e) {
            logFailure(e, "testLoginInvalidCredentials");
        }
    }
}
