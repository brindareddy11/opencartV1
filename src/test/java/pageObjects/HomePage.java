package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//a[@title='My Account']")
	WebElement lnkMyaccount;
	@FindBy(linkText="Register")
	WebElement lnkRegister;
	
	@FindBy(linkText="Login")
	WebElement lnkLogin;
	
	@FindBy(xpath="//input[@placeholder='Search']")
	WebElement txtSearchbox;
	
	@FindBy(xpath="//button[@class='btn btn-default btn-lg']")
	WebElement btnSearch;
	
	public void clickMyAccount()
	{
		lnkMyaccount.click();
	}
	
	public void clickRegister()
	{
		lnkRegister.click();
	}
	
	public void clickLogin()
	{
		lnkLogin.click();
	}
	
	public void setSearch(String st)
	{
		txtSearchbox.sendKeys(st);
		
	}
	public void clickSearch()
	{
		btnSearch.click();
	}
	
}
