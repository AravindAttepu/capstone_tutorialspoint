package Base;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentTest;

import Utilities.ReportManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import Utilities.ScreenshotUtil;

public class BaseTest {
	
protected static WebDriver driver;
protected static ExtentTest test;


@BeforeClass
public static void setupReport() {
    ReportManager.getReporter();  // Initialize ExtentReport
}

@BeforeMethod
public void setUp() {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
}
@AfterMethod
public void tearDown() {
    if (driver != null) {
        driver.quit();
        test.info("Browser closed");
    }
}

@AfterClass
public static void closeReport() {
    ReportManager.flush();  // Generate report after all tests
}

// Utility for failure handling
protected void logFailure(Throwable e, String screenshotName) throws IOException {
    test.fail(e.getMessage());
    ScreenshotUtil.attachScreenshot(driver, screenshotName, test);
    Assert.fail(e.getMessage());
}
}
