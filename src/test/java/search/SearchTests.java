package search;


import Base.BaseTest;
import Pages.HomePage;
import Utilities.ReportManager;
import Utilities.WaitUtil;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

import java.io.IOException;


public class SearchTests extends BaseTest {

    @Test
    public void testValidProductSearch() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Valid product search");
            HomePage homePage = new HomePage(driver);
            assertTrue(homePage.searchProduct("iphone"), "Valid search failed");
        } catch (Exception e) {
            logFailure(e, "testValidProductSearch");
        }
    }

    @Test
    public void testInvalidProductSearch() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Invalid product search");
            HomePage homePage = new HomePage(driver);
            assertTrue(homePage.searchProduct("invalidproduct"), "Invalid search failed");
        } catch (Exception e) {
            logFailure(e, "testInvalidProductSearch");
        }
    }

    @Test
    public void testBlankProductSearch() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Blank product search");
            HomePage homePage = new HomePage(driver);
            assertTrue(homePage.searchProduct(""), "Blank search failed");
        } catch (Exception e) {
            logFailure(e, "testBlankProductSearch");
        }
    }

    @Test
    public void testSpecialCharSearch() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Special character search");
            HomePage homePage = new HomePage(driver);
            assertTrue(homePage.searchProduct("@#$%"), "Special character search failed");
        } catch (Exception e) {
            logFailure(e, "testSpecialCharSearch");
        }
    }
}
