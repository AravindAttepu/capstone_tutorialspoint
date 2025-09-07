package Testcases;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.annotations.Test;

import Base.BaseTest;
import Pages.HomePage;
import Utilities.ReportManager;

public class UIChecks extends BaseTest {

    @Test
    public void verifyLogo() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Verify Logo");
            HomePage homePage = new HomePage(driver);
            assertTrue(homePage.isLogoDisplayed(), "Logo is not displayed");
        } catch (Exception e) {
            logFailure(e, "verifyLogo");
        }
    }

    @Test
    public void verifySearchBar() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Verify Search Bar");
            HomePage homePage = new HomePage(driver);
            assertTrue(homePage.isSearchBarDisplayed(), "Search bar is not displayed");
        } catch (Exception e) {
            logFailure(e, "verifySearchBar");
        }
    }

    @Test
    public void verifyMainMenu() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Verify Main Menu");
            HomePage homePage = new HomePage(driver);
            assertTrue(homePage.isMainMenuDisplayed(), "Main menu is not displayed");
        } catch (Exception e) {
            logFailure(e, "verifyMainMenu");
        }
    }
}
