package com.org.pageObjects;

import com.org.base.BaseTest;
import com.org.base.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



/**
 * Created by Soumya
 */
public class LoginPage extends BaseTest {
    By EMAIL= By.xpath("//input[contains(@type,'email')]");
    By PASSWORD= By.xpath("//input[contains(@type,'password')]");
    By SIGNIN_BTN= By.xpath("//button[@type='submit' and text()=' Sign In ']");

    public void login(String email, String password){
        sendKeys(DriverFactory.getInstance().getDriver().findElement(EMAIL),"Email field ",email);
        sendKeys(DriverFactory.getInstance().getDriver().findElement(PASSWORD),"Password field ",password);
        click(DriverFactory.getInstance().getDriver().findElement(SIGNIN_BTN),"Clicked on Signin Button");
    }
}
