package Pages;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.service.DriverCommandExecutor;

import Utilities.WaitUtil;

public class LoginPage {
WebDriver driver= null;

	By email= By.id("input-email");
	By password= By.id("input-password");
	By loginbtn  = By.xpath("//input[@type=\"submit\"]");
	By forgotpasswordbtn= By.xpath("//a[text() = \"Forgotten Password\"]");
	
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
	public boolean forgotpassword()
	{
		driver.findElement(forgotpasswordbtn).click();
		driver.findElement(By.id("input-email")).sendKeys("fhfg@gdfg.com");
		driver.findElement(By.xpath("//input[@type=\"submit\"]")).click();
		String msg=	driver.findElement(By.xpath("//div[@class=\"alert alert-success alert-dismissible\"]")).getText();
		if(msg.contains("An email with a confirmation link has been sent your email address."))
		{
			return true;
		}
		return false;
		//fhfg@gdfg.com
	}
}
