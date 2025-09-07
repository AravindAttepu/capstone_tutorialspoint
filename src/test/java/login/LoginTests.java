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

            // Registration
            RegisterPage accountPage = new RegisterPage(driver);
            assertTrue(accountPage.reigisteruser(data), "Registration Failed");

            // Login
            LoginPage loginPage = new LoginPage(driver);
            assertTrue(loginPage.Accountlogin(data), "Login Failed");

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

            // Login
            LoginPage loginPage = new LoginPage(driver);
            assertTrue(loginPage.Accountlogin(data), "Invalid Login test failed");

        } catch (Exception e) {
            logFailure(e, "testInvalidLogin");
        }
    }
}
