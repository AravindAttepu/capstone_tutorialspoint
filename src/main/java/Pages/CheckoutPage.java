package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

public class CheckoutPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//input[@value='guest']")
    private WebElement guestCheckoutRadioButton;

    @FindBy(id = "input-payment-firstname")
    private WebElement firstNameInput;

    @FindBy(id = "input-payment-lastname")
    private WebElement lastNameInput;

    @FindBy(id = "input-payment-email")
    private WebElement emailInput;

    @FindBy(id = "input-payment-telephone")
    private WebElement telephoneInput;

    @FindBy(id = "input-payment-address-1")
    private WebElement address1Input;

    @FindBy(id = "input-payment-city")
    private WebElement cityInput;

    @FindBy(id = "input-payment-postcode")
    private WebElement postcodeInput;

    @FindBy(id = "input-payment-country")
    private WebElement countryDropdown;

    @FindBy(id = "input-payment-zone")
    private WebElement regionDropdown;

    @FindBy(id = "button-guest")
    private WebElement continueButton;

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

    public boolean enterBillingDetails(Map<String, String> data) {
        try {
            wait.until(ExpectedConditions.visibilityOf(firstNameInput)).sendKeys(data.get("FirstName"));
            lastNameInput.sendKeys(data.get("LastName"));
            emailInput.sendKeys(data.get("Email"));
            telephoneInput.sendKeys(data.get("Telephone"));
            address1Input.sendKeys(data.get("Address1"));
            cityInput.sendKeys(data.get("City"));
            postcodeInput.sendKeys(data.get("PostCode"));
            new Select(countryDropdown).selectByVisibleText(data.get("Country"));
            new Select(regionDropdown).selectByVisibleText(data.get("Region"));
            continueButton.click();
            return true;
        } catch (Exception e) {
            return false;
        }
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
