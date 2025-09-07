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

public class OrderHistoryTests extends BaseTest {

    @Test
    public void testViewOrderHistoryAndDetails() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "View Order History and Details");

            test.info("Registering a new user");
            Map<String, String> data = ScannerUtil.readExcelToMap("src/main/resources/Userdetails.xlsx");
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
            CheckoutPage checkoutPage = new CheckoutPage(driver);
            checkoutPage.confirmOrder();

            test.info("Viewing order history");
            OrderHistoryPage orderHistoryPage = new OrderHistoryPage(driver);
            orderHistoryPage.goToOrderHistory();
            assertTrue(orderHistoryPage.isOrderHistoryDisplayed(), "Order history is not displayed");
            assertTrue(orderHistoryPage.isOrderInHistory(), "Order is not in history");
            test.pass("Successfully viewed order history and details");

        } catch (Exception e) {
            logFailure(e, "testViewOrderHistoryAndDetails");
        }
    }
}
