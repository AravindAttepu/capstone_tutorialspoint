package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class checkstatus {

    public static boolean checkstatusById(String id, String checkdata, WebDriver driver) {
        WebElement element = driver.findElement(By.id(id));
        return element.getText().contains(checkdata);
    }

    public static boolean checkstatusByXpath(String xpath, String checkdata, WebDriver driver) {
        WebElement element = driver.findElement(By.xpath(xpath));
        return element.getText().contains(checkdata);
    }

    public static boolean checkstatusByCss(String css, String checkdata, WebDriver driver) {
        WebElement element = driver.findElement(By.cssSelector(css));
        return element.getText().contains(checkdata);
    }
}
