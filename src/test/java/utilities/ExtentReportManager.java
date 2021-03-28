package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener
{
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	String repName;
	public void onStart(ITestContext context)
	{	
		
		String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName="Test-Report-"+timestamp+".html";
		sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+ "\\reports\\"+repName);// name of the report.html
		
		sparkReporter.config().setDocumentTitle("nopCommerce Automation Report");// you can set the title
		sparkReporter.config().setReportName("nopCommerce Functional Testing");// set the name of the report
		sparkReporter.config().setTheme(Theme.DARK);  // set theme
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Host name", "localhost");//which computer you running this report
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "Sudhakar");
		extent.setSystemInfo("os", "Windows10");
		extent.setSystemInfo("Browser name", "Chrome,Firefox,Edge");
		
				
	}
	
	public void onTestSuccess(ITestResult result)
	{
		test=extent.createTest(result.getName());// take the name of the report and create the test
		test.log(Status.PASS, "Test case PASSED is:"+result.getName());// this method update the status
	}
	
	public void onTestFailure(ITestResult result)
	{
		test=extent.createTest(result.getName());
		test.log(Status.FAIL, "Test case Failed");// this method update the status
		test.log(Status.FAIL, "Test case Failed cause is:"+result.getThrowable().getMessage());
		try
		{
			String screenshotPath=System.getProperty("user.dir")+"\\screenshots\\"+result.getName()+".png";
			test.addScreenCaptureFromPath(screenshotPath);  // add the report to the report
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
	}
	
	public void onTestSkipped(ITestResult result)
	{
		test=extent.createTest(result.getName());
		test.log(Status.SKIP, "Test case skipped is:"+result.getName());// this method update the status
		
	}
	

	public void onFinish(ITestContext context)
	{
		extent.flush();// arrange everything properly and  complete the report
	}
	
	
	
}
