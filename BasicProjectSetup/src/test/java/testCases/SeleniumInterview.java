package testCases;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import javax.imageio.ImageIO;

import java.awt.Dimension;
import java.awt.Rectangle;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.internal.thread.ThreadTimeoutException;


import io.github.bonigarcia.wdm.WebDriverManager;




public class SeleniumInterview {
	WebDriver driver;
	@BeforeMethod
	public void test1() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	
	/*Types of Wait - Implicit, Explicit, Fluent
	 * 1. Implicit Wait - Implicit wait will check for the element is present in DOM.. if yes it will perform the task
	 * Default time is 'Zero' seconds.. 500 ms polling rate
	 * Downside - it will not check if the element is visible/interactable.. Cant give any explicit conditions
	 * 
	 * 2. Explicit Wait - Can give conditions explicitly to wait until the conditions are met.
	 * conditions are given specifically to a webelement and not to entire code. 500 ms polling rate
	 * Best practice as per official docs - Dont mix both Implicit and Expicit (in reality we mix this)
	 * 
	 * 3.Fluent Wait - To reduce the default polling rate, we can give our own polling rate. With own conditions to wait and also we can ignore
	 * exceptions caused during the wait.
	 * */
	
	public void waits() {
		
		//Implicit Wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//Explicit wait
		WebElement test = driver.findElement(By.id(""));
		WebDriverWait wait1 = new WebDriverWait(driver,Duration.ofSeconds(20));
		WebElement Clickable = wait1.until(ExpectedConditions.elementToBeClickable(test));
		Clickable.click();
		
		//Fluent Wait
		Wait<WebDriver> fluWait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(3))
				.ignoring(NoSuchElementException.class);
		fluWait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.id(""));
			}
		});	
	}
	
	/*Quit vs Close
	 * Close - will close the current window (WebDriver focus)
	 * Quit - will close all browser windows.
	 */
	public void quitClose() {
		driver.close();
		driver.quit();
	}
	
	/*GET vs Navigate
	 * Get can only open the url, but does not store any history
	 * Navigate opens the url and also stores the history
	 * Get waits for all the elements to appear in DOM then next steps will start
	 * Navigate will not wait until all elements starts to appear. 
	 */
	public void getNavigate() {
		driver.get("url");
		driver.navigate().to("url");
		driver.navigate().forward();
		driver.navigate().back();
		driver.navigate().refresh();
	}
	
	/*Ways to Take Screenshots
	 * Selenium inbuilt  - TakesScreenshot
	 * This will not work on taking screenshots in Alert - unhandled alert exception
	 * Takes Screenshots will not take full screenshots. Robot class can do it
	 */
	public void screenshots() throws IOException, AWTException {
		
		TakesScreenshot source = (TakesScreenshot) driver;
		File sourceFile = source.getScreenshotAs(OutputType.FILE);
		File destFile = new File("path.png");
		FileHandler.copy(sourceFile, destFile); //inbuilt selenium function
		FileUtils.copyFile(sourceFile, destFile); //apache function
		
		//Take screenshot in Alerts
		Robot robot = new Robot();
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle rectangle = new Rectangle(size);
		BufferedImage source1 =  robot.createScreenCapture(rectangle);
		File destFile1 = new File("dest.png");
		ImageIO.write(source1, "png", destFile1);
	}
	
	/*Different Ways to Refresh a browser
	 *1. Navigate and refresh
	 *2. get using current url
	 *3. Javascript Executor - location.reload() or history.go(0)
	 *4. Robot class
	 */
	
	public void refreshBrowsers() throws AWTException {
		driver.navigate().refresh(); //#1
		driver.get(driver.getCurrentUrl()); //#2
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("location.reload()"); //history.go(0)  //#3
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_F5);
		robot.keyRelease(KeyEvent.VK_F5);   //#4
	}
	
	/*Different ways to maximize browser
	 * 1. manage maximize function
	 * 2. Dimensions and set size -- Used for resize also
	 * 3. Chrome options
	 */
	
	public void maximizeBrowser(){
		driver.manage().window().maximize(); //#1
		
		org.openqa.selenium.Dimension dimension = new org.openqa.selenium.Dimension(1920, 1080);
		driver.manage().window().setSize(dimension);  //#2
		
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--start-maximized");    //#3
		driver=new ChromeDriver(chromeOptions);	
	}
	
	/*Thread.Sleep vs SetSpeed
	 * Thread.sleep will wait only for the given instance
	 * SetSpeed will wait for all lines or operations -- this is deprecated now used only in IDE and RC
	 */
	
	/*Launch Browser without System.setProperty
	 * 1. Using Environment variables -- Set driver path in environment variables
	 * 2. Using WebDriverManager WebDriverManager.chromeDriver().setup();
	 */
	
	/*Authentication popup handling
	 * 1. Using third party toold like Sikuli or AutoIt
	 * 2. passing username and password in url -- https://username:passoword@google.com	
	 */
	
	/*Print Google suggestions
	 *1. Get the unordered list using xpath and get the list of suggestions in a list
	 *2. use loop and get the text from the elements and print
	 *3. If needed we can use a if condition and click matching the condition
	 */
	
	/*Print all the google suggestions links
	 * 1. Write xpath to get the entire page layout, in that get the heading value using following 
	 * and get the 'cite' tag which gives only the search links
	 * 2. Store them in a list and print using the "href" attribute or getText based on the element
	 */
	
	/*Ways to press enter key to perform Google search
	 * 1. Send Keys plus Keys.Enter
	 * 2. element.submit() if the form allows it
	 * 3. Robot class
	 * 4. In send keys provide \n "Solomon \n"
	 */
	
	/*
	 * Switch to Active Element
	 * 1. Without using the FindElement we need to perform action on active Element
	 */
	public void ativeElement() {
		driver.switchTo().activeElement().sendKeys("Testing \n");
	}
	
	/*How to run failed testcases
	 * Method 1: Run manually from testOutput/testng-failed.xml (which will be created when the tests are failed, contains only failed tc's in the xml)
	 * Method 2: Using IRetryAnalyzer interface.. Downside we need to specify the parameters inside the test annotations
	 * Method 3: IAnnotationTransformer interface.. we can use transform method and call the iretry method and add the listener in XML.
	 */
	@Test   //retryAnalyzer = RetryListener.class Method2
	public void failedretry1() {
		Assert.assertEquals(false, true);
	}
	@Test
	public void failedretry2() {
		Assert.assertEquals(false, true);
	}
	
	/*Scrolling
	 * 1. Using JavascripExecutor
	 * 2. Robot class
	 */
	@Test
	public void scrolling() throws InterruptedException, AWTException {
		driver.get("https:google.com");
		driver.switchTo().activeElement().sendKeys("test \n");
		
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		//scroll down and up
		jse.executeScript("window.scroll(0,500)", "");
		Thread.sleep(3000);
		jse.executeScript("window.scrollBy(0,-500)", ""); //scroll up (scrollTo, scrollBy, scroll)
		Thread.sleep(3000);
		
		//scroll to bottom of page
		jse.executeScript("window.scrollTo(0,document.body.scrollHeight)", "");
		Thread.sleep(3000);
		
		//scroll to top
		jse.executeScript("window.scroll(0,0)", "");
		Thread.sleep(3000);
		
		//scroll to Element
		WebElement related = driver.findElement(By.xpath("//*[text()='See results about']"));
		jse.executeScript("arguments[0].scrollIntoView(true);", related);
		Thread.sleep(2000);
		
		//Using Robot class
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_PAGE_DOWN);
		robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
		Thread.sleep(3000);
		robot.keyPress(KeyEvent.VK_PAGE_UP);
		robot.keyRelease(KeyEvent.VK_PAGE_UP);
	}
	
	/*Text is underlined or Not (text-decoration css property)
	 */
	@Test
	public void underlined() {
		driver.get("https:google.com");
		WebElement ele = driver.findElement(By.xpath("//a[text()=\"தமிழ்\"]"));
		Actions action = new Actions(driver);
		
		String beforeHovering = ele.getCssValue("text-decoration");
		action.moveToElement(ele).build().perform();
		String afterHovering = ele.getCssValue("text-decoration");
		
		System.out.println(beforeHovering);
		System.out.println(afterHovering);
		if(afterHovering.contains("underline")) {
			System.out.println(ele.getText() +" is underlined");
		}
	}
	
	/*Assert vs Verify
	 * Assert conditions doesn't matches the test execution fails (Hard Assertion)
	 * Verify if the condition/verification fail the test will not stop (Soft Assertion)
	 */
	public void assertVerify(){
		//Hard Assertion
		Assert.assertEquals(false, true);
		//Soft Assert
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(false, true);
	}
	
	/*Send keys without using 'send keys method'
	 * 1. Javascript Executor -- "document.getElementsByName('q')[0].value='Solomon' //  arguments[0].value='' Webelement
	 * 2. Robot class.. switch to active element and type keys
	 */
	@Test
	public void sendkeys() throws InterruptedException, AWTException {
		driver.get("https:google.com");
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("document.getElementsByName('q')[0].value='Solomon'", ""); //Method 1
		Thread.sleep(3000);
		WebElement search = driver.findElement(By.name("q"));
		jse.executeScript("arguments[0].value='Solo'", search); //Method2
		Thread.sleep(2000);
		
		Robot robot = new Robot();
		driver.switchTo().activeElement();
		robot.keyPress(KeyEvent.VK_S);
		robot.keyRelease(KeyEvent.VK_S);  //Method 3
	}
	
	/*Click all check boxes in a page
	 * Identify elements with input type 'Checkbox'.. then add all in a list and loop and click them
	 */
	
	/*Run a Test multiple times
	 * Invocation count attribute passed as parameter in @Test
	 */
	
	/*TimeoutExample
	 * pass timeOut as an attribute in @Test method.. this will wait for that time, if the test method takes long time the test case will fail.
	 * Timeout Exception should come.
	 */
	
	/*Exception Handling in TestNG
	 * expectedExceptions attribute in @Test
	 * This will not execute the next steps in test case// so if needed we need to use Try Catch
	 */
	@Test(timeOut=1000, expectedExceptions = ThreadTimeoutException.class)
	public void exception() throws InterruptedException {
		Thread.sleep(2000);
		System.out.println("I skipped exception");
	}
	
	/*How to skip exception using try catch 
	 */
	@Test
	public void skipException() {
		try {
			Assert.fail();
		}catch(AssertionError e){
			//throw e
			System.out.println(e);
		}
		System.out.println("I skipped exception");
	}
	
	/*Always run
	 * pass always run attribute.. This will run even if the depends on method for the test is failed	
	 */
	
	/*How to pass url without Get and Navigate
	 * Using JAvascript Executor
	 */
	@Test
	public void url() {
		JavascriptExecutor jse = (JavascriptExecutor)driver;  
		jse.executeScript("window.location='https:google.com'", "");
	}
	
	/*Handling Pagination
	 */
	@Test
	public void pagination() {
		driver.get("https://mdbootstrap.com/docs/b4/jquery/tables/pagination/");
		
		List<String> finalNamesList = new ArrayList<String>();
		List<WebElement> NameslistPages;
		WebElement next;
		int paginationSize = driver.findElements(By.xpath("//div[@id='dtBasicExample_paginate']//li")).size();
		
		
		for(int i=0;i<paginationSize; i++) {
			NameslistPages =  driver.findElements(By.xpath("//td[@class='sorting_1']"));
			for(WebElement namesWeb:NameslistPages) {
				finalNamesList.add(namesWeb.getText());
			}
			next=driver.findElement(By.id("dtBasicExample_next"));
			String verify = next.getAttribute("class");
			if(!verify.contains("disabled")) {
				next.click();
			}else {
				break;
			}
		}
		
		System.out.println(finalNamesList.toString());
		for(String fi:finalNamesList) {
			System.out.println(fi);
		}
	}
}

