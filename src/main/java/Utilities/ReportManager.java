package Utilities;


import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportManager {
    public static ExtentReports extent;
    private static ExtentTest test;

    public static ExtentReports getReporter() {
        if (extent == null) {
            ExtentSparkReporter reporter = new ExtentSparkReporter("reports/ExtentReport.html");
            
            reporter.config().setReportName("Selenium Automation Report");
            reporter.config().setDocumentTitle("Automation Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Tester", "Aravind Attepu");
            extent.setSystemInfo("Framework", "JUnit4 + Selenium");
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
