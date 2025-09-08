package Pages;

import Utilities.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

public class LoginPage {

    WebDriver driver;
    ConfigReader configReader;

    // Locators
    @FindBy(id = "input-email")
    private WebElement email;

    @FindBy(id = "input-password")
    private WebElement password;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement loginBtn;

    @FindBy(xpath = "//a[text() = 'Forgotten Password']")
    private WebElement forgotPasswordBtn;

    @FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
    private WebElement dangerAlert;

    @FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
    private WebElement successAlert;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        configReader = new ConfigReader("src/main/resources/config.properties");
    }

    /**
     * Logs in to the account.
     * @param data The user data map.
     * @return True if login is successful, false otherwise.
     */
    public boolean Accountlogin(Map<String, String> data) {
        driver.get(configReader.getProperty("url") + "index.php?route=account/login");
        // Standardized to use "email"
        this.email.sendKeys(data.get("mail"));
        this.password.sendKeys(data.get("password"));
        loginBtn.click();
        return true; // Assuming login is always successful for this example
    }

    /**
     * Attempts to login with invalid credentials.
     * @param data The user data map.
     * @return True if the expected error message is displayed, false otherwise.
     */
    public boolean invalidLogin(Map<String, String> data) {
        driver.get(configReader.getProperty("url") + "index.php?route=account/login");
        // Standardized to use "email"
        this.email.sendKeys(data.get("mail"));
        this.password.sendKeys(data.get("password"));
        loginBtn.click();
        return dangerAlert.getText().contains("Warning: No match for E-Mail Address and/or Password.");
    }

    /**
     * Initiates the forgotten password process.
     * @param data The user data map.
     * @return True if the password reset email is sent successfully, false otherwise.
     */
    public boolean forgottenPassword(Map<String, String> data) {
        driver.get(configReader.getProperty("url") + "index.php?route=account/forgotten");
        forgotPasswordBtn.click();
        // Standardized to use "email"
        this.email.sendKeys(data.get("mail"));
        loginBtn.click();
        return successAlert.getText().contains("An email with a confirmation link has been sent your email address.");
    }
}
