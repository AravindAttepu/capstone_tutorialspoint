package Testcases;

import Base.BaseTest;
import Pages.*;
import Utilities.ReportManager;
import Utilities.ScannerUtil;

import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertTrue;

public class OrderHistory extends BaseTest {

    @Test(description = "Verify order history functionality.")
    public void verifyOrderHistory() throws IOException {
        try {
            test = ReportManager.createTest("Verify Order History", 
                                            "Verify that a user can view their order history.");

            // 1. Login
            test.info("Logging in with valid user credentials");
            LoginPage loginPage = new LoginPage(driver);
            Map<String, String> loginData = new HashMap<>();
            loginData = ScannerUtil.readExcelToMap("src/main/resources/UserDetails.xlsx");
            loginPage.Accountlogin(loginData);
            test.pass("User logged in successfully");

            // 2. Search for a product and add to cart
            test.info("Searching for product: iPhone");
            HomePage homePage = new HomePage(driver);
            homePage.searchProduct("iphone");
            test.pass("Search executed successfully");

            test.info("Viewing product details");
            ProductsPage productsPage = new ProductsPage(driver);
            productsPage.viewProduct();
            test.pass("Product details opened");

            test.info("Adding product to cart");
            ProductPage productPage = new ProductPage(driver);
            productPage.addToCart();
            test.pass("Product added to cart successfully");

            // 3. Checkout
            test.info("Proceeding to checkout");
            CartPage cartPage = new CartPage(driver);
            cartPage.checkout();
            CheckoutPage checkoutPage = new CheckoutPage(driver);

            assertTrue(checkoutPage.selectGuestCheckout(), "Selecting guest checkout failed");
            test.pass("Guest checkout option selected successfully");

            assertTrue(checkoutPage.enterBillingDetails(loginData), "Entering billing details failed");
            test.pass("Billing details entered successfully");

            // 4. Navigate to Order History
            test.info("Navigating to Order History page");
            OrderHistoryPage orderHistoryPage = new OrderHistoryPage(driver);
            orderHistoryPage.goToOrderHistory();
            test.pass("Navigated to Order History page successfully");

            // 5. Verify Order History
            test.info("Verifying that order history is displayed");
            assertTrue(orderHistoryPage.isOrderHistoryDisplayed(), "Order history is not displayed");
            test.pass("Order history is displayed");

            test.info("Verifying that the placed order is present in history");
            assertTrue(orderHistoryPage.isOrderInHistory(), "Order is not in history");
            test.pass("Order is listed in order history successfully");

        } catch (Exception e) {
            test.fail("Test failed due to exception: " + e.getMessage());
            logFailure(e, "verifyOrderHistory");
        }
    }
}
