package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverSingleton {
    private static WebDriver driver;
    public static WebDriver getDriver(){
        if(driver == null){
            System.setProperty("webdriver.chrome.driver","drv/chromedriver.exe");
            driver = new ChromeDriver();
        }
        return driver;
    }

}