package user;


import static org.junit.Assert.assertTrue;

import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.testng.annotations.*;

import Base.BaseTest;
import Pages.HomePage;
import Pages.ProductPage;
import Pages.ProductsPage;

public class WishlistCompareTests extends BaseTest {

    @BeforeClass
    public void setup() {}

    @Test(priority = 1)
    public void testAddToWishlist() {
    	
    	HomePage homePage= new HomePage(driver);
    	homePage.searchProduct("iphone");
    	ProductsPage productsPage= new ProductsPage(driver);
    assertTrue(	productsPage.areProductsDisplayed());
    productsPage.viewProduct();
    ProductPage product= new ProductPage(driver);
    product.addtocompare();
    
    HomePage homePage2= new HomePage(driver);
	homePage.searchProduct("mac");
	ProductsPage productsPage2= new ProductsPage(driver);
assertTrue(	productsPage.areProductsDisplayed());
productsPage.viewProduct();
ProductPage product2= new ProductPage(driver);
product.addtocompare();
product.showcomparision();
    			
    }


    @AfterClass
    public void tearDown() {}
}
