package testCases;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import junit.framework.Assert;

public class SeleniumBasicMethods {

	WebDriver driver;
	@BeforeTest
	public void openBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@Test
	public void links() throws InterruptedException {

		driver.get("http://leafground.com/pages/Link.html");

		//Click Link
		driver.findElement(By.linkText("Go to Home Page")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("HyperLink")).click();
		Thread.sleep(2000);

		//Click link and verify
		driver.findElement(By.partialLinkText("Find where am supposed")).click();
		Thread.sleep(2000);
		Assert.assertEquals("TestLeaf - Interact with Buttons",driver.getTitle());
		Thread.sleep(2000);
		driver.get("http://leafground.com/pages/Link.html");
		Thread.sleep(2000);

		//Verify broken link
		String URL = driver.findElement(By.linkText("Verify am I broken?")).getAttribute("href");
		System.out.println(URL);	
		Response response = RestAssured.given().get(URL);
		if(response.getStatusCode()>=400) {
			System.out.println("Link is broken");
		}else {
			System.out.println("Link is valid");
		}

		driver.get("http://leafground.com/pages/Link.html");

		//get all links in page.
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println(links);
		int count = 0;
		for(WebElement link:links) {
			System.out.println(link.getText()+"-"+link.getAttribute("href"));
			count++;
		}
		System.out.println("Total links are "+count);
	}


	@Test
	public void textBox() {

		driver.get("http://leafground.com/pages/Edit.html");

		//Enter text
		driver.findElement(By.id("email")).sendKeys("123@gmail.com");

		//Append Text
		driver.findElement(By.xpath("//input[@value=\"Append \"]")).sendKeys("Appending name");

		//Get value from textbox
		String value = driver.findElement(By.xpath("//*[contains(text(),'Get default text entered')]/following::input")).getAttribute("value");
		System.out.println(value);

		//Clear text
		driver.findElement(By.xpath("//*[contains(text(),'Clear the text')]/following::input")).clear();

		//Verify if the textbox is editable
		boolean disaabled = driver.findElement(By.xpath("//*[contains(text(),'disabled')]/following::input")).isEnabled();
		Assert.assertEquals(false, disaabled);
	}


	@Test
	public void buttons() throws InterruptedException {

		driver.get("http://leafground.com/pages/Button.html");

		//click Button with verification
		driver.findElement(By.xpath("//button[contains(text(),'Go to Home Page')]")).click();
		Assert.assertEquals("TestLeaf - Selenium Playground", driver.getTitle());
		Thread.sleep(3000);

		driver.get("http://leafground.com/pages/Button.html");

		//Get position of button
		WebElement positionButton = driver.findElement(By.id("position"));
		Point point = positionButton.getLocation();
		System.out.println("X position is "+point.getX());
		System.out.println("Y position is "+point.getY());

		//Get color of the button
		String color = driver.findElement(By.id("color")).getCssValue("background-color");
		System.out.println("Color of the button is "+color);

		//Get size of the button
		Dimension size =  driver.findElement(By.id("size")).getSize();
		System.out.println("Height of the button is "+size.getHeight());
		System.out.println("Width of the button is "+size.getWidth());
	}


	@Test
	public void dropDowns() {

		driver.get("http://leafground.com/pages/Dropdown.html");

		//Select by Index
		new Select(driver.findElement(By.id("dropdown1"))).selectByIndex(1);

		//Select by Text
		new Select(driver.findElement(By.name("dropdown2"))).selectByVisibleText("Selenium");

		//Select by Value
		new Select(driver.findElement(By.id("dropdown3"))).selectByValue("2");

		//get number of dropdown options
		List<WebElement> dropdownValue =  new Select(driver.findElement(By.xpath("//*[@class=\"dropdown\"]"))).getOptions();
		System.out.println(dropdownValue.size());

		//SendKeys to select
		driver.findElement(By.xpath("//div[@class=\"example\"][5]/child::select")).sendKeys("LoadRunner");

		//multiselect
		Select multiselect = new Select(driver.findElement(By.xpath("//*[@class=\"example\"][6]/child::select")));
		multiselect.selectByIndex(3);
		multiselect.selectByIndex(1);
	}

