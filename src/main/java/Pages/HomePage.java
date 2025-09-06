package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

	WebDriver driver= null;
	By searchbar= By.xpath("//input[@name=\"search\"]");
	By searchbtn= By.xpath("//button[@type=\"button\"]/i");
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
	
	
}
