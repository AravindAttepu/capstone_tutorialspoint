package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage {

	WebDriver driver=null;
	By chageaccountdetails= null;
	By changepassword= null;
	
	
	public AccountPage(WebDriver driver) {
		this.driver= driver;
		// TODO Auto-generated constructor stub
		chageaccountdetails=	By.xpath("//a[@href=\"https://tutorialsninja.com/demo/index.php?route=account/edit\"]");
		changepassword= By.xpath("//a[@href=\"https://tutorialsninja.com/demo/index.php?route=account/password\"]");
			
	}
	
	public void clickchangeaccountdetails()
	{
		driver.findElement(chageaccountdetails).click();
		driver.findElement(By.id("input-lastname")).sendKeys("Attepu");
		driver.findElement(By.xpath("//input[@type=\"submit\"]")).click();
	}
	
	public void clickchangepassword()
	{
		driver.findElement(changepassword).click();
		driver.findElement(By.id("input-password")).sendKeys("Aravind@123");
		driver.findElement(By.id("input-confirm")).sendKeys("Aravind@123");
		
		driver.findElement(By.xpath("//input[@type=\"submit\"]")).click();
	}
	
}
