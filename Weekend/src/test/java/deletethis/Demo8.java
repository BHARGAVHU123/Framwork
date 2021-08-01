package deletethis;

import java.lang.reflect.Method;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Demo8 {

	
	@DataProvider
	public String[][] getData(Method method)
	{
		
		String[][] a=new String[1][1];
		a[0][0]=method.getName();
		return a;
	}
	
	
	@Test(dataProvider = "getData")
	public void testA(String s)
	{
		System.out.println(s);
	}
}
