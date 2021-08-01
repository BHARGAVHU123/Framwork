package generic;

import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class WebUtil {
//implement log4j reporting in this class
	public ExtentTest eTest;
	
	public WebUtil(ExtentTest eTest)
	{
		this.eTest=eTest;
	}
	public void sendKeys(WebElement element,String input)
	{
		element.sendKeys(input);
		eTest.log(Status.INFO, "Entering the input:"+input);
	}
	
	public void click(WebElement element)
	{
		element.click();
		eTest.log(Status.INFO, "clicking on the element");
	}
}
