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

public class OrderReturnTests extends BaseTest {

    @Test
    public void testRequestAndVerifyProductReturn() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Request and Verify Product Return");

            // Registration
            Map<String, String> data = ScannerUtil.readExcelToMap("src/main/resources/Userdetails.xlsx");
            RegisterPage accountPage = new RegisterPage(driver);
            assertTrue(accountPage.reigisteruser(data), "Registration Failed");

            // Login
            LoginPage loginPage = new LoginPage(driver);
            assertTrue(loginPage.Accountlogin(data), "Login Failed");

            // Place an order
            HomePage homePage = new HomePage(driver);
            homePage.searchProduct("iphone");
            WaitUtil.waitForPageLoad(driver, 10);
            ProductsPage productsPage = new ProductsPage(driver);
            productsPage.viewProduct();
            ProductPage productPage = new ProductPage(driver);
            productPage.addToCart();
            CartPage cartPage = new CartPage(driver);
            cartPage.checkout();
            CheckoutPage checkoutPage = new CheckoutPage(driver);
            checkoutPage.confirmOrder();

            // Request a return
            OrderHistoryPage orderHistoryPage = new OrderHistoryPage(driver);
            orderHistoryPage.goToOrderHistory();
            orderHistoryPage.requestReturn();

        } catch (Exception e) {
            logFailure(e, "testRequestAndVerifyProductReturn");
        }
    }
}
