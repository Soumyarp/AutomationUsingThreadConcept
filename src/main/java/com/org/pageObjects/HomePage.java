package com.org.pageObjects;

import com.org.base.BaseTest;
import com.org.base.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * Created by Soumya
 */
public class HomePage extends BaseTest {
    By txt_welcome=By.xpath("//h2[starts-with(text(),'Welcome')]");

    public void clickOnMenuIcon(String menuIcon){
      //  String menubariconXpath="//div[@class='col-md-2 nav-bar no-padding-left no-padding-right height-100 overflow-auto']/following::div[@title='"+menuIcon+"']";
        String menubariconXpath="//div[@class='col-md-2 nav-bar no-padding-left no-padding-right height-100 overflow-auto']/child::div[2]/div//div[1][@title='"+menuIcon+"']";
        DriverFactory.getInstance().getDriver().findElement(By.xpath(menubariconXpath)).click();
    }

    public void clickOnMenu(String mainmenu){
        String menuXpath="//div[@class='col-md-2 nav-bar no-padding-left no-padding-right height-100 overflow-auto']/div[3]/div/div/a/div/div[contains(.,'"+mainmenu+"')]";
        DriverFactory.getInstance().getDriver().findElement(By.xpath(menuXpath)).click();
    }

    public void clickOnSubMenu(String submenu){
        String submenuXpath="//div[@class='col-md-2 nav-bar no-padding-left no-padding-right height-100 overflow-auto']/child::div[3]//following-sibling::div[2]/div/div//a[normalize-space()='"+submenu+"']";
        DriverFactory.getInstance().getDriver().findElement(By.xpath(submenuXpath)).click();
    }
    public void checkWelcomeTxtisDisplayed(){
        Assert.assertTrue(isElementPresent(DriverFactory.getInstance().getDriver().findElement(txt_welcome),"Welcome txt "));
    }
}
