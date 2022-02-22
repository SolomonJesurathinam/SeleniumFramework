package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC002 {

	WebDriver driver;
	@Test(groups = "UAT")
	public void dummy() {
		System.out.println("Test case for TC002");
		WebDriverManager.edgedriver().setup();
		driver= new EdgeDriver();
	}
	
}
