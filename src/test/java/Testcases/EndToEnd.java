package Testcases;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.Test;

import Base.BaseTest;
import Pages.CartPage;
import Pages.CheckoutPage;
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
            test = ReportManager.createTest("End to End", "Perform an end-to-end test");

            test.info("Registering a new user");
            Map<String, String> data = ScannerUtil.readExcelToMap("src/main/resources/UserDetails.xlsx");
            RegisterPage accountPage = new RegisterPage(driver);
            assertTrue(accountPage.reigisteruser(data), "Registration Failed");

            test.info("Logging in with the new user");
            

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
            
            test.pass("Successfully completed end-to-end test");

        } catch (Exception e) {
            logFailure(e, "endToEndTest");
        }
    }
}
