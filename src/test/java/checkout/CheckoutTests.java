package checkout;


import Base.BaseTest;
import Pages.*;
import Utilities.ReportManager;
import Utilities.ScannerUtil;
import Utilities.WaitUtil;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

import static org.testng.Assert.assertTrue;

public class CheckoutTests extends BaseTest {

    @Test
    public void testProceedToCheckoutGuestOption() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Proceed to checkout as guest");
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
            test.info("Proceeding to checkout");
            CartPage cartPage = new CartPage(driver);
            cartPage.checkout();
            CheckoutPage checkoutPage = new CheckoutPage(driver);
            test.info("Selecting guest checkout");
            assertTrue(checkoutPage.selectGuestCheckout(), "Guest checkout selection failed");
            test.pass("Successfully proceeded to checkout as guest");
        } catch (Exception e) {
            logFailure(e, "testProceedToCheckoutGuestOption");
        }
    }

    @Test
    public void testGuestCheckoutBillingShipping() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Guest checkout with billing and shipping");
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
            test.info("Proceeding to checkout");
            CartPage cartPage = new CartPage(driver);
            cartPage.checkout();
            CheckoutPage checkoutPage = new CheckoutPage(driver);
            test.info("Selecting guest checkout");
            checkoutPage.selectGuestCheckout();
            Map<String, String> data = ScannerUtil.readExcelToMap("src/main/resources/Userdetails.xlsx");
            test.info("Entering billing details");
            assertTrue(checkoutPage.enterBillingDetails(data), "Entering billing details failed");
            test.pass("Guest checkout with billing and shipping successful");
        } catch (Exception e) {
            logFailure(e, "testGuestCheckoutBillingShipping");
        }
    }

    @Test
    public void testRegisteredUserCheckout() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Registered user checkout");
            Map<String, String> data = ScannerUtil.readExcelToMap("src/main/resources/Userdetails.xlsx");

            test.info("Registering a new user");
            RegisterPage accountPage = new RegisterPage(driver);
            accountPage.reigisteruser(data);
            test.info("Logging in with the new user");
            LoginPage loginPage = new LoginPage(driver);
            loginPage.Accountlogin(data);

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
            test.info("Proceeding to checkout");
            CartPage cartPage = new CartPage(driver);
            cartPage.checkout();
            CheckoutPage checkoutPage = new CheckoutPage(driver);
            test.info("Confirming the order");
            assertTrue(checkoutPage.confirmOrder(), "Confirm order failed");
            test.pass("Registered user checkout successful");
        } catch (Exception e) {
            logFailure(e, "testRegisteredUserCheckout");
        }
    }

    @Test
    public void testAddNewDeliveryAddress() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Add new delivery address");
            Map<String, String> data = ScannerUtil.readExcelToMap("src/main/resources/Userdetails.xlsx");

            test.info("Registering a new user");
            RegisterPage accountPage = new RegisterPage(driver);
            accountPage.reigisteruser(data);
            test.info("Logging in with the new user");
            LoginPage loginPage = new LoginPage(driver);
            loginPage.Accountlogin(data);

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
            test.info("Proceeding to checkout");
            CartPage cartPage = new CartPage(driver);
            cartPage.checkout();
            CheckoutPage checkoutPage = new CheckoutPage(driver);
            test.info("Adding a new delivery address");
            assertTrue(checkoutPage.addNewAddress(data), "Add new address failed");
            test.pass("Successfully added a new delivery address");
        } catch (Exception e) {
            logFailure(e, "testAddNewDeliveryAddress");
        }
    }

    @Test
    public void testMandatoryAddressFields() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Mandatory address fields");
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
            test.info("Proceeding to checkout");
            CartPage cartPage = new CartPage(driver);
            cartPage.checkout();
            CheckoutPage checkoutPage = new CheckoutPage(driver);
            test.info("Selecting guest checkout");
            checkoutPage.selectGuestCheckout();
            test.info("Verifying mandatory fields");
            assertTrue(checkoutPage.verifyMandatoryFields(), "Mandatory fields verification failed");
            test.pass("Mandatory address fields verification successful");
        } catch (Exception e) {
            logFailure(e, "testMandatoryAddressFields");
        }
    }
}
