package com.org.pageObjects.t_a.organization;

import com.org.base.BaseTest;
import com.org.base.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashMap;

/**
 * Created by Soumya
 */

public class DepartmentsPage extends BaseTest {

    By btn_newDepartment =By.xpath("//span[contains(text(),'New Department')]");
    By dd_selectAffiliate = By.xpath("//span[contains(text(),'Select Affiliate')]");
    By dd_values = By.xpath("//div[@class='dropdown open']//ul//li");
    By txt_deptName=By.xpath("//input[@placeholder='Name']");
    By btn_save= By.xpath("//button[contains(text(),'SAVE')]");
    By view =By.xpath("//*[@id='appContentContainer']/div/app-department-search/div/div/st-table/div[2]/div/div");

    public void createDepartment(HashMap<String,String> data) throws InterruptedException {
        click(DriverFactory.getInstance().getDriver().findElement(btn_newDepartment),"clicking on new department btn");
        click(DriverFactory.getInstance().getDriver().findElement(dd_selectAffiliate),"clicking on select affiliate dd");
        getDropDownValue(DriverFactory.getInstance().getDriver().findElements(dd_values),data.get("AffiliateName"));
        sendKeys(DriverFactory.getInstance().getDriver().findElement(txt_deptName),"dept name is ",data.get("DeptName"));
        Thread.sleep(2000);
        click(DriverFactory.getInstance().getDriver().findElement(btn_save),"Clicked on save btn");

    }

    public void viewallDept(String deptname){
        String beforeXpath="//*[@id='appContentContainer']/div/app-department-search/div/div/st-table/div[2]/div/div[";
        String afterXpath="]/div[2]/div[1]";
        handleWebTable(beforeXpath,afterXpath,DriverFactory.getInstance().getDriver().findElements(view),deptname);
    }
}
