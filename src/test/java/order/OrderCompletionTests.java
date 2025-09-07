package order;

import Base.BaseTest;
import Pages.*;
import Utilities.ReportManager;
import Utilities.ScannerUtil;
import Utilities.WaitUtil;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

import static org.testng.Assert.assertTrue;

public class OrderCompletionTests extends BaseTest {

    @Test
    public void testPlaceOrderAndVerifyConfirmation() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Place Order and Verify Confirmation");

            // Registration
            Map<String, String> data = ScannerUtil.readExcelToMap("src/main/resources/Userdetails.xlsx");
            RegisterPage accountPage = new RegisterPage(driver);
            assertTrue(accountPage.reigisteruser(data), "Registration Failed");

            // Login
            LoginPage loginPage = new LoginPage(driver);
            assertTrue(loginPage.Accountlogin(data), "Login Failed");

            // Search and view product
            HomePage homePage = new HomePage(driver);
            homePage.searchProduct("iphone");
            WaitUtil.waitForPageLoad(driver, 10);

            // View product
            ProductsPage productsPage = new ProductsPage(driver);
            productsPage.viewProduct();

            // Add to cart
            ProductPage productPage = new ProductPage(driver);
            assertTrue(productPage.addToCart(), "Add to cart failed");

            // Checkout
            CartPage cartPage = new CartPage(driver);
            cartPage.checkout();

            CheckoutPage checkoutPage = new CheckoutPage(driver);
            checkoutPage.confirmOrder();

        } catch (Exception e) {
            logFailure(e, "testPlaceOrderAndVerifyConfirmation");
        }
    }
}
