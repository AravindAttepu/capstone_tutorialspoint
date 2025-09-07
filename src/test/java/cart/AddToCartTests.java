package cart;

import Base.BaseTest;
import Pages.HomePage;
import Pages.ProductPage;
import Pages.ProductsPage;
import Utilities.ReportManager;
import Utilities.WaitUtil;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class AddToCartTests extends BaseTest {

    @Test
    public void testAddProductSingleQty() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Add single quantity to cart");
            test.info("Searching for a product");
            HomePage homePage = new HomePage(driver);
            homePage.searchProduct("iphone");
            WaitUtil.waitForPageLoad(driver, 10);
            test.info("Viewing the product");
            ProductsPage productsPage = new ProductsPage(driver);
            productsPage.viewProduct();
            ProductPage productPage = new ProductPage(driver);
            test.info("Adding product to the cart");
            assertTrue(productPage.addToCart(), "Add to cart failed");
            test.pass("Successfully added a single quantity of the product to the cart");
        } catch (Exception e) {
            logFailure(e, "testAddProductSingleQty");
        }
    }

    @Test
    public void testAddProductSpecificQty() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Add specific quantity to cart");
            test.info("Searching for a product");
            HomePage homePage = new HomePage(driver);
            homePage.searchProduct("iphone");
            WaitUtil.waitForPageLoad(driver, 10);
            test.info("Viewing the product");
            ProductsPage productsPage = new ProductsPage(driver);
            productsPage.viewProduct();
            ProductPage productPage = new ProductPage(driver);
            test.info("Adding a specific quantity of the product to the cart");
            assertTrue(productPage.addToCart("3"), "Add to cart with specific quantity failed");
            test.pass("Successfully added a specific quantity of the product to the cart");
        } catch (Exception e) {
            logFailure(e, "testAddProductSpecificQty");
        }
    }
}
