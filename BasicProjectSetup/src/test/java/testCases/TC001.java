package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC001 {
	
	WebDriver driver;

	@Test(groups = "UAT")
	@Parameters("Name")
	public void dummy(String name) {
		System.out.println("Test case for TC001 "+name);
		WebDriverManager.chromedriver().setup();
		driver= new ChromeDriver();
	}
}
