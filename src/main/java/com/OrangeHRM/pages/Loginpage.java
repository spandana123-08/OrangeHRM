package com.OrangeHRM.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.OrangeHRM.basepage.basepage;
import com.OrangeHRM.constants.*;
import com.relevantcodes.extentreports.ExtentTest;

public class Loginpage extends basepage {
	@FindBy(xpath = HRMConstants.USERNAME_XPATH)
	WebElement username;
	@FindBy(xpath = HRMConstants.PASSWORD_XPATH)
	WebElement password;
	public Loginpage(WebDriver driver,ExtentTest test) {
		this.driver=driver;
		this.test=test;
		PageFactory.initElements(driver, test);
	}
	public void doLogin(String UserName, String PassWord) {
		username.sendKeys(UserName);
		password.sendKeys(PassWord);
		password.sendKeys(Keys.ENTER);
		
	}
}
