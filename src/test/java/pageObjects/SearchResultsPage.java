package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultsPage {
WebDriver driver;
	
	public SearchResultsPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
		
	@FindBy(xpath="//img[@title='iMac']")
	WebElement itemiMac;
	
	
	public boolean searchresults()
	{
		return(itemiMac.isDisplayed());
	}
		
	
}
