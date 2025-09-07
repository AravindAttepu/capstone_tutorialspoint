package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "button-cart")
    private WebElement addToCartButton;

    @FindBy(css = "div.alert-success")
    private WebElement successAlert;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void addToCart() {
        addToCartButton.click();
    }

    public String getAddToCartSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOf(successAlert)).getText();
    }
}
