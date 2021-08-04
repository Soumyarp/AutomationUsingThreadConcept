package com.org.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.org.reusableComponents.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Soumya
 */
public class ExtentReportSetup {
    static ExtentReports extent;

    public static ExtentReports setupExtentReport() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
        Date date = new Date();
        String actualDate = format.format(date);
        String reportPath= System.getProperty("user.dir")+"/Reports/ExecutionReport_"+actualDate+".html";
        ExtentSparkReporter sparkReporter= new ExtentSparkReporter(reportPath);
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        sparkReporter.config().setDocumentTitle(" Securtime Test Report");
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setReportName("Execution Report");
        extent.setSystemInfo("Executed on Environment:- ", Utils.getPropertyValueByKey("url"));
        extent.setSystemInfo("Executed on Browser:- ", Utils.getPropertyValueByKey("browser"));
        extent.setSystemInfo("Executed on OS:- ",System.getProperty("os.name"));
        extent.setSystemInfo("Executed by User:- ",System.getProperty("user.name"));
        return extent;
    }
}
