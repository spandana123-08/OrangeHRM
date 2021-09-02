package com.OrangeHRM.constants;

import java.io.File;
import java.util.Date;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
private static ExtentReports extent;
	
	public static ExtentReports geInstance(){
		if(extent==null){
		Date d=new Date();
		System.out.println(d.toString());
		String filename=d.toString().replace(":", "_").replace(" ", "_")+".html";;
		String reportPath=HRMConstants.REPORTPATH+filename;
		extent=new ExtentReports(reportPath, true, DisplayOrder.NEWEST_FIRST);
		extent.loadConfig(new File(System.getProperty("user.dir")+"//extent-config.xml"));
		extent.addSystemInfo("Selenium Version", "Selenium 3.4.o").addSystemInfo("Environment", "QA").addSystemInfo("TesterNmae", "Amaresh");
		
		}
		return extent;

}
}
