package testBase;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
// throught the testcases sth is repeated in all the testcases --keep them into Baseclass
public class BaseClass {
	public Logger logger;
	public WebDriver driver;
	public ResourceBundle rb;
	@BeforeClass(groups= {"master", "regression", "sanity"})
	@Parameters({"browser"})
	public void setup(String br)
	{
		rb=ResourceBundle.getBundle("config");// this particular statement load the config file
		logger=LogManager.getLogger(this.getClass());// if you one single testcases you can hardcode class name here, run time dynamically(each testcase name treated as class) get the name of the classes, 
		
		
		if(br.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			logger.info("Launched Chrome Browser");
			
		}
		else if(br.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			logger.info("Launched firfox Browser");
			
		}
		else if(br.equals("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
			logger.info("Launched edge Browser");
			
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	@AfterClass(groups= {"master", "regression", "sanity"})
	public void tearDown()
	{
		driver.close();
	}
	
	
	
	public String randomeString() 
	{
		
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return (generatedString);
				
	}
		
	
	public int randomeNumber() 
	{
		
		String generatedString2=RandomStringUtils.randomNumeric(5);
		return (Integer.parseInt(generatedString2));
				
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException
	{// if you call this method on failure , this method take the screen shot and store it in screenshots folder
	TakesScreenshot ts=(TakesScreenshot)driver;
	File source =ts.getScreenshotAs(OutputType.FILE);
	File target=new File(System.getProperty("user.dir")+"\\screenshots\\"+ tname +".png");
	FileUtils.copyFile(source, target);
	System.out.println("Screenshot taken");
	}
	
}
