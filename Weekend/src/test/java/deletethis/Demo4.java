package deletethis;

import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Demo4 {

	@BeforeMethod
	public void bm(ITestResult t)
	{
		System.out.println(t.getMethod().getMethodName());
	}
	
	@Test
	public void testA()
	{
		System.out.println("run1");
	}

	@Test
	public void testB()
	{
		System.out.println("run2");
	}
}
