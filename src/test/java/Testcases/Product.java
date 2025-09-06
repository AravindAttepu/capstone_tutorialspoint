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
	
  @Test(priority = '1')
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
  
  @Test(priority = '2')
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
}
