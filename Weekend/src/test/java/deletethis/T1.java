package deletethis;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import generic.BaseTest;
import generic.RetryTestRun;

@Test //(retryAnalyzer =RetryTestRun.class )
public class T1 extends BaseTest
{
		public void TestA()
		{
			eTest.log(Status.PASS, "inside TestA");
			System.out.println(driver.getTitle());
			Assert.fail();
		}
}
