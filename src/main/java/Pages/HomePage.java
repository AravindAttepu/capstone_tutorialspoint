package Pages;

import Utilities.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage {

    WebDriver driver;
    ConfigReader configReader;
    WebDriverWait wait;

    @FindBy(xpath = "//a[@title='My Account']")
    private WebElement myAccount;

    @FindBy(name = "search")
    private WebElement searchBar;

    @FindBy(xpath = "//button[@type='button' and @class='btn btn-default btn-lg']")
    private WebElement searchButton;

    @FindBy(xpath = "//ul[contains(@class, 'navbar-nav')]/li/a")
    private List<WebElement> categories;

    @FindBy(className = "dropdown-toggle")
    private WebElement currencyButton;

    @FindBy(linkText = "Contact Us")
    private WebElement contactUsButton;

    @FindBy(linkText = "My Account")
    private WebElement myAccountLink;

    @FindBy(linkText = "Logout")
    private WebElement logoutLink;

    @FindBy(linkText = "Register")
    private WebElement registerLink;

    @FindBy(linkText = "Login")
    private WebElement loginLink;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        configReader = new ConfigReader("src/main/resources/config.properties");
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(configReader.getProperty("url"));
    }

    public void searchProduct(String product) {
        searchBar.clear();
        searchBar.sendKeys(product);
        searchButton.click();
    }

    public void browseViaCategory(String category, String subcategory) {
        for (WebElement categoryElement : categories) {
            if (categoryElement.getText().equalsIgnoreCase(category)) {
                categoryElement.click();
                WebElement subCategoryElement = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(subcategory)));
                subCategoryElement.click();
                return;
            }
        }
    }

    public void clickOnRegister() {
        myAccount.click();
        registerLink.click();
    }

    public void clickOnLogin() {
        myAccount.click();
        loginLink.click();
    }

    public void changeCurrency(String currency) {
        currencyButton.click();
        WebElement currencyOption = driver.findElement(By.name(currency));
        currencyOption.click();
    }

    public void redirectToContactUs() {
        contactUsButton.click();
    }

    public boolean isLoggedIn() {
        myAccount.click();
        return !loginLink.isDisplayed();
    }

    public void clickOnMyAccount() {
        myAccountLink.click();
    }

    public void logout() {
        myAccount.click();
        logoutLink.click();
    }
}
