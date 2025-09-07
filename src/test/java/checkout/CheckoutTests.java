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

    @Test(priority = 1)
    public void testProceedToCheckoutGuestOption() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Proceed to checkout as guest");

            test.info("Searching for product: iPhone");
            HomePage homePage = new HomePage(driver);
            homePage.searchProduct("iphone");
            WaitUtil.waitForPageLoad(driver, 10);
            test.pass("Product search completed");

            test.info("Selecting product from results");
            ProductsPage productsPage = new ProductsPage(driver);
            productsPage.viewProduct();
            test.pass("Product selected");

            test.info("Adding product to cart");
            ProductPage productPage = new ProductPage(driver);
            productPage.addToCart();
            test.pass("Product added to cart");

            test.info("Proceeding to checkout");
            CartPage cartPage = new CartPage(driver);
            cartPage.checkout();

            test.info("Selecting guest checkout option");
            CheckoutPage checkoutPage = new CheckoutPage(driver);
            assertTrue(checkoutPage.selectGuestCheckout(), "Guest checkout selection failed");
            test.pass("Guest checkout option selected successfully");

        } catch (Exception e) {
            test.fail("Test failed: " + e.getMessage());
            logFailure(e, "testProceedToCheckoutGuestOption");
        }
    }

    @Test(priority = 2)
    public void testGuestCheckoutBillingShipping() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Guest checkout with billing and shipping");

            test.info("Searching for product: iPhone");
            HomePage homePage = new HomePage(driver);
            homePage.searchProduct("iphone");
            WaitUtil.waitForPageLoad(driver, 10);
            test.pass("Product search completed");

            test.info("Selecting product from results");
            ProductsPage productsPage = new ProductsPage(driver);
            productsPage.viewProduct();
            test.pass("Product selected");

            test.info("Adding product to cart");
            ProductPage productPage = new ProductPage(driver);
            productPage.addToCart();
            test.pass("Product added to cart");

            test.info("Proceeding to checkout");
            CartPage cartPage = new CartPage(driver);
            cartPage.checkout();

            test.info("Selecting guest checkout option");
            CheckoutPage checkoutPage = new CheckoutPage(driver);
            checkoutPage.selectGuestCheckout();
            test.pass("Guest checkout option selected");

            test.info("Reading user data from Excel");
            Map<String, String> data = ScannerUtil.readExcelToMap("src/main/resources/Userdetails.xlsx");

            test.info("Entering billing details");
            assertTrue(checkoutPage.enterBillingDetails(data), "Entering billing details failed");
            test.pass("Billing and shipping details entered successfully");

        } catch (Exception e) {
            test.fail("Test failed: " + e.getMessage());
            logFailure(e, "testGuestCheckoutBillingShipping");
        }
    }

    @Test(priority = 3)
    public void testRegisteredUserCheckout() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Registered user checkout");

            test.info("Reading user data from Excel");
            Map<String, String> data = ScannerUtil.readExcelToMap("src/main/resources/UserDetails.xlsx");

            test.info("Registering new user");
            RegisterPage accountPage = new RegisterPage(driver);
            accountPage.reigisteruser(data);
            test.pass("User registered successfully");

            test.info("Logging in with registered user");
            LoginPage loginPage = new LoginPage(driver);
            loginPage.Accountlogin(data);
            test.pass("User logged in successfully");

            test.info("Searching for product: iPhone");
            HomePage homePage = new HomePage(driver);
            homePage.searchProduct("iphone");
            WaitUtil.waitForPageLoad(driver, 10);
            test.pass("Product search completed");

            test.info("Selecting product from results");
            ProductsPage productsPage = new ProductsPage(driver);
            productsPage.viewProduct();
            test.pass("Product selected");

            test.info("Adding product to cart");
            ProductPage productPage = new ProductPage(driver);
            productPage.addToCart();
            test.pass("Product added to cart");

            test.info("Proceeding to checkout");
            CartPage cartPage = new CartPage(driver);
            cartPage.checkout();
            test.pass("Checkout page opened successfully");

        } catch (Exception e) {
            test.fail("Test failed: " + e.getMessage());
            logFailure(e, "testRegisteredUserCheckout");
        }
    }

    @Test(priority = 4)
    public void testAddNewDeliveryAddress() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Add new delivery address");

            test.info("Reading user data from Excel");
            Map<String, String> data = ScannerUtil.readExcelToMap("src/main/resources/UserDetails.xlsx");

            test.info("Registering new user");
            RegisterPage accountPage = new RegisterPage(driver);
            accountPage.reigisteruser(data);
            test.pass("User registered successfully");

            test.info("Logging in with registered user");
            LoginPage loginPage = new LoginPage(driver);
            loginPage.Accountlogin(data);
            test.pass("User logged in successfully");

            test.info("Searching for product: iPhone");
            HomePage homePage = new HomePage(driver);
            homePage.searchProduct("iphone");
            WaitUtil.waitForPageLoad(driver, 10);
            test.pass("Product search completed");

            test.info("Selecting product from results");
            ProductsPage productsPage = new ProductsPage(driver);
            productsPage.viewProduct();
            test.pass("Product selected");

            test.info("Adding product to cart");
            ProductPage productPage = new ProductPage(driver);
            productPage.addToCart();
            test.pass("Product added to cart");

            test.info("Proceeding to checkout");
            CartPage cartPage = new CartPage(driver);
            cartPage.checkout();
            test.pass("Checkout page opened successfully");

            // CheckoutPage checkoutPage = new CheckoutPage(driver);
            // You can add test.info() + test.pass() once you implement new delivery address step

        } catch (Exception e) {
            test.fail("Test failed: " + e.getMessage());
            logFailure(e, "testAddNewDeliveryAddress");
        }
    }

    @Test(priority = 5)
    public void testMandatoryAddressFields() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Mandatory address fields");

            test.info("Searching for product: iPhone");
            HomePage homePage = new HomePage(driver);
            homePage.searchProduct("iphone");
            WaitUtil.waitForPageLoad(driver, 10);
            test.pass("Product search completed");

            test.info("Selecting product from results");
            ProductsPage productsPage = new ProductsPage(driver);
            productsPage.viewProduct();
            test.pass("Product selected");

            test.info("Adding product to cart");
            ProductPage productPage = new ProductPage(driver);
            productPage.addToCart();
            test.pass("Product added to cart");

            test.info("Proceeding to checkout");
            CartPage cartPage = new CartPage(driver);
            cartPage.checkout();
            test.pass("Checkout page opened successfully");

            test.info("Selecting guest checkout option");
            CheckoutPage checkoutPage = new CheckoutPage(driver);
            checkoutPage.selectGuestCheckout();
            test.pass("Guest checkout option selected");

        } catch (Exception e) {
            test.fail("Test failed: " + e.getMessage());
            logFailure(e, "testMandatoryAddressFields");
        }
    }
}
