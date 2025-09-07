package Testcases;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import Base.BaseTest;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.ProductsPage;
import Utilities.ReportManager;
import Utilities.WaitUtil;

public class NegativeScenarios extends BaseTest {

    @Test
    public void invalidLogin() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Invalid Login");

            test.info("Entering invalid login credentials");
            Map<String, String> data = new HashMap<>();
            data.put("mail", "invalid@email.com");
            data.put("password", "invalidpassword");

            LoginPage loginPage = new LoginPage(driver);
            assertTrue(loginPage.invalidLogin(data), "Invalid Login Test Failed");
            test.pass("Invalid login attempt displayed proper error message");

        } catch (Exception e) {
            test.fail("Test failed due to exception: " + e.getMessage());
            logFailure(e, "invalidLogin");
        }
    }

    @Test
    public void searchWithBlankInput() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Search with blank input");

            test.info("Searching with blank input");
            HomePage homePage = new HomePage(driver);
            homePage.searchProduct("");
            WaitUtil.waitForPageLoad(driver, 10);

            test.info("Verifying 'No product' message is displayed");
            ProductsPage productsPage = new ProductsPage(driver);
            assertTrue(productsPage.isNoProductMessageDisplayed(), "No product message is not displayed");
            test.pass("Blank input search displayed 'No product' message successfully");

        } catch (Exception e) {
            test.fail("Test failed due to exception: " + e.getMessage());
            logFailure(e, "searchWithBlankInput");
        }
    }

    @Test
    public void searchWithSpecialCharacters() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Search with special characters");

            test.info("Searching with special characters '@@@###'");
            HomePage homePage = new HomePage(driver);
            homePage.searchProduct("@@@###");
            WaitUtil.waitForPageLoad(driver, 10);

            test.info("Verifying 'No product' message is displayed");
            ProductsPage productsPage = new ProductsPage(driver);
            assertTrue(productsPage.isNoProductMessageDisplayed(), "No product message is not displayed");
            test.pass("Special character search displayed 'No product' message successfully");

        } catch (Exception e) {
            test.fail("Test failed due to exception: " + e.getMessage());
            logFailure(e, "searchWithSpecialCharacters");
        }
    }
}
