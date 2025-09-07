package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



import java.sql.DatabaseMetaData;
import java.time.Duration;
import java.util.Map;

public class CheckoutPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//input[@value='guest']")
    private WebElement guestCheckoutRadioButton;

   @FindBy(linkText = "Use Coupon Code ")
   private WebElement coupondrop;

    @FindBy(linkText = "Estimate Shipping & Taxes ")
    private WebElement shippingdrop;

   @FindBy(linkText = "Use Gift Certificate ")
   private WebElement giftdrop;
   
    @FindBy(id = "input-payment-telephone")
    private WebElement telephoneInput;

    @FindBy(id = "input-payment-address-1")
    private WebElement address1Input;

    @FindBy(id = "input-payment-city")
    private WebElement cityInput;

    @FindBy(id = "input-postcode")
    private WebElement postcodeInput;

    @FindBy(id = "input-country")
    private WebElement countryDropdown;

    @FindBy(id = "input-zone")
    private WebElement regionDropdown;
    
    @FindBy(id="button-quote")
    private WebElement quoteButton;
    
   
    
  
    @FindBy(linkText =  "Checkout")
    private WebElement checkoutButton;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public boolean selectGuestCheckout() {
        try {
           
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean enterBillingDetails(Map<String, String> data) {
        try {
          //  wait.until(ExpectedConditions.visibilityOf(firstNameInput)).sendKeys(data.get("FirstName"));
           
            applycoupon(data.get("coupon"));
            postcodeInput.sendKeys(data.get("pincode"));
            new Select(countryDropdown).selectByVisibleText(data.get("country"));
            new Select(regionDropdown).selectByVisibleText(data.get("region"));
            driver.findElement(By.xpath("//input[@type=\\\"radio\\\"]"));
            driver.findElement(By.id("button-shipping")).click();
            checkoutButton.click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

  
    public boolean applycoupon(String coupon)
    {
    	coupondrop.click();
    	driver.findElement(By.id("input-coupon")).sendKeys(coupon);
		return true;
    }
}
