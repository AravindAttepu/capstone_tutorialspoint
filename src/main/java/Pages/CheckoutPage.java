package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//input[@value='guest']")
    private WebElement guestCheckoutRadioButton;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public boolean selectGuestCheckout() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(guestCheckoutRadioButton)).click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean enterBillingDetails() {
        // Placeholder for entering billing details
        return true;
    }

    public boolean enterShippingDetails() {
        // Placeholder for entering shipping details
        return true;
    }

    public boolean confirmOrder() {
        // Placeholder for confirming the order
        return true;
    }
}
