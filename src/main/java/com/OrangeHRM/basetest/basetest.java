package com.OrangeHRM.basetest;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.OrangeHRM.basepage.basepage;
import com.OrangeHRM.constants.ExtentManager;
import com.OrangeHRM.constants.HRMConstants;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class basetest extends basepage{
	public ExtentReports ext=ExtentManager.geInstance();
	public ExtentTest test;
	public WebDriver driver;
	public void init(String bType){
		test.log(LogStatus.INFO	, "Opening browser "+bType);
		if(bType.equals("Mozilla")){
			System.setProperty("webdriver.firefox.marionette", HRMConstants.FIREFOX_DRIVER_EXE);
			driver=new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			test.log(LogStatus.INFO	, bType+" browser successfully opend");	
	}
			else if (bType.equals("Chrome")){ 
				System.setProperty("webdriver.chrome.driver",HRMConstants.CHROME_DRIVER_EXE);
				driver=new ChromeDriver();	
				
			
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		test.log(LogStatus.INFO	, bType+" browser successfully opend");
			
		}
	
	
}
	public void takeScreenshot() throws Exception{
		Date d=new Date();
		String ScreenshotFile=d.toString().replace(":", "_").replace(" ","_")+".png";
		String Filepath=HRMConstants.REPORTPATH+"\\ScreenShots"+ScreenshotFile;
	TakesScreenshot srcFile=(TakesScreenshot)driver;
	File ts=srcFile.getScreenshotAs(OutputType.FILE);
		try {
		     FileUtils.copyFile(ts, new File(Filepath));
		     test.log(LogStatus.INFO,  test.addScreenCapture(Filepath));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
public void reportFailure(String failureMessage) throws Exception {
		
		test.log(LogStatus.FAIL, "Fail the Test");
		takeScreenshot();
		//Assert.fail(failureMessage);
	}
public static String getScreenhot(WebDriver driver, String screenshotName) throws Exception {
	 String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	 TakesScreenshot ts = (TakesScreenshot) driver;
	 File source = ts.getScreenshotAs(OutputType.FILE);
	                //after execution, you could see a folder "FailedTestsScreenshots" under src folder
	 String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/"+screenshotName+dateName+".png";
	 File finalDestination = new File(destination);
	 FileUtils.copyFile(source, finalDestination);
	 return destination;
	 }
}
