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
            assertTrue(checkoutPage.selectGuestCheckout(), "Guest checkout selection failed");
        } catch (Exception e) {
            logFailure(e, "testProceedToCheckoutGuestOption");
        }
    }

    @Test
    public void testGuestCheckoutBillingShipping() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Guest checkout with billing and shipping");
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
            checkoutPage.selectGuestCheckout();
            Map<String, String> data = ScannerUtil.readExcelToMap("src/main/resources/Userdetails.xlsx");
            assertTrue(checkoutPage.enterBillingDetails(data), "Entering billing details failed");
        } catch (Exception e) {
            logFailure(e, "testGuestCheckoutBillingShipping");
        }
    }

    @Test
    public void testRegisteredUserCheckout() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Registered user checkout");
            Map<String, String> data = ScannerUtil.readExcelToMap("src/main/resources/Userdetails.xlsx");

            // Registration and Login
            RegisterPage accountPage = new RegisterPage(driver);
            accountPage.reigisteruser(data);
            LoginPage loginPage = new LoginPage(driver);
            loginPage.Accountlogin(data);

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
            assertTrue(checkoutPage.confirmOrder(), "Confirm order failed");
        } catch (Exception e) {
            logFailure(e, "testRegisteredUserCheckout");
        }
    }

    @Test
    public void testAddNewDeliveryAddress() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Add new delivery address");
            Map<String, String> data = ScannerUtil.readExcelToMap("src/main/resources/Userdetails.xlsx");

            // Registration and Login
            RegisterPage accountPage = new RegisterPage(driver);
            accountPage.reigisteruser(data);
            LoginPage loginPage = new LoginPage(driver);
            loginPage.Accountlogin(data);

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
            assertTrue(checkoutPage.addNewAddress(data), "Add new address failed");
        } catch (Exception e) {
            logFailure(e, "testAddNewDeliveryAddress");
        }
    }

    @Test
    public void testMandatoryAddressFields() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Mandatory address fields");
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
            checkoutPage.selectGuestCheckout();
            assertTrue(checkoutPage.verifyMandatoryFields(), "Mandatory fields verification failed");
        } catch (Exception e) {
            logFailure(e, "testMandatoryAddressFields");
        }
    }
}
