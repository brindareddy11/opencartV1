package testCases;

import static org.testng.Assert.fail;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;
// this is login test
public class TC_002_LoginPage extends BaseClass
{
	@Test(groups= {"master", "sanity"})
	public void test_Login()
	{
		logger.info("Starting TC_002_Login");
		
		try
		{
			
		driver.get(rb.getString("appURL"));
		
		logger.info("HomePage displayed");
		
		driver.manage().window().maximize();
		
		HomePage hp=new HomePage(driver);  // make every test case independent
		hp.clickMyAccount();
		logger.info("Clickedon MyAccount");
		hp.clickLogin();
		logger.info("Clickedon login");
		
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(rb.getString("email"));
		logger.info("setEmail");
		lp.setPassword(rb.getString("password"));
		lp.clickLogin();
		logger.info("loggedin");
		
		if(lp.isMyAccountPageExists()==true)
		{
		logger.info("Login Success");
		Assert.assertTrue(true);
				
		}
		else
		{
			logger.info("Login Failed");
			Assert.assertTrue(false);
					
		}
		}
		
		catch(Exception e)
		{
			logger.fatal("Login Failed");
			Assert.fail();
		}
}
}

