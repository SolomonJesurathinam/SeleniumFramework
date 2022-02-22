package testCases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

public class SeleniumMoreAdvancedconcepts {
	
	WebDriver driver;
	@BeforeTest
	public void openBrowser() {
		WebDriverManager.chromedriver().setup();
		driver= new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@Test
	public void dynamicTables() {
		driver.get("http://leafground.com/pages/table.html");
		
		WebElement table = driver.findElement(By.id("table_id"));
		
		//get number of columns and rows
		int columns = table.findElements(By.tagName("th")).size();
		int rows = table.findElements(By.tagName("tr")).size();
		System.out.println("Number of columns is "+columns);
		System.out.println("Number of rows is "+rows);
		
		//Get the progress value of 'Lern to interact with emelents'
		String progress = table.findElement(By.xpath("//td[text()='Learn to interact with Elements']//following::td[1]")).getText();
		System.out.println("Progress value of 'Learn to interact with Elements' is "+progress);
		
		//Check vital task for least completed progress
		List<WebElement>  progressElements= table.findElements(By.xpath("//td[2]"));
		List<Integer> progressValues = new LinkedList<Integer>();
		for(WebElement pE:progressElements) {
			String value = pE.getText().replace("%", "");
			progressValues.add(Integer.parseInt(value));
		}
		
		String xpathVitalTask = "//td[contains(text(),'"+Collections.min(progressValues)+"')]//following::input[1]";
		System.out.println(xpathVitalTask);
		table.findElement(By.xpath(xpathVitalTask)).click();
		//td[contains(text(),'30')]//following::input[1]
	}

	@Test
	public void sortableTables() throws InterruptedException {
		driver.get("http://leafground.com/pages/sorttable.html");
		
		WebElement table = driver.findElement(By.id("table_id"));
		
		//Sort Name column and verify the functionality
		
		List<WebElement> NameWE = table.findElements(By.xpath("//td[2]"));
		List<String> beforeSort = new ArrayList<String>();
		
		for(WebElement nWE: NameWE) {
			beforeSort.add(nWE.getText());
		}
		
		table.findElement(By.xpath("//th[2]")).click();
		Assert.assertEquals("ascending", table.findElement(By.xpath("//th[2]")).getAttribute("aria-sort"));
		
		List<WebElement> NameWE1 = table.findElements(By.xpath("//td[2]"));
		List<String> afterSort = new ArrayList<String>();
		
		for(WebElement nWE: NameWE1) {
			afterSort.add(nWE.getText());
		}
		
		//Checking if ascending sort is working
		Collections.sort(beforeSort);
		System.out.println("Sort Ascending");
		System.out.println(beforeSort + " "+afterSort);
		boolean check = beforeSort.equals(afterSort);
		Assert.assertEquals(true, check);
		
		//Checking if descending sort is working
		table.findElement(By.xpath("//th[2]")).click();
		Assert.assertEquals("descending", table.findElement(By.xpath("//th[2]")).getAttribute("aria-sort"));
		
		List<WebElement> NameWE2 = table.findElements(By.xpath("//td[2]"));
		List<String> afterSortDes = new ArrayList<String>();
		
		for(WebElement nWE: NameWE2) {
			afterSortDes.add(nWE.getText());
		}
		Collections.reverse(beforeSort);
		System.out.println("Sort Descending");
		System.out.println(beforeSort + " "+afterSortDes);
		boolean check1 = beforeSort.equals(afterSortDes);
		Assert.assertEquals(true, check);
	}
	
	@Test
	public void calenders() throws InterruptedException {
		driver.get("http://leafground.com/pages/Calendar.html");
		
		WebElement calender = driver.findElement(By.id("datepicker"));
		//Method 1 
		calender.sendKeys("02/15/2022"+Keys.ENTER);
		
		//Method2 (Not workingdue to DOM refresh)
		WebElement datePicker = driver.findElement(By.id("ui-datepicker-div"));
		String Exactdate ="04/20/2023";
		
		String date = Exactdate.substring(0, 2);
		String month = Exactdate.substring(3, 5);
		String year = Exactdate.substring(6, 10);
		
		
		HashMap<Integer, String> monthValue = new HashMap<Integer, String>();

		monthValue.put(1, "January");
		monthValue.put(2, "February");
		monthValue.put(3, "March");
		monthValue.put(4, "April");
		monthValue.put(5, "May");
		monthValue.put(6, "June");
		monthValue.put(7, "July");
		monthValue.put(8, "August");
		monthValue.put(9, "September");
		monthValue.put(10, "October");
		monthValue.put(11, "November");
		monthValue.put(12, "December");
		
		calender.click();

		String getMonth = "//*[@class=\"ui-datepicker-month\"]";
		String getYear = "//*[@class=\"ui-datepicker-year\"]";
		String nextBTN = "//*[@class=\"ui-datepicker-next ui-corner-all\"]";
		String backBTN = "//*[@class=\"ui-datepicker-prev ui-corner-all\"]";
		
		Actions actions = new Actions(driver);
		
		for(int i=0; i<=100; i++) {
			if(Integer.parseInt(datePicker.findElement(By.xpath(getYear)).getText()) < Integer.parseInt(year)) {
				//datePicker.findElement(By.xpath(nextBTN)).click();
				actions.moveToElement(datePicker.findElement(By.xpath(nextBTN))).click().build().perform();
				Thread.sleep(5000);
			}
			if(Integer.parseInt(datePicker.findElement(By.xpath(getYear)).getText()) > Integer.parseInt(year)) {
				calender.findElement(By.xpath(backBTN)).click();
			}
			if(Integer.parseInt(datePicker.findElement(By.xpath(getYear)).getText()) == Integer.parseInt(year)) {
				break;
			}
		}
	
	}
	
}
