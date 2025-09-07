package Pages;

import java.nio.Buffer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Utilities.WaitUtil;

public class ContactUsPage {

	WebDriver driver= null;
	By name=By.xpath("//input[@id=\"input-name\"]");
	By mail= By.xpath("//input[@id=\"input-email\"]");
	By summary= By.xpath("//input[@id=\"input-enquiry\"]");
	By submitbtn= By.xpath("//input[@type=\"submit\"]");
	
	
	public ContactUsPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver= driver;
	}
	
	public boolean fillform()
	{
		driver.findElement(name).sendKeys("Aravind");
		driver.findElement(mail).sendKeys("ara@g.com");
		driver.findElement(summary).sendKeys("qwertyuioppdhffyheu");
		driver.findElement(submitbtn).click();
		WaitUtil.waitForPageLoad(driver, 10);
		driver.findElement(By.xpath("//input[@type=\\\"Continue\\\"]"));
		return true;
	}
}
