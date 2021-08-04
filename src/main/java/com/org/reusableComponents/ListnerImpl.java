package com.org.reusableComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.org.base.DriverFactory;
import com.org.base.ExtentReportFactory;
import com.org.base.ExtentReportSetup;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.IOException;
import java.util.Date;
import java.io.File;
import java.text.SimpleDateFormat;

/**
 * Created by Soumya
 */
public class ListnerImpl implements ITestListener {
    static ExtentReports report;
    ExtentTest test;

    public void onTestStart(ITestResult result) {
        //before each test case
        test = report.createTest(result.getMethod().getMethodName());
        ExtentReportFactory.getInstance().setExtent(test);
    }

    public void onTestSuccess(ITestResult result) {
        ExtentReportFactory.getInstance().getExtent().log(Status.PASS, "Test Case: "+result.getMethod().getMethodName()+ " is Passed.");
        ExtentReportFactory.getInstance().removeExtentObject();
    }

    public void onTestFailure(ITestResult result) {
        ExtentReportFactory.getInstance().getExtent().log(Status.FAIL, "Test Case: "+result.getMethod().getMethodName()+ " is Failed.");
        ExtentReportFactory.getInstance().getExtent().log(Status.FAIL, result.getThrowable());

        //add screenshot for failed test.
        File src = ((TakesScreenshot) DriverFactory.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
        Date date = new Date();
        String actualDate = format.format(date);

        String screenshotPath = System.getProperty("user.dir")+
                "/Reports/Screenshots/"+actualDate+".jpeg";
        File dest = new File(screenshotPath);

        try {
            FileUtils.copyFile(src, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ExtentReportFactory.getInstance().getExtent().addScreenCaptureFromPath(screenshotPath, "Test case failure screenshot");
            ExtentReportFactory.getInstance().removeExtentObject();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult result) {
        ExtentReportFactory.getInstance().getExtent().log(Status.SKIP, "Test Case: "+result.getMethod().getMethodName()+ " is skipped.");
        ExtentReportFactory.getInstance().removeExtentObject();
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    public void onTestFailedWithTimeout(ITestResult result) {
    }

    public void onStart(ITestContext context) {
        try {
            report = ExtentReportSetup.setupExtentReport();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onFinish(ITestContext context) {
        //close extent
        report.flush();
    }

}
