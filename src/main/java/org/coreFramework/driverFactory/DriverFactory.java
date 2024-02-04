package org.coreFramework.driverFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.coreFramework.utils.ConfigUtils;

import java.time.Duration;

public class DriverFactory {

    public WebDriver initDriver(){
        WebDriver driver;
        String browser = System.getProperty("BROWSER","chrome");
        String browserMode = ConfigUtils.getInstance().getHeadlessMode();
        switch (browser){
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                if(browserMode.equals("true")){
                    options.addArguments("--remote-allow-origins=*");
                    options.addArguments("--start-maximized");
                    options.addArguments("--headless=new");
                }
                driver = new ChromeDriver(options);
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                if(browserMode.equals("true")){
                    options.addArguments("--headless=new");
                }
                driver = new FirefoxDriver(options);
            }
            case "edge" -> {
                WebDriverManager.edgedriver().setup();
                EdgeOptions options = new EdgeOptions();
                if(browserMode.equals("true")){
                    options.addArguments("--headless=new");
                }
                driver = new EdgeDriver(options);
            }
            default -> {
              throw new RuntimeException("Browser not supported!");
            }
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }
}
