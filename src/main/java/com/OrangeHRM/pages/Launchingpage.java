package com.OrangeHRM.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.OrangeHRM.basepage.basepage;
import com.OrangeHRM.constants.HRMConstants;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Launchingpage extends basepage{
public ExtentTest test;
	
	
	public Launchingpage(WebDriver driver,ExtentTest test) {
		this.driver=driver;
		this.test=test;
		
	}
	public Loginpage openApplication() {
		test.log(LogStatus.INFO	, HRMConstants.URL +" is going to open");		
		driver.get(HRMConstants.URL);
		test.log(LogStatus.INFO	, HRMConstants.URL +" is opened");
		Loginpage loginpage=new Loginpage(driver, test);
		PageFactory.initElements(driver, loginpage);
		return loginpage;
	}
		
		
}
