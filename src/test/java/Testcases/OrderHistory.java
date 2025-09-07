package Testcases;

import Base.BaseTest;
import Pages.*;
import Utilities.ReportManager;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertTrue;

public class OrderHistory extends BaseTest {

    @Test(description = "Verify order history functionality.")
    public void verifyOrderHistory() throws IOException {
        try {
            test = ReportManager.createTest("Verify Order History", "Verify that a user can view their order history.");

            // 1. Login
            LoginPage loginPage = new LoginPage(driver);
            Map<String, String> loginData = new HashMap<>();
            loginData.put("email", "testuser@example.com"); // Replace with a valid test user email
            loginData.put("password", "password123");       // Replace with a valid test user password
            loginPage.Accountlogin(loginData);

            // 2. Search for a product and add to cart
            HomePage homePage = new HomePage(driver);
            homePage.searchProduct("iphone");
            ProductsPage productsPage = new ProductsPage(driver);
            productsPage.viewProduct();
            ProductPage productPage = new ProductPage(driver);
            productPage.addToCart();

            // 3. Checkout
            CartPage cartPage = new CartPage(driver);
            cartPage.checkout();
            CheckoutPage checkoutPage = new CheckoutPage(driver);
            assertTrue(checkoutPage.selectGuestCheckout(), "Selecting guest checkout failed");
            assertTrue(checkoutPage.enterBillingDetails(loginData), "Entering billing details failed");
            assertTrue(checkoutPage.enterShippingDetails(), "Entering shipping details failed");
            assertTrue(checkoutPage.confirmOrder(), "Confirming order failed");

            // 4. Navigate to Order History
            OrderHistoryPage orderHistoryPage = new OrderHistoryPage(driver);
            orderHistoryPage.goToOrderHistory();

            // 5. Verify Order History
            assertTrue(orderHistoryPage.isOrderHistoryDisplayed(), "Order history is not displayed");
            assertTrue(orderHistoryPage.isOrderInHistory(), "Order is not in history");

        } catch (Exception e) {
            logFailure(e, "verifyOrderHistory");
        }
    }
}
