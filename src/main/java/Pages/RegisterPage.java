package Pages;

import static org.testng.Assert.assertNotNull;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Utilities.WaitUtil;

public class RegisterPage {

	WebDriver driver=null;
	By firstname = By.id("input-firstname");
	By lastname= By.id("input-lastname");
	By mail = By.id("input-email");
	By telephone= By.id("input-telephone");
	By password = By.id("input-password");
	By cpassword = By.id("input-confirm");
	By subscribe = By.xpath("//label[text()=\"Yes\"]");
	By unsubscribe = By.xpath("//label[text()=\"No\"]");
	By policy = By.xpath("//input[@type= \"checkbox\"]");
	By submit= By.xpath("//input[@type=\"submit\"]");
	
	public RegisterPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver= driver;
	}
	
	public boolean reigisteruser(Map<String, String> data)
	{
		try {
			
		//driver.get("https://tutorialsninja.com/demo/index.php?route=account/register");
		WaitUtil.waitForPageLoad(driver, 20);
		driver.findElement(firstname).sendKeys(data.get("firstname"));
		driver.findElement(lastname).sendKeys(data.get("lastname"));
		driver.findElement(mail).sendKeys(data.get("mail"));
		driver.findElement(telephone).sendKeys(data.get("phone"));
		driver.findElement(password).sendKeys(data.get("password"));
		driver.findElement(cpassword).sendKeys(data.get("password"));
		driver.findElement(subscribe).click();
		driver.findElement(policy).click();
		driver.findElement(submit).click();
		return true;

		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
}
