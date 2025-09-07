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
            Map<String, String> data = new HashMap<String, String>();
            data.put("Email", "invalid@email.com");
            data.put("Password", "invalidpassword");
            LoginPage loginPage = new LoginPage(driver);
            assertTrue(loginPage.invalidLogin(data), "Invalid Login Test Failed");
        } catch (Exception e) {
            logFailure(e, "invalidLogin");
        }
    }

    @Test
    public void searchWithBlankInput() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Search with blank input");
            HomePage homePage = new HomePage(driver);
            homePage.searchProduct("");
            WaitUtil.waitForPageLoad(driver, 10);
            ProductsPage productsPage = new ProductsPage(driver);
            assertTrue(productsPage.isNoProductMessageDisplayed(), "No product message is not displayed");
        } catch (Exception e) {
            logFailure(e, "searchWithBlankInput");
        }
    }

    @Test
    public void searchWithSpecialCharacters() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Search with special characters");
            HomePage homePage = new HomePage(driver);
            homePage.searchProduct("@@@###");
            WaitUtil.waitForPageLoad(driver, 10);
            ProductsPage productsPage = new ProductsPage(driver);
            assertTrue(productsPage.isNoProductMessageDisplayed(), "No product message is not displayed");
        } catch (Exception e) {
            logFailure(e, "searchWithSpecialCharacters");
        }
    }
}
