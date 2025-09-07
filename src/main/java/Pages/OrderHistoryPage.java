package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderHistoryPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(linkText = "Order History")
    private WebElement orderHistoryLink;

    @FindBy(xpath = "//h2[text()='Order History']")
    private WebElement orderHistoryHeader;

    @FindBy(xpath = "//table[@class='table table-bordered table-hover']//tbody/tr")
    private WebElement firstOrderInHistory;

    public OrderHistoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void goToOrderHistory() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(orderHistoryLink)).click();
        } catch (Exception e) {
            // Handle or log the exception as needed
            e.printStackTrace();
        }
    }

    public boolean isOrderHistoryDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(orderHistoryHeader));
            return orderHistoryHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isOrderInHistory() {
        try {
            wait.until(ExpectedConditions.visibilityOf(firstOrderInHistory));
            return firstOrderInHistory.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

	public void requestReturn() {
		// TODO Auto-generated method stub
		
		
	}
}
