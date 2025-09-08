package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//div[contains(@class, 'product-layout')]")
    private List<WebElement> products;

    @FindBy(xpath = "//div[@id='content']/p[contains(text(), 'There is no product that matches the search criteria.')]")
    private WebElement noProductMessage;

    @FindBy(id = "input-sort")
    private WebElement sortByDropdown;

    @FindBy(css = ".product-layout .caption h4 a")
    private List<WebElement> productNames;

    @FindBy(css = ".product-layout .price")
    private List<WebElement> productPrices;

    @FindBy(linkText = "Monitors")
    private WebElement monitorsCategory;

    @FindBy(id = "input-price-min")
    private WebElement minPriceInput;

    @FindBy(id = "input-price-max")
    private WebElement maxPriceInput;

    @FindBy(id = "button-filter")
    private WebElement filterButton;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public boolean isProductDisplayed(String productName) {
        By productLocator = By.linkText(productName);
        return wait.until(ExpectedConditions.presenceOfElementLocated(productLocator)).isDisplayed();
    }

    public void viewProduct(String productName) {
        By productLocator = By.linkText(productName);
        wait.until(ExpectedConditions.elementToBeClickable(productLocator)).click();
    }

    public void viewProduct() {
        wait.until(ExpectedConditions.visibilityOfAllElements(products));
        if (!products.isEmpty()) {
            products.get(0).click();
        }
    }

    public boolean areProductsDisplayed() {
        return !products.isEmpty();
    }

    public String getNoResultsMessage() {
        return wait.until(ExpectedConditions.visibilityOf(noProductMessage)).getText();
    }

    public boolean isNoProductMessageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(noProductMessage)).isDisplayed();
    }

    public void sortBy(String option) {
        Select select = new Select(sortByDropdown);
        select.selectByVisibleText(option);
    }

    public List<String> getProductNames() {
        return productNames.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<Double> getProductPrices() {
        List<Double> prices = new ArrayList<>();
        for (WebElement priceElement : productPrices) {
            String priceText = priceElement.getText();
            // Assuming price is in format like "$123.00" or "Ex Tax: $100.00\n$122.00"
            if (priceText.contains("\n")) {
                priceText = priceText.substring(priceText.indexOf("\n") + 1);
            }
            prices.add(Double.parseDouble(priceText.replaceAll("[^\\d.]", "")));

        }
        return prices;
    }

    public void selectCategory(String category) {
    	driver.findElement(By.name("category_id")).click();
        driver.findElement(By.linkText(category)).click();
        driver.findElement(By.xpath("//input[@id=\"button-search\"]")).click();
    }

    public void setPriceRange(String min, String max) {
        minPriceInput.sendKeys(min);
        maxPriceInput.sendKeys(max);
        filterButton.click();
    }
}
