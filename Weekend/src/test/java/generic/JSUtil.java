package generic;


import org.apache.log4j.Logger;
import org.apache.log4j.spi.RootLogger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class JSUtil {
	public WebDriver driver;
	public JavascriptExecutor jse;
	public ExtentTest eTest;
	public Logger log4j= RootLogger.getRootLogger();
	
	public JSUtil(WebDriver driver,ExtentTest eTest)
	{
		this.driver=driver;
		jse=(JavascriptExecutor)driver;
		this.eTest=eTest;
	}
	
	
	public void sendKeys(WebElement element,String input)
	{
		eTest.log(Status.INFO,"Enter the input '"+input+"' using JSE");
		log4j.info("Enter the input '"+input+"' using JSE");
		
		jse.executeScript("arguments[0].value=arguments[1]",element,input);
	}
	
	public  void clear(WebElement element)
	{
		jse.executeScript("arguments[0].value=''",element);
	}
	
	public  void click(WebElement element)
	{
		jse.executeScript("arguments[0].click()",element);
	}
	
	public  void scrollTo(int x,int y) {
		jse.executeScript("window.scrollTo("+x+","+y+")");
	}
	
	public  void scrollBy(int x,int y) {
		jse.executeScript("window.scrollBy("+x+","+y+")");
	}
	
	public void scrollIntoView(WebElement element) {
		jse.executeScript("arguments[0].scrollIntoView(true)",element);
	}
	
	public void scrollToPageBottom() {
		jse.executeScript("window.scrollTo(0,document.body.scrollHeight)"); 
	}
	
}
