package Testcases;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.annotations.Test;

import Base.BaseTest;
import Pages.HomePage;
import Pages.ProductsPage;
import Utilities.ReportManager;
import Utilities.WaitUtil;

public class Product extends BaseTest {
	
  @Test(priority = 1)
  public void searchproductandview() throws IOException {
	  try {
	 test = ReportManager.createTest("TutorialPoint", "Search and view product");
	  HomePage homePage=  new HomePage(driver);
	  homePage.searchproduct("iphone");
	  WaitUtil.waitForPageLoad(driver, 10);
	  ProductsPage productsPage= new ProductsPage(driver);
	 System.out.println( productsPage.viewproduct());

		} catch (Exception e) {
			// TODO: handle exception
			logFailure(e, "searchproducyandview");
			
		}
	 
  }
  
  @Test(priority = 2)
  public void Browseviacategoryandfilter() throws IOException
  {
	  try {
		  test = ReportManager.createTest("TutorialPoint", "Browse via category");
		  HomePage homePage= new HomePage(driver);
		 assertTrue( homePage.browseviacategory("Laptops & Notebooks", "Windows (0)"),"Category search failed");
		  
		
	} catch (Exception e) {
		// TODO: handle exception
		logFailure(e, "Browseviacategoryandfilter");
	}
  }

    @Test(priority = 3)
    public void searchProductInvalid() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Search with invalid keyword");
            HomePage homePage = new HomePage(driver);
            homePage.searchproduct("XYZ123");
            WaitUtil.waitForPageLoad(driver, 10);
            ProductsPage productsPage = new ProductsPage(driver);
            assertTrue(productsPage.isNoProductMessageDisplayed(), "No product message is not displayed");
        } catch (Exception e) {
            logFailure(e, "searchProductInvalid");
        }
    }

    @Test(priority = 4)
    public void addProductToCart() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Add product to cart");
            HomePage homePage = new HomePage(driver);
            homePage.searchproduct("iphone");
            WaitUtil.waitForPageLoad(driver, 10);
            ProductsPage productsPage = new ProductsPage(driver);
            productsPage.viewproduct();
            ProductPage productPage = new ProductPage(driver);
            assertTrue(productPage.addToCart(), "Add to cart failed");
        } catch (Exception e) {
            logFailure(e, "addProductToCart");
        }
    }

    @Test(priority = 5)
    public void checkout() throws IOException {
        try {
            test = ReportManager.createTest("TutorialPoint", "Checkout");
            HomePage homePage = new HomePage(driver);
            homePage.searchproduct("iphone");
            WaitUtil.waitForPageLoad(driver, 10);
            ProductsPage productsPage = new ProductsPage(driver);
            productsPage.viewproduct();
            ProductPage productPage = new ProductPage(driver);
            productPage.addToCart();
            CartPage cartPage = new CartPage(driver);
            assertTrue(cartPage.checkout(), "Checkout failed");
        } catch (Exception e) {
            logFailure(e, "checkout");
        }
    }
}
