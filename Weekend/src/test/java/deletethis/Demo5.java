package deletethis;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class Demo5 {

	@Test
	public void testA()
	{
		Reporter.log("hi");
		Assert.fail();
	}
	
	@AfterMethod
	public void am(ITestResult result)
	{
		int s = result.getStatus();//1 pass 2-->Fail 3-->Skip
		System.out.println(s);
	}
	
}
