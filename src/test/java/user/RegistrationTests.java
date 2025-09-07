package user;


import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.*;

import Base.BaseTest;
import Pages.RegisterPage;
import Utilities.ReportManager;
import Utilities.ScannerUtil;

public class RegistrationTests extends BaseTest {


    @Test
    public void testValidUserRegistration() throws IOException {
    	try {
			
    		 test = ReportManager.createTest("Register User", "Fill the details of user to register");
 		test.info("registration initiated");
    	Map<String, String> data = ScannerUtil.readExcelToMap("src/main/resources/Userdetails.xlsx");
    	test.info("data read form excel file");
    	RegisterPage page = new RegisterPage(driver);
    	test.info("page called");
    	
    	assertTrue(page.reigisteruser(data));
    	test.pass("User Registered");
		} catch (Exception e) {
			// TODO: handle exception
			logFailure(e, "Registartion unsuccessful");
		}
    }

    @Test
    public void testDuplicateEmailRegistration() throws IOException {
    	try {
			
       	 test = ReportManager.createTest("Register with duplicate User", "Fill the details of user to register");
    		test.info("registration initiated");
       	Map<String, String> data = ScannerUtil.readExcelToMap("src/main/resources/Userdetails.xlsx");
       	test.info("data read form excel file");
       	RegisterPage page = new RegisterPage(driver);
       	test.info("page called");
       	
        assertFalse(page.reigisteruser(data), "Duplicate email should not allow registration!");
        test.pass("Duplicate Email Validation Successful");
   		} catch (Exception e) {
   			// TODO: handle exception
   			logFailure(e, "Duplicate Email Registration Test Failed");
   		}
    }

}
