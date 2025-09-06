package Utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtil {

    /**
     * Waits until the page's document.readyState is "complete".
     *
     * @param driver   WebDriver instance
     * @param timeout  timeout in seconds
     */
    public static void waitForPageLoad(WebDriver driver, int timeout) {
        new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until((ExpectedCondition<Boolean>) wd ->
                        ((JavascriptExecutor) wd)
                                .executeScript("return document.readyState")
                                .equals("complete"));
    }
}