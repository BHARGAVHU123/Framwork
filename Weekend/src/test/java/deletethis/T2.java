package deletethis;

import org.testng.annotations.Test;

import generic.BaseTest;

public class T2 extends BaseTest
{
		@Test
		public void TestB()
		{
			System.out.println(driver.getTitle());
		}
}
