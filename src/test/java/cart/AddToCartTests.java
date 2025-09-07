package cart;

import Base.BaseTest;
import Pages.HomePage;
import Pages.ProductPage;
import Pages.ProductsPage;
import Utilities.ReportManager;
import Utilities.WaitUtil;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class AddToCartTests extends BaseTest {

    @Test
    public void testAddProductSingleQty() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Add single quantity to cart");
            HomePage homePage = new HomePage(driver);
            homePage.searchproduct("iphone");
            WaitUtil.waitForPageLoad(driver, 10);
            ProductsPage productsPage = new ProductsPage(driver);
            productsPage.viewproduct();
            ProductPage productPage = new ProductPage(driver);
            assertTrue(productPage.addToCart(), "Add to cart failed");
        } catch (Exception e) {
            logFailure(e, "testAddProductSingleQty");
        }
    }

    @Test
    public void testAddProductSpecificQty() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Add specific quantity to cart");
            HomePage homePage = new HomePage(driver);
            homePage.searchProduct("iphone");
            WaitUtil.waitForPageLoad(driver, 10);
            ProductsPage productsPage = new ProductsPage(driver);
            productsPage.viewProduct();
            ProductPage productPage = new ProductPage(driver);
            assertTrue(productPage.addToCart(3), "Add to cart with specific quantity failed");
        } catch (Exception e) {
            logFailure(e, "testAddProductSpecificQty");
        }
    }
}
