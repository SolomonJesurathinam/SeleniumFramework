package testCases;

import java.io.File;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumAdvancedMethods {

	WebDriver driver;
	@BeforeTest
	public void openBrowser() {
		WebDriverManager.chromedriver().setup();
		driver= new ChromeDriver();
		driver.manage().window().maximize();
	}


	@Test
	public void windowHandles() throws InterruptedException {
		driver.get("http://leafground.com/pages/Window.html");

		//	switch to next window
		String parentWindow = driver.getWindowHandle();
		driver.findElement(By.xpath("//*[contains(text(),'Open Home Page')]")).click();
		Set<String> windows = driver.getWindowHandles();

		for(String switchWindow:windows){
			driver.switchTo().window(switchWindow);
			System.out.println(driver.getTitle());
		}
		driver.findElement(By.xpath("//*[@src=\"images/button.png\"]")).click();
		driver.close();
		driver.switchTo().window(parentWindow);
		Thread.sleep(2000);

		//Find number of windows
		driver.findElement(By.xpath("//*[contains(text(),'Open Multiple Windows')]")).click();
		int count = driver.getWindowHandles().size();
		System.out.println(count);

		//Close all windows except Parent
		driver.findElement(By.id("color")).click();
		Thread.sleep(5000);
		Set<String> newWindows = driver.getWindowHandles();

		for(String newWin:newWindows) {
			if(!newWin.equals(parentWindow)) {
				driver.switchTo().window(newWin);
				driver.close();
			}
		}
	}

	@Test
	public void frames() {
		driver.get("http://leafground.com/pages/frame.html");

		//switch to frame
		driver.switchTo().frame(driver.findElement(By.xpath("//*[@src=\"default.html\"]")));
		driver.findElement(By.id("Click")).click();
		Assert.assertEquals("Hurray! You Clicked Me.", driver.findElement(By.id("Click")).getText());

		driver.switchTo().defaultContent();

		//Switch to nested frame
		driver.switchTo().frame(driver.findElement(By.xpath("//*[@src=\"page.html\"]")));
		driver.switchTo().frame("frame2");
		driver.findElement(By.id("Click1")).click();
		Assert.assertEquals("Hurray! You Clicked Me.", driver.findElement(By.id("Click1")).getText());

		driver.switchTo().defaultContent();

		//count of iframes
		List<WebElement> frames = driver.findElements(By.tagName("iframe"));
		System.out.println(frames.size()+" is the count of frames");
	}

	@Test
	public void dragDropTooltip() throws InterruptedException {
		driver.get("http://leafground.com/pages/drop.html");

		//drag and drop	
		WebElement draggable = driver.findElement(By.id("draggable"));
		WebElement droppable = driver.findElement(By.id("droppable"));

		Actions actions = new Actions(driver);

		actions.clickAndHold(draggable).moveToElement(droppable).release().perform(); //Method 1
		 Thread.sleep(5000);
		  
		  driver.get("http://leafground.com/pages/drop.html"); Thread.sleep(5000);
		  actions.dragAndDrop(draggable, droppable).build().perform(); //Method 2
		 		
		//Tool Tip
		driver.get("http://leafground.com/pages/tooltip.html");
		WebElement  toolTip = driver.findElement(By.id("age"));
		
		System.out.println(toolTip.getAttribute("title"));  //Method 1
		
		actions.moveToElement(toolTip).build().perform();
		WebElement tooltipText = driver.findElement(By.xpath("//*[@class=\"ui-tooltip-content\"]"));
		Assert.assertEquals("Enter your Name", tooltipText.getText());
	}

	@Test
	public void selectable() {
		driver.get("http://leafground.com/pages/selectable.html");
		
		List<WebElement> selectable = driver.findElements(By.xpath("//*[@id=\"selectable\"]/li"));
		System.out.println(selectable.size());
		
		Actions action = new Actions(driver);
		action.keyDown(Keys.CONTROL).click(selectable.get(0)).click(selectable.get(1)).click(selectable.get(3)).build().perform(); //Method 1
		
		//Method 2 (Doesn't work for random values)
		action.clickAndHold(selectable.get(0)).clickAndHold(selectable.get(1)).clickAndHold(selectable.get(3)).build().perform();
		
	}
	
	@Test
	public void autoComplete() throws InterruptedException {
		driver.get("http://leafground.com/pages/autoComplete.html");
		
		driver.findElement(By.id("tags")).sendKeys("a");
		Thread.sleep(2000);
		List <WebElement> values = driver.findElements(By.xpath("//*[@id=\"ui-id-1\"]/li/div"));
		for(WebElement value:values) {
			if(value.getText().equals("Protractor")) {
				value.click();
				break;
			}
		}
	}
	
	@Test
	public void downloadFiles() throws InterruptedException {
		driver.get("http://leafground.com/pages/download.html");
		
		WebElement excel = driver.findElement(By.linkText("Download Excel"));
		WebElement pdf = driver.findElement(By.linkText("Download PDF"));
		WebElement text = driver.findElement(By.linkText("Download Text"));
		
		String excelName = excel.getAttribute("href").substring(22);
		String pdfName = pdf.getAttribute("href").substring(22);
		String textName = text.getAttribute("href").substring(22);
		
		excel.click(); 
		//pdf.click(); 
		//text.click();
		
		Thread.sleep(2000);
		File filelocation = new File("C:\\Users\\Solo\\Downloads");
		File[] file = filelocation.listFiles();
		
		for(File filetxt:file) {
			System.out.println(filetxt.getName());
			if(filetxt.getName().equals(excelName)) {
				System.out.println(excelName+" File is downloaded succesfully");
				break;
			}
			/*
			 * if(file.equals(pdfName)) {
			 * System.out.println(pdfName+" File is downloaded succesfully"); }
			 */
			/*
			 * if(file.equals(textName)) {
			 * System.out.println(textName+" File is downloaded succesfully"); }
			 */
		}
		
	}
	
}