	@Test
	public void alerts() throws InterruptedException {

		driver.get("http://leafground.com/pages/Alert.html");

		//Dismiss alert
		driver.findElement(By.xpath("//button[@onclick=\"normalAlert()\"]")).click();
		driver.switchTo().alert().dismiss();

		//Confirmation Alert
		driver.findElement(By.xpath("//button[@onclick=\"confirmAlert()\"]")).click();
		driver.switchTo().alert().accept();
		Assert.assertEquals("You pressed OK!", driver.findElement(By.id("result")).getText());

		Thread.sleep(3000);

		//Prompt alerts
		driver.findElement(By.xpath("//*[@onclick=\"confirmPrompt()\"]")).click();
		driver.switchTo().alert().sendKeys("Testing");
		driver.switchTo().alert().accept();
		Assert.assertEquals("You should not have enjoyed learning at Testing as compared to TestLeaf! Right?", driver.findElement(By.id("result1")).getText());

		//getalertText
		driver.findElement(By.xpath("//*[@onclick=\"lineBreaks()\"]")).click();
		System.out.println(driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();

		//sweetAlert
		driver.findElement(By.xpath("//*[@onclick=\"sweetalert()\"]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
	}

	@Test
	public void radiobuttons() throws InterruptedException {

		driver.get("http://leafground.com/pages/radio.html");

		//Clicking Radio buttons
		WebElement radio1 = driver.findElement(By.xpath("//*[@id=\"first\"]"));	
		radio1.findElement(By.id("yes")).click();
		radio1.findElement(By.id("no")).click();

		//Finding default selected radio button
		List<WebElement> MainList = driver.findElements(By.xpath("//*[contains(text(),'Find default selected radio button')]/following::label"));
		for(WebElement value:MainList) {
			if(value.findElement(By.name("news")).isSelected()) {
				System.out.println("Selected default radio button is "+value.getText());
				break;
			}
		}

		Thread.sleep(5000);
		//Select your age group (Only if choice wasn't selected)
		int userAge = 45;
		String  Value = null;
		if(userAge <= 0) {
			throw new ArithmeticException("Enter correct age");
		}
		if(userAge  > 0 && userAge <= 20) {
			Value = "0";
		}
		if(userAge  > 20 && userAge <= 40) {
			Value = "1";
		}
		if(userAge  > 40) {
			Value = "2";
		}

		List<WebElement> ageList = driver.findElements(By.xpath("//*[@name=\"age\"]"));
		for(WebElement age:ageList) {
			String getValue = age.getAttribute("value");
			if(getValue.equalsIgnoreCase(Value)) {
				if(age.isSelected()) {
					System.out.println("Already selected");
				}else {
					age.click();
					System.out.println("Selecting the new value");
				}

			}
		}

	}

	@Test
	public void checkBoxes() throws InterruptedException {

		driver.get("http://leafground.com/pages/checkbox.html");
		
		//Select the languages that you know?
		List<WebElement> languages = driver.findElements(By.xpath("//*[@class=\"example\"][1]/child::div"));
		List<String> KnownLanguages = new ArrayList<String>();
		KnownLanguages.add("Java");
		KnownLanguages.add("C++");
		KnownLanguages.add("SQL");
		
		for(WebElement lang:languages) {
			for(String knLang:KnownLanguages) {
				if(knLang.equalsIgnoreCase(lang.getText())) {
					lang.findElement(By.xpath("./input")).click();
				}
			}
		}
	
		Thread.sleep(5000);
		//Confirm Selenium is checked
		WebElement checkedBox = driver.findElement(By.xpath("//*[@class=\"example\"][2]/child::div"));
		if(checkedBox.findElement(By.xpath("./input")).isSelected()) {
			System.out.println(checkedBox.getText()+" is clicked");
		}else {
			System.out.println(checkedBox.getText()+" is NOT clicked");
		}
	
	
	//Deselect checked box
	List<WebElement> deSelect = driver.findElements(By.xpath("//*[@class=\"example\"][3]/child::div"));
	for(WebElement de:deSelect) {
		if(de.findElement(By.xpath("./input")).isSelected()) {
			de.findElement(By.xpath("./input")).click();
			System.out.println(de.getText()+" is clicked");
		}else {
			System.out.println(de.getText()+" is NOT clicked");
		}
	}
	
	//Select All
	List<WebElement> selectAll = driver.findElements(By.xpath("//*[@class=\"example\"][4]/child::div/input"));
	for(WebElement selectAl:selectAll) {
		selectAl.click();
	}
	}
}
