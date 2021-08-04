package com.org.base;

import com.org.reusableComponents.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

/**
 * Created by Soumya
 */
public class BaseTest extends Utils{
    BrowserFactory browserFactory= new BrowserFactory();
    @BeforeMethod
    public void init() throws Exception {
        DriverFactory.getInstance().setDriver(browserFactory.createBrowserInstance(Utils.getPropertyValueByKey("browser")));
        WebDriver driverInstance= DriverFactory.getInstance().getDriver();
        driverInstance.manage().window().maximize();
        driverInstance.manage().deleteAllCookies();
        driverInstance.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        driverInstance.navigate().to(Utils.getPropertyValueByKey("url"));
    }
    @AfterMethod
    public void tearDown(){
        DriverFactory.getInstance().closeBrowser();
    }
}
