package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SearchResultsPage;
import testBase.BaseClass;

public class TC_004_SearchBoxValidate extends BaseClass
{
	
	@Test
	public void testSearchBox()
	{
		try
		{
		logger.info("TC_004_SearchBoxValidation is started...");
		driver.get(rb.getString("appURL"));
		driver.manage().window().maximize();
		HomePage hp=new HomePage(driver);
		hp.setSearch("Imac");
		hp.clickSearch();
		SearchResultsPage srp=new SearchResultsPage(driver);
		boolean res=srp.searchresults();
		
		if(res==true)
		{
			logger.info("Search results displayed");
			Assert.assertTrue(true);
		}
		else
		{
			logger.info("Search results not displayed");
			Assert.assertTrue(false);
		}
		
		}
		catch(Exception e)
		{
			Assert.fail("Searchbox is not validated ");
		}
		
		
		
		
		
		
		
		
		
	}
	
	
	
	

}
