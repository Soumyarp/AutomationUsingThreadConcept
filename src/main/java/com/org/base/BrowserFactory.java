package com.org.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Soumya
 */
@Test
public class BrowserFactory {
private WebDriver driver= null;
    public WebDriver createBrowserInstance(String browser) throws MalformedURLException {

        String host="localhost";
        DesiredCapabilities cap= new DesiredCapabilities();
        cap.setCapability(CapabilityType.BROWSER_NAME,"chrome");

        if(System.getProperty("BROWSER")!=null && System.getProperty("BROWSER").equalsIgnoreCase("chrome")) {
            //     if(browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            /*options.addArguments("--disable-extensions");
            options.addArguments("test-type");
            options.addArguments("--ignore-certificate-errors");
            options.addArguments("no-sandbox");
            options.addArguments("--headless");//hide browser*/
            driver = new ChromeDriver(options);
            System.setProperty("webdriver.chrome.SilentOutput", "true");
        }  else if(System.getProperty("BROWSER")!=null && System.getProperty("BROWSER").equalsIgnoreCase("firefox")){
    //    }else if(browser.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver= new FirefoxDriver();
        }else if(System.getProperty("HUB_HOST")!= null){
            host=System.getProperty("HUB_HOST");
        }
        String url="http://"+host+":4444/wd/hub";
       this.driver= new RemoteWebDriver(new URL(url),cap);
       return driver;
    }
}
