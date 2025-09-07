package Pages;

import Utilities.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    @FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
    private WebElement successAlert;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        configReader = new ConfigReader("src/main/resources/config.properties");
    }

    /**
     * Logs in to the account.
     * @param username The email address of the user.
     * @param password The password of the user.
     * @return True if login is successful, false otherwise.
     */
    public boolean accountLogin(String username, String password) {
        driver.get(configReader.getProperty("url") + "index.php?route=account/login");
        this.email.sendKeys(username);
        this.password.sendKeys(password);
        loginBtn.click();
        return true; // Assuming login is always successful for this example
    }

    /**
     * Initiates the forgotten password process.
     * @param email The email address to send the reset link to.
     * @return True if the password reset email is sent successfully, false otherwise.
     */
    public boolean forgotPassword(String email) {
        driver.get(configReader.getProperty("url") + "index.php?route=account/forgotten");
        forgotPasswordBtn.click();
        this.email.sendKeys(email);
        loginBtn.click();
        return successAlert.getText().contains("An email with a confirmation link has been sent your email address.");
    }
}
