package com.org.base;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;

/**
 * Created by Soumya
 */
public class ExtentReportFactory {

    private ExtentReportFactory(){
    }
    private static ExtentReportFactory instance= new ExtentReportFactory();
    public static ExtentReportFactory getInstance(){
        return instance;
    }
    ThreadLocal<ExtentTest> extent= new ThreadLocal<ExtentTest>();
    public ExtentTest getExtent(){
        return extent.get();
    }
    public void setExtent(ExtentTest extentTestobj){
        extent.set(extentTestobj);
    }
    public void removeExtentObject(){
        extent.remove();
    }
}
