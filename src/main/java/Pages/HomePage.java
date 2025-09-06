package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

	WebDriver driver= null;
	By searchbar= By.xpath("//input[@name=\"search\"]");
	By searchbtn= By.xpath("//button[@type=\"button\"]/i");
	By categoriespath= By.xpath("//ul[@class=\"nav navbar-nav\"]/li");
	
	
	public HomePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		
	}
	public boolean searchproduct(String product)
	{
		driver.get("https://tutorialsninja.com/demo/index.php");
		driver.findElement(searchbar).clear();
		driver.findElement(searchbar).sendKeys(product);
		driver.findElement(searchbtn).click();
		return true;
		
	}
	public boolean browseviacategory(String category, String Subcategory)
	{
		String currenturl= driver.getCurrentUrl();
		List<WebElement> categories= driver.findElements(categoriespath);
		for (WebElement webElement : categories) {
			System.out.println(webElement.getText());
			if(webElement.getText().equals(category)) {
				webElement.click();
				if(currenturl.equals(driver.getCurrentUrl()))
				{
				List<WebElement> subcatElements = webElement.findElements(By.xpath("//div/div/ul/li/a"));
				for (WebElement webElement2 : subcatElements) {
					System.out.println(webElement2.getText());
					if(webElement2.getText().equals(Subcategory))
					{
						webElement2.click();
						return true;
					}
				}
				return false;
				}
				else {
					
					return true;
					
				}
			}
		}
		return false;
		
	}
	
	
}
