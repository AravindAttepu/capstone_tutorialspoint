package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportManager {

    public static ExtentReports extent;
    private static ExtentTest test;

    public static ExtentReports getReporter() {
        if (extent == null) {
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            String repName = "Test-Report-" + timeStamp + ".html";
            ExtentSparkReporter reporter = new ExtentSparkReporter("test-output/" + repName);
            reporter.config().setReportName("Selenium Automation Report");
            reporter.config().setDocumentTitle("Automation Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Tester", "Aravind Attepu");
        }
        return extent;
    }

    public static ExtentTest createTest(String testName, String description) {
        test = getReporter().createTest(testName, description);
        return test;
    }

    public static void flush() {
        if (extent != null) {
            extent.flush();
        }
    }
}
