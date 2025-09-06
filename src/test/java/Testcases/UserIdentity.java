package Testcases;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import Base.BaseTest;
import Pages.LoginPage;
import Pages.RegisterPage;
import Utilities.ScannerUtil;
import Utilities.ReportManager;

public class UserIdentity extends BaseTest {

	
  @Test
  public void Registration() throws IOException {
	  try {
		  test = ReportManager.createTest("TutorialPoint", "Register and Login");
		
	Map<String, String> data = ScannerUtil.readExcelToMap("src/main/resources/Userdetails.xlsx");
	RegisterPage accountPage= new RegisterPage(driver);
	assertTrue(accountPage.reigisteruser(data),"Registration Failed");
	LoginPage loginPage= new LoginPage(driver);
	assertTrue(loginPage.Accountlogin(data),"Registration Failed");
	
 
	
	} catch (Exception e) {
		// TODO: handle exception
		logFailure(e, "user registration and login");
	}
  }
  
  @Test
  public void Login() throws IOException
  {
	  test = ReportManager.createTest("TutorialPoint", "Register and Login");
		Map<String, String> data = ScannerUtil.readExcelToMap("src/main/resources/Userdetails.xlsx");
		LoginPage loginPage= new LoginPage(driver);
		assertTrue(loginPage.Accountlogin(data),"Registration Failed");
  }
}
