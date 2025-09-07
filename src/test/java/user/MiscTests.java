package user;


import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.testng.annotations.*;

import Base.BaseTest;
import Pages.ContactUsPage;
import Pages.HomePage;
import Utilities.ReportManager;

public class MiscTests extends BaseTest {

    

    @Test(priority = 1)
    public void testChangeCurrencyPersistence() throws IOException {
    	try {
    		
    		test= ReportManager.createTest("Change Currency", "changing currency to EUR");
    		test.info("test started");
    		HomePage page= new HomePage(driver);
    		test.info("Home Page object loaded");
    	 assertTrue(page.changeCurrency("EUR"));
    	 test.pass("currency changed");

		} catch (Exception e) {
			// TODO: handle exception
			logFailure(e, "currency change operation failure");
		}
    }

    @Test(priority = 2)
    public void testContactUsFormSubmission() {
        try {
    	    test= ReportManager.createTest("Redirect to contact us", "jump to contact us page ");
    	    test.info("test started");
    	    HomePage page=new HomePage(driver);
    	    test.info("home page object loaded");
    	    page.redirectToContactUs();
    	    test.info("redirected to contactus");
    	    ContactUsPage contactUsPage = new ContactUsPage(driver);
    	    test.info("contactuspage object loaded");
    	    contactUsPage.fillform();
    	    test.info("filled form and submitted");
    	    test.pass("Summary submitted via contact us");
        } catch (Exception e) {
            logFailure(e, "testContactUsFormSubmission");
        }
    }

}
