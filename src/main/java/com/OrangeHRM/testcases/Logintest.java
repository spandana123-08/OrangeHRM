package com.OrangeHRM.testcases;

import java.util.Hashtable;

import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.OrangeHRM.basetest.basetest;
import com.OrangeHRM.constants.HRMConstants;
import com.OrangeHRM.constants.TestDataFromExcel;
import com.OrangeHRM.pages.Launchingpage;
import com.OrangeHRM.pages.Loginpage;
import com.relevantcodes.extentreports.LogStatus;

public class Logintest extends basetest {
	@Test(dataProvider = "getdata", dataProviderClass = TestDataFromExcel.class)
	public void dologin(Hashtable<String, String> data) throws Exception {
		test = ext.startTest("Logintest");
		System.out.println(data.get("Runmode")+"--"+data.get("Browser")+"--"+data.get("Username")+"--"+data.get("Password")+"--"+data.get("ExpectedResult"));
		if(data.get(HRMConstants.RUNMODE_COL).equals("N")){
			test.log(LogStatus.SKIP, "Skipping the test as Runmode is N");
			throw new SkipException("Skipping the test as Runmode is N");
		}
		init(data.get("Browser"));
	Launchingpage launch = new Launchingpage(driver, test);
	PageFactory.initElements(driver, launch);
	Loginpage login = launch.openApplication();
	PageFactory.initElements(driver, login);
	login.doLogin(data.get("Username"), data.get("Password"));
	takeScreenshot(); 
	}
	
	@AfterMethod
	public void getResult(ITestResult result) throws Exception{
		 if(result.getStatus() == ITestResult.FAILURE){
		 test.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
		 test.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
		 //To capture screenshot path and store the path of the screenshot in the string "screenshotPath"
		                        //We do pass the path captured by this mehtod in to the extent reports using "logger.addScreenCapture" method. 
		                        String screenshotPath = getScreenhot(driver, result.getName());
		 //To add it in the extent report 
		 test.log(LogStatus.FAIL, test.addScreenCapture(screenshotPath));
		 }else if(result.getStatus() == ITestResult.SKIP){
		 test.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
		 }
		 
		 ext.endTest(test);
		 }
	@AfterSuite
	public void quit(){
		if(ext!=null){
		//ext.endTest(test);
	     ext.flush();
	}
}
}
