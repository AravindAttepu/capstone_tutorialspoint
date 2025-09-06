package Utilities;


import com.aventstack.extentreports.MediaEntityBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtil {
    public static String capture(WebDriver driver, String screenshotName) {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = "reports/screenshots/" + screenshotName + ".png";
        try {
            FileUtils.copyFile(src, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    public static void attachScreenshot(WebDriver driver, String name, com.aventstack.extentreports.ExtentTest test) throws IOException {
        String path = capture(driver, name);
        test.fail("Screenshot:", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
    }
}
