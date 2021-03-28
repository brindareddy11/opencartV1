package testCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import testBase.BaseClass;

public class TC_001_AccountRegistration extends BaseClass  // once you extends tests in Baseclass will part of the this test--thats it
{


		
	@Test(groups= {"regression", "master"})// in latest version available run and debug
	public void test_account_Registration()
	{
		logger.info("starting TC_001_AccountRegistration");
		
	try
	{
		driver.get(rb.getString("appURL"));// get the url from propertiesfile--this should be for every testcase
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on Myaccount");
		hp.clickRegister();
		
		RegistrationPage rp=new RegistrationPage(driver);
		
		rp.setFirstName("Jhon");
		rp.setLastName("Kennedy");
		//rp.setEmail("abc@gmail.com");// you need here dynamic testdata-- how to generate random testdata?
		rp.setEmail(randomeString()+"@gmail.com");
		
		rp.setTelephone("789456123");
		logger.info("Provided Telphone number");
		rp.setPassword("abcabc");
		rp.setConfirmPassword("abcabc");
		rp.setPrivacyPolicy();
		logger.info("clicked privacy polocy");
		rp.clkContinue();
		String conf=rp.getConfirmationMsg();
		
		if(conf.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			captureScreen(driver, "test_account_Registration");
			Assert.assertTrue(false);
		}
	}
	catch(Exception e)
	{
		logger.fatal("Account Registration Failed");
	}
		
		logger.info("Test is finished-TC_001_AccountRegistration");
		
	}
		
	
}
