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

    @Test
    public void testCartSummaryDetails() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Verify cart summary details");
            HomePage homePage = new HomePage(driver);
            homePage.searchproduct("iphone");
            WaitUtil.waitForPageLoad(driver, 10);
            ProductsPage productsPage = new ProductsPage(driver);
            productsPage.viewproduct();
            ProductPage productPage = new ProductPage(driver);
            productPage.addToCart();
            CartPage cartPage = new CartPage(driver);
            assertTrue(cartPage.verifyCartSummary(), "Cart summary verification failed");
        } catch (Exception e) {
            logFailure(e, "testCartSummaryDetails");
        }
    }

    @Test
    public void testCartPersistenceAcrossPages() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Verify cart persistence");
            HomePage homePage = new HomePage(driver);
            homePage.searchproduct("iphone");
            WaitUtil.waitForPageLoad(driver, 10);
            ProductsPage productsPage = new ProductsPage(driver);
            productsPage.viewproduct();
            ProductPage productPage = new ProductPage(driver);
            productPage.addToCart();
            driver.get("https://tutorialsninja.com/demo/index.php?route=common/home");
            WaitUtil.waitForPageLoad(driver, 10);
            driver.get("https://tutorialsninja.com/demo/index.php?route=checkout/cart");
            WaitUtil.waitForPageLoad(driver, 10);
            CartPage cartPage = new CartPage(driver);
            assertTrue(cartPage.isCartNotEmpty(), "Cart is empty");
        } catch (Exception e) {
            logFailure(e, "testCartPersistenceAcrossPages");
        }
    }
}
