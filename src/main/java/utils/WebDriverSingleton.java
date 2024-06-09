package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class WebDriverSingleton {
    private static WebDriver driver;
    public static WebDriver getDriver(){
        if(driver == null){
            System.setProperty("webdriver.chrome.driver","drv/chromedriver.exe");
            ChromeOptions opt = new ChromeOptions();
            opt.setPageLoadStrategy(PageLoadStrategy.EAGER);
            driver = new ChromeDriver(opt);
            driver.manage().window().maximize();
        }
        return driver;
    }

}