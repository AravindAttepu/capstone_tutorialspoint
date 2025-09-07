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

            // Registration
            Map<String, String> data = ScannerUtil.readExcelToMap("src/main/resources/Userdetails.xlsx");
            RegisterPage accountPage = new RegisterPage(driver);
            assertTrue(accountPage.reigisteruser(data), "Registration Failed");

            // Login
            LoginPage loginPage = new LoginPage(driver);
            assertTrue(loginPage.Accountlogin(data), "Login Failed");

            // Place an order
            HomePage homePage = new HomePage(driver);
            homePage.searchproduct("iphone");
            WaitUtil.waitForPageLoad(driver, 10);
            ProductsPage productsPage = new ProductsPage(driver);
            productsPage.viewproduct();
            ProductPage productPage = new ProductPage(driver);
            productPage.addToCart();
            CartPage cartPage = new CartPage(driver);
            cartPage.checkout();
            CheckoutPage checkoutPage = new CheckoutPage(driver);
            checkoutPage.confirmOrder();

            // View order history
            OrderHistoryPage orderHistoryPage = new OrderHistoryPage(driver);
            orderHistoryPage.goToOrderHistory();
            assertTrue(orderHistoryPage.isOrderHistoryDisplayed(), "Order history is not displayed");
            assertTrue(orderHistoryPage.isOrderInHistory(), "Order is not in history");

        } catch (Exception e) {
            logFailure(e, "testViewOrderHistoryAndDetails");
        }
    }
}
