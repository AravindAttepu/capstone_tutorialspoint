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

public class CartUpdateTests extends BaseTest {

    @Test
    public void testUpdateCartQuantity() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Update cart quantity");
            HomePage homePage = new HomePage(driver);
            homePage.searchproduct("iphone");
            WaitUtil.waitForPageLoad(driver, 10);
            ProductsPage productsPage = new ProductsPage(driver);
            productsPage.viewproduct();
            ProductPage productPage = new ProductPage(driver);
            productPage.addToCart();
            CartPage cartPage = new CartPage(driver);
            assertTrue(cartPage.updateQuantity(2), "Update quantity failed");
        } catch (Exception e) {
            logFailure(e, "testUpdateCartQuantity");
        }
    }

    @Test
    public void testInvalidQuantity() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Invalid quantity in cart");
            HomePage homePage = new HomePage(driver);
            homePage.searchproduct("iphone");
            WaitUtil.waitForPageLoad(driver, 10);
            ProductsPage productsPage = new ProductsPage(driver);
            productsPage.viewproduct();
            ProductPage productPage = new ProductPage(driver);
            productPage.addToCart();
            CartPage cartPage = new CartPage(driver);
            assertTrue(cartPage.updateQuantity(0), "Invalid quantity test failed");
        } catch (Exception e) {
            logFailure(e, "testInvalidQuantity");
        }
    }

    @Test
    public void testRemoveProductFromCart() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Remove product from cart");
            HomePage homePage = new HomePage(driver);
            homePage.searchproduct("iphone");
            WaitUtil.waitForPageLoad(driver, 10);
            ProductsPage productsPage = new ProductsPage(driver);
            productsPage.viewproduct();
            ProductPage productPage = new ProductPage(driver);
            productPage.addToCart();
            CartPage cartPage = new CartPage(driver);
            assertTrue(cartPage.removeProduct(), "Remove product failed");
        } catch (Exception e) {
            logFailure(e, "testRemoveProductFromCart");
        }
    }
}
