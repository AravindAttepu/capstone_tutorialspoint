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

            test.info("Registering a new user");
            Map<String, String> data = ScannerUtil.readExcelToMap("src/main/resources/Userdetails.xlsx");
            RegisterPage accountPage = new RegisterPage(driver);
            assertTrue(accountPage.reigisteruser(data), "Registration Failed");

            test.info("Logging in with the new user");
            LoginPage loginPage = new LoginPage(driver);
            assertTrue(loginPage.Accountlogin(data), "Login Failed");

            test.info("Searching for a product");
            HomePage homePage = new HomePage(driver);
            homePage.searchProduct("iphone");
            WaitUtil.waitForPageLoad(driver, 10);

            test.info("Viewing the product");
            ProductsPage productsPage = new ProductsPage(driver);
            productsPage.viewProduct();

            test.info("Adding product to the cart");
            ProductPage productPage = new ProductPage(driver);
            assertTrue(productPage.addToCart(), "Add to cart failed");

            test.info("Proceeding to checkout");
            CartPage cartPage = new CartPage(driver);
            cartPage.checkout();

            test.info("Confirming the order");
            CheckoutPage checkoutPage = new CheckoutPage(driver);
            checkoutPage.confirmOrder();
            test.pass("Order placed and confirmation verified successfully");

        } catch (Exception e) {
            logFailure(e, "testPlaceOrderAndVerifyConfirmation");
        }
    }
}
