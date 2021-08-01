package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import generic.WebUtil;

public class LoginPage extends BasePage
{
	
	public LoginPage(WebDriver driver, ExtentTest eTest) 
	{
		super(driver, eTest);
	}

	@FindBy(id="username")
	private WebElement unTB;
	
	@FindBy(name="pwd")
	private WebElement pwTB;
	
	@FindBy(xpath="//div[text()='Login ']")
	private WebElement loginBTN;;
	
	public void setUserName(String un)
	{
		webUtil.sendKeys(unTB, un);
	}
	
	public void setPassord(String pw)
	{
		webUtil.sendKeys(pwTB, pw);
	}
	
	public void clickLogin()
	{
		webUtil.click(loginBTN);
	}
	
}
