package Testcases;

import Base.BaseTest;
import Pages.*;
import Utilities.ReportManager;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class Product extends BaseTest {

    @Test(priority = 1, description = "Search for a product and view its details.")
    public void searchProductAndView() throws IOException {
        try {
            test = ReportManager.createTest("Search and View Product", "Search for a product and verify its details.");
            HomePage homePage = new HomePage(driver);
            homePage.searchProduct("iphone");
            ProductsPage productsPage = new ProductsPage(driver);
            productsPage.viewProduct();
            // Add assertions to verify product details on the product page
        } catch (Exception e) {
            logFailure(e, "searchProductAndView");
        }
    }

    @Test(priority = 2, description = "Browse for products by category and filter the results.")
    public void browseByCategoryAndFilter() throws IOException {
        try {
            test = ReportManager.createTest("Browse by Category and Filter", "Browse for products by category and apply filters.");
            HomePage homePage = new HomePage(driver);
            homePage.browseViaCategory("Laptops & Notebooks", "Show AllLaptops & Notebooks");
            // Add assertions to verify the filtered results
        } catch (Exception e) {
            logFailure(e, "browseByCategoryAndFilter");
        }
    }

    @Test(priority = 3, description = "Search for a product with an invalid keyword.")
    public void searchProductInvalid() throws IOException {
        try {
            test = ReportManager.createTest("Search with Invalid Keyword", "Search for a product with an invalid keyword and verify the result.");
            HomePage homePage = new HomePage(driver);
            homePage.searchProduct("XYZ123");
            ProductsPage productsPage = new ProductsPage(driver);
            assertTrue(productsPage.isNoProductMessageDisplayed(), "'No product' message is not displayed.");
        } catch (Exception e) {
            logFailure(e, "searchProductInvalid");
        }
    }

    @Test(priority = 4, description = "Add a product to the shopping cart.")
    public void addProductToCart() throws IOException {
        try {
            test = ReportManager.createTest("Add Product to Cart", "Add a product to the shopping cart and verify.");
            HomePage homePage = new HomePage(driver);
            homePage.searchProduct("iphone");
            ProductsPage productsPage = new ProductsPage(driver);
            productsPage.viewProduct();
            ProductPage productPage = new ProductPage(driver);
            productPage.addToCart();
            // Add assertions to verify that the product is added to the cart
        } catch (Exception e) {
            logFailure(e, "addProductToCart");
        }
    }

    @Test(priority = 5, description = "Proceed to checkout.")
    public void checkout() throws IOException {
        try {
            test = ReportManager.createTest("Checkout", "Proceed to checkout and verify the checkout process.");
            HomePage homePage = new HomePage(driver);
            homePage.searchProduct("iphone");
            ProductsPage productsPage = new ProductsPage(driver);
            productsPage.viewProduct();
            ProductPage productPage = new ProductPage(driver);
            productPage.addToCart();
            CartPage cartPage = new CartPage(driver);
            cartPage.checkout();
            CheckoutPage checkoutPage = new CheckoutPage(driver);
            assertTrue(checkoutPage.selectGuestCheckout(), "Selecting guest checkout failed");
            assertTrue(checkoutPage.enterBillingDetails(null), "Entering billing details failed");
            assertTrue(checkoutPage.enterShippingDetails(), "Entering shipping details failed");
            assertTrue(checkoutPage.confirmOrder(), "Confirming order failed");
        } catch (Exception e) {
            logFailure(e, "checkout");
        }
    }

    @Test(priority = 6, description = "Update quantity and remove a product from the cart.")
    public void updateQuantityAndRemoveProductFromCart() throws IOException {
        try {
            test = ReportManager.createTest("Update and Remove Product from Cart", "Update quantity and then remove a product from the shopping cart.");
            HomePage homePage = new HomePage(driver);
            homePage.searchProduct("iphone");
            ProductsPage productsPage = new ProductsPage(driver);
            productsPage.viewProduct();
            ProductPage productPage = new ProductPage(driver);
            productPage.addToCart();
            CartPage cartPage = new CartPage(driver);
            assertTrue(cartPage.updateQuantity(2), "Update quantity failed");
            assertTrue(cartPage.removeProduct(), "Remove product failed");
            assertTrue(cartPage.verifyCartSummary(), "Cart summary verification failed");
        } catch (Exception e) {
            logFailure(e, "updateQuantityAndRemoveProductFromCart");
        }
    }
}
