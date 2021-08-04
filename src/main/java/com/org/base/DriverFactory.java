package com.org.base;

import org.openqa.selenium.WebDriver;

/**
 * Created by Soumya
 */
public class DriverFactory {
    private DriverFactory(){
    }
    private static DriverFactory instance= new DriverFactory();
    public static DriverFactory getInstance(){

        return instance;
    }

    ThreadLocal<WebDriver> driver= new ThreadLocal<WebDriver>();
    public WebDriver getDriver(){
        return driver.get();
    }

    public void setDriver(WebDriver driverparam){
        driver.set(driverparam);
    }

    public void closeBrowser(){
        driver.get().close();
        driver.remove();
    }
}
