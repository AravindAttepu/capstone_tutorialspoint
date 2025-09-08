package Base;

import com.aventstack.extentreports.ExtentTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import Utilities.ReportManager;
import Utilities.ScreenshotUtil;

import java.io.IOException;
import java.time.Duration;

public class BaseTest {

    protected static WebDriver driver;
    protected static ExtentTest test;

    @BeforeSuite(alwaysRun = true)
    public void suiteSetup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // The report is initialized once for the entire execution
        if (ReportManager.getReporter() == null) {
            ReportManager.init("reports/ExtentReport.html", "TutorialsPoint Test Automation");
        }
    }

    @AfterSuite(alwaysRun = true)
    public void suiteTeardown() {
        if (driver != null) {
            driver.quit();
        }
        // Flush the report at the very end of the suite execution
        ReportManager.flush();
    }

    // Utility method to log failures and capture screenshots
    protected void logFailure(Throwable e, String screenshotName) throws IOException {
        if (test != null && driver != null) { // Added a null check for driver
            test.fail("Test failed with exception: " + e.getMessage());
            String screenshotPath = ScreenshotUtil.takeScreenshot(driver, screenshotName);
            if (screenshotPath != null) {
                test.addScreenCaptureFromPath(screenshotPath);
            }
        }
    }
}
