package search;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Base.BaseTest;
import Pages.HomePage;
import Pages.ProductsPage;

import java.util.List;

public class FilterTests extends BaseTest {

    private ProductsPage productsPage;

    @BeforeClass
    public void setup() {
        HomePage homePage = new HomePage(driver);
        homePage.searchProduct("imac");
        productsPage = new ProductsPage(driver);
    }

    @Test(priority = 1)
    public void testFilterByCategory() {
        productsPage.selectCategory("Monitors");
        Assert.assertTrue(productsPage.areProductsDisplayed(), "Products are not displayed after filtering by category");
        List<String> productNames = productsPage.getProductNames();
        for (String name : productNames) {
            // This is a weak assertion, as we are just checking for the word "Monitor"
            // A better approach would be to have more specific product data to assert against
            Assert.assertTrue(name.toLowerCase().contains("monitor"), "Product name does not contain 'monitor'");
        }
    }

    @Test(priority = 2)
    public void testFilterByPriceRange() {
        productsPage.setPriceRange("100", "500");
        Assert.assertTrue(productsPage.areProductsDisplayed(), "Products are not displayed after filtering by price");
        List<Double> productPrices = productsPage.getProductPrices();
        for (Double price : productPrices) {
            Assert.assertTrue(price >= 100 && price <= 500, "Product price is not within the specified range");
        }
    }

    @Test(priority = 3)
    public void testFilterWithNoMatchingPrice() {
        productsPage.setPriceRange("1000", "2000");
        Assert.assertTrue(productsPage.isNoProductMessageDisplayed(), "No product message is not displayed for a price range with no results");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
