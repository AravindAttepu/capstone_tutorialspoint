package search;

import Base.BaseTest;
import Pages.HomePage;
import Pages.ProductsPage;
import Utilities.ReportManager;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

import java.io.IOException;


public class SearchTests extends BaseTest {

    @Test
    public void testValidProductSearch() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Valid product search");
            test.info("Searching for a valid product");
            HomePage homePage = new HomePage(driver);
            homePage.searchProduct("iphone");
            ProductsPage productsPage = new ProductsPage(driver);
            assertTrue(productsPage.areProductsDisplayed(), "Valid search failed");
            test.pass("Valid product search successful");
        } catch (Exception e) {
            logFailure(e, "testValidProductSearch");
        }
    }

    @Test
    public void testInvalidProductSearch() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Invalid product search");
            test.info("Searching for an invalid product");
            HomePage homePage = new HomePage(driver);
            homePage.searchProduct("invalidproduct");
            ProductsPage productsPage = new ProductsPage(driver);
            assertTrue(productsPage.getNoResultsMessage().contains("There is no product that matches the search criteria."), "Invalid search failed");
            test.pass("Invalid product search successful");
        } catch (Exception e) {
            logFailure(e, "testInvalidProductSearch");
        }
    }

    @Test
    public void testBlankProductSearch() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Blank product search");
            test.info("Performing a blank product search");
            HomePage homePage = new HomePage(driver);
            homePage.searchProduct("");
            ProductsPage productsPage = new ProductsPage(driver);
            assertTrue(productsPage.getNoResultsMessage().contains("There is no product that matches the search criteria."), "Blank search failed");
            test.pass("Blank product search successful");
        } catch (Exception e) {
            logFailure(e, "testBlankProductSearch");
        }
    }

    @Test
    public void testSpecialCharSearch() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Special character search");
            test.info("Searching with special characters");
            HomePage homePage = new HomePage(driver);
            homePage.searchProduct("@#$%");
            ProductsPage productsPage = new ProductsPage(driver);
            assertTrue(productsPage.getNoResultsMessage().contains("There is no product that matches the search criteria."), "Special character search failed");
            test.pass("Special character search successful");
        } catch (Exception e) {
            logFailure(e, "testSpecialCharSearch");
        }
    }
}
