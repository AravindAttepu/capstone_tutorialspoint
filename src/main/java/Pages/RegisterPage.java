package Pages;

import Utilities.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

public class RegisterPage {

    WebDriver driver;
    ConfigReader configReader;

    // Locators
    @FindBy(id = "input-firstname")
    private WebElement firstname;

    @FindBy(id = "input-lastname")
    private WebElement lastname;

    @FindBy(id = "input-email")
    private WebElement email;

    @FindBy(id = "input-telephone")
    private WebElement telephone;

    @FindBy(id = "input-password")
    private WebElement password;

    @FindBy(id = "input-confirm")
    private WebElement confirmPassword;

    @FindBy(xpath = "//label[text()='Yes']")
    private WebElement subscribeYes;

    @FindBy(xpath = "//label[text()='No']")
    private WebElement subscribeNo;

    // Using a more specific locator for the privacy policy checkbox
    @FindBy(name = "agree")
    private WebElement policy;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement submit;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        configReader = new ConfigReader("src/main/resources/config.properties");
    }

    /**
     * Registers a new user.
     * @param data A map containing user registration data (firstname, lastname, email, phone, password).
     * @return True if registration is successful, false otherwise.
     */
    public boolean reigisteruser(Map<String, String> data) {
        try {
            driver.get("https://tutorialsninja.com/demo/index.php?route=account/register");
            firstname.sendKeys(data.get("firstname"));
            lastname.sendKeys(data.get("lastname"));
            String mail = "test" + System.currentTimeMillis() + "@example.com";
            email.sendKeys(mail);
           
            telephone.sendKeys(data.get("phone"));
            password.sendKeys(data.get("password"));
            confirmPassword.sendKeys(data.get("password"));
            subscribeYes.click();
            
            // Defensive check before clicking
            if (!policy.isSelected()) {
                policy.click();
            }
            
            submit.click();

            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
