package com.org.reusableComponents;

import com.org.base.DriverFactory;
import com.org.base.ExtentReportFactory;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.service.DriverCommandExecutor;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import com.aventstack.extentreports.Status;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

/**
 * Created by Soumya
 */
public class Utils {
    static Properties prop= new Properties();
    public static String getPropertyValueByKey(String key) throws Exception {
        String filePath=System.getProperty("user.dir")+"/src/test/resources/config.properties";
        FileInputStream fis = new FileInputStream(filePath);
        prop.load(fis);
        String value = prop.get(key).toString();
        if(StringUtils.isEmpty(value)){
            throw new Exception("value is not specified for the key:"+key+"in property file.");
        }
        return value;
    }
    //Customized sendkeys method-> To log sendkeys message for every occ.
    public void sendKeys(WebElement element, String fieldName, String valueToBeSent) {
        try {
            element.sendKeys(valueToBeSent);
            //log success message in exgent report
            ExtentReportFactory.getInstance().getExtent().log(Status.PASS, fieldName+"given value as: "+valueToBeSent);
        } catch (Exception e) {
            //log failure in extent
            ExtentReportFactory.getInstance().getExtent().log(Status.FAIL, "Value enter in field: "+fieldName + " is failed due to exception: "+e);
        }
    }


    //custom click method to log evey click action in to extent report
    public void click(WebElement element, String fieldName) {
        try {
            element.click();
            //log success message in exgent report
            ExtentReportFactory.getInstance().getExtent().log(Status.PASS, fieldName+" => Clicked Successfully! ");
        } catch (Exception e) {
            //log failure in extent
            ExtentReportFactory.getInstance().getExtent().log(Status.FAIL, "Unable to click on field: " +fieldName +" due to exception: "+e);
        }
    }

    //clear data from field
    public void clear(WebElement element,String fieldName) {
        try {
            element.clear();
            Thread.sleep(250);
            ExtentReportFactory.getInstance().getExtent().log(Status.PASS, fieldName+"==> Data Cleared Successfully! ");
        } catch (Exception e) {
            ExtentReportFactory.getInstance().getExtent().log(Status.FAIL, "Unable to clear Data on field: " +fieldName +" due to exception: "+e);

        }
    }

    //custom mouseHover
    public void moveToElement(WebElement element,String fieldName){
        try{
            JavascriptExecutor executor = (JavascriptExecutor) DriverFactory.getInstance().getDriver();
            executor.executeScript("arguments[0].scrollIntoView(true);", element);
            Actions actions = new Actions(DriverFactory.getInstance().getDriver());
            actions.moveToElement(element).build().perform();
            ExtentReportFactory.getInstance().getExtent().log(Status.PASS, fieldName+"==> Mouse hovered Successfully! ");
            Thread.sleep(1000);
        }catch(Exception e){
            ExtentReportFactory.getInstance().getExtent().log(Status.FAIL, "Unable to hover mouse on field: " +fieldName +" due to exception: "+e);

        }
    }

    //check if element is Present
    public boolean isElementPresent(WebElement element,String fieldName){
        boolean flag = false;
        try {
            flag = element.isDisplayed();
            ExtentReportFactory.getInstance().getExtent().log(Status.PASS, fieldName+"==> Presence of field is: "+ flag);
            return flag;
        } catch (Exception e) {
            ExtentReportFactory.getInstance().getExtent().log(Status.FAIL, "Checking for presence of field: " +fieldName +" not tested due to exception: "+e);
            return flag;
        }
    }

    //Select dropdown value value by visibleText
    public void selectDropDownByVisibleText(WebElement element, String fieldName, String ddVisibleText) throws Throwable {
        try {
            Select s = new Select(element);
            s.selectByVisibleText(ddVisibleText);
            ExtentReportFactory.getInstance().getExtent().log(Status.PASS, fieldName+"==> Dropdown Value Selected by visible text: "+ ddVisibleText);
        } catch (Exception e) {
            ExtentReportFactory.getInstance().getExtent().log(Status.FAIL, "Dropdown value not selected for field: " +fieldName +"  due to exception: "+e);
        }
    }

    //Select dropdown value value by value
    public void selectDropDownByValue(WebElement element, String fieldName, String ddValue) throws Throwable {
        try {
            Select s = new Select(element);
            s.selectByValue(ddValue);
            ExtentReportFactory.getInstance().getExtent().log(Status.PASS, fieldName+"==> Dropdown Value Selected by visible text: "+ ddValue);
        } catch (Exception e) {
            ExtentReportFactory.getInstance().getExtent().log(Status.FAIL, "Dropdown value not selected for field: " +fieldName +"  due to exception: "+e);
        }
    }

    //String Asserts
    public void assertEqualsString(String expvalue, String actualValue, String locatorName) throws Throwable {
        try {
            if(actualValue.equals(expvalue)) {
                ExtentReportFactory.getInstance().getExtent().log(Status.PASS, "String Assertion is successful on field "+ locatorName + " Expected value was: "+ expvalue + " actual value is: "+actualValue);
            }else {
                ExtentReportFactory.getInstance().getExtent().log(Status.FAIL, "String Assertion FAILED on field "+ locatorName + " Expected value was: "+ expvalue + " actual value is: "+actualValue);
                Assert.assertTrue(false);
            }
        } catch (Exception e) {
            Assert.assertTrue(false, e.toString());
        }
    }

    //Get text from webelement
    public String getText(WebElement element, String fieldName) {
        String text = "";
        try {
            text = element.getText();
            ExtentReportFactory.getInstance().getExtent().log(Status.PASS, fieldName+"==> Text retried is: "+ text);
            return text;
        } catch (Exception e) {
            ExtentReportFactory.getInstance().getExtent().log(Status.FAIL, fieldName+"==> Text not retried due to exception: "+ e);
        }
        return text;
    }

    public String getPageTitle(String fieldName){
        String text = "";
        try {
            text= DriverFactory.getInstance().getDriver().getTitle();
            ExtentReportFactory.getInstance().getExtent().log(Status.PASS, fieldName+"==> Title retried is: "+ text);
            return text;
        } catch (Exception e) {
            ExtentReportFactory.getInstance().getExtent().log(Status.FAIL, fieldName+"==> Text not retried due to exception: "+ e);
        }
        return text;
    }

    public void getDropDownValue(List<WebElement> element, String value){
        for(int i=0;i<element.size();i++){
            if(element.get(i).getText().equalsIgnoreCase(value)){
                element.get(i).click();
            }
        }
    }
    public void handleWebTable(String ele1, String ele2, List<WebElement> elements, String value) {
        String beforeXpath = ele1;
        String afterXpath = ele2;
        int rowCount=elements.size();
        System.out.println(rowCount);
        for (int i = 1; i < rowCount; i++) {
            String actualXath = beforeXpath + i + afterXpath;
            WebElement element = DriverFactory.getInstance().getDriver().findElement(By.xpath(actualXath));
            System.out.println(element.getText());
            if(element.getText().equalsIgnoreCase(value)){
                System.out.println("name:- "+element.getText()+"is found at position :- " +i);
            }
        }
    }
    public void selectRadioButton(List<WebElement> elements, String value){
        for (WebElement element:elements){
            if(element.getText().equalsIgnoreCase(value)){
                element.click();
                break;
            }
        }
    }

    public void selectCheckBoxes(List<WebElement> elements, String checkBox){
        String [] checkbox_array=checkBox.split(",");
        for (String str:checkbox_array){
            for(WebElement element:elements){
                if(element.getText().equalsIgnoreCase(str)){
                    element.click();
                    break;
                }
            }
        }

    }

}
