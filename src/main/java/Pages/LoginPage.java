package Pages;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Utilities.WaitUtil;

public class LoginPage {
WebDriver driver= null;

	By email= By.id("input-email");
	By password= By.id("input-password");
	By loginbtn  = By.xpath("//input[@type=\"submit\"]");
	
	public LoginPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver= driver;
	}
	
	public boolean Accountlogin(Map<String, String> data)
	{
		
		
		driver.get("https://tutorialsninja.com/demo/index.php?route=account/login");
		WaitUtil.waitForPageLoad(driver, 10);

		 driver.findElement(email).sendKeys(data.get("mail"));
		 driver.findElement(password).sendKeys(data.get("password"));
		 driver.findElement(loginbtn).click();
		 return true;
		
	}
}
