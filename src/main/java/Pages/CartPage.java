package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(linkText = "Checkout")
    private WebElement checkoutButton;

    @FindBy(xpath = "//input[contains(@name, 'quantity')]")
    private WebElement quantityInput;

    @FindBy(xpath = "//button[@data-original-title='Update']")
    private WebElement updateButton;

    @FindBy(xpath = "//button[@data-original-title='Remove']")
    private WebElement removeButton;

    @FindBy(xpath = "//div[@id='content']/p[contains(text(), 'Your shopping cart is empty!')]")
    private WebElement emptyCartMessage;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void checkout() {
        checkoutButton.click();
    }

    public boolean updateQuantity(int quantity) {
        try {
            wait.until(ExpectedConditions.visibilityOf(quantityInput));
            quantityInput.clear();
            quantityInput.sendKeys(String.valueOf(quantity));
            updateButton.click();
            return true; 
        } catch (Exception e) {
            return false;
        }
    }

    public boolean removeProduct() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(removeButton)).click();
            return true; 
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verifyCartSummary() {
        try {
            wait.until(ExpectedConditions.visibilityOf(emptyCartMessage));
            return emptyCartMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isCartNotEmpty() {
        try {
            wait.until(ExpectedConditions.visibilityOf(checkoutButton));
            return checkoutButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
