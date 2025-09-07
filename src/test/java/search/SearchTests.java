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
            HomePage homePage = new HomePage(driver);
            homePage.searchProduct("iphone");
            ProductsPage productsPage = new ProductsPage(driver);
            assertTrue(productsPage.areProductsDisplayed(), "Valid search failed");
        } catch (Exception e) {
            logFailure(e, "testValidProductSearch");
        }
    }

    @Test
    public void testInvalidProductSearch() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Invalid product search");
            HomePage homePage = new HomePage(driver);
            homePage.searchProduct("invalidproduct");
            ProductsPage productsPage = new ProductsPage(driver);
            assertTrue(productsPage.getNoResultsMessage().contains("There is no product that matches the search criteria."), "Invalid search failed");
        } catch (Exception e) {
            logFailure(e, "testInvalidProductSearch");
        }
    }

    @Test
    public void testBlankProductSearch() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Blank product search");
            HomePage homePage = new HomePage(driver);
            homePage.searchProduct("");
            ProductsPage productsPage = new ProductsPage(driver);
            assertTrue(productsPage.getNoResultsMessage().contains("There is no product that matches the search criteria."), "Blank search failed");
        } catch (Exception e) {
            logFailure(e, "testBlankProductSearch");
        }
    }

    @Test
    public void testSpecialCharSearch() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Special character search");
            HomePage homePage = new HomePage(driver);
            homePage.searchProduct("@#$%");
            ProductsPage productsPage = new ProductsPage(driver);
            assertTrue(productsPage.getNoResultsMessage().contains("There is no product that matches the search criteria."), "Special character search failed");
        } catch (Exception e) {
            logFailure(e, "testSpecialCharSearch");
        }
    }
}
