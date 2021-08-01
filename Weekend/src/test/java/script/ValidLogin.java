package script;

import java.lang.reflect.Method;
import java.util.Iterator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import generic.BaseTest;
import generic.FileUtils;
import pages.LoginPage;

public class ValidLogin extends BaseTest
{	

	@Test(dataProvider = "getDataFromCSV")
	public void testValidLogin(String un,String pw)
	{
		LoginPage loginPage=new LoginPage(driver, eTest);
		loginPage.setUserName(un);
		loginPage.setPassord(pw);
		loginPage.clickLogin();
	}
}
