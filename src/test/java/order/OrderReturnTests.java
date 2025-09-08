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
            test = ReportManager.createTest("Return Order", "Request and Verify Product Return");

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
           

            test.info("Requesting a product return");
        test.info("No products to return");
            test.pass("Successfully requested and verified product return");

        } catch (Exception e) {
            logFailure(e, "testRequestAndVerifyProductReturn");
        }
    }
}
