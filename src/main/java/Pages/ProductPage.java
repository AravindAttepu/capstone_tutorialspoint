package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {

    WebDriver driver;

    @FindBy(id = "button-cart")
    private WebElement addToCartButton;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean addToCart() {
        addToCartButton.click();
        // Add verification logic here
        return true;
    }
}
