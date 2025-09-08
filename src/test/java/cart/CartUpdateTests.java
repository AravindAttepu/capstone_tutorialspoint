package cart;


import Base.BaseTest;
import Pages.CartPage;
import Pages.HomePage;
import Pages.ProductPage;
import Pages.ProductsPage;
import Utilities.ReportManager;
import Utilities.WaitUtil;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class CartUpdateTests extends BaseTest {

    @Test(priority = 1)
    public void testUpdateCartQuantity() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Update cart quantity");
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
            driver.findElement(By.xpath("//span[text() = \"Checkout\"]")).click();
            CartPage cartPage = new CartPage(driver);
            test.info("Updating cart quantity");
            assertTrue(cartPage.updateQuantity(2), "Update quantity failed");
            test.pass("Successfully updated the cart quantity");
        } catch (Exception e) {
            logFailure(e, "testUpdateCartQuantity");
        }
    }

    @Test(priority = 2)
    public void testInvalidQuantity() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Invalid quantity in cart");
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
            driver.findElement(By.xpath("//span[text() = \"Checkout\"]")).click();
            CartPage cartPage = new CartPage(driver);
            test.info("Updating with invalid quantity");
            assertFalse(cartPage.updateQuantity(0), "Invalid quantity test failed");
            test.pass("Successfully handled invalid quantity update");
        } catch (Exception e) {
            logFailure(e, "testInvalidQuantity");
        }
    }

    @Test(priority = 3)
    public void testRemoveProductFromCart() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Remove product from cart");
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
            driver.findElement(By.xpath("//span[text() = \"Checkout\"]")).click();
            CartPage cartPage = new CartPage(driver);
            test.info("Removing product from cart");
            assertTrue(cartPage.removeProduct(), "Remove product failed");
            test.pass("Successfully removed product from the cart");
        } catch (Exception e) {
            logFailure(e, "testRemoveProductFromCart");
        }
    }
}
