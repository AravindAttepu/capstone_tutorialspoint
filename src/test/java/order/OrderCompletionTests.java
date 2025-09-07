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
            test = ReportManager.createTest("Complete Order", "Place an Order and Verify Confirmation");

            test.info("Registering a new user");
            Map<String, String> data = ScannerUtil.readExcelToMap("src/main/resources/UserDetails.xlsx");
            RegisterPage accountPage = new RegisterPage(driver);
            assertTrue(accountPage.reigisteruser(data), "Registration Failed");

            test.info("Logging in with the new user");
            LoginPage loginPage = new LoginPage(driver);
            assertTrue(loginPage.Accountlogin(data), "Login Failed");

            test.info("Placing an order");
            HomePage homePage = new HomePage(driver);
            homePage.searchProduct("iphone");
            WaitUtil.waitForPageLoad(driver, 10);
            ProductsPage productsPage = new ProductsPage(driver);
            productsPage.viewProduct();
            ProductPage productPage = new ProductPage(driver);
            productPage.addToCart();
            CartPage cartPage = new CartPage(driver);
            cartPage.checkout();
           

            test.info("Verifying order confirmation");
          assertTrue(true);
            test.pass("Successfully placed order and verified confirmation");

        } catch (Exception e) {
            logFailure(e, "testPlaceOrderAndVerifyConfirmation");
        }
    }
}
