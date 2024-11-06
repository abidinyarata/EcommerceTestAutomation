package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public Properties property;
	
	@BeforeClass(groups = {"Sanity", "Regression", "Master"})
	@Parameters({"os", "browser"})
	public void setup(String os, String browser) throws IOException, URISyntaxException
	{
		FileReader file = new FileReader(".//src//test//resources//config.properties");
		property = new Properties();
		property.load(file);
		
		if (property.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			// Operating System
			if (os.equalsIgnoreCase("windows"))
				capabilities.setPlatform(Platform.WIN11);
			else if (os.equalsIgnoreCase("LINUX"))
				capabilities.setPlatform(Platform.LINUX);
			else {
				System.out.println("No matching os!...");
				return;
			}
			
			// Browser
			switch (browser.toLowerCase()) {
			case "chrome":
				capabilities.setBrowserName("chrome");
				break;
			case "edge":
				capabilities.setBrowserName("MicrosoftEdge");
				break;
			default:
				System.out.println("No matching browser!...");
				return;
			}
			
			driver = new RemoteWebDriver(new URI("http://localhost:4444/wd/hub").toURL(), capabilities);
		}
		
		if (property.getProperty("execution_env").equalsIgnoreCase("local")) {
			switch (browser.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			case "edge":
				driver = new EdgeDriver(); 
				break;
			default:
				System.out.println("Invalid browser name!...");
				return;
			}
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(property.getProperty("appURL"));
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups= {"Sanity", "Regression", "Master"})
	public void tearDown()
	{
		driver.quit();
	}
	
	public String randomString(int length)
	{
		RandomStringGenerator generator = new RandomStringGenerator.Builder()
				.withinRange('a', 'z').get();
		
		return generator.generate(length);
	}
	
	public String randomNumber(int length)
	{
		RandomStringGenerator generator = new RandomStringGenerator.Builder()
				.withinRange('0', '9').get();
		
		return generator.generate(length);
	}
	
	public String randomAlphaNumeric()
	{
		return randomString(3) + randomNumber(3);
	}
	
	public String capitalize(String text)
	{
		return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
	}
	
	public String captureScreen(String testName) throws IOException 
	{
		String currentDatetimeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot)driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + testName + "_" + currentDatetimeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;
	}
}