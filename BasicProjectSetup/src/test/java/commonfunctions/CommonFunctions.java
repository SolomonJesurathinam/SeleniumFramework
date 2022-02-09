package commonfunctions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
@Listeners(CustomListeners.class)
public class CommonFunctions {
	
	public static Properties properties = null;
	public static WebDriver driver = null;
	public static ExtentReports extReport = null;
	public static ExtentSparkReporter reporter = null;
	public static ExtentTest extTest = null;
	public static WebElement ColoringFailed = null;
	public static String Classname = null;
	
	static Logger log = LogManager.getLogger(CommonFunctions.class.getName());

	public static Properties loadPropertyFile() throws IOException {
		
		FileInputStream fileInputStream = new FileInputStream("config.properties");
		properties = new Properties();
		log.info("Loading Config File");
		properties.load(fileInputStream);
		log.info("Loaded Config File");
		return properties;
	}
	
	public static void screenshot() throws IOException {
		
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].style.border='3px solid red'", ColoringFailed);
		} catch(JavascriptException e) {
			log.info("No element available to mark");
		}
		
		TakesScreenshot screenshots = (TakesScreenshot) driver;
		File source = screenshots.getScreenshotAs(OutputType.FILE);
		String Filename =System.getProperty("user.dir")+"//screenshots//"+"test_fail"+ Classname+".png";
		//System.getProperty("user.dir")+"//screenshots//"+"test_fail"+ MethodHandles.lookup().lookupClass()+".png";
		File destination = new File(Filename);
		System.out.println(Filename);
		FileHandler.copy(source, destination);
		extTest.addScreenCaptureFromPath(Filename);
	}
	
	@BeforeSuite
	public void firstSetup() throws IOException {
		extReport = new ExtentReports();
		reporter = new ExtentSparkReporter("spark.html");
		extReport.attachReporter(reporter);
		loadPropertyFile();
	}
	
	@BeforeTest
	public void openingBrowser() {
		
		String url = properties.getProperty("url");
		String browser = properties.getProperty("browser");
		
		if(browser.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			log.info("Opening Chrome browser");
		}
		else if (browser.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			log.info("Opening Edge browser");
		}
		
		log.info("browser opened");
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}
	
	@AfterTest
	public void closeBrowser() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
	}
	@AfterSuite
	public void teardown() {
		//driver.close();
		//log.info("Browser closed");
		extReport.flush();
	}
}
