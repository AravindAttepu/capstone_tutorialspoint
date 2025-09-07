package cart;

import Base.BaseTest;
import Pages.CartPage;
import Pages.HomePage;
import Pages.ProductPage;
import Pages.ProductsPage;
import Utilities.ReportManager;
import Utilities.WaitUtil;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class CartValidationTests extends BaseTest {

    @Test(priority = 1)
    public void testCartSummaryDetails() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Verify cart summary details");
            test.info("Searching for a product");
            HomePage homePage = new HomePage(driver);
            homePage.searchProduct("iphone");
            WaitUtil.waitForPageLoad(driver, 10);
            test.info("Viewing the product");
            ProductsPage productsPage = new ProductsPage(driver);
            productsPage.viewProduct();
            test.info("Adding product to the cart");
            ProductPage productPage = new ProductPage(driver);
            productPage.addToCart();
            CartPage cartPage = new CartPage(driver);
            test.info("Verifying cart summary");
            assertTrue(cartPage.verifyCartSummary(), "Cart summary verification failed");
            test.pass("Successfully verified cart summary details");
        } catch (Exception e) {
            logFailure(e, "testCartSummaryDetails");
        }
    }

    @Test(priority = 2)
    public void testCartPersistenceAcrossPages() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Verify cart persistence");
            test.info("Searching for a product");
            HomePage homePage = new HomePage(driver);
            homePage.searchProduct("iphone");
            WaitUtil.waitForPageLoad(driver, 10);
            test.info("Viewing the product");
            ProductsPage productsPage = new ProductsPage(driver);
            productsPage.viewProduct();
            test.info("Adding product to the cart");
            ProductPage productPage = new ProductPage(driver);
            productPage.addToCart();
            test.info("Navigating to home page");
            driver.get("https://tutorialsninja.com/demo/index.php?route=common/home");
            WaitUtil.waitForPageLoad(driver, 10);
            test.info("Navigating back to cart");
            driver.get("https://tutorialsninja.com/demo/index.php?route=checkout/cart");
            WaitUtil.waitForPageLoad(driver, 10);
            CartPage cartPage = new CartPage(driver);
            test.info("Verifying cart is not empty");
            assertTrue(cartPage.isCartNotEmpty(), "Cart is empty");
            test.pass("Successfully verified cart persistence across pages");
        } catch (Exception e) {
            logFailure(e, "testCartPersistenceAcrossPages");
        }
    }
}
