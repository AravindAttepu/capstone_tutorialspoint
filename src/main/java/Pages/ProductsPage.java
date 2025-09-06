package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductsPage {
	WebDriver driver= null;
	By productsgroup = By.xpath("//div[@class=\"caption\"]/h4/a");

	public ProductsPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		
	}
	
	public String viewproduct()
	{
		List<WebElement> productlist = driver.findElements(productsgroup);
		String prod =productlist.get(0).getText();
		productlist.get(0).click();
		return prod;
				
	}
}
