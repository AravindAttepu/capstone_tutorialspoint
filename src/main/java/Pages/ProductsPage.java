package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductsPage {

    WebDriver driver;

    @FindBy(xpath = "//div[@class='caption']/h4/a")
    private List<WebElement> productList;

    @FindBy(xpath = "//p[contains(text(),'There is no product that matches the search criteria.')]")
    private WebElement noProductMessage;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isProductDisplayed(String productName) {
        for (WebElement product : productList) {
            if (product.getText().equalsIgnoreCase(productName)) {
                return true;
            }
        }
        return false;
    }

    public void viewProduct(String productName) {
        for (WebElement product : productList) {
            if (product.getText().equalsIgnoreCase(productName)) {
                product.click();
                return;
            }
        }
    }

    public boolean isNoProductMessageDisplayed() {
        return noProductMessage.isDisplayed();
    }
}
