package generic;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

@Listeners(TestListener.class)
public class BaseTest implements IConstant
{
	public WebDriver driver;
	public WebDriverWait wait;
	public static ExtentReports extentReports;
	public ExtentTest eTest;

	@BeforeSuite
	public void startGrid() throws IOException
	{
		String useGrid = FileUtils.getPropertyValue(settingsPath,"grid");
		
		if(useGrid.equalsIgnoreCase("yes")) 
		{
		//   /c will send remaining inputs as command to cmd
		String[] command = {"cmd.exe", "/C", "Start", ".\\remote\\RunMe.bat"};
        Process p =  Runtime.getRuntime().exec(command);
		}
	}
	
	@BeforeSuite
	public void initReport()
	{
        extentReports=new ExtentReports();
        ExtentSparkReporter spark=new ExtentSparkReporter(extentPath);
        extentReports.attachReporter(spark);
        
	}
	
	@AfterSuite
	public void publishReport()
	{
		extentReports.flush();
	}
	
	
	@Parameters({"hubURL","browser"})
	@BeforeMethod
	public void preCondition(@Optional(defaultHubURL)String hubURL,@Optional(defaultBrowser)String browser,ITestResult testResult) throws Exception
	{
		String testName = testResult.getMethod().getMethodName();
		eTest = extentReports.createTest(testName);
		
		String useGrid = FileUtils.getPropertyValue(settingsPath,"grid");
		
		if(useGrid.equalsIgnoreCase("yes")) 
		{
			eTest.log(Status.INFO,"Using Grid");
			URL url=new URL(hubURL);
			eTest.log(Status.INFO,"hubURL:"+hubURL);
			DesiredCapabilities capability=new DesiredCapabilities();
			capability.setBrowserName(browser);
			eTest.log(Status.INFO,"Browser:"+browser);
			driver=new RemoteWebDriver(url, capability);
		}
		else
		{
			eTest.log(Status.INFO,"Using Local System");
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();//Home work ---open required browser based on ppt file
		}
	
	
		long ETO = Long.parseLong(FileUtils.getPropertyValue(settingsPath,"ETO"));
		wait=new WebDriverWait(driver,ETO);
		eTest.log(Status.INFO,"ETO:"+ETO);
		
		long ITO = Long.parseLong(FileUtils.getPropertyValue(settingsPath,"ITO"));
		driver.manage().timeouts().implicitlyWait(ITO,TimeUnit.SECONDS);
		eTest.log(Status.INFO,"ITO:"+ITO);
		
		long PLT = Long.parseLong(FileUtils.getPropertyValue(settingsPath,"PLT"));
		driver.manage().timeouts().pageLoadTimeout(PLT, TimeUnit.SECONDS);
		eTest.log(Status.INFO,"PLT:"+PLT);
		
		long STO = Long.parseLong(FileUtils.getPropertyValue(settingsPath,"STO"));
		driver.manage().timeouts().setScriptTimeout(STO,TimeUnit.SECONDS);
		eTest.log(Status.INFO,"STO:"+STO);
		
		String appUrl=FileUtils.getPropertyValue(settingsPath, "AppUrl");
		driver.get(appUrl);
		eTest.log(Status.INFO,"appUrl:"+appUrl);
		
		driver.manage().deleteAllCookies();
		eTest.log(Status.INFO,"Cookies Deleted");
		
		driver.manage().window().maximize();
		eTest.log(Status.INFO,"Browser Maximized");
		
	}
	
	@AfterMethod
	public void postCondition(ITestResult result)
	{
		driver.quit();
		eTest.log(Status.INFO,"Browser is closed");
		
		int status = result.getStatus();
		if(status==1)
		{
			eTest.log(Status.PASS,"Test executed successfully");
		}
		else if(status==2)
		{
			eTest.log(Status.FAIL,"Test execution failed");
		}
		else if(status==3)
		{
			eTest.log(Status.SKIP,"Test execution is Skipped");
		}
		else
		{
			eTest.log(Status.WARNING,"Test execution is unknown");
		}
	}
	
	@DataProvider
	public Iterator<String[]> getDataFromXL(Method method)
	{
		/*1. Data should be present in input.xlsx
		 *2. sheet name should be same as test method name
		 *3. 1st row in xl should be column names .and rest should be data 
		 */
		String sheet=method.getName();
		Iterator<String[]> data = FileUtils.getDataFromXLForDP(INPUT_XLPATH,sheet);
		return data;
	}
	
	@DataProvider
	public Iterator<String[]>  getDataFromCSV(Method method) throws Exception
	{
		/*1. Data should be present in test method.csv
		 *2. 1st row in csv should be column names, and rest should be data 
		 */
		String path=DATA_PATH+method.getName()+".csv";
		Iterator<String[]> data =FileUtils.getDataFromCSVForDP(path);
		
		return data;
		
	}
	
	//HOME WORK: add data provider which takes the data from DB
}
