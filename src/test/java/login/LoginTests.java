package login;

import Base.BaseTest;
import Pages.LoginPage;
import Pages.RegisterPage;
import Utilities.ReportManager;
import Utilities.ScannerUtil;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

import static org.testng.Assert.assertTrue;

public class LoginTests extends BaseTest {

    @Test
    public void testValidLogin() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Valid Login");
            Map<String, String> data = ScannerUtil.readExcelToMap("src/main/resources/Userdetails.xlsx");

            test.info("Registering a new user");
            RegisterPage accountPage = new RegisterPage(driver);
            assertTrue(accountPage.reigisteruser(data), "Registration Failed");

            test.info("Logging in with valid credentials");
            LoginPage loginPage = new LoginPage(driver);
            assertTrue(loginPage.Accountlogin(data), "Login Failed");
            test.pass("Valid login test successful");

        } catch (Exception e) {
            logFailure(e, "testValidLogin");
        }
    }

    @Test
    public void testInvalidLogin() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Invalid Login");
            Map<String, String> data = ScannerUtil.readExcelToMap("src/main/resources/Userdetails.xlsx");
            data.put("email", "invalid@email.com");

            test.info("Attempting to log in with invalid credentials");
            LoginPage loginPage = new LoginPage(driver);
            assertTrue(loginPage.Accountlogin(data), "Invalid Login test failed");
            test.pass("Invalid login test successful");

        } catch (Exception e) {
            logFailure(e, "testInvalidLogin");
        }
    }
}
