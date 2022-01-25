package utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;


public class AllureUtils {
    public static void screenshotBeforeStep() {
        Allure.addAttachment("Screenshot before step", new ByteArrayInputStream(
                ((TakesScreenshot) WebDriverSingleton.getDriver())
                        .getScreenshotAs(OutputType.BYTES)));
    }

    public static void screenshotAfterStep() {
        Allure.addAttachment("Screenshot after step", new ByteArrayInputStream(
                ((TakesScreenshot) WebDriverSingleton.getDriver())
                        .getScreenshotAs(OutputType.BYTES)));
    }
}