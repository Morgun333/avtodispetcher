package utils;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.session.ChromeFilter;

import java.time.Duration;

public class WebDriverSingleton {
    private static WebDriver driver;
    public static WebDriver getDriver(){
        if(driver == null){
            System.setProperty("webdriver.chrome.driver","drv/chromedriver.exe");
            ChromeOptions opt = new ChromeOptions();
            opt.setPageLoadStrategy(PageLoadStrategy.EAGER);
            driver = new ChromeDriver(opt);
        }
        return driver;
    }

}