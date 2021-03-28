package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccount;
import testBase.BaseClass;
import utilities.XLUtils;

public class TC_003_LoginDDT extends BaseClass
{
	@Test(dataProvider="LoginData")
	public void test_LoginDDT(String email, String pwd, String exp) throws InterruptedException
	{
		logger.info("Strating TC_003_LoginDDT");
		
		
		try
		{
			driver.get(rb.getString("appURL"));
			logger.info("Home Page Displayed");
			//Thread.sleep(5000);
			driver.manage().window().maximize();
			HomePage hp=new HomePage(driver);  // make every test case independent
			hp.clickMyAccount();
			logger.info("Clickedon MyAccount");
			hp.clickLogin();
			logger.info("Clickedon login");
			
			LoginPage lp=new LoginPage(driver);
			lp.setEmail(email);
			logger.info("setEmail");
			lp.setPassword(pwd);
			lp.clickLogin();
			logger.info("loggedin");
			
			boolean targetpage=lp.isMyAccountPageExists();
			
			if(exp.equals("Valid"))
			{
				if(targetpage==true)
				{
					logger.info("Login Success");
					
					MyAccount myaccpage=new MyAccount(driver);
					myaccpage.clickLogout();
					Assert.assertTrue(true);
				}
				else
				{
					logger.error("Login Failed");
					Assert.assertTrue(false);
				}
			}
			else if(exp.equals("Invalid"))
			{
				if(targetpage==true)
				{
					logger.error("Login success for invalid data -Failed");
					MyAccount myaccpage=new MyAccount(driver);
					myaccpage.clickLogout();
					Assert.assertTrue(false);
				}
				else
				{
					logger.info("Login not Success for invalid data");
					Assert.assertTrue(true);
				}
				
			}
			
				
		}
		catch(Exception e)
		{
			logger.fatal("Login Failed");
			Assert.fail();
			
		}
		
		logger.info("Finished TC_03_LoginDDT");
	}
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException
	{
		String path=".\\testData\\OpenCart_LoginData.xlsx";
		XLUtils xlutil=new XLUtils(path);
		int totalrows=xlutil.getRowCount("Sheet1");
		int totalcols=xlutil.getCellCount("Sheet1", 1);
	
		String logindata[][]=new String[totalrows][totalcols];
		for(int i=1; i<=totalrows; i++)
		{
			for(int j=0;j<totalcols; j++)
			{
				logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j); // store the whole data into the 2-dimentinal array
			}
		}
		
		return logindata;// return the two dimentional array
	
	
	
	
	}
	

}
