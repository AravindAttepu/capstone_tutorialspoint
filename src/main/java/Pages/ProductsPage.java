package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//div[contains(@class, 'product-layout')]")
    private WebElement product;

    @FindBy(xpath = "//div[@id='content']/p[contains(text(), 'There is no product that matches the search criteria.')]")
    private WebElement noProductMessage;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public boolean isProductDisplayed(String productName) {
        By productLocator = By.linkText(productName);
        return wait.until(ExpectedConditions.presenceOfElementLocated(productLocator)).isDisplayed();
    }

    public void viewProduct(String productName) {
        By productLocator = By.linkText(productName);
        wait.until(ExpectedConditions.elementToBeClickable(productLocator)).click();
    }

    public boolean isNoProductMessageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(noProductMessage)).isDisplayed();
    }
}
