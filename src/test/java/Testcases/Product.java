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

public class Product extends BaseTest {

	@Test(priority = 1, description = "Search for a product and view its details.")
	public void searchProductAndView() throws IOException {
	    try {
	        test = ReportManager.createTest("Search and View Product", "Search for a product and verify its details.");

	        HomePage homePage = new HomePage(driver);
	        test.info("Navigated to Home Page");

	        homePage.searchProduct("iphone");
	        test.info("Searched for product: iphone");

	        ProductsPage productsPage = new ProductsPage(driver);
	        productsPage.viewProduct();
	        test.info("Viewed product details");

	        test.pass("Product search and view test passed successfully.");
	    } catch (Exception e) {
	        logFailure(e, "searchProductAndView");
	    }
	}


	@Test(priority = 2, description = "Browse for products by category and filter the results.")
	public void browseByCategoryAndFilter() throws IOException {
	    try {
	        test = ReportManager.createTest("Browse by Category and Filter", "Browse for products by category and apply filters.");

	        HomePage homePage = new HomePage(driver);
	        test.info("Navigated to Home Page");

	        homePage.browseViaCategory("Laptops & Notebooks", "Show AllLaptops & Notebooks");
	        test.info("Browsed category: Laptops & Notebooks -> Show All");

	        test.pass("Category browsing and filtering test passed successfully.");
	    } catch (Exception e) {
	        logFailure(e, "browseByCategoryAndFilter");
	    }
	}

	@Test(priority = 3, description = "Search for a product with an invalid keyword.")
	public void searchProductInvalid() throws IOException {
	    try {
	        test = ReportManager.createTest("Search with Invalid Keyword", "Search for a product with an invalid keyword and verify the result.");

	        HomePage homePage = new HomePage(driver);
	        test.info("Navigated to Home Page");

	        homePage.searchProduct("XYZ123");
	        test.info("Searched for invalid product keyword: XYZ123");

	        ProductsPage productsPage = new ProductsPage(driver);
	        assertTrue(productsPage.isNoProductMessageDisplayed(), "'No product' message is not displayed.");
	        test.pass("Invalid search verified successfully with 'No product' message.");
	    } catch (Exception e) {
	        logFailure(e, "searchProductInvalid");
	    }
	}


	@Test(priority = 4, description = "Add a product to the shopping cart.")
	public void addProductToCart() throws IOException {
	    try {
	        test = ReportManager.createTest("Add Product to Cart", "Add a product to the shopping cart and verify.");

	        HomePage homePage = new HomePage(driver);
	        test.info("Navigated to Home Page");

	        homePage.searchProduct("iphone");
	        test.info("Searched for product: iphone");

	        ProductsPage productsPage = new ProductsPage(driver);
	        productsPage.viewProduct();
	        test.info("Viewed product details");

	        ProductPage productPage = new ProductPage(driver);
	        productPage.addToCart();
	        test.info("Added product to cart");

	        test.pass("Product added to cart successfully.");
	    } catch (Exception e) {
	        logFailure(e, "addProductToCart");
	    }
	}


	@Test(priority = 5, description = "Proceed to checkout.")
	public void checkout() throws IOException {
	    try {
	        test = ReportManager.createTest("Checkout", "Proceed to checkout and verify the checkout process.");

	        HomePage homePage = new HomePage(driver);
	        Map<String, String> Data = ScannerUtil.readExcelToMap("src/main/resources/UserDetails.xlsx");
	        test.info("Read user details from Excel");

	        homePage.searchProduct("iphone");
	        test.info("Searched for product: iphone");

	        ProductsPage productsPage = new ProductsPage(driver);
	        productsPage.viewProduct();
	        test.info("Viewed product details");

	        ProductPage productPage = new ProductPage(driver);
	        productPage.addToCart();
	        test.info("Added product to cart");

	        CartPage cartPage = new CartPage(driver);
	        cartPage.checkout();
	        test.info("Proceeded to checkout");

	        CheckoutPage checkoutPage = new CheckoutPage(driver);
	        assertTrue(checkoutPage.selectGuestCheckout(), "Selecting guest checkout failed");
	        test.info("Selected Guest Checkout");

	        assertTrue(checkoutPage.enterBillingDetails(Data), "Entering billing details failed");
	        test.info("Entered billing details");

	        test.pass("Checkout process completed successfully.");
	    } catch (Exception e) {
	        logFailure(e, "checkout");
	    }
	}

	@Test(priority = 6, description = "Update quantity and remove a product from the cart.")
	public void updateQuantityAndRemoveProductFromCart() throws IOException {
	    try {
	        test = ReportManager.createTest("Update and Remove Product from Cart", "Update quantity and then remove a product from the shopping cart.");

	        HomePage homePage = new HomePage(driver);
	        test.info("Navigated to Home Page");

	        homePage.searchProduct("iphone");
	        test.info("Searched for product: iphone");

	        ProductsPage productsPage = new ProductsPage(driver);
	        productsPage.viewProduct();
	        test.info("Viewed product details");

	        ProductPage productPage = new ProductPage(driver);
	        productPage.addToCart();
	        test.info("Added product to cart");

	        CartPage cartPage = new CartPage(driver);
	        assertTrue(cartPage.updateQuantity(2), "Update quantity failed");
	        test.info("Updated product quantity to 2");

	        assertTrue(cartPage.removeProduct(), "Remove product failed");
	        test.info("Removed product from cart");

	        assertTrue(cartPage.verifyCartSummary(), "Cart summary verification failed");
	        test.info("Verified empty cart summary");

	        test.pass("Update and remove from cart test passed successfully.");
	    } catch (Exception e) {
	        logFailure(e, "updateQuantityAndRemoveProductFromCart");
	    }
	}

}
