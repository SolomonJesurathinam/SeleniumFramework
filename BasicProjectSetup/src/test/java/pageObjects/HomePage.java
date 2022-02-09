package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {
	
	@FindBy(xpath="//input[@class='gLFyf gsfi']")
	public static WebElement entersearch;
	
	@FindBy(xpath="//div[@class='FPdoLc lJ9FBc']/child::center/input[1]")
	public static WebElement searchBtn;

}
