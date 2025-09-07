package Testcases;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.Test;

import Base.BaseTest;
import Pages.CartPage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.ProductPage;
import Pages.ProductsPage;
import Pages.RegisterPage;
import Utilities.ReportManager;
import Utilities.ScannerUtil;
import Utilities.WaitUtil;

public class EndToEnd extends BaseTest {

    @Test
    public void endToEndTest() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "End-to-End Test");

            // Registration
            Map<String, String> data = ScannerUtil.readExcelToMap("src/main/resources/Userdetails.xlsx");
            RegisterPage accountPage = new RegisterPage(driver);
            assertTrue(accountPage.reigisteruser(data), "Registration Failed");

            // Login
            LoginPage loginPage = new LoginPage(driver);
            assertTrue(loginPage.Accountlogin(data), "Login Failed");

            // Search and view product
            HomePage homePage = new HomePage(driver);
            homePage.searchProduct("iphone");
            WaitUtil.waitForPageLoad(driver, 10);

            // View product
            ProductsPage productsPage = new ProductsPage(driver);
            productsPage.viewProduct("iPhone");

            // Add to cart
            ProductPage productPage = new ProductPage(driver);
            productPage.addToCart();

            // Checkout
            CartPage cartPage = new CartPage(driver);
            cartPage.checkout();

        } catch (Exception e) {
            logFailure(e, "endToEndTest");
        }
    }
}
