package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import generic.JSUtil;
import generic.RobotUtil;
import generic.WebUtil;

public class BasePage
{
	public WebDriver driver;
	public WebUtil webUtil;
	public JSUtil jsUtil;
	public RobotUtil robotUtil;
	
	public BasePage(WebDriver driver,ExtentTest eTest)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		webUtil=new WebUtil(eTest);
		jsUtil=new JSUtil(driver, eTest);
		robotUtil=new RobotUtil(eTest);
	}
}
